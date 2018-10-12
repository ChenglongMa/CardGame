package view.panels;

import model.interfaces.PlayingCard;
import utils.CardFactory;

import javax.swing.*;
import java.awt.*;

/**
 * To display the cards
 */
public class CardPanel extends JLayeredPane {

    private Integer constraints;
    private Point location;

    public CardPanel() {
        location = new Point(10, 10);
        constraints = 0;
        setOpaque(true);
        //TODO
        setPreferredSize(new Dimension(300, 310));
    }

    public void addCard(PlayingCard card) {
        JLabel c = new JLabel(CardFactory.getIcon(card));
        c.setOpaque(true);
        c.setBorder(BorderFactory.createLineBorder(Color.black));
        c.setBounds(location.x, location.y, 222, 323);
        offset(c.getWidth() / 3, 10);
        add(c, constraints++);
    }

    private void offset(int off_x, int off_y) {
        int x = location.x + off_x;
        int y = location.y + off_y;
        location = new Point(x, y);
    }
}
