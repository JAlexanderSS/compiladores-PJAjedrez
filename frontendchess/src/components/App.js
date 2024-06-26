// App.js

import '../styles/App.css';
import { useState, useEffect } from 'react';
import { Chessboard } from 'react-chessboard';
import { Chess } from 'chess.js';
import axios from 'axios';

function App() {
  const [game, setGame] = useState(new Chess());
  const [winner, setWinner] = useState(null);
  const [gameOver, setGameOver] = useState(false);
  const [file, setFile] = useState(null);
  const [message, setMessage] = useState('');
  const [data, setData] = useState([]);

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
    console.log('File:', e.target.files[0]);
  };

  const handleFileUpload = async () => {
    if (!file) {
      setMessage('Please select a file first');
      return;
    }

    const formData = new FormData();
    console.log('formDataFILE:', file);
    formData.append('file', file);
    console.log('formData:', formData.append('file', file));

    try {
      const response = await axios.post('http://localhost:8080/upload', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
      console.log('Responses:', response.data);
      setData(response.data);
      if (Array.isArray(data)) {
        for (let i = 0; i < data.length; i++) {
          const [from, to] = data[i].split(' ');
          console.log('Move:', from, to);
          await new Promise(resolve => setTimeout(() => {
            safeGameMutate((game) => {
              game.move({ from, to });
            });
            resolve();
          }, 1000)); // 1000ms delay between movess
        }
      } else {
        console.error("Expected an array of moves, but received:", data);
      }
      setMessage('File uploaded successfully: ');
    } catch (error) {
      setMessage('Error uploading file: ' + error.message);
    }
  };


  // Let's perform a function on the game state
  function safeGameMutate(modify) {
    setGame((g) => {
      const update = { ...g };
      modify(update);
      return update;
    });
  }

  // Movement of computer
  function makeRandomMove() {
    const possibleMove = game.moves();
    console.log('Possible moves:', possibleMove);

    // exit if the game is over
    if (game.game_over() || game.in_draw() || possibleMove.length === 0) {
      setGameOver(true);
      const winner = game.turn() === 'w' ? 'Black' : 'White';
      setWinner(winner);
      return;
    }

    // select random move
    const randomIndex = Math.floor(Math.random() * possibleMove.length);
    console.log('Random index:', randomIndex);
    // play random move
    safeGameMutate((game) => {
      console.log('Computer move:', possibleMove[randomIndex]);
      game.move(possibleMove[randomIndex]);
    });
  }

  // Perform an action when a piece is dropped by a user
  function onDrop(source, target) {
    console.log('onDrop', source, target);
    if (gameOver) return false;

    let move = null;
    safeGameMutate((game) => {
      move = game.move({
        from: source,
        to: target,
        promotion: 'q',
      });
    });
    // illegal move
    if (move === null) return false;
    // valid move
    // setTimeout(makeRandomMove, 200);
    return true;
  }

  // Fetch game data from API and load moves
  useEffect(() => {
    async function fetchGameData() {
      try {
        const response = await fetch('https://mocki.io/v1/ecea211d-5f51-41b2-8b38-f252a71b7c4d');
        const datas = await response.json();
        const datass = [
          "e2 e4",
          "b8 c6"
        ];
        console.log('Fetched data:', data);

        if (Array.isArray(data)) {
          for (let i = 0; i < data.length; i++) {
            const [from, to] = data[i].split(' ');
            console.log('Move:', from, to);
            await new Promise(resolve => setTimeout(() => {
              safeGameMutate((game) => {
                game.move({ from, to });
              });
              resolve();
            }, 1000)); // 1000ms delay between moves
          }
        } else {
          console.error("Expected an array of moves, but received:", data);
        }
      } catch (error) {
        console.error("Error fetching game data:", error);
      }
    }

    fetchGameData();
  }, []);

  /*useEffect(() => {
    const shepherdsMateMoves = [
      "e4 e5",
      "Ac4 Cc6",
      "Dh5 Ac5",
      "Dxf7++"
    ];
  
    async function playMovesWithDelay(moves, delay) {
      for (let i = 0; i < moves.length; i++) {
        const [from, to] = moves[i].split(' ');
        await new Promise(resolve => setTimeout(() => {
          safeGameMutate((game) => {
            game.move({ from, to });
          });
          resolve();
        }, delay));
      }
    }
  
    playMovesWithDelay(shepherdsMateMoves, 1000); // 1000ms delay between moves
  }, []);*/


  // Reset the game
  function restartGame() {
    setGame(new Chess());
    setGameOver(false);
    setWinner(null);
  }

  // Listen for Enter key press to restart the game
  useEffect(() => {
    function handleKeyPress(event) {
      if (event.key === 'Enter') {
        restartGame();
      }
    }
    window.addEventListener('keydown', handleKeyPress);
    return () => {
      window.removeEventListener('keydown', handleKeyPress);
    };
  }, []);

  return (
    <div className="app">
      botton to call the api
      <button onClick={makeRandomMove}>Random moves</button>
      <div className="header">
        <div className="game-info">
          <h1>Chess Game</h1>
          <input type="file" onChange={handleFileChange} />
            <button onClick={handleFileUpload}>Upload</button>
            <p>{message}</p>
        </div>
      </div>
      <div className="chessboard-container">
        <Chessboard position={game.fen()} onPieceDrop={onDrop} />
        {gameOver && (
          <div className="game-over">
            <p>Game Over</p>
            <p>Winner: {winner}</p>
            <p>Press Enter to restart</p>
          </div>
        )}
      </div>
    </div>
  );
}

export default App;
