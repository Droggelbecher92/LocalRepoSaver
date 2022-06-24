import React from "react";
import {useNavigate, useParams} from "react-router-dom";

interface FavButtonProps{
    own : boolean
}

export default function FavButton({own}:FavButtonProps){

    const nav = useNavigate()
    const {username} = useParams()

    return(
        own ?
                <div className={'favButton'}>
                    <button onClick={() => nav(`/repos/${username}/${username}`)}>Eigene Repos</button>
                </div>
                :
                <div className={'favButton'}>
                    <button onClick={() => nav(`/fav/${username}`)}>Gespeicherte Repos</button>
                </div>
    )
}