import Heading from "../components/Heading";
import SearchBar from "../components/SearchBar";
import FavButton from "../components/FavButton";

export default function HomePage(){
    return(
        <div className={'homePage'}>
            <Heading/>
            <SearchBar/>
            <FavButton own={false}/>
            <FavButton own={true}/>
        </div>
    )
}