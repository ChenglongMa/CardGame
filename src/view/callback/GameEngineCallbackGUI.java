package view.callback;

import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.AppFrame;
import view.panels.CardPanel;
import view.panels.MainGamePanel;
import view.panels.PlayerPanel;

public class GameEngineCallbackGUI extends GameEngineCallbackGUITemplate {
    private final CardPanel housePanel;
    private final MainGamePanel gamePanel;
    private final PlayerPanel playerPanel;


    public GameEngineCallbackGUI(AppFrame appFrame) {
        this.gamePanel = appFrame.getGamePanel();
        housePanel = gamePanel.getHousePanel();
        playerPanel = appFrame.getPlayerPanel();
    }

    @Override
    void addPlayerCard(Player player, PlayingCard card) {
        CardPanel playerPanel = gamePanel.getPlayerPanel(player);
        if (playerPanel == null) {
            return;
        }
        playerPanel.addCard(card);
    }

    @Override
    void updateFinalResult(int result) {
        gamePanel.getStatusBar().updateHouseResult(result);
        gamePanel.updateStatus();
        playerPanel.refreshListAndWait();
        //todo:未完成
    }

    @Override
    void addHouseCard(PlayingCard card) {
        housePanel.addCard(card);
    }

    @Override
    void updatePlayerStatus() {
        gamePanel.updateStatus();
    }
}
