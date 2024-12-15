import Game from "../components/Game";
import Header from "../components/Header";


const LocalGame = () => {
    return(
        <>
            <Header/>
            <h1 style={{marginTop: '75px'}}>Локальная игра</h1>
            <Game/>
        </>
    )
    
};

export default LocalGame;