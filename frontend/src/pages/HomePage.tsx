import Heading from "../components/Heading";
import SearchBar from "../components/SearchBar";
import FavButton from "../components/FavButton";
import {useParams} from "react-router-dom";

export default function HomePage(){

    const {username} = useParams()

    return(
        <div className={'homePage'}>
            <Heading/>
            <SearchBar user={username!}/>
            <FavButton own={false}/>
            <FavButton own={true}/>
        </div>
    )
}