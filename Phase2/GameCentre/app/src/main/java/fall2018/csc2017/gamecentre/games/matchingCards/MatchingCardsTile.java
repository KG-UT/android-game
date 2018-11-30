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
    public MatchingCardsTile(int number){
        this.cardNumber = number;
    }

    /**
     * Sets the card face up.
     */
    public void setFaceUp(){faceUp = true;}

    /**
     * Sets the card face down.
     */
    public void setFaceDown(){faceUp = false;}

    /**
     * Gets the card number.
     * @return the card number.
     */
    public int getNumber(){return cardNumber;}

    /**
     * Returns if this card is the same as another card, for the purpose of comparison.
     * @param other Another MatchingCardsTile.
     * @return a boolean stating if this card is equal to another.
     */
    public boolean isEqual(MatchingCardsTile other){
        return this.getNumber() == other.getNumber();
    }

    /**
     * Returns a bool stating if this card is face up.
     * @return a bool stating if this card is face up.
     */
    public boolean isFaceUp(){return faceUp;}
}
