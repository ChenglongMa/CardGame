package utils;

import model.interfaces.PlayingCard;

import javax.swing.*;

public class CardFactory {
    private static final String FILE_PREFIX = "/cards/";
    private static final String FILE_SUFFIX = ".png";

    public static Icon getIcon(PlayingCard card) {
        String suit = card.getSuit().toString().toLowerCase();
        String value = card.getValue().toString().toLowerCase();
        String fileName = FILE_PREFIX + value + "_" + suit + FILE_SUFFIX;
        return new ImageIcon(CardFactory.class.getResource(fileName));
    }
}
