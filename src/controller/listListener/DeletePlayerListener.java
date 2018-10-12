package controller.listListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.panels.PlayerPanel;

public class DeletePlayerListener extends ListListenerTemplate {
    private final PlayerPanel playerPanel;

    public DeletePlayerListener(PlayerPanel playerPanel) {
        super(playerPanel);
        this.playerPanel = playerPanel;
    }

    @Override
    void operation() {
        Player selectedPlayer = playerPanel.getSelectedPlayer();
        if (selectedPlayer==null) {
            return;
        }
        GameEngine gameEngine = playerPanel.getGameEngine();
        gameEngine.removePlayer(selectedPlayer);
    }
}
