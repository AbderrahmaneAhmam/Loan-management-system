
package views;

import controllers.LayoutController;

import javax.swing.*;
import java.util.ArrayList;


public class Layout extends JFrame {
    public JPanel mainPanel;
    JMenuBar menu;
    JMenu menuUsers;
    JMenu menuMaterials;
    JMenu menuLoans;
    JMenu themeMenu;
    private final ArrayList<JMenuItem> itemsMaterials = new ArrayList<JMenuItem>();
    private final ArrayList<JMenuItem> itemsUsers = new ArrayList<JMenuItem>();
    private final ArrayList<JMenuItem> itemsLoans = new ArrayList<JMenuItem>();
    private final ArrayList<JMenuItem> itemstheme = new ArrayList<JMenuItem>();
    private final LayoutController controller = new LayoutController(this);
    public Layout() {
        super("Gestion Emprunts");
        mainPanel = new JPanel();
        menu = new JMenuBar();
        menuUsers = new JMenu("Utilisateurs");
        menuMaterials = new JMenu("Matériels");
        menuLoans = new JMenu("Emprunts");
        themeMenu = new JMenu("Thèmes");
        itemstheme.add(new JMenuItem("Dark"));
        itemstheme.add(new JMenuItem("Light"));
        itemsMaterials.add(new JMenuItem("Add"));
        initComponents();
    }
    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        for (var item :
                itemstheme) {
            item.addActionListener(e->controller.themeChange(((JMenuItem)e.getSource()).getText()));
            themeMenu.add(item);
        }
        for (var item :
                itemsMaterials) {
            item.addActionListener(e->controller.mainChange("main"));
            menuMaterials.add(item);
        }
        menu.add(menuUsers);
        menu.add(menuMaterials);
        menu.add(menuLoans);
        menu.add(themeMenu);
        this.setJMenuBar(menu);
        this.add(mainPanel);
        this.setVisible(true);
    }
}
