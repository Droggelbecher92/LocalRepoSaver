import axios, {AxiosResponse} from "axios";
import {GithubRepo, GithubUser} from "./models";

export const getUser = (username:string)=>{
    return axios.get(`https://api.github.com/users/${username}`)
        .then((response : AxiosResponse<GithubUser>) => response.data)
}

export const getUserRepos = (username : string) => {
    return axios.get(`https://api.github.com/users/${username}/repos?per_page=100`)
        .then((response : AxiosResponse<Array<GithubRepo>>) => response.data)
}