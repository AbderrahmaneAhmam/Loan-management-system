package views;

import controllers.HistoUserController;
import models.UserModel;
import javax.swing.*;
import java.awt.*;

public class HistoUserView extends JPanel {
    private final JTable table;
    private final HistoUserController controller;
    private final JScrollPane scroll;
    public HistoUserView(UserModel user) {
        table = new JTable();
        scroll = new JScrollPane(table);
        controller = new HistoUserController(user);
        initComponents();
    }
    private void initComponents() {
        this.setLayout(new GridLayout());
        table.setModel(controller.getAccountsTableModel());
        this.add(scroll);
    }
}
