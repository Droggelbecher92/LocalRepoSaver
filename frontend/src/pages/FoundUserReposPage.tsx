import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {getGithubUserRepos} from "../service/githubService";
import {GithubRepo} from "../service/models";
import GithubRepoCard from "../components/GithubRepoCard";
import {getUser} from "../service/apiService";

export default function FoundUserReposPage(){

    const [repos ,setRepos] = useState<Array<GithubRepo>>()
    const [favRepos, setFavRepos] = useState<Array<GithubRepo>>()
    const [err, setErr] = useState('')

    const {username, owner} = useParams();

    const getFavs = () => {
        username &&
        getUser(username)
            .then(data => setFavRepos(data.savedRepos))
    }

    const checkFav = (name : string) =>{
        for (const favRepo of favRepos!) {
            if (favRepo.name===name){
                return true
            }
        }
        return false
    }

    useEffect(()=>{
        owner &&
        getGithubUserRepos(owner)
            .then(data => setRepos(data))
            .catch(() => setErr("User nicht gefunden...."))
        username &&
        getUser(username)
            .then(data => setFavRepos(data.savedRepos))
    },[username, owner])

    return(
        <div className={'foundUserReposPage'}>
            {err && <h2>{err}</h2>}
            {
                repos && favRepos ?
                    <div>
                        {username &&
                            username===owner ?
                            repos.map((repo:GithubRepo )=>{
                            return <GithubRepoCard infos={repo} currentUser={username} own={true} reload={getFavs}/>
                        })
                            :
                            repos.map(repo =>{
                                if (checkFav(repo.name)){
                                    return <GithubRepoCard infos={repo} currentUser={username!} own={false} fav={true} reload={getFavs}/>
                                }
                                return <GithubRepoCard infos={repo} currentUser={username!} own={false} fav={false} reload={getFavs}/>
                            })
                        }
                    </div>
                    :
                    <h3>Loading....</h3>
            }
        </div>
    )
}