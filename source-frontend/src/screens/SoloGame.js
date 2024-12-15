import Game from "../components/Game";
import Header from "../components/Header";


const SoloGame = () => {
    
    return(
        <>
            <Header/>
            <h1 style={{marginTop: '75px'}}>Одиночная игра</h1>
            <Game isBotEnabled/>
        </>
    )
    
};

export default SoloGame;