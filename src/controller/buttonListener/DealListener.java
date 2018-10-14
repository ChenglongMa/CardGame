package controller.buttonListener;

import model.interfaces.Player;
import view.bars.RightToolbar;
import view.panels.MainGamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DealListener implements ActionListener {
    private final MainGamePanel gamePanel;
    private final RightToolbar toolbar;

    public DealListener(MainGamePanel gamePanel) {
        this.gamePanel = gamePanel;
        toolbar = gamePanel.getToolbar();
    }

    /**
     * Set components status before dealing
     */
    private void ready() {
        toolbar.setCanPlaceBet(false);
        toolbar.setCanDeal(false);
        toolbar.setDealHouseEnabled(false);
        gamePanel.getHousePanel().clearCard();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case RightToolbar.DEAL_PLAYER_COMMAND:
                final Player player = gamePanel.getCurrentPlayer();
                if (player == null || player.getBet() <= 0) {
                    JOptionPane.showMessageDialog(gamePanel, "Please place your bet.");
                    return;
                }
                gamePanel.getPlayerPanel(player).clearCard();
                ready();
                gamePanel.getCurrentPlayerThread().start();
                break;
            case RightToolbar.DEAL_HOUSE_COMMAND:
                ready();
                gamePanel.getHouseThread().start();
                break;
        }
    }
}
