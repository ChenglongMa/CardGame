package view.dialogs;

import view.AppFrame;

import javax.swing.*;

public class ResultDialog extends JDialog {
    private final AppFrame appFrame;

    public ResultDialog(AppFrame appFrame) {
        super(appFrame, "Game Result!", true);
        this.appFrame = appFrame;
    }
}
