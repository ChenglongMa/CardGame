package view;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.callback.GameEngineCallbackGUI;
import view.callback.GameEngineCallbackImpl;
import view.interfaces.GameEngineCallback;
import view.panels.MainGamePanel;
import view.panels.PlayerPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class AppFrame extends JFrame {
    private final GameEngine gameEngine;
    private final PlayerPanel playerPanel;
    private final MainGamePanel gamePanel;
    private final Map<Player, MainGamePanel> gamePanelMap;

    public AppFrame() throws HeadlessException {
        super("Card Game");
        gamePanelMap = new HashMap<>();
        gameEngine = new GameEngineImpl();
        gamePanel = new MainGamePanel(this);
        GameEngineCallback gameEngineCallback = new GameEngineCallbackGUI(gamePanel);
        gameEngine.addGameEngineCallback(gameEngineCallback);

        JSplitPane contentPane = new JSplitPane();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);


        playerPanel = new PlayerPanel(this);
        contentPane.setLeftComponent(playerPanel);


        contentPane.setRightComponent(gamePanel);


        // finish setup
        pack();
        setMinimumSize(getSize());
        // centered on screen
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        //TODO:tmp
        gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
        gameEngine.addPlayer(new SimplePlayer("1", "John", 500));
        gameEngine.addPlayer(new SimplePlayer("2", "Tom", 1000));
    }

    public PlayerPanel getPlayerPanel() {
        return playerPanel;
    }

    public MainGamePanel getGamePanel(Player player) {
        return gamePanelMap.get(player);
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

}
