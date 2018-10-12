package view.bars;

import controller.listListener.AddPlayerListener;
import controller.listListener.DeletePlayerListener;
import controller.listListener.EditPlayerListener;
import view.abstracts.AbstractToolBar;

import javax.swing.*;

public class LeftToolbar extends AbstractToolBar {
    private final JButton newPlayerBtn;
    private final JButton editPlayerBtn;
    private final JButton deletePlayerBtn;

    public LeftToolbar() {
        super();
        newPlayerBtn = new JButton("New Player");//TODO:add listener
        add(newPlayerBtn);
        editPlayerBtn = new JButton("Edit Player");//todo:set enabled
        add(editPlayerBtn);
        deletePlayerBtn = new JButton("Delete Player");//todo:set enabled
        add(deletePlayerBtn);
    }

    public void setAddPlayerListener(AddPlayerListener listener) {
        newPlayerBtn.addActionListener(listener);
    }

    public void setDeletePlayerListener(DeletePlayerListener listener) {
        deletePlayerBtn.addActionListener(listener);
    }

    public void setEditPlayerListener(EditPlayerListener listener) {
        editPlayerBtn.addActionListener(listener);
    }

    public void setButtonEnabled(boolean enabled) {
        editPlayerBtn.setEnabled(enabled);
        deletePlayerBtn.setEnabled(enabled);
    }
}
