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
    private static final int DELAY_MILLS = 1000;
    private final RightToolbar toolbar;
    private final StatusBar statusBar;
    private final CardPanel housePanel;
    private final AppFrame appFrame;
    private final GameEngine gameEngine;
    private final DealListener dealListener;
    private final Map<Player, CardPanel> cardPanelMap;
    private final JSplitPane contentPane;
    private Thread dealPlayerThread;
    private Player currentPlayer;

    public MainGamePanel(AppFrame app) {
        cardPanelMap = new HashMap<>();
        cardPanelMap.put(null, new CardPanel());
        toolbar = new RightToolbar();
        dealListener = new DealListener(this);
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
    }

    public Thread getHouseThread() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                gameEngine.dealHouse(DELAY_MILLS);
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
                gameEngine.dealPlayer(currentPlayer, DELAY_MILLS);
                for (Player p : gameEngine.getAllPlayers()) {
                    if (p.getPoints() > 0 && p.getBet() <= 0) {
                        return;
                    }
                }
                getHouseThread().start();
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

    public void updateStatus() {
        //TODO:check change or not
        boolean canPlace = currentPlayer != null && currentPlayer.getPoints() > 0;
        boolean canDeal = canPlace && currentPlayer.getBet() > 0;
        toolbar.setCanPlaceBet(canPlace);
        toolbar.setCanDeal(canDeal);
        toolbar.setDealHouseEnabled(true);
        statusBar.updatePlayerStatus(currentPlayer);
    }
}
