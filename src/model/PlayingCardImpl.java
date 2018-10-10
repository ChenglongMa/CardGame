package model;

import model.interfaces.PlayingCard;

import java.util.Objects;

/**
 * The implementation of {@link PlayingCard}
 *
 * @author Chenglong Ma
 * @see PlayingCard
 */
public class PlayingCardImpl implements PlayingCard {
    private final Suit suit;
    private final Value value;

    public PlayingCardImpl(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    @Override
    public Suit getSuit() {
        return suit;
    }

    @Override
    public Value getValue() {
        return value;
    }

    @Override
    public int getScore() {
        switch (value) {

            case ACE:
                return 1;
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
            case JACK:
            case QUEEN:
            case KING:
                return 10;
        }
        return -1;
           /*// The following is not robust
        int score = value.ordinal() + 1;
        return score < 10 ? score : 10;*/
    }

    @Override
    public boolean equals(PlayingCard card) {
        if (card == null) {
            return false;
        }
        return suit.equals(card.getSuit()) && value.equals(card.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, value);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PlayingCard)) {
            return super.equals(obj);
        }
        return equals((PlayingCard) obj);
    }

    @Override
    public String toString() {
        return String.format("Suit: %s, Value: %s, Score: %d", suit, value, getScore());
    }
}
