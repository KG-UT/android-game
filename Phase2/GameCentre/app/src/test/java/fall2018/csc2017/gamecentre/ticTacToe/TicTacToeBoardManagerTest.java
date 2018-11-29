package fall2018.csc2017.gamecentre.games.ticTacToe;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Iterator;

public class TicTacToeBoardManagerTest {
    private final int NUM_ROWS = 3;
    private final int NUM_COLS = 3;

    public TicTacToeBoardManager createTicTacToeBoardManager() {
        TicTacToeBoardManager board = new TicTacToeBoardManager(NUM_ROWS, NUM_COLS);
        return board;
    }

    public void fillAllExpectOne(TicTacToeBoardManager boardManager, String player) {
        Iterator<Integer[]> moves = boardManager.getAllBlanks().iterator();
        int numMoves = boardManager.getAllBlanks().size();
        for(int i = 0; i < numMoves - 1; i++) {
            Integer[] move = moves.next();
            boardManager.makeMove(move[0], move[1], player);
        }
    }

    public void fillRow(TicTacToeBoardManager boardManager, int rowNum, String player) {
        for(int col = 0; col < NUM_COLS; col++) {
            boardManager.makeMove(rowNum, col, player);
        }
    }

    public void fillCol(TicTacToeBoardManager boardManager, int colNum, String player) {
        for(int row = 0; row < NUM_ROWS; row++) {
            boardManager.makeMove(row, colNum, player);
        }
    }

    public void fillLeftRightDiagonal(TicTacToeBoardManager boardManager, String player) {
        for(int i = 0; i < NUM_COLS; i++) {
            boardManager.makeMove(i, i, player);
        }
    }

    public void fillRightLeftDiagonal(TicTacToeBoardManager boardManager, String player) {
        for(int i = 0; i < NUM_COLS; i++) {
            boardManager.makeMove(NUM_ROWS - i - 1, i, player);
        }
    }

    /**
     * Test getAllBlanks for an empty board
     */
    @Test
    public void testGetAllBlanksAllEmpty() {
        TicTacToeBoardManager boardManager = createTicTacToeBoardManager();
        ArrayList<Integer[]>  empties = boardManager.getAllBlanks();
        assertEquals(NUM_ROWS * NUM_COLS, empties.size());
    }

    /**
     * Test getAllBlanks for semi-empty board
     */
    @Test
    public void testGetAllBlanksSemiEmpty() {
        TicTacToeBoardManager boardManager = createTicTacToeBoardManager();
        boardManager.getBoard().updateTile(0, 1, TicTacToeTile.X);
        boardManager.getBoard().updateTile(1, 1, TicTacToeTile.O);

        ArrayList<Integer[]>  empties = boardManager.getAllBlanks();
        assertEquals(NUM_COLS * NUM_ROWS - 2, empties.size());
    }

    /**
     * Test getAllBlanks completely empty board
     */
    @Test
    public void testGetAllBlanksEntirelyEmpty() {
        TicTacToeBoardManager boardManager = createTicTacToeBoardManager();
        for(int i = 0; i < NUM_ROWS; i++) {
            for(int j = 0; j < NUM_COLS; j++) {
                boardManager.getBoard().updateTile(i, j, TicTacToeTile.O);
            }
        }

        ArrayList<Integer[]>  empties = boardManager.getAllBlanks();
        assertEquals(0, empties.size());
    }

    /**
     * Test that a computer move registers on the board
     */
    @Test
    public void testComputerMakeMove() {
        TicTacToeBoardManager boardManager = createTicTacToeBoardManager();
        int originalSize = boardManager.getAllBlanks().size();
        boardManager.computerMakeMove();
        int newSize = boardManager.getAllBlanks().size();
        assertEquals(true, newSize < originalSize);
    }

    /**
     * Test touch move when there is no winner
     */
    @Test
    public void testTouchMoveNoWinnerAndBoardFull() {
        TicTacToeBoardManager boardManager = createTicTacToeBoardManager();

        assertEquals(NUM_COLS * NUM_ROWS, boardManager.getAllBlanks().size());

        TicTacToeBoard board = boardManager.getBoard();
        String[] moves = {TicTacToeTile.X,  TicTacToeTile.O, TicTacToeTile.O,
                          TicTacToeTile.X, TicTacToeTile.O, TicTacToeTile.O,
                          TicTacToeTile.O, TicTacToeTile.X};
        for(int i = 0; i < moves.length; i++) {
            int row = i / NUM_ROWS;
            int col = i % NUM_COLS;
            boardManager.makeMove(row, col, moves[i]);
        }
        boardManager.touchMove(8);

        int availableMoves = boardManager.getAllBlanks().size();
        assertEquals(NUM_COLS * NUM_ROWS, availableMoves);
    }

    /**
     * Test touch move when there is no winner
     */
    @Test
    public void testTouchMoveNoWinnerAndBoardNotFull() {
        TicTacToeBoardManager boardManager = createTicTacToeBoardManager();

        assertEquals(9, boardManager.getAllBlanks().size());

        TicTacToeBoard board = boardManager.getBoard();
        String[] moves = {TicTacToeTile.X};
        for(int i = 0; i < moves.length; i++) {
            int row = i / NUM_ROWS;
            int col = i % NUM_COLS;
            boardManager.makeMove(row, col, moves[i]);
        }
        boardManager.touchMove(3);

        int availableMoves = boardManager.getAllBlanks().size();
        assertEquals(NUM_COLS * NUM_ROWS - 3, availableMoves);
    }

    /**
     *
     */
    @Test
    public void testTouchMoveHasWinner() {
//        TicTacToeBoardManager boardManager = createTicTacToeBoardManager();
//        fillAllExpectOne(boardManager, TicTacToeTile.X);
//
//        Integer[] lastMove = boardManager.getAllBlanks().get(0);
//        boardManager.touchMove(lastMove[0] * board.getNumCols() + lastMove[1]);
//        assertEquals(board.numObjects(), boardManager.getAllBlanks().size());
//        assertEquals(1, boardManager.getScore());
    }

    /**
     * Test is valid tap
     */
    @Test
    public void testIsValidTap() {
        TicTacToeBoardManager boardManager = createTicTacToeBoardManager();
        boardManager.makeMove(0, 0, TicTacToeTile.X);
        assertEquals(false, boardManager.isValidTap(0));
        assertEquals(true, boardManager.isValidTap(2));
    }

    /**
     * Test if the board manager detects if a column has 3 in a row
     */
    @Test
    public void testColSame() {
        TicTacToeBoardManager boardManager = createTicTacToeBoardManager();
        assertEquals("", boardManager.colsSame());

        fillCol(boardManager, 0, TicTacToeTile.X);
        String colSame = boardManager.colsSame();
        assertEquals(TicTacToeTile.X, colSame);
    }

    /**
     * Test if the board manager detects if a row has 3 in a row
     */
    @Test
    public void testRowSame() {
        TicTacToeBoardManager boardManager = createTicTacToeBoardManager();
        assertEquals("", boardManager.rowsSame());

        fillRow(boardManager, 0, TicTacToeTile.O);
        String rowSame = boardManager.rowsSame();
        assertEquals(TicTacToeTile.O, rowSame);
    }

    /**
     * Test if the board manager gets the correct winner from a row win
     */
    @Test
    public void testGetWinnerRow() {
        TicTacToeBoardManager boardManager = createTicTacToeBoardManager();
        fillRow(boardManager, 1, TicTacToeTile.O);
        assertEquals(TicTacToeTile.O, boardManager.getWinner());
    }

    /**
     * Test if the board manager gets the correct winner from a col win
     */
    @Test
    public void testGetWinnerCol() {
        TicTacToeBoardManager boardManager = createTicTacToeBoardManager();
        fillCol(boardManager, 1, TicTacToeTile.O);
        assertEquals(TicTacToeTile.O, boardManager.getWinner());
    }

    /**
     * Test if the board manager gets the correct winner from a col win
     */
    @Test
    public void testGetWinnerDiagonal() {
        TicTacToeBoardManager boardManager = createTicTacToeBoardManager();
        fillLeftRightDiagonal(boardManager, TicTacToeTile.O);
        assertEquals(TicTacToeTile.O, boardManager.getWinner());

        boardManager = createTicTacToeBoardManager();
        fillRightLeftDiagonal(boardManager, TicTacToeTile.O);
        assertEquals(TicTacToeTile.O, boardManager.getWinner());
    }

    /**
     * Test if board manager gets no winner
     */
    @Test
    public void testGetWinnerNoWinner() {
        TicTacToeBoardManager boardManager = createTicTacToeBoardManager();
        assertEquals("", boardManager.getWinner());
    }

    /**
     * Test if the puzzle is solved for a blank game
     */
    @Test
    public void testPuzzleSolvedNewGame() {
        TicTacToeBoardManager boardManager = createTicTacToeBoardManager();
        assertEquals(false, boardManager.puzzleSolved());
    }

    /**
     * Test if the puzzle is solved for a full board where player won
     */
    @Test
    public void testPuzzleSolvedPlayerWon() {
        TicTacToeBoardManager boardManager = createTicTacToeBoardManager();
        fillLeftRightDiagonal(boardManager, TicTacToeTile.X);
        assertEquals(false, boardManager.puzzleSolved());
    }

    /**
     * Test if the puzzle is solved for a full board where computer
     */
    @Test
    public void testPuzzleSolvedComputerWon() {
        TicTacToeBoardManager boardManager = createTicTacToeBoardManager();
        fillLeftRightDiagonal(boardManager, TicTacToeTile.O);
        assertEquals(true, boardManager.puzzleSolved());
    }
}
