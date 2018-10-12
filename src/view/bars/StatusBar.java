package view.bars;

import model.interfaces.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class StatusBar extends JPanel {
    private static final String CURR_PLAYER = "Player: ";
    private static final String BETS = "Bets: ";
    private static final String RESULT = "Result: ";
    private static final String HOUSE_POINTS = "House Points: ";

    private JLabel playerInfo;
    private JLabel playerBets;
    private JLabel playerResult;
    private JLabel houseStatus;
    private JLabel sumStatus;

    public StatusBar() {
        playerInfo = new JLabel(CURR_PLAYER, SwingConstants.LEFT);
        playerBets = new JLabel(BETS, SwingConstants.LEFT);
        playerResult = new JLabel(RESULT, SwingConstants.LEFT);
        houseStatus = new JLabel(HOUSE_POINTS, SwingConstants.LEFT);
        sumStatus = new JLabel("Ready", SwingConstants.RIGHT);
        Border border = BorderFactory.createRaisedBevelBorder();
        playerInfo.setBorder(border);
        playerBets.setBorder(border);
        playerResult.setBorder(border);
        houseStatus.setBorder(border);
        sumStatus.setBorder(border);

        setLayout(new GridLayout(1, 5));
        // add three labels
        add(playerInfo);
        add(playerBets);
        add(playerResult);
        add(houseStatus);
        add(sumStatus);
    }

    public void updatePlayerStatus(Player player) {
        String info = CURR_PLAYER;
        String bets = BETS;
        String result = RESULT;
        if (player != null) {
            info = player.toString();
            bets += player.getBet();
            result += player.getResult();
        }
        playerInfo.setText(info);
        playerBets.setText(bets);
        playerResult.setText(result);
    }

    public void updateHouseStatus(int points) {
        houseStatus.setText(HOUSE_POINTS + points);
    }
}
