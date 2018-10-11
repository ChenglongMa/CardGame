package view;

import javax.swing.*;
import java.awt.*;

abstract class AbstractToolBar extends JToolBar {
    AbstractToolBar() {
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        setLayout(flowLayout);
        setFloatable(false);
    }
}
