package fall2018.csc2017.gamecentre.games.matchingCards;

import java.io.Serializable;

/**
 * A Matching Cards tile.
 */
public class MatchingCardsTile implements Serializable {

    /**
     * A boolean that states if the current tile is face up.
     */
    private boolean faceUp = false;

    /**
     * The number of the card.
     */
    private int cardNumber;

    /**
     * A tile with the cardNumber given by number.
     *
     * @param number the card number.
     */
    MatchingCardsTile(int number){
        this.cardNumber = number;
    }

    /**
     * Sets the card face up.
     */
    void setFaceUp(){faceUp = true;}

    /**
     * Sets the card face down.
     */
    void setFaceDown(){faceUp = false;}

    /**
     * Gets the card number.
     * @return the card number.
     */
    int getNumber(){return cardNumber;}

    /**
     * Returns if this card is the same as another card, for the purpose of comparison.
     * @param other Another MatchingCardsTile.
     * @return a boolean stating if this card is equal to another.
     */
    boolean isequal(MatchingCardsTile other){
        return this.getNumber() == other.getNumber();
    }
}
