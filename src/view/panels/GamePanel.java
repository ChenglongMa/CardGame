package view.panels;

import view.bars.RightToolbar;
import view.bars.StatusBar;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final RightToolbar toolbar;
    private final StatusBar statusBar;
    private final CardPanel playerPanel;
    private final CardPanel housePanel;

    public GamePanel() {
        toolbar = new RightToolbar();
        statusBar = new StatusBar();
        setLayout(new BorderLayout());
        setBorder(null);

        add(statusBar, BorderLayout.SOUTH);
        add(toolbar, BorderLayout.NORTH);

        JSplitPane contentPane = new JSplitPane();
        contentPane.setOrientation(JSplitPane.VERTICAL_SPLIT);


        housePanel = new CardPanel();
        playerPanel = new CardPanel();
        contentPane.setTopComponent(playerPanel);
        contentPane.setBottomComponent(housePanel);

    }

}
