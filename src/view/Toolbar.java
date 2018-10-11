package view;

import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;

public class Toolbar extends JToolBar {
    private JButton btnAddPlayer;
    private JComboBox<Player> cmbSelectPlayer;
    private JButton btnPlaceBet;
    private JButton btnDealPlayer;
    private JButton btnStop;

    @Deprecated
    public Toolbar() {
        btnAddPlayer = new JButton("Add Player");
        cmbSelectPlayer = new JComboBox<>();//TODO:more specific
        btnPlaceBet = new JButton("Place Bet");
        btnDealPlayer = new JButton("Deal Player");
        btnStop = new JButton("Stop");


        setComponentsEnabled(false);
        setLayout(new GridLayout(1, 5));
        add(btnAddPlayer);
        add(cmbSelectPlayer);
        add(btnPlaceBet);
        add(btnDealPlayer);
        add(btnStop);
        add(Box.createHorizontalGlue());
        setFloatable(false);

    }

    private void setComponentsEnabled(boolean isDealing) {
        btnAddPlayer.setEnabled(!isDealing);
        btnDealPlayer.setEnabled(!isDealing);
        btnAddPlayer.setEnabled(!isDealing);
        btnPlaceBet.setEnabled(!isDealing);
        cmbSelectPlayer.setEnabled(!isDealing);
        btnStop.setEnabled(isDealing);
    }
}
