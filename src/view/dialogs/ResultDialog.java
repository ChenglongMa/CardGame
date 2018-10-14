package view.dialogs;

import model.interfaces.Player;
import view.AppFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.Iterator;

public class ResultDialog extends JDialog {
    private final AppFrame appFrame;
    private final JList<String> playerJList;

    public ResultDialog(AppFrame appFrame) {
        super(appFrame, "Game Result!", true);
        this.appFrame = appFrame;
        setResizable(false);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        addTitle();
        add(Box.createVerticalStrut(20));
        playerJList = new JList<>();
        playerJList.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));

        add(playerJList);
        setSize(600, 480);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(appFrame);

    }

    private void addTitle() {
        JLabel title = new JLabel("Game Result");
        title.setFont(new Font(Font.SERIF, Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);

    }

    public void updateResult(String houseResult) {
        Collection<Player> list = appFrame.getGameEngine().getAllPlayers();
        int length = list.size();
        Iterator iterator = list.iterator();
        String[] content = new String[length + 1];
        for (int i = 0; i < length; i++) {
            content[i] = iterator.next().toString();
        }
        content[length] = houseResult;
        playerJList.setListData(content);
    }
}
