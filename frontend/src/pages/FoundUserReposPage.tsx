import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {getUserRepos} from "../service/githubService";
import {GithubRepo} from "../service/models";
import GithubRepoCard from "../components/GithubRepoCard";

export default function FoundUserReposPage(){

    const [repos ,setRepos] = useState<Array<GithubRepo>>()
    const [err, setErr] = useState('')

    const {username} = useParams();

    useEffect(()=>{
        username &&
        getUserRepos(username)
            .then(data => setRepos(data))
            .catch(() => setErr("User nicht gefunden...."))
    },[username])

    return(
        <div className={'foundUserReposPage'}>
            {err && <h2>{err}</h2>}
            {
                repos ?
                    <div>
                        {repos.map(repo =>{
                            return <GithubRepoCard infos={repo}/>
                        })}
                    </div>
                    :
                    <h3>Loading....</h3>
            }
        </div>
    )
}