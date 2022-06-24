import axios, {AxiosResponse} from "axios";
import {GithubRepo, GithubUser} from "./models";


export const getGithubUser = (username:string)=>{
    return axios.get(`https://api.github.com/users/${username}`)
        .then((response : AxiosResponse<GithubUser>) => response.data)
}

export const getGithubUserRepos = (username : string) => {
    return axios.get(`https://api.github.com/users/${username}/repos?per_page=100`)
        .then((response : AxiosResponse<Array<GithubRepo>>) => response.data)
}
