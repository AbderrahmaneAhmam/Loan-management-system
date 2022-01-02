package views;

import controllers.ConsultationUsersController;
import controllers.MainController;
import controls.ButtonEditorTable;
import controls.ButtonRendererTable;
import controls.CustomChartPanel;
import controls.CustomDialog;
import managers.AppSDK;
import models.MaterialModel;
import models.UserModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ConsultationUsersView extends JPanel {
    private final ConsultationUsersController controller;
    private final JPanel globalPanel,topPanel,mainPanel,bottomPanel,topPanelDelays,searchPanel;
    private final JLabel labTitle;
    private final JTable table,tableDelays;
    private final JScrollPane scroller,scrollDelays;
    private final JButton tableBtn,btnSearch,btnSearchDelays;
    private final JTextField txtSearch,txtSearchDelays;
    public ConsultationUsersView(){
        controller = new ConsultationUsersController();
        table = new JTable();
        tableDelays = new JTable();
        tableBtn = new JButton("Log");
        scroller = new javax.swing.JScrollPane(table);
        scrollDelays = new javax.swing.JScrollPane(tableDelays);
        globalPanel = new JPanel();
        topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        mainPanel = new JPanel();
        topPanelDelays = new JPanel(new GridLayout());
        labTitle = new JLabel("Overdue loans");
        bottomPanel = new JPanel(new BorderLayout());
        btnSearch = new JButton("Search");
        btnSearchDelays = new JButton("Search");
        txtSearch = new JTextField("",20);
        txtSearchDelays = new JTextField("",20);
        initComponents();
    }
    private void initComponents(){
        globalPanel.setLayout(new BorderLayout());
        mainPanel.setLayout(new GridLayout(2,1));
        this.setLayout(new GridLayout(1,1));
        table.setModel(controller.getAccountsTableModel());
        tableDelays.setModel(controller.getDelaysTableModel());
        table.setAutoCreateRowSorter(true);
        tableDelays.setAutoCreateRowSorter(true);
        table.getColumn("").setCellRenderer(new ButtonRendererTable("Log"));
        table.getColumn("").setCellEditor(new ButtonEditorTable(new JCheckBox(),tableBtn));
        topPanel.add(txtSearch);
        topPanel.add(btnSearch);
        mainPanel.add(scroller);
        txtSearch.add(txtSearchDelays);
        txtSearch.add(btnSearchDelays);
        labTitle.setFont(new Font("", Font.PLAIN, 20));
        searchPanel.add(txtSearchDelays);
        searchPanel.add(btnSearchDelays);
        topPanelDelays.add(labTitle);
        topPanelDelays.add(searchPanel);
        bottomPanel.add(topPanelDelays,BorderLayout.PAGE_START);
        bottomPanel.add(scrollDelays,BorderLayout.CENTER);
        mainPanel.add(bottomPanel);
        globalPanel.add(topPanel,BorderLayout.PAGE_START);
        globalPanel.add(mainPanel,BorderLayout.CENTER);
        tableBtn.addActionListener((e)->{
            var row = ((DefaultTableModel)table.getModel()).getDataVector().elementAt(table.getSelectedRow());
            var user = new UserModel((int)row.get(0),row.get(1).toString(),row.get(2).toString(),row.get(3).toString());
            new CustomDialog((JFrame) SwingUtilities.getWindowAncestor(this),"Log loans",new HistoUserView(user),500,500);
        });
        AppSDK.UsersManager.addChangesListener(b->{
            SwingUtilities.updateComponentTreeUI(this);
            controller.refreshTableUsers(txtSearch.getText());
        });
        AppSDK.LoansManager.addChangesListener(b->{
            SwingUtilities.updateComponentTreeUI(this);
            controller.refreshTableLoans(txtSearch.getText());
        });
        btnSearch.addActionListener(e-> {SwingUtilities.updateComponentTreeUI(this);controller.refreshTableUsers(txtSearch.getText());});
        btnSearchDelays.addActionListener(e-> {SwingUtilities.updateComponentTreeUI(this);controller.refreshTableLoans(txtSearchDelays.getText());});
        this.add(globalPanel);
    }
}
