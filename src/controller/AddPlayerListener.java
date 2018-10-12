package controller;

import model.interfaces.GameEngine;
import view.dialogs.AddPlayerDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPlayerListener implements ActionListener {
    private final AddPlayerDialog addPlayerDialog;

    public AddPlayerListener(AddPlayerDialog addPlayerDialog) {
        this.addPlayerDialog = addPlayerDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameEngine engine = addPlayerDialog.getGameEngine();
        engine.addPlayer(addPlayerDialog.getPlayer());
    }
}
