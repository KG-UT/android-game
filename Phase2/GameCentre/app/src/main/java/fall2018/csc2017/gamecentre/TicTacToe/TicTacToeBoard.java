package fall2018.csc2017.gamecentre.TicTacToe;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import fall2018.csc2017.gamecentre.Board;
import fall2018.csc2017.gamecentre.Tile;

/**
 * The sliding tiles board.
 */
public class TicTacToeBoard extends Board {
    TicTacToeBoard(int rows, int cols, List<Tile> tiles){
        super(rows, cols, tiles);
    }
}
