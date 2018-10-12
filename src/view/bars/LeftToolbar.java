package view.bars;

import view.AppFrame;
import view.abstracts.AbstractToolBar;
import view.dialogs.AddPlayerDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LeftToolbar extends AbstractToolBar {
    private final JButton newPlayerBtn;
    private final JButton editPlayerBtn;
    private final JButton deletePlayerBtn;
    private final AddPlayerDialog addPlayerDialog;

    public LeftToolbar(AppFrame appFrame) {
        super();
        newPlayerBtn = new JButton("New Player");//TODO:add listener
        add(newPlayerBtn);
        editPlayerBtn = new JButton("Edit Player");//todo:set enabled
        add(editPlayerBtn);
        deletePlayerBtn = new JButton("Delete Player");//todo:set enabled
        add(deletePlayerBtn);
        addPlayerDialog = new AddPlayerDialog(appFrame);
        newPlayerBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPlayerDialog.setVisible(true);
            }
        });
    }
}
