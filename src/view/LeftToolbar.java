package view;

import javax.swing.*;
import java.awt.*;

class LeftToolbar extends AbstractToolBar {
    private final JButton newPlayerBtn;
    private final JButton editPlayerBtn;
    private final JButton deletePlayerBtn;
    LeftToolbar() {
        super();
        newPlayerBtn = new JButton("New Player");//TODO:add listener
        add(newPlayerBtn);
        editPlayerBtn = new JButton("Edit Player");//todo:set enabled
        add(editPlayerBtn);
        deletePlayerBtn = new JButton("Delete Player");//todo:set enabled
        add(deletePlayerBtn);
    }
}
