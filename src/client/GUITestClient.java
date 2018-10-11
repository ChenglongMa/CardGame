package client;

import view.AppFrame;

import javax.swing.*;

public class GUITestClient {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new AppFrame());
    }

    public class ToolBar extends JToolBar {

    }
}
