package controls;

import views.MainView;

import javax.swing.*;
import java.awt.*;

public class CustomDialog extends JDialog {
    public CustomDialog(JFrame source, String title, Component cmp,int width,int height) {
        super(source, title);
        this.setSize(width,height);
        this.setMinimumSize(new Dimension(width,height));
        this.setLocationRelativeTo(null);
        this.add(cmp);
        this.setVisible(true);
    }
}
