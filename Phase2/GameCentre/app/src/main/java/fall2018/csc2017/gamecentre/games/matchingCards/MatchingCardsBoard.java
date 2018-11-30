package fall2018.csc2017.gamecentre.games.matchingCards;

import fall2018.csc2017.gamecentre.abstractClasses.Board;

import java.util.ArrayList;
import java.util.List;

/**
 * The Matching Cards game board.
 */
public class MatchingCardsBoard extends Board {

    /**
     * The cards that currently face up
     */
    private ArrayList<MatchingCardsTile> tempFaceUpCards = new ArrayList<>();

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
     * States if two cards are currently face up.
     *
     * @return a boolean stating if two cards are currently face up
     */
    public boolean twoTempCardsAreUp(){return tempFaceUpCards.size() == 2;}

    /**
     * Returns the MatchingCardsTile at (row, col).
     *
     * @param row the MatchingCardsTile row
     * @param col the MatchingCardsTile column
     * @return The matchingCardsTile
     */
    public MatchingCardsTile getCard(int row, int col){return (MatchingCardsTile) getItem(row, col);}

    /**
     * Flips the card at (row, col) up.
     *
     * @param row the MatchingCardsTile row
     * @param column the MatchingCardsTile column
     */
    public void flipCardUp(int row, int column){
        MatchingCardsTile card = (MatchingCardsTile) getItem(row, column);
        card.setFaceUp();
        tempFaceUpCards.add(card);
        setChanged();
        notifyObservers();
    }

    /**
     * Flips the temporary face up cards back down.
     */
    public void flipTempCardsDown(){
        for (int i=0; i<2; i++){
            MatchingCardsTile tile = tempFaceUpCards.get(i);
            tile.setFaceDown();
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Gets the temporarily face-up cards.
     */
    public ArrayList<MatchingCardsTile> getTempFaceupCards(){
        return tempFaceUpCards;
    }

    /**
     * Resets the TempFaceUpCards array.
     */
    public void clearTempFaceUpCards(){
        tempFaceUpCards.clear();
        setChanged();
        notifyObservers();
    }
}
