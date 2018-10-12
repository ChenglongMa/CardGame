package controller;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.dialogs.AddPlayerDialog;
import view.panels.PlayerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPlayerListener implements ActionListener {
    private final PlayerPanel playerPanel;

    public AddPlayerListener(PlayerPanel playerPanel) {
        this.playerPanel = playerPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            AddPlayerDialog dialog = new AddPlayerDialog(playerPanel);
            Player player = dialog.showDialog();
            if (player == null) {
                return;
            }
            GameEngine engine = playerPanel.getGameEngine();
            engine.addPlayer(player);
            playerPanel.refreshList();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(playerPanel.getParent(), ex.getMessage());
        }

    }
}
