package controller.listListener;

import view.panels.PlayerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ListListenerTemplate implements ActionListener {
    private final PlayerPanel playerPanel;

    ListListenerTemplate(PlayerPanel playerPanel) {
        this.playerPanel = playerPanel;
    }

    abstract void operation();

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            operation();
            playerPanel.refreshListLater();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(playerPanel.getParent(), ex.getMessage());
        }

    }

}
