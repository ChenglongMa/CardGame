package controller.buttonListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.panels.MainGamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BetListener implements ActionListener {
    private final MainGamePanel gamePanel;

    public BetListener(MainGamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean validBet;
        try {
            Player player = gamePanel.getCurrentPlayer();
            GameEngine gameEngine = gamePanel.getGameEngine();
            String prompt = "Please place your bets:";
            String betStr = JOptionPane.showInputDialog(gamePanel, prompt);
            int bet = Integer.parseInt(betStr);
            validBet = gameEngine.placeBet(player, bet);
            gamePanel.getStatusBar().updatePlayerStatus(player);
        } catch (Exception ex) {
            validBet = false;
        }
        gamePanel.getToolbar().setCanDeal(validBet);
        String msg = validBet
                ? "Ready to Deal Cards, have fun!"
                : "Please place an valid bet.";
        JOptionPane.showMessageDialog(gamePanel, msg);
    }
}
