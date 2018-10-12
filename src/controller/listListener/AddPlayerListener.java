package controller.listListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.dialogs.AddEditPlayerDialog;
import view.panels.PlayerPanel;

public class AddPlayerListener extends ListListenerTemplate {
    private final PlayerPanel playerPanel;

    public AddPlayerListener(PlayerPanel playerPanel) {
        super(playerPanel);
        this.playerPanel = playerPanel;
    }

    @Override
    void operation() {
        AddEditPlayerDialog dialog = new AddEditPlayerDialog(playerPanel);
        Player player = dialog.showDialog();
        if (player == null) {
            return;
        }
        GameEngine engine = playerPanel.getGameEngine();
        engine.addPlayer(player);
    }
}
