import axios from 'axios';
import {useState} from 'react'


export default function Spike() {

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [responseData, setResponseData] = useState('')

    const spike = (event) => {
        event.preventDefault()
        console.log('connected')
        const credential = {
            username: username,
            password: password,
        }
        console.log(credential)
        axios.post('http://localhost:8080/login', credential).then((response)=>{
            console.log(response)
            setResponseData(response.data.access_token)
        }).catch((error)=>{
            console.log(error)
        })
    }

    return(
        <div>
            <h2>Login:</h2>
            <form>
                <input
                type="text"
                value={username}
                placeholder="username"
                onChange={(event)=>setUsername(event.target.value)}>
                </input>
                <input
                type="text"
                value={password}
                placeholder="password"
                onChange={(event)=>setPassword(event.target.value)}>
                </input>
            </form>
            <button type="submit" onClick={(event)=>spike(event)}>Send HTTP</button>
            <div>{responseData}</div>
        </div>
    )
}
