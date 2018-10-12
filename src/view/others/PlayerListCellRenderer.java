package view.others;

import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerListCellRenderer extends JLabel implements ListCellRenderer<Player> {
    private final Color defaultBackground;

    public PlayerListCellRenderer() {
        defaultBackground = getBackground();
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Player> list, Player value, int index, boolean isSelected, boolean cellHasFocus) {
        String text = String.format("[%s] %s: Points:%d", value.getPlayerId(), value.getPlayerName(), value.getPoints());
        setText(text);
        setBackground(isSelected ? Color.CYAN : defaultBackground);
        return this;
    }
}
