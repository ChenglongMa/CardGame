package view.panels;

import view.AppFrame;
import view.bars.RightToolbar;
import view.bars.StatusBar;

import javax.swing.*;
import java.awt.*;

public class MainGamePanel extends JPanel {
    private final RightToolbar toolbar;
    private final StatusBar statusBar;
    private final CardPanel playerPanel;
    private final CardPanel housePanel;
    private final AppFrame appFrame;

    public MainGamePanel(AppFrame app) {
        toolbar = new RightToolbar();
        statusBar = new StatusBar();
        setLayout(new BorderLayout());
        setBorder(null);



        JSplitPane contentPane = new JSplitPane();
        contentPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

        housePanel = new CardPanel();
        playerPanel = new CardPanel();
        contentPane.setTopComponent(playerPanel);
        contentPane.setBottomComponent(housePanel);

        add(contentPane, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);
        add(toolbar, BorderLayout.NORTH);
        this.appFrame = app;
    }

}
