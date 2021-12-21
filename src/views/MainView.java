package views;

import javax.swing.*;

public class MainView extends JPanel {
    JLabel label;

    public MainView() {
        this.label = new JLabel();
        label.setText("Hello");
        this.add(label);
        this.setVisible(true);
    }
}
