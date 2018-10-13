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
        initLocation();
        setOpaque(true);
        //TODO
//        Container parent = getParent();
//        setPreferredSize(new Dimension(parent.getWidth(), parent.getHeight() / 2));
    }

    private void initLocation() {
        location = new Point(10, 10);
        constraints = 0;
    }

    public void addCard(PlayingCard card) {
        JLabel c = new JLabel(CardFactory.getIcon(card));
        c.setOpaque(true);
        c.setBorder(BorderFactory.createLineBorder(Color.black));
        c.setBackground(Color.WHITE);
        c.setBounds(location.x, location.y, 222, 323);
        offset(c.getWidth() / 3, 10);
        add(c, constraints++);
    }

    public void clearCard() {
        initLocation();
        removeAll();
        repaint();
    }

    private void offset(int off_x, int off_y) {
        int x = location.x + off_x;
        int y = location.y + off_y;
        location = new Point(x, y);
    }
}
