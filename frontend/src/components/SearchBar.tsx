import React, {FormEvent, useState} from "react";
import {getGithubUser} from "../service/githubService";
import {useNavigate} from "react-router-dom";

export default function SearchBar(){

    const [name, setName] = useState(localStorage.getItem('searchname')??'')
    const [err, setErr] = useState('')

    const nav = useNavigate()

    const searchUser = (ev: FormEvent) => {
        ev.preventDefault()
        setErr('')
        getGithubUser(name)
            .then(data => {
                if (data.public_repos===0){
                    setErr(`${name} hat keine öffentlichen Repos`)
                } else {
                    nav(`/repos/${name}`)
                }
            })
            .catch(() => setErr(`${name} nicht gefunden`))
        localStorage.removeItem('searchname')
        setName('')
    }


    return(
        <form className={'searchBar'} onSubmit={searchUser}>
            <input
                type="text"
                placeholder={'Github Username'}
                value={name}
                onChange={ev => {
                    setName(ev.target.value)
                    localStorage.setItem('searchname',ev.target.value)
                }
                }/>
            <button type={'submit'}>suchen</button>
            {err && <h2>{err}</h2>}
        </form>
    )
}