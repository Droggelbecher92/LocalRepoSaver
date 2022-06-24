import React from "react";

interface FavButtonProps{
    own : boolean
}

export default function FavButton({own}:FavButtonProps){
    return(
        own ?
                <div className={'favButton'}>
                    <button>Eigene Repos</button>
                </div>
                :
                <div className={'favButton'}>
                    <button>Gespeicherte Repos</button>
                </div>
    )
}