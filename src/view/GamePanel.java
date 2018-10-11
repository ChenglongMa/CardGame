package view;

import javax.swing.*;
import java.awt.*;

class GamePanel extends JPanel {
    private final RightToolbar toolbar;
    private final StatusBar statusBar;

    GamePanel() {
        toolbar = new RightToolbar();
        statusBar = new StatusBar();

        setLayout(new BorderLayout());
        add(statusBar, BorderLayout.SOUTH);
        add(toolbar, BorderLayout.NORTH);

    }
}
