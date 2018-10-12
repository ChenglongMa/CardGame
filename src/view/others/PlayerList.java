package view.others;

import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;

public class PlayerList extends JList<Player> {

    public PlayerList() {
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setCellRenderer(new PlayerListCellRenderer());

    }

}
