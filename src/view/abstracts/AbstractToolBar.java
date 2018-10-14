package view.abstracts;

import javax.swing.*;
import java.awt.*;

/**
 * Abstract tool bar for {@link view.bars.LeftToolbar} and {@link view.bars.RightToolbar}
 */
public abstract class AbstractToolBar extends JToolBar {
    public AbstractToolBar() {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        setLayout(flowLayout);
        setFloatable(false);
    }

}
