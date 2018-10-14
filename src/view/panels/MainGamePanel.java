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
import java.util.HashMap;
import java.util.Map;

public class MainGamePanel extends JPanel {
    private final RightToolbar toolbar;
    private final StatusBar statusBar;
    private final CardPanel housePanel;
    private final AppFrame appFrame;
    private final GameEngine gameEngine;
    private final DealListener dealListener;
    private final Map<Player, CardPanel> cardPanelMap;
    private final JSplitPane contentPane;
    private final Thread dealHouseThread;
    private Thread dealPlayerThread;
    private Player currentPlayer;

    public MainGamePanel(AppFrame app) {
        cardPanelMap = new HashMap<>();
        cardPanelMap.put(null, new CardPanel());
        dealListener = new DealListener(this);
        toolbar = new RightToolbar();
        toolbar.setBetListener(new BetListener(this));
        toolbar.setDealListener(dealListener);
        statusBar = new StatusBar();
        setLayout(new BorderLayout());
        setBorder(null);


        contentPane = new JSplitPane();
        contentPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        housePanel = new CardPanel();
        CardPanel playerPanel = new CardPanel();
        contentPane.setTopComponent(playerPanel);
        contentPane.setBottomComponent(housePanel);
        contentPane.setDividerLocation(0.5);
        contentPane.setResizeWeight(0.5);
        add(contentPane, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);
        add(toolbar, BorderLayout.NORTH);
        this.appFrame = app;
        gameEngine = app.getGameEngine();
        dealHouseThread = getHouseThread();
    }

    private Thread getHouseThread() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                gameEngine.dealHouse(1000);
            }
        });
    }

    public Thread getCurrentPlayerThread() {
        return dealPlayerThread;
    }

    public void switchPlayerPanel() {
        CardPanel currPanel = getPlayerPanel(currentPlayer);
        contentPane.setTopComponent(currPanel);
        dealPlayerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                gameEngine.dealPlayer(currentPlayer, 1000);//TODO: need more flexible
                for (Player p : gameEngine.getAllPlayers()) {
                    if (p.getPoints() > 0 && p.getBet() <= 0) {
                        return;
                    }
                }
                dealHouseThread.start();
            }
        });
    }

    public CardPanel getPlayerPanel(Player player) {
        if (!cardPanelMap.containsKey(player)) {
            CardPanel panel = new CardPanel();
            cardPanelMap.put(player, panel);
        }
        return cardPanelMap.get(player);
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
        //TODO:check change or not
        boolean canPlace = currentPlayer != null && currentPlayer.getPoints() > 0;
        boolean canDeal = canPlace && currentPlayer.getBet() > 0;
        toolbar.setCanPlaceBet(canPlace);
        toolbar.setCanDeal(canDeal);
        statusBar.updatePlayerStatus(currentPlayer);
    }

    public Thread getDealHouseThread() {
        return dealHouseThread;
    }
}
