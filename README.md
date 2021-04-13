# codex-card-time-web
A visual UI for non-rules enforced game state tracking of games of Codex: Card Time Strategy by David Sirlin (https://sirlingames.com/codex)

## To run client
- `cd client/codex-ui`
- `npm install && npm start` (or use yarn like the `client/codex-ui/README.md` says, that's cool too)


## To run the `auth` service
- `cd codex-auth`
- `./gradlew run`
- Test server, copy and paste this command into your terminal:
```
curl --location --request POST 'localhost:8080/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "sherlock",
    "password": "password"
}'\
```
- The test should return something like:
```
{"username":"sherlock","access_token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaGVybG9jayIsIm5iZiI6MTYxODI4Mzg0NSwicm9sZXMiOltdLCJpc3MiOiJjb2RleC1hdXRoIiwiZXhwIjoxNjE4Mjg3NDQ1LCJpYXQiOjE2MTgyODM4NDV9.vyp0d1sXbbItidkOob0dG4Ro7W2U3TZkLO3r9RpRNs0","token_type":"Bearer","expires_in":3600}
```
