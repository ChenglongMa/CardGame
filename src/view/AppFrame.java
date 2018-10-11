package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class AppFrame extends JFrame {
    private StatusBar status;
//    private Toolbar toolbar;
    private JSplitPane contentPane;
    private PlayerPanel playerPanel;
    private GamePanel gamePanel;

    public AppFrame() throws HeadlessException {
        super("Card Game");

        contentPane = new JSplitPane();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        playerPanel = new PlayerPanel(new ArrayList<>());
        contentPane.setLeftComponent(playerPanel);

        gamePanel = new GamePanel();
        contentPane.setRightComponent(gamePanel);

        // finish setup
        pack();
        setMinimumSize(getSize());
        // centered on screen
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
