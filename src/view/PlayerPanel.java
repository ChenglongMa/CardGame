package view;

import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlayerPanel extends JPanel {
    private final List<Player> players;
    private final JList<Player> playerJList;
    private final LeftToolbar toolbar;


    public PlayerPanel(List<Player> players) {
        setBorder(null);
        this.players = players;
        //Set layout
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, 1.0E-4};
        gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0E-4};
        setLayout(gridBagLayout);
        //Set toolbar
        toolbar = new LeftToolbar();
        addToolBar(toolbar);
        //Set JList
        playerJList = new JList<>();
        playerJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addJList(playerJList);
    }

    private void addJList(JList jList) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(jList, gbc);
    }

    private void addToolBar(JToolBar jToolBar) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(jToolBar, gbc);
    }
}
