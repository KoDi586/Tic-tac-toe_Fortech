import { Square } from "../components/Square";
import { useEffect, useState } from "react";
import './styles.css'
import { Button, Container } from "react-bootstrap";
import { calculateWinner } from "../helper/calculateWInner";

const Game = ({isBotEnabled}) => {
    const [history, setHistory] = useState([{
      squares: Array(9).fill(null),
      lastMoveIndex: null,
    }]);

    const [stepNumber, setStepNumber] = useState(0); 
    const [xIsNext, setXIsNext] = useState(true);
    const [currentSymbol, setCurrentSymbol] = useState('X')
    const [botSymbol, setBotSymbol] = useState('O');;

    const current = history[stepNumber];
    const winner = calculateWinner(current.squares);
    
    const handleClick = (i) => {
      if (winner || current.squares[i]) {
        return;
      }
      
      const newSquares = [...current.squares];
        newSquares[i] = xIsNext ? currentSymbol : (currentSymbol === 'X' ? 'O' : 'X');
  
      const newHistory =  history.concat([{
        squares: newSquares,
        lastMoveIndex: i,
      }]);
  
      setHistory(newHistory);
      setStepNumber(history.length);
      setXIsNext(!xIsNext);
    };

    const botPlay = () => {
        if (xIsNext || winner || !isBotEnabled) return;

        const availableMoves = current.squares
            .map((val, index) => val === null ? index : null)
            .filter(val => val !== null);

        const indexToPlay = availableMoves[Math.floor(Math.random() * availableMoves.length)];

        if (indexToPlay !== undefined) {
            const newSquares = [...current.squares];
            newSquares[indexToPlay] = botSymbol;

            const newHistory = history.concat([{
                squares: newSquares,
                lastMoveIndex: indexToPlay,
            }]);

            setHistory(newHistory);
            setStepNumber(history.length);
            setXIsNext(true);
        }
    };

    useEffect(() => {
        if (!xIsNext) {
            const timer = setTimeout(() => {
                botPlay();
            }, 500);

            return () => clearTimeout(timer);
        }
    }, [xIsNext, current, winner, isBotEnabled]);
  
    const restartGame = () => {
      setHistory([{
        squares: Array(9).fill(null),
        lastMoveIndex: null,
      }]);
      setStepNumber(0);
      setXIsNext(true);
    };
  
    const undoLastMove = () => {
        if (stepNumber != 0) {
            if (stepNumber > 0) {
                setStepNumber(stepNumber - 1);
                setXIsNext((stepNumber - 1) % 2 !== 0);
            }
        
            const newHistory = history.slice(0, stepNumber);
            setHistory(newHistory);
            setStepNumber(stepNumber - 1);
            setXIsNext(!xIsNext);
        }
    };
    
    const toggleSymbol = () => {
        if (stepNumber === 0) {
            setCurrentSymbol(currentSymbol === 'X' ? 'O' : 'X');
            setBotSymbol(botSymbol === 'X' ? 'O' : 'X')
        }
      };

    const jumpTo = (step) => {
        setStepNumber(step);
        setXIsNext((step % 2) === 0);
    };
    

    const renderInitialBoard = () => {
      return (
        <Container>
            <div className="status" style={{marginLeft: '75px'}} onClick={toggleSymbol}>{status}</div>
            <div className="status" style={{marginLeft: '100px'}} onClick={toggleSymbol}>Смена знака</div>
            <div className="board-row initial-board">
                <Square value={current.squares[0]} onSquareClick={(i) => handleClick(0)} />
                <Square value={current.squares[1]} onSquareClick={(i) => handleClick(1)} />
                <Square value={current.squares[2]} onSquareClick={(i) => handleClick(2)} />
            </div>
            <div className="board-row initial-board">
                <Square value={current.squares[3]} onSquareClick={(i) => handleClick(3)} />
                <Square value={current.squares[4]} onSquareClick={(i) => handleClick(4)} />
                <Square value={current.squares[5]} onSquareClick={(i) => handleClick(5)} />
            </div>
            <div className="board-row initial-board">
                <Square value={current.squares[6]} onSquareClick={(i) => handleClick(6)} />
                <Square value={current.squares[7]} onSquareClick={(i) => handleClick(7)} />
                <Square value={current.squares[8]} onSquareClick={(i) => handleClick(8)} />
            </div>
            <Button style={{width: '100px', marginTop:'25px'}} onClick={restartGame}>Рестарт</Button>
            <Button style={{width: '100px', marginTop:'25px', marginLeft:'100px'}} onClick={undoLastMove}>Возврат</Button>
        </Container>
        
      );
    };
  
    const renderPastBoards = () => {
      return history.slice(0, stepNumber).map((_, index) => {
        const state = history[index];
        return (
            <Container style={{marginTop: '25px'}}>
                <div className="status" style={{marginLeft:'100px'}}>Ход №{index}</div>
                <div key={index} className={`nohange board-row past-board-${index}`}>
                    <Square value={state.squares[0]} />
                    <Square value={state.squares[1]} />
                    <Square value={state.squares[2]} />
                </div>
                <div key={index} className={`nohange board-row past-board-${index}`}>
                    <Square value={state.squares[3]} />
                    <Square value={state.squares[4]} />
                    <Square value={state.squares[5]} />
                </div>
                <div key={index} className={`nohange board-row past-board-${index}`}>
                    <Square value={state.squares[6]} />
                    <Square value={state.squares[7]} />
                    <Square value={state.squares[8]} />
                </div>
                
            </Container>
        
        );
      });
    };
  
    let status;
    if (winner) {
      status = `Победитель: ${winner}`;
    } else {
      status = `Следующий игрок: ${xIsNext ? currentSymbol : (currentSymbol === 'X' ? 'O' : 'X')}`;
    }
  
    return (
      <Container style={{ marginTop: '75px', display:'grid', gridTemplateColumns:'auto auto'}}>
        
        <div>  
            {renderInitialBoard()}
        </div>
        <div>
            {renderPastBoards()}
        </div>
      </Container>
    );
  };
  
  
  export default Game;