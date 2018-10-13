package view.panels;

import controller.buttonListener.BetListener;
import controller.buttonListener.DealListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;
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
    private final GameEngine gameEngine;
    private Player currentPlayer;

    public MainGamePanel(AppFrame app) {
        toolbar = new RightToolbar();
        toolbar.setBetListener(new BetListener(this));
        toolbar.setDealListener(new DealListener(this));
        statusBar = new StatusBar();
        setLayout(new BorderLayout());
        setBorder(null);


        JSplitPane contentPane = new JSplitPane();
        contentPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        housePanel = new CardPanel();
        playerPanel = new CardPanel();
        contentPane.setTopComponent(playerPanel);
        contentPane.setBottomComponent(housePanel);
        contentPane.setDividerLocation(0.5);
        contentPane.setResizeWeight(0.5);
        add(contentPane, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);
        add(toolbar, BorderLayout.NORTH);
        this.appFrame = app;
        gameEngine = app.getGameEngine();
    }

    public CardPanel getPlayerPanel() {
        return playerPanel;
    }

    public CardPanel getHousePanel() {
        return housePanel;
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public RightToolbar getToolbar() {
        return toolbar;
    }

    public StatusBar getStatusBar() {
        return statusBar;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void updatePlayerStatus() {
        boolean canPlace = currentPlayer != null && currentPlayer.getPoints() > 0;
        boolean canDeal = canPlace && currentPlayer.getBet() > 0;
        toolbar.setCanPlaceBet(canPlace);
        toolbar.setCanDeal(canDeal);
        statusBar.updatePlayerStatus(currentPlayer);
    }
}
