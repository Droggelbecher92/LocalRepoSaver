import {FormEvent, useState} from "react";
import {getUser, postNewUser} from "../service/apiService";
import {useNavigate} from "react-router-dom";

export default function LoginPage(){

    const [userInput, setUserInput] = useState('')
    const [login, setLogin] = useState(true)
    const [error, setError] = useState('')

    const nav = useNavigate()

    const handleSubmit = (ev : FormEvent) => {
        ev.preventDefault()
        setError('')
        login ?
            getUser(userInput)
                .then(()=>nav(`/home/${userInput}`))
                .catch(err => {
                    setError("Nutzer nicht gefunden")
                    setUserInput('')
                })
            :
            postNewUser(userInput)
                .then(() => setLogin(!login))
    }

    return(
        <div className={'loginPage'}>
            <form onSubmit={handleSubmit}>
                <input type="text" placeholder={'Nutzername'} value={userInput} onChange={ev => setUserInput(ev.target.value)}/>
                <button type={'submit'}>Go!</button>
            </form>
            {error && <h2>{error}</h2>}
            <button onClick={()=> setLogin(!login)}>{login?"Anmelden":"Registrieren"}</button>
        </div>
    )
}