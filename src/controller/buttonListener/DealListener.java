package controller.buttonListener;

import view.panels.MainGamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DealListener implements ActionListener {
    private final MainGamePanel gamePanel;

    public DealListener(MainGamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
