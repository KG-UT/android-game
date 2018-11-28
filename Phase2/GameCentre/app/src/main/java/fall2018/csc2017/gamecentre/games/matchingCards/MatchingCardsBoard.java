package fall2018.csc2017.gamecentre.games.matchingCards;

import fall2018.csc2017.gamecentre.game.Board;
import java.util.List;

/**
 * The Matching Cards game board.
 */
public class MatchingCardsBoard extends Board {

    private boolean cardIsUp = false;
    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param rows the number of rows
     * @param cols the number of columns
     * @param tiles the tiles for the board
     */
    public MatchingCardsBoard(int rows, int cols, List<MatchingCardsTile> tiles){
        super(rows, cols, tiles);
    }

    /**
     * return the MatchingCardsTile at (row, col)
     * @param row the MatchingCardsTile row
     * @param col the MatchingCardsTile column
     * @return The matchingCardsTile
     */
    MatchingCardsTile getCard(int row, int col){return (MatchingCardsTile) getItem(row, col);}

    /**
     * flips the card at (row, col) up
     * @param row the MatchingCardsTile row
     * @param column the MatchingCardsTile column
     */
    void flipCardUp(int row, int column){
        MatchingCardsTile card = (MatchingCardsTile) getItem(row, column);
        card.setFaceUp();
        if (cardIsUp){
            flipAllCardsDown();
        } else {cardIsUp = true;}
    }

    /**
     * flips all the cards on the board down
     */
    void flipAllCardsDown(){
        for (int row=0; row < NUM_ROWS; row++){
            for (int col=0; col < NUM_COLS; col++){
                MatchingCardsTile card = getCard(row, col);
                card.setFaceDown();
            }
        }
    }
}
