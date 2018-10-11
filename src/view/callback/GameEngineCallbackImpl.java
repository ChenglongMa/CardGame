package view.callback;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Skeleton/Partial example implementation of GameEngineCallback showing Java logging behaviour
 *
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 */
public class GameEngineCallbackImpl implements GameEngineCallback {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public GameEngineCallbackImpl() {
        logger.setLevel(Level.FINE);
    }

    @Override
    public void nextCard(Player player, PlayingCard card, GameEngine engine) {
        logger.log(Level.FINE, String.format("Card Dealt to %s .. %s", player.getPlayerName(), card));
    }

    @Override
    public void bustCard(Player player, PlayingCard card, GameEngine engine) {
        logger.log(Level.FINE, String.format("Card Dealt to %s .. %s ... YOU BUSTED!", player.getPlayerName(), card));
    }

    @Override
    public void result(Player player, int result, GameEngine engine) {
        logger.log(Level.INFO, String.format("%s, final result=%d", player.getPlayerName(), result));
    }

    @Override
    public void nextHouseCard(PlayingCard card, GameEngine engine) {
        logger.log(Level.FINE, String.format("Card Dealt to House .. %s", card));
    }

    @Override
    public void houseBustCard(PlayingCard card, GameEngine engine) {
        logger.log(Level.FINE, String.format("Card Dealt to House .. %s ... HOUSE BUSTED!", card));
    }

    @Override
    public void houseResult(int result, GameEngine engine) {
        logger.log(Level.INFO, String.format("House, final result=%d", result));
        StringBuilder finalRes = new StringBuilder("Final Player Results\n");
        for (Player player : engine.getAllPlayers()) {
            finalRes.append(player).append("\n");
        }
        logger.log(Level.INFO, finalRes.toString());
    }

}
