
package views;

import javax.swing.*;
import java.awt.*;


public class Layout extends JFrame {
    JPanel globalPanel;
    JPanel leftPanel;
    JButton btn1;
    JButton btn2;
    JButton btn3;

    public Layout() {
        super("Gestion Emprunts");
        globalPanel = new JPanel();
        leftPanel = new JPanel();
        btn1=new JButton("Materials");
        btn2=new JButton("Users");
        btn3=new JButton("Emprunts");
        initComponents();
    }
    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        globalPanel.setLayout(new BorderLayout());
        leftPanel.setLayout(new GridLayout(3,1));
        btn1.setSize(200,100);
        leftPanel.add(btn1);
        leftPanel.add(btn2);
        leftPanel.add(btn3);
        globalPanel.add(leftPanel,BorderLayout.LINE_START);
        this.add(globalPanel);
        this.setVisible(true);
    }
}
