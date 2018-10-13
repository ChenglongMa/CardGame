package view.callback;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;
import view.panels.CardPanel;
import view.panels.MainGamePanel;

import javax.swing.*;

public class GameEngineCallbackGUI implements GameEngineCallback {
    private final MainGamePanel gamePanel;
    private final CardPanel playerPanel;
    private final CardPanel housePanel;

    public GameEngineCallbackGUI(MainGamePanel gamePanel) {
        this.gamePanel = gamePanel;
        playerPanel = gamePanel.getPlayerPanel();
        housePanel = gamePanel.getHousePanel();
    }

    @Override
    public void nextCard(final Player player, final PlayingCard card, GameEngine engine) {
        playerPanel.addCard(card);
    }

    @Override
    public void bustCard(Player player, PlayingCard card, GameEngine engine) {
        playerPanel.addCard(card);
        JOptionPane.showMessageDialog(playerPanel, "YOU BUSTED!");

    }

    @Override
    public void result(Player player, int result, GameEngine engine) {
        gamePanel.setCurrentPlayer(player);
        gamePanel.updatePlayerStatus();
    }

    @Override
    public void nextHouseCard(PlayingCard card, GameEngine engine) {
        housePanel.addCard(card);
    }

    @Override
    public void houseBustCard(PlayingCard card, GameEngine engine) {
        housePanel.addCard(card);
        JOptionPane.showMessageDialog(housePanel, "HOUSE BUSTED!");
    }

    @Override
    public void houseResult(int result, GameEngine engine) {
        gamePanel.getStatusBar().updateHouseResult(result);

        gamePanel.updatePlayerStatus();
    }
}
