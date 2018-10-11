package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class StatusBar extends JPanel {
    private JLabel playerStatus;
    private JLabel playerPoints;
    private JLabel houseStatus;
    private JLabel sumStatus;

    public StatusBar() {
        playerStatus = new JLabel("Current Player: ", SwingConstants.LEFT);
        playerPoints = new JLabel("Points: ");
        houseStatus = new JLabel("House Points: ");
        sumStatus = new JLabel("Ready", SwingConstants.RIGHT);
        Border border = BorderFactory.createRaisedBevelBorder();
        playerStatus.setBorder(border);
        playerPoints.setBorder(border);
        houseStatus.setBorder(border);
        sumStatus.setBorder(border);

        setLayout(new GridLayout(1, 4));
        // add three labels
        add(playerStatus);
        add(playerPoints);
        add(houseStatus);
        add(sumStatus);
    }
}
