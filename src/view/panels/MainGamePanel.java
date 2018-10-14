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
    private final GameEngine gameEngine;
    private final Map<Player, CardPanel> cardPanelMap;
    private final JSplitPane contentPane;
    private Thread dealPlayerThread;
    private Player currentPlayer;

    public MainGamePanel(AppFrame app) {
        cardPanelMap = new HashMap<>();
        cardPanelMap.put(null, new CardPanel());
        toolbar = new RightToolbar();
        toolbar.setBetListener(new BetListener(this));
        toolbar.setDealListener(new DealListener(this));
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
        gameEngine = app.getGameEngine();
    }

    public Thread getHouseThread() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    gameEngine.dealHouse(DELAY_MILLS);
                } catch (Exception e) {
                    //ignore - just stop animating
                }
            }
        });
    }

    public Thread getCurrentPlayerThread() {
        return dealPlayerThread;
    }

    void switchPlayerPanel() {
        CardPanel currPanel = getPlayerPanel(currentPlayer);
        contentPane.setTopComponent(currPanel);
        dealPlayerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    gameEngine.dealPlayer(currentPlayer, DELAY_MILLS);
                } catch (Exception e) {
                    //ignore - just stop animating
                }
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

    void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void updateStatus() {
        boolean canPlace = currentPlayer != null && currentPlayer.getPoints() > 0;
        boolean canDeal = canPlace && currentPlayer.getBet() > 0;
        toolbar.setCanPlaceBet(canPlace);
        toolbar.setCanDeal(canDeal);
        toolbar.setDealHouseEnabled(true);
        statusBar.updatePlayerStatus(currentPlayer);
    }
}
