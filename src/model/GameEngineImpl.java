package model;

import model.interfaces.CallbackStrategy;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

import java.util.*;

import static model.interfaces.PlayingCard.Suit;
import static model.interfaces.PlayingCard.Value;

/**
 * The implementation of {@link GameEngine}
 *
 * @author Chenglong Ma
 * @see GameEngine
 */
public class GameEngineImpl implements GameEngine {
    private final Collection<Player> players;
    private final Collection<GameEngineCallback> gameEngineCallbacks;
    private Deque<PlayingCard> deck;

    public GameEngineImpl() {
        players = new ArrayList<>();
        gameEngineCallbacks = new ArrayList<>();
        deck = getShuffledDeck();
    }

    @Override
    public void dealPlayer(Player player, int delay) {
        dealImpl(new PlayerCallbackStrategy(player, this), delay);
    }

    @Override
    public void dealHouse(int delay) {
        dealImpl(new HouseCallbackStrategy(this), delay);
    }

    @Override
    public void addPlayer(Player player) {
        Player p = getPlayer(player.getPlayerId());
        if (p != null) {
            removePlayer(p);
        }
        players.add(player);
    }

    @Override
    public Player getPlayer(String id) {
        for (Player player : players) {
            if (Objects.equals(player.getPlayerId(), id)) {
                return player;
            }
        }
        return null;
    }

    @Override
    public boolean removePlayer(Player player) {
        return players.remove(player);
    }

    @Override
    public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
        gameEngineCallbacks.add(gameEngineCallback);
    }

    @Override
    public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
        return gameEngineCallbacks.remove(gameEngineCallback);
    }

    @Override
    public Collection<Player> getAllPlayers() {
        return Collections.unmodifiableCollection(players);
    }

    @Override
    public boolean placeBet(Player player, int bet) {
        return player.placeBet(bet);
    }

    @Override
    public Deque<PlayingCard> getShuffledDeck() {
        List<PlayingCard> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                cards.add(new PlayingCardImpl(suit, value));
            }
        }
        Collections.shuffle(cards);
        return new ArrayDeque<>(cards);
    }

    /**
     * Deal cards with different strategies.
     *
     * @param callbackStrategy the strategy for logging information
     * @param delay            the delay between cards being dealt
     */
    private void dealImpl(CallbackStrategy callbackStrategy, int delay) {
        PlayingCard card = nextCard();
        int res = 0;
        while (res + card.getScore() <= BUST_LEVEL) {
            try {
                callbackStrategy.nextCard(card);
                res += card.getScore();
                card = nextCard();
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        callbackStrategy.bustCard(card);
        callbackStrategy.result(res);
    }

    /**
     * Returns a available {@link PlayingCard} from the {@link #deck}
     * <p>
     * A {@link IllegalArgumentException} will be thrown when deck was empty
     * </p>
     *
     * @return a available card
     */
    private PlayingCard nextCard() {
        if (deck.isEmpty()) {
            deck = getShuffledDeck();
        }
        return deck.poll();
    }

    /**
     * All players settle with the House and reset their bets.
     * <p>
     * PS: The bet will be returned to the player if it's a tie.
     * </p>
     *
     * @param houseResult the result of the House
     * @see Player#resetBet()
     */
    private void updateAllPlayers(int houseResult) {
        for (Player player : players) {
            int result = player.getResult();
            int points = player.getPoints();
            int bet = player.getBet();
            if (result < houseResult) {
                player.setPoints(points - bet);
            } else if (result > houseResult) {
                player.setPoints(points + bet);
            }
            player.resetBet();
        }
    }

    /**
     * The logging strategy for Player
     */
    private class PlayerCallbackStrategy implements CallbackStrategy {
        private final Player player;
        private final GameEngine gameEngine;

        private PlayerCallbackStrategy(Player player, GameEngine gameEngine) {
            this.player = player;
            this.gameEngine = gameEngine;
        }


        @Override
        public void nextCard(PlayingCard card) {
            for (GameEngineCallback engineCallback : gameEngineCallbacks) {
                engineCallback.nextCard(player, card, gameEngine);
            }
        }

        @Override
        public void bustCard(PlayingCard card) {
            for (GameEngineCallback engineCallback : gameEngineCallbacks) {
                engineCallback.bustCard(player, card, gameEngine);
            }
        }


        @Override
        public void result(int result) {
            player.setResult(result);
            for (GameEngineCallback engineCallback : gameEngineCallbacks) {
                engineCallback.result(player, result, gameEngine);
            }
        }
    }

    /**
     * The logging strategy for House
     */
    private class HouseCallbackStrategy implements CallbackStrategy {

        private final GameEngine gameEngine;

        private HouseCallbackStrategy(GameEngine gameEngine) {
            this.gameEngine = gameEngine;
        }

        @Override
        public void nextCard(PlayingCard card) {
            for (GameEngineCallback engineCallback : gameEngineCallbacks) {
                engineCallback.nextHouseCard(card, gameEngine);
            }
        }


        @Override
        public void bustCard(PlayingCard card) {
            for (GameEngineCallback engineCallback : gameEngineCallbacks) {
                engineCallback.houseBustCard(card, gameEngine);
            }
        }

        @Override
        public void result(int result) {
            updateAllPlayers(result);
            for (GameEngineCallback engineCallback : gameEngineCallbacks) {
                engineCallback.houseResult(result, gameEngine);
            }
        }
    }
}
