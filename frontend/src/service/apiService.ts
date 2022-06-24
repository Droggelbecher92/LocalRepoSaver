import axios from "axios";

export const getUser = (username:string) => {
    return axios.get(`/api/saver/find/${username}`)
        .then(response => response.data)
}

export const postNewUser = (username:string) => {
    return axios.post(`api/saver`,{username})
        .then(response => response.data)
}