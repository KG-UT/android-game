package fall2018.csc2017.gamecentre.ticTacToe;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class TicTacToeBoardManagerTest {
    private final int NUM_ROWS = 3;
    private final int NUM_COLS = 3;
    public TicTacToeBoardManager createTicTacToeBoardManager() {
        TicTacToeBoardManager board = new TicTacToeBoardManager(NUM_ROWS, NUM_COLS);
        return board;
    }

    /**
     * Test getAllBlanks for an empty board
     */
    @Test
    public void testGetAllBlanksAllEmpty() {
        TicTacToeBoardManager boardManager = createTicTacToeBoardManager();
        ArrayList<Integer[]>  empties = boardManager.getAllBlanks();
        assertEquals(empties.size(), NUM_ROWS * NUM_COLS);
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
        assertEquals(empties.size(), NUM_COLS * NUM_ROWS - 2);
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
        assertEquals(empties.size(), 0);
    }

}
