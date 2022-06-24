import axios, {AxiosResponse} from "axios";
import {GithubRepo, SaverUser} from "./models";

export const getUser = (username:string) => {
    return axios.get(`/api/saver/find/${username}`)
        .then((response:AxiosResponse<SaverUser>) => response.data)
}

export const postNewUser = (username:string) => {
    return axios.post(`/api/saver`,{username})
        .then((response : AxiosResponse<SaverUser>)=> response.data)
}

export const putNewRepoToList = (username : string, repo : GithubRepo) => {
    return axios.put(`/api/saver/add/${username}`,repo)
        .then(response => response.data)
}