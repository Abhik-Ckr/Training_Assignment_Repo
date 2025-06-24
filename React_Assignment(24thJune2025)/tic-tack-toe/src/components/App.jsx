import './App.css'
import { useSelector, useDispatch } from 'react-redux';
import { makeMove, resetGame } from '../reducers/gameSlice';

function App() {
  const { board, currentPlayer, winner, isDraw } = useSelector(state => state.game);
  const dispatch = useDispatch();

  const handleClick = (idx) => {
    dispatch(makeMove(idx));
  };

  const renderCell = (idx) => (
    <button
      className="cell"
      onClick={() => handleClick(idx)}
      disabled={!!board[idx] || winner}
      key={idx}
    >
      {board[idx]}
    </button>
  );

  return (
    <div className="app-container">
      <h1>Tic-Tac-Toe</h1>
      <div className="board">
        {board.map((_, idx) => renderCell(idx))}
      </div>
      <div className="info">
        {winner && <h2>Winner: {winner}</h2>}
        {!winner && isDraw && <h2>It's a Draw!</h2>}
        {!winner && !isDraw && <h2>Current Player: {currentPlayer}</h2>}
      </div>
      <button className="reset-btn" onClick={() => dispatch(resetGame())}>Reset Game</button>
    </div>
  );
}

export default App
