package controller.buttonListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.panels.MainGamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DealListener implements ActionListener {
    private final MainGamePanel gamePanel;

    public DealListener(MainGamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: to be finished
        final Player player = gamePanel.getCurrentPlayer();
        if (player == null) {
            return;
        }
        final GameEngine gameEngine = gamePanel.getGameEngine();
        new Thread(new Runnable() {
            @Override
            public void run() {
                gameEngine.dealPlayer(player, 1000);//TODO: need more flexible
            }
        }).start();

    }
}
