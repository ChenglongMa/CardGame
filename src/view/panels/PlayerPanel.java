package view.panels;

import controller.listListener.AddPlayerListener;
import controller.listListener.DeletePlayerListener;
import controller.listListener.EditPlayerListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AppFrame;
import view.bars.LeftToolbar;
import view.bars.RightToolbar;
import view.bars.StatusBar;
import view.others.PlayerListCellRenderer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Collection;

public class PlayerPanel extends JPanel implements ListSelectionListener {
    //    private final List<Player> players;
    private final JList<Player> playerJList;
    private final LeftToolbar toolbar;
    private final AppFrame appFrame;
    private final GameEngine gameEngine;
    private Player selectedPlayer;
    private int selectedIndex = -1;
    private boolean toChange = true;
    private boolean changeIndex = true;

    public PlayerPanel(AppFrame appFrame) {
        setBorder(null);
        this.appFrame = appFrame;
        gameEngine = appFrame.getGameEngine();
        //Set layout
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, 1.0E-4};
        gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0E-4};
        setLayout(gridBagLayout);
        //Set toolbar
        toolbar = new LeftToolbar();
        toolbar.setAddPlayerListener(new AddPlayerListener(this));
        toolbar.setDeletePlayerListener(new DeletePlayerListener(this));
        toolbar.setEditPlayerListener(new EditPlayerListener(this));
        toolbar.setButtonEnabled(false);
        addToolBar(toolbar);
        //Set JList
        playerJList = new JList<>();
        playerJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playerJList.setCellRenderer(new PlayerListCellRenderer());
        playerJList.addListSelectionListener(this);
        addJList(playerJList);
        refreshListLater();
    }

    private void addJList(JList jList) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(jList, gbc);
    }

    private void addToolBar(JToolBar jToolBar) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(jToolBar, gbc);
    }

    public void refreshListAndWait() {
        changeIndex = false;
        Collection<Player> list = gameEngine.getAllPlayers();
        Player[] players = new Player[list.size()];
        list.toArray(players);
        playerJList.setListData(players);
        setSelectedIndex();
        changeIndex = true;
    }

    public void refreshListLater() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                refreshListAndWait();
            }
        });
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        MainGamePanel gamePanel = appFrame.getGamePanel();
        Thread currThread = gamePanel.getCurrentPlayerThread();
        if (e.getValueIsAdjusting()) {
            // Ask users if they want to switch to another player.
            // if there is a running thread.
            if (currThread != null && currThread.isAlive()) {
                String msg = "Current game would be interrupted,\nDo you want to continue?";
                int res = JOptionPane.showConfirmDialog(appFrame, msg);
                if (res == JOptionPane.YES_OPTION) {
                    currThread.interrupt();
                } else {
                    toChange = false;
                }
            }
            return;
        }
        if (!toChange) {
            return;
        }
        toChange = true;
        if (changeIndex) {
            selectedIndex = playerJList.getSelectedIndex();
        }
        changeIndex = true;
        selectedPlayer = playerJList.getSelectedValue();
        boolean hasSelected = selectedPlayer != null;
        gamePanel.setCurrentPlayer(selectedPlayer);
        gamePanel.switchPlayerPanel();
        toolbar.setButtonEnabled(hasSelected);
        StatusBar statusBar = gamePanel.getStatusBar();
        statusBar.updatePlayerStatus(selectedPlayer);
        RightToolbar rToolbar = gamePanel.getToolbar();
        hasSelected = hasSelected && selectedPlayer.getPoints() > 0;
        rToolbar.setCanPlaceBet(hasSelected);
        rToolbar.setCanDeal(hasSelected);
    }

    public void setSelectedIndex() {
        int size = playerJList.getModel().getSize();
        int index = selectedIndex < size ? selectedIndex : size - 1;
        playerJList.setSelectedIndex(index);
    }

    public Player getSelectedPlayer() {
        return selectedPlayer;
    }
}
