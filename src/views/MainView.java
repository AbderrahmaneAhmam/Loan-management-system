package views;

import controllers.MainController;
import controls.ButtonEditorTable;
import controls.ButtonRendererTable;
import controls.CustomChartPanel;
import controls.CustomDialog;
import managers.AppSDK;
import models.MaterialModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainView extends JPanel {
    private final MainController controller;
    private final JPanel globalPanel,topPanel,mainPanel,searchPanel,btnsPanel,graphPanel;
    private final JTable table;
    private final JScrollPane scroller;
    private final JButton tableBtn,btnSearch,btnAddUser;
    private final JTextField txtSearch;
    public MainView(){
        controller = new MainController();
        table = new JTable();
        tableBtn = new JButton("Loan");
        scroller = new javax.swing.JScrollPane(table);
        globalPanel = new JPanel();
        graphPanel = new JPanel();
        topPanel = new JPanel();
        mainPanel = new JPanel();
        btnSearch = new JButton("Search");
        txtSearch = new JTextField("",20);
        searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnAddUser = new JButton("Add user");
        initComponents();
    }
    private void initComponents(){
        globalPanel.setLayout(new BorderLayout());
        topPanel.setLayout(new GridLayout(1,2));
        mainPanel.setLayout(new GridLayout(2,1));
        graphPanel.setLayout(new GridLayout(1,2));
        table.setAutoCreateRowSorter(true);
        this.setLayout(new GridLayout(1,1));
        table.setModel(controller.getAccountsTableModel());
        table.getColumn("").setCellRenderer(new ButtonRendererTable("Loan"));
        table.getColumn("").setCellEditor(new ButtonEditorTable(new JCheckBox(),tableBtn));
        btnsPanel.add(btnAddUser);
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        graphPanel.add(new CustomChartPanel(controller.Graph1(),true));
        graphPanel.add(new CustomChartPanel(controller.Graph2(),true));
        topPanel.add(btnsPanel);
        topPanel.add(searchPanel);
        mainPanel.add(scroller);
        mainPanel.add(graphPanel);
        globalPanel.add(topPanel,BorderLayout.PAGE_START);
        globalPanel.add(mainPanel,BorderLayout.CENTER);

        tableBtn.addActionListener((e)->{
            var row = ((DefaultTableModel)table.getModel()).getDataVector().elementAt(table.getSelectedRow());
            var material = new MaterialModel((int)row.get(0),row.get(1).toString(),"");
            var view = new AddLoanView(material);
            var d = new CustomDialog((JFrame) SwingUtilities.getWindowAncestor(this),"Add loan",view,500,200);
        });
        AppSDK.LoansManager.addChangesListener(b->{
            SwingUtilities.updateComponentTreeUI(this);
            controller.refreshTable(txtSearch.getText());
            graphPanel.removeAll();
            graphPanel.add(new CustomChartPanel(controller.Graph1(),true));
            graphPanel.add(new CustomChartPanel(controller.Graph2(),true));
        });
        AppSDK.MaterialsManager.addChangesListener(b->{
            SwingUtilities.updateComponentTreeUI(this);
            controller.refreshTable(txtSearch.getText());
            graphPanel.removeAll();
            graphPanel.add(new CustomChartPanel(controller.Graph1(),true));
            graphPanel.add(new CustomChartPanel(controller.Graph2(),true));
        });
        btnSearch.addActionListener(e-> controller.refreshTable(txtSearch.getText()));
        btnAddUser.addActionListener(e->{
            var d = new CustomDialog((JFrame) SwingUtilities.getWindowAncestor(this),"Add user",new AddUserView(),500,200);
        });
        this.add(globalPanel);
    }
}
