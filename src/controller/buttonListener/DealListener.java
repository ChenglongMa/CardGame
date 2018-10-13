package controller.buttonListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.panels.MainGamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DealListener implements ActionListener {
    private final MainGamePanel gamePanel;
    private Thread currThread;

    public DealListener(MainGamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public Thread getCurrThread() {
        return currThread;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: to be finished
        final Player player = gamePanel.getCurrentPlayer();
        if (player == null || player.getBet() <= 0) {
            JOptionPane.showMessageDialog(gamePanel, "Please place your bet.");
            return;
        }
        gamePanel.getToolbar().setCanPlaceBet(false);
        gamePanel.getPlayerPanel(player).clearCard();
        gamePanel.getHousePanel().clearCard();
        final GameEngine gameEngine = gamePanel.getGameEngine();
        currThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    gamePanel.getToolbar().setCanDeal(false);
                    gameEngine.dealPlayer(player, 1000);//TODO: need more flexible
                    gameEngine.dealHouse(1000);//TODO: in another thread
                } catch (Exception ex) {
                    gamePanel.getToolbar().setCanPlaceBet(true);

                }
            }
        });
        currThread.start();
    }
}
