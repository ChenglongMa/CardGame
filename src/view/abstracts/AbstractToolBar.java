package view.abstracts;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractToolBar extends JToolBar {
    public AbstractToolBar() {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        setLayout(flowLayout);
        setFloatable(false);
    }

    public abstract void setButtonEnabled(boolean enabled);
}
