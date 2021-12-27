package controls;

import views.MainView;

import javax.swing.*;
import java.awt.*;

public class CustomDialog extends JDialog {
    public CustomDialog(JFrame source, String title, Component cmp) {
        super(source, title);
        this.setSize(500,450);
        this.setLocationRelativeTo(null);
        this.add(cmp);
        this.setVisible(true);
    }
}
