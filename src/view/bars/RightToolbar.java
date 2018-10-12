package view.bars;

import view.abstracts.AbstractToolBar;

import javax.swing.*;

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
        setButtonEnabled(false);
    }

    @Override
    public void setButtonEnabled(boolean enabled) {
        placeBetBtn.setEnabled(enabled);
        dealCardBtn.setEnabled(enabled);
        String tip = enabled ? null : "Please select players with enough points.";
        placeBetBtn.setToolTipText(tip);
        dealCardBtn.setToolTipText(tip);
    }
}
