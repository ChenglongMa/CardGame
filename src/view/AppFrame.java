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

public class AppFrame extends JFrame {
    private final GameEngine gameEngine;
    private final GameEngineCallback gameEngineCallback;
    private JSplitPane contentPane;
    private PlayerPanel playerPanel;
    private MainGamePanel gamePanel;
    public AppFrame() throws HeadlessException {
        super("Card Game");
        gameEngine = new GameEngineImpl();
        gameEngineCallback = new GameEngineCallbackGUI();
        gameEngine.addGameEngineCallback(gameEngineCallback);

        contentPane = new JSplitPane();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        //TODO:tmp
        gameEngine.addPlayer(new SimplePlayer("1","abc",1000));

        playerPanel = new PlayerPanel(this);
        contentPane.setLeftComponent(playerPanel);

        gamePanel = new MainGamePanel(this);
        contentPane.setRightComponent(gamePanel);


        // finish setup
        pack();
        setMinimumSize(getSize());
        // centered on screen
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);

    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

}
