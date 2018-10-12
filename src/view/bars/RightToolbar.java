package view.bars;

import view.abstracts.AbstractToolBar;

import javax.swing.*;
import java.awt.event.ActionListener;

public class RightToolbar extends AbstractToolBar {
    private final JButton placeBetBtn;
    private final JButton dealCardBtn;
    private final JButton stopBtn;

    public RightToolbar() {
        super();
        placeBetBtn = new JButton("Place Bet");//TODO:add listener
        add(placeBetBtn);
        dealCardBtn = new JButton("Deal Card");//todo:set enabled
        add(dealCardBtn);
        stopBtn = new JButton("Stop");//todo:set enabled
        stopBtn.setEnabled(false);
        add(stopBtn);
        setCanPlaceBet(false);
        setCanDeal(false);
    }

    public void setCanPlaceBet(boolean canPlaceBet) {
        placeBetBtn.setEnabled(canPlaceBet);
        String tip = canPlaceBet ? null : "Please select players with enough points.";
        placeBetBtn.setToolTipText(tip);

    }

    public void setCanDeal(boolean canDeal) {
        dealCardBtn.setEnabled(canDeal);
        String tip = canDeal ? null : "Please place your bet.";
        dealCardBtn.setToolTipText(tip);
    }

    public void setBetListener(ActionListener listener) {
        placeBetBtn.addActionListener(listener);
    }

    public void setDealListener(ActionListener listener) {
        dealCardBtn.addActionListener(listener);
    }
}
