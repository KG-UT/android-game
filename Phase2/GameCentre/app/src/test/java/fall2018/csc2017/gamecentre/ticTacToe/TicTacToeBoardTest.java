package fall2018.csc2017.gamecentre.ticTacToe;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fall2018.csc2017.gamecentre.games.slidingTile.SlidingTileBoardManager;
import fall2018.csc2017.gamecentre.games.ticTacToe.TicTacToeBoard;
import fall2018.csc2017.gamecentre.games.ticTacToe.TicTacToeTile;

import static org.junit.Assert.assertEquals;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TicTacToeBoardTest {
    Random random = new Random();

    public TicTacToeBoard createTicTacToeBoard() {
        List<TicTacToeTile> tiles = new ArrayList<>();
        final int numTiles = 3 * 3;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TicTacToeTile());
        }

        TicTacToeBoard board = new TicTacToeBoard(3, 3, tiles);
        return board;
    }

    /**
     * Test whether getting tiles works
     */
    @Test
    public void testGetTile() {
        TicTacToeBoard board = createTicTacToeBoard();
        assertEquals(board.getTile(0, 1).getState(), TicTacToeTile.BLANK);
        TicTacToeTile newTile = new TicTacToeTile();
        newTile.setState(TicTacToeTile.X);
        board.setTile(0, 1, newTile);

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                TicTacToeTile tile = board.getTile(i, j);
                if(i == 0 && j == 1) {
                    assertEquals(tile.getState(), TicTacToeTile.X);
                } else {
                    assertEquals(tile.getState(), TicTacToeTile.BLANK);
                }

            }
        }
    }

    /**
     * Test whether setting tiles works
     */
    @Test
    public void testSetTile() {
        TicTacToeBoard board = createTicTacToeBoard();
        assertEquals(board.getTile(0, 1).getState(), TicTacToeTile.BLANK);
        TicTacToeTile newTile = new TicTacToeTile();
        newTile.setState(TicTacToeTile.O);
        board.setTile(2, 1, newTile);

        assertEquals(board.getTile(2, 1).getState(), TicTacToeTile.O);
    }

    /**
     * Test whether updating a tile works
     */
    @Test
    public void testUpdateTile() {
        TicTacToeBoard board = createTicTacToeBoard();
        int trials = 0;
        for(int i = 0; i < trials; i++) {
            int randomI = random.nextInt(2);
            int randomJ = random.nextInt(2);
            board.updateTile(randomI, randomJ, TicTacToeTile.O);
            assertEquals(board.getTile(randomI, randomJ).getState(), TicTacToeTile.O);
        }
    }

    /**
     * Test whether
     */
    @Test
    public void testReset() {
        TicTacToeBoard board = createTicTacToeBoard();
        for(int i = 0; i < 10; i++) {
            int randomI = random.nextInt(2);
            int randomJ = random.nextInt(2);
            board.updateTile(randomI, randomJ, TicTacToeTile.O);
        }
        // TODO: Mine doesn't have reset?
        board.reset();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                assertEquals(board.getTile(i, j).getState(), TicTacToeTile.BLANK);
            }
        }
    }

}
