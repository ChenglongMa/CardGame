package view.callback;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

import javax.swing.*;


public abstract class GameEngineCallbackGUITemplate implements GameEngineCallback {
    abstract void addPlayerCard(Player player, PlayingCard card);

    private void nextCard(final Player player, final PlayingCard card) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                addPlayerCard(player, card);
            }
        });
    }

    @Override
    public final void nextCard(final Player player, final PlayingCard card, GameEngine engine) {
        nextCard(player, card);
    }

    @Override
    public final void bustCard(final Player player, final PlayingCard card, GameEngine engine) {
        nextCard(player, card);
    }

    @Override
    public final void result(Player player, int result, GameEngine engine) {
        result();
    }

    @Override
    public final void nextHouseCard(PlayingCard card, GameEngine engine) {
        nextHouseCard(card);
    }

    @Override
    public final void houseBustCard(PlayingCard card, GameEngine engine) {
        nextHouseCard(card);
    }

    @Override
    public final void houseResult(final int houseResult, GameEngine engine) {
        houseResult(houseResult);
    }

    abstract void updateFinalResult(final int result);

    private void houseResult(final int result) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                updateFinalResult(result);
            }
        });
    }

    abstract void addHouseCard(final PlayingCard card);

    private void nextHouseCard(final PlayingCard card) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                addHouseCard(card);
            }
        });
    }

    abstract void updatePlayerStatus();

    private void result() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                updatePlayerStatus();
            }
        });
    }
}
