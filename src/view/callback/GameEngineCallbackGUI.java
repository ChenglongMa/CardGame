package view.callback;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;
import view.panels.CardPanel;
import view.panels.MainGamePanel;

import javax.swing.*;

public class GameEngineCallbackGUI implements GameEngineCallback {
    private final CardPanel housePanel;
    private final MainGamePanel gamePanel;
    private CardPanel playerPanel;


    public GameEngineCallbackGUI(MainGamePanel gamePanel) {
        this.gamePanel = gamePanel;
        housePanel = gamePanel.getHousePanel();
    }

    private void getCardPanel(Player player) {
        playerPanel = gamePanel.getPlayerPanel(player);
    }


    private void addPlayerCard(Player player, PlayingCard card) {
        playerPanel = gamePanel.getPlayerPanel(player);
        if (playerPanel == null) {
            return;
        }
        playerPanel.addCard(card);
    }

    @Override
    public void nextCard(final Player player, final PlayingCard card, GameEngine engine) {
        addPlayerCard(player, card);
    }

    @Override
    public void bustCard(Player player, PlayingCard card, GameEngine engine) {
        addPlayerCard(player, card);
    }

    @Override
    public void result(Player player, int result, GameEngine engine) {
        gamePanel.updatePlayerStatus();
    }

    @Override
    public void nextHouseCard(PlayingCard card, GameEngine engine) {
        housePanel.addCard(card);
    }

    @Override
    public void houseBustCard(PlayingCard card, GameEngine engine) {
        if (housePanel != null) {
            housePanel.addCard(card);
        }
        JOptionPane.showMessageDialog(housePanel, "HOUSE BUSTED!");
    }

    @Override
    public void houseResult(int result, GameEngine engine) {
        gamePanel.getStatusBar().updateHouseResult(result);
        gamePanel.updatePlayerStatus();
    }
}
