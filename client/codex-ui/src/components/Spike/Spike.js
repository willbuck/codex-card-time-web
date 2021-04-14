import axios from 'axios';

export default function Spike() {

    const spike = () => {
        console.log('connected')
        axios.get('/api/user').then((response)=>{
            console.log(response)
        }).catch((error)=>{
            console.log(error)
        })
    }

    return(
        <div>
            <h2>Button To Test:</h2>
            <button onClick={()=>spike()}>Send HTTP</button>
        </div>
    )
}
