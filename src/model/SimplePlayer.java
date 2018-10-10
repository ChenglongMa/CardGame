package model;

import model.interfaces.Player;

import java.util.Objects;

/**
 * The implementation of {@link Player}
 *
 * @author Chenglong Ma
 * @see Player
 */
public class SimplePlayer implements Player {
    private String playerId;
    private String playerName;
    private int points;
    private int bet;
    private int result;

    public SimplePlayer(String playerId, String playerName, int initialPoints) {
        if (playerId == null) {
            throw new IllegalArgumentException("playerId cannot be null.");
        }
        if (initialPoints < 0) {
            throw new IllegalArgumentException("Points cannot be less than 0.");
        }
        this.playerId = playerId;
        this.playerName = playerName;
        this.points = initialPoints;
        bet = 0;
        result = 0;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public void setPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Points cannot be less than 0.");
        }
        this.points = points;
    }

    @Override
    public String getPlayerId() {
        return playerId;
    }

    @Override
    public boolean placeBet(int bet) {
        if (bet >= 0 && bet <= points) {
            this.bet = bet;
            return true;
        }
        return false;
    }

    @Override
    public int getBet() {
        return bet;
    }

    @Override
    public void resetBet() {
        bet = 0;
    }

    @Override
    public int getResult() {
        return result;
    }

    @Override
    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SimplePlayer)) {
            return super.equals(obj);
        }

        SimplePlayer other = (SimplePlayer) obj;
        return playerId.equals(other.playerId);
    }

    @Override
    public String toString() {
        return String.format("Player: id=%s, name=%s, points=%d", playerId, playerName, points);
    }
}
