package view.interfaces;

import model.interfaces.Player;

public interface PlayerObserver {
    void onPlayerChanged(Player player);
}
