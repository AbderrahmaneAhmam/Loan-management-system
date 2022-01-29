package views;

import controllers.LayoutController;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Layout extends JFrame {
    private final JMenuBar menu;
    private final JMenu aboutMenu,themeMenu;
    private final ArrayList<JMenuItem> itemsAbout = new ArrayList<JMenuItem>();
    private final ArrayList<JMenuItem> itemsTheme = new ArrayList<JMenuItem>();
    private final LayoutController controller = new LayoutController(this);
    public Layout() throws IOException {
        super("Loans Manager");
        menu = new JMenuBar();
        aboutMenu = new JMenu("About");
        themeMenu = new JMenu("Theme");
        itemsTheme.add(new JMenuItem("Dark"));
        itemsTheme.add(new JMenuItem("Light"));
        itemsAbout.add(new JMenuItem("About-as"));
        itemsAbout.add(new JMenuItem("Contact-as"));
        itemsAbout.add(new JMenuItem("Help"));
        initComponents();
    }
    private void initComponents(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setSize(700,500);
        this.setMinimumSize(new Dimension(700,500));
        for (var item :
                itemsTheme) {
            item.addActionListener(e -> controller.themeChange(((JMenuItem) e.getSource()).getText()));
            themeMenu.add(item);
        }
        for (var item :
                itemsAbout) {
            aboutMenu.add(item);
        }
        menu.add(aboutMenu);
        menu.add(themeMenu);
        this.setJMenuBar(menu);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab( "Main", new MainView());
        tabs.addTab( "Consultation", new ConsultationUsersView());
        tabs.addTab( "Return", new BackLoanView());
        this.add(tabs);
        this.setVisible(true);
    }
}