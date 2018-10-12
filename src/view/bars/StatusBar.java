package view.bars;

import model.interfaces.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class StatusBar extends JPanel {
    private static final String CURR_PLAYER = "Current Player: ";
    private static final String POINTS = "Points: ";
    private static final String HOUSE_POINTS = "House Points: ";

    private JLabel playerInfo;
    private JLabel playerPoints;
    private JLabel houseStatus;
    private JLabel sumStatus;

    public StatusBar() {
        playerInfo = new JLabel(CURR_PLAYER, SwingConstants.LEFT);
        playerPoints = new JLabel(POINTS);
        houseStatus = new JLabel(HOUSE_POINTS);
        sumStatus = new JLabel("Ready", SwingConstants.RIGHT);
        Border border = BorderFactory.createRaisedBevelBorder();
        playerInfo.setBorder(border);
        playerPoints.setBorder(border);
        houseStatus.setBorder(border);
        sumStatus.setBorder(border);

        setLayout(new GridLayout(1, 4));
        // add three labels
        add(playerInfo);
        add(playerPoints);
        add(houseStatus);
        add(sumStatus);
    }

    public void setCurrPlayer(Player player) {
        String info = String.format("[%s] %s", player.getPlayerId(), player.getPlayerName());
        playerInfo.setText(CURR_PLAYER + info);
    }

    public void setPoints(Player player) {
        playerPoints.setText(POINTS + player.getPoints());
    }

    public void setHousePoints(int points) {
        houseStatus.setText(HOUSE_POINTS + points);
    }
}
