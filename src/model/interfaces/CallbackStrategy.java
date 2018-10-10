package model.interfaces;

/**
 * The interface for {@link GameEngine} providing callback strategy
 *
 * @author Chenglong Ma
 * @see GameEngine
 */
public interface CallbackStrategy {

    /**
     * Log the nex card
     *
     * @param card the next card that was dealt
     */
    void nextCard(PlayingCard card);

    /**
     * Log the bust card
     *
     * @param card the bust card that was dealt
     */
    void bustCard(PlayingCard card);

    /**
     * Log the final result
     *
     * @param result the final score of the hand
     */
    void result(int result);
}
