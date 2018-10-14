package view.callback;

import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.panels.CardPanel;
import view.panels.MainGamePanel;

public class GameEngineCallbackGUI extends GameEngineCallbackGUITemplate {
    private final CardPanel housePanel;
    private final MainGamePanel gamePanel;
    private CardPanel playerPanel;


    public GameEngineCallbackGUI(MainGamePanel gamePanel) {
        this.gamePanel = gamePanel;
        housePanel = gamePanel.getHousePanel();
    }

    @Override
    void addPlayerCard(Player player, PlayingCard card) {
        playerPanel = gamePanel.getPlayerPanel(player);
        if (playerPanel == null) {
            return;
        }
        playerPanel.addCard(card);
    }

    @Override
    void updateFinalResult(int result) {
        gamePanel.getStatusBar().updateHouseResult(result);
        gamePanel.updatePlayerStatus();
        //todo:未完成
    }

    @Override
    void addHouseCard(PlayingCard card) {
        housePanel.addCard(card);
    }

    @Override
    void updatePlayerStatus() {
        gamePanel.updatePlayerStatus();
    }
}
