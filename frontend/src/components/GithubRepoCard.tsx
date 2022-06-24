import {GithubRepo} from "../service/models";

interface GithubRepoCardProps{
    infos : GithubRepo
}

export default function GithubRepoCard({infos} : GithubRepoCardProps){
    return (
        <div className={'githubRepoCard'}>
            <p>{infos.name}</p>
        </div>
    )
}