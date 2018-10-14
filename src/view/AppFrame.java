package view;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import view.callback.GameEngineCallbackGUI;
import view.interfaces.GameEngineCallback;
import view.panels.MainGamePanel;
import view.panels.PlayerPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AppFrame extends JFrame {
    private final GameEngine gameEngine;
    private final PlayerPanel playerPanel;
    private final JSplitPane contentPane;
    private final MainGamePanel gamePanel;

    public AppFrame() throws HeadlessException {
        super("Card Game");
        gameEngine = new GameEngineImpl();
        gamePanel = new MainGamePanel(this);

        contentPane = new JSplitPane();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        playerPanel = new PlayerPanel(this);
        contentPane.setLeftComponent(playerPanel);
        contentPane.setRightComponent(gamePanel);
        addMenuBar();


        // finish setup
        pack();
        setMinimumSize(new Dimension(600, 400));
        // centered on screen
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        GameEngineCallback gameEngineCallback = new GameEngineCallbackGUI(this);
        gameEngine.addGameEngineCallback(gameEngineCallback);

        //TODO: for test
        gameEngine.addPlayer(new SimplePlayer("1", "John", 500));
        gameEngine.addPlayer(new SimplePlayer("2", "Tom", 1000));
    }

    private void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        file.add(exitItem);
        menuBar.add(file);
        setJMenuBar(menuBar);
    }

    public PlayerPanel getPlayerPanel() {
        return playerPanel;
    }

    public MainGamePanel getGamePanel() {
        return gamePanel;
    }

    @Deprecated
    public void setGamePanel(MainGamePanel gamePanel) {
        contentPane.setRightComponent(gamePanel);
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }
}
