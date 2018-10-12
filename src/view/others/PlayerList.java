package view.others;

import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;

public class PlayerList extends JList<Player> {
    private final DefaultListModel<Player> listModel;
    private final GameEngine gameEngine;

    public PlayerList(GameEngine gameEngine) {
        listModel = new DefaultListModel<>();
        this.gameEngine = gameEngine;
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setCellRenderer(new PlayerListCellRenderer());

    }

}
