package view.interfaces;

import model.interfaces.PlayingCard;

public interface CardObserver {
    void onCardChanged(PlayingCard card);
}
