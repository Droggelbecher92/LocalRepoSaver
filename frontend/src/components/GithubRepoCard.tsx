import {GithubRepo} from "../service/models";
import {putNewRepoToList} from "../service/apiService";

interface GithubRepoCardProps{
    infos : GithubRepo,
    currentUser : string,
    own : boolean,
    fav ?: boolean,
    reload : () => void,
}

export default function GithubRepoCard({infos,currentUser,own,fav,reload} : GithubRepoCardProps){

    const addToFav = () => {
        putNewRepoToList(currentUser,infos)
            .then(()=> reload)
    }

    const removeFav = () => {
        console.log("WIP....")
    }


    return (
        own ?
            <div className={'githubRepoCard'}>
                <p>{infos.name}</p>
            </div>
            :
            <div className={'githubRepoCard'}>
                <p>{infos.name}</p>
                {
                    fav ?
                        <button onClick={removeFav}>Remove Fav</button>
                        :
                        <button onClick={addToFav}>Add to Fav</button>
                }

            </div>
    )
}