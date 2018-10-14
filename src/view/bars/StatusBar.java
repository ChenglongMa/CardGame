package view.bars;

import model.interfaces.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class StatusBar extends JPanel {
    private static final String CURR_PLAYER = "Player: ";
    private static final String BETS = "Bets: ";
    private static final String RESULT = "Player Result: ";
    private static final String HOUSE_RESULT = "House Result: ";

    private JLabel playerInfo;
    private JLabel playerBets;
    private JLabel playerResult;
    private JLabel houseResult;

    public StatusBar() {
        playerInfo = new JLabel(CURR_PLAYER, SwingConstants.LEFT);
        playerBets = new JLabel(BETS, SwingConstants.LEFT);
        playerResult = new JLabel(RESULT, SwingConstants.LEFT);
        houseResult = new JLabel(HOUSE_RESULT, SwingConstants.LEFT);
        Border border = BorderFactory.createRaisedBevelBorder();
        playerInfo.setBorder(border);
        playerBets.setBorder(border);
        playerResult.setBorder(border);
        houseResult.setBorder(border);

        setLayout(new GridLayout(1, 4));
        // add labels
        add(playerInfo);
        add(playerBets);
        add(playerResult);
        add(houseResult);

    }

    /**
     * Set the text on {@link StatusBar}
     * @param player
     */
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

    public void updateHouseResult(int result) {
        houseResult.setText(HOUSE_RESULT + result);
    }
}
