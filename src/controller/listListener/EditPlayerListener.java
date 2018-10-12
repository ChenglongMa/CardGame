package controller.listListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.dialogs.AddEditPlayerDialog;
import view.panels.PlayerPanel;

public class EditPlayerListener extends ListListenerTemplate {
    //TODO:to be finished
    private final PlayerPanel playerPanel;

    public EditPlayerListener(PlayerPanel playerPanel) {
        super(playerPanel);
        this.playerPanel = playerPanel;
    }


    @Override
    void operation() {
        Player selectedPlayer = playerPanel.getSelectedPlayer();
        if (selectedPlayer == null) {
            return;
        }
        AddEditPlayerDialog dialog = new AddEditPlayerDialog(playerPanel, selectedPlayer);
        Player player = dialog.showDialog();
        if (player == null) {
            return;
        }
        GameEngine engine = playerPanel.getGameEngine();
        engine.removePlayer(selectedPlayer);
        engine.addPlayer(player);
        playerPanel.setSelectedIndex();
    }
}
