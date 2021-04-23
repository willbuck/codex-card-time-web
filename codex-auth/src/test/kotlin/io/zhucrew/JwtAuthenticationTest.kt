package io.zhucrew

import com.nimbusds.jwt.JWTParser
import com.nimbusds.jwt.SignedJWT
import io.kotest.core.spec.style.StringSpec
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.security.authentication.UsernamePasswordCredentials
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.function.Executable

@MicronautTest
class JwtAuthenticationTest(private val application: EmbeddedApplication<*>): StringSpec({

    val embeddedServer = autoClose(
        ApplicationContext.run(EmbeddedServer::class.java, mapOf())
    )

    val client = autoClose(
        application.applicationContext.createBean(RxHttpClient::class.java, embeddedServer.getURL())
    )

    "accessingASecuredUrlWithoutAuthenticatingReturnsUnauthorized" {
        val e = Executable { client.toBlocking().exchange<Any, Any>(HttpRequest.GET<Any>("/").accept(MediaType.TEXT_PLAIN)) }
        val thrown = assertThrows(HttpClientResponseException::class.java, e)
        assertEquals(thrown.status, HttpStatus.UNAUTHORIZED)
    }

    "jwt works for user" {
        val creds = UsernamePasswordCredentials("sherlock", "password")
        val request : HttpRequest<Any>  = HttpRequest.POST("/login", creds)
        val rsp : HttpResponse<BearerAccessRefreshToken> = client.toBlocking().exchange(request, BearerAccessRefreshToken::class.java)
        assertEquals(HttpStatus.OK, rsp.status)

        val bearerAccessRefreshToken : BearerAccessRefreshToken = rsp.body()!!

        assertEquals("sherlock", bearerAccessRefreshToken.username)
        assertNotNull(bearerAccessRefreshToken.accessToken)
        assertTrue(JWTParser.parse(bearerAccessRefreshToken.accessToken) is SignedJWT)

        val accessToken : String = bearerAccessRefreshToken.accessToken
        val response: HttpResponse<String> = client.toBlocking().exchange(HttpRequest.GET<Any>("/").accept(MediaType.TEXT_PLAIN).bearerAuth(accessToken), String::class.java)

        assertEquals(HttpStatus.OK, rsp.status)
        assertEquals("sherlock", response.body()!!)
    }
})
