package view.callback;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {
    @Override
    public void nextCard(Player player, PlayingCard card, GameEngine engine) {

    }

    @Override
    public void bustCard(Player player, PlayingCard card, GameEngine engine) {

    }

    @Override
    public void result(Player player, int result, GameEngine engine) {

    }

    @Override
    public void nextHouseCard(PlayingCard card, GameEngine engine) {

    }

    @Override
    public void houseBustCard(PlayingCard card, GameEngine engine) {

    }

    @Override
    public void houseResult(int result, GameEngine engine) {

    }
}
