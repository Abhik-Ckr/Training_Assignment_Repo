import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  board: Array(9).fill(null),
  currentPlayer: 'X',
  winner: null,
  isDraw: false,
};

function calculateWinner(board) {
  const lines = [
    [0, 1, 2],
    [3, 4, 5],
    [6, 7, 8],
    [0, 3, 6],
    [1, 4, 7],
    [2, 5, 8],
    [0, 4, 8],
    [2, 4, 6],
  ];
  for (let line of lines) {
    const [a, b, c] = line;
    if (board[a] && board[a] === board[b] && board[a] === board[c]) {
      return board[a];
    }
  }
  return null;
}

const gameSlice = createSlice({
  name: 'game',
  initialState,
  reducers: {
    makeMove: (state, action) => {
      const idx = action.payload;
      if (state.board[idx] || state.winner) return;
      state.board[idx] = state.currentPlayer;
      state.winner = calculateWinner(state.board);
      state.isDraw = !state.winner && state.board.every(cell => cell);
      if (!state.winner && !state.isDraw) {
        state.currentPlayer = state.currentPlayer === 'X' ? 'O' : 'X';
      }
    },
    resetGame: () => initialState,
  },
});

export const { makeMove, resetGame } = gameSlice.actions;
export default gameSlice.reducer; 