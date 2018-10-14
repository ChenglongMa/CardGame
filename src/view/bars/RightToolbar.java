package view.bars;

import view.abstracts.AbstractToolBar;

import javax.swing.*;
import java.awt.event.ActionListener;

public class RightToolbar extends AbstractToolBar {
    public static final String DEAL_PLAYER_COMMAND = "Deal Player";
    public static final String DEAL_HOUSE_COMMAND = "Deal House";
    private final JButton placeBetBtn;
    private final JButton dealPlayer;
    private final JButton dealHouse;

    public RightToolbar() {
        super();
        placeBetBtn = new JButton("Place Bet");
        add(placeBetBtn);
        dealPlayer = new JButton("Deal Player");
        dealPlayer.setActionCommand(DEAL_PLAYER_COMMAND);
        add(dealPlayer);
        dealHouse = new JButton("Deal House");
        dealHouse.setActionCommand(DEAL_HOUSE_COMMAND);
        add(dealHouse);
        setCanPlaceBet(false);
        setCanDeal(false);
    }

    public void setCanPlaceBet(boolean canPlaceBet) {
        placeBetBtn.setEnabled(canPlaceBet);
        String tip = canPlaceBet ? null : "Please select players with enough points.";
        placeBetBtn.setToolTipText(tip);

    }

    public void setDealHouseEnabled(boolean canDeal) {
        dealHouse.setEnabled(canDeal);
    }

    public void setCanDeal(boolean canDeal) {
        dealPlayer.setEnabled(canDeal);
        String tip = canDeal ? null : "Please place your bet.";
        dealPlayer.setToolTipText(tip);
    }

    public void setBetListener(ActionListener listener) {
        placeBetBtn.addActionListener(listener);
    }

    public void setDealListener(ActionListener listener) {
        dealPlayer.addActionListener(listener);
        dealHouse.addActionListener(listener);
    }
}
