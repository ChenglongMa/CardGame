package client;

import model.PlayingCardImpl;
import model.interfaces.PlayingCard;
import view.AppFrame;
import view.panels.CardPanel;

import javax.swing.*;

public class GUITestClient {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AppFrame();
            }
        });
    }
}
