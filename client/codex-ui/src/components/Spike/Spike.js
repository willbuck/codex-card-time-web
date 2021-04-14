export default function Spike() {

    const spike = () => {
        console.log('connected')
    }
    
    return(
        <div>
            <h2>Button To Test:</h2>
            <button onClick={()=>spike()}>Send HTTP</button>
        </div>
    )
}
