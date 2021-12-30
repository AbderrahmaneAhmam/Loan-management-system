package views;

import controllers.MainController;
import controls.ButtonEditorTable;
import controls.ButtonRendererTable;
import controls.CustomDialog;
import models.MaterialModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ConsultationUsersView extends JPanel {
    private final MainController controller;
    private final JPanel globalPanel,topPanel,mainPanel,searchPanel;
    private final JTable table;
    private final JScrollPane scroller;
    private final JButton tableBtn,btnSearch,btnAddUser;
    private final JTextField txtSearch;
    public ConsultationUsersView(){
        controller = new MainController();
        table = new JTable();
        tableBtn = new JButton("Loan");
        scroller = new javax.swing.JScrollPane(table);
        globalPanel = new JPanel();
        topPanel = new JPanel();
        searchPanel = new JPanel();
        mainPanel = new JPanel();
        btnSearch = new JButton("Search");
        txtSearch = new JTextField("",20);
        btnAddUser = new JButton("Add user");
        initComponents();
    }
    private void initComponents(){
        globalPanel.setLayout(new BorderLayout());
        topPanel.setLayout(new GridLayout(1,1));
        mainPanel.setLayout(new GridLayout(2,1));
        table.setAutoCreateRowSorter(true);
        this.setLayout(new GridLayout(1,1));
        table.setModel(controller.getAccountsTableModel());
        table.getColumn("").setCellRenderer(new ButtonRendererTable("Loan"));
        table.getColumn("").setCellEditor(new ButtonEditorTable(new JCheckBox(),tableBtn));


        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        topPanel.add(searchPanel);
        mainPanel.add(scroller);
        mainPanel.add(new JPanel());
        globalPanel.add(topPanel,BorderLayout.PAGE_START);
        globalPanel.add(mainPanel,BorderLayout.CENTER);

        tableBtn.addActionListener((e)->{
            var row = ((DefaultTableModel)table.getModel()).getDataVector().elementAt(table.getSelectedRow());
            var material = new MaterialModel((int)row.get(0),row.get(1).toString(),"");
            var view = new AddLoanView(material);
            view.addEventListener(()->{
                SwingUtilities.updateComponentTreeUI(this);
                controller.refreshTable(txtSearch.getText());
            });
            var d = new CustomDialog((JFrame) SwingUtilities.getWindowAncestor(this),"Add loan",view,500,200);
        });
        btnSearch.addActionListener(e-> controller.refreshTable(txtSearch.getText()));
        btnAddUser.addActionListener(e->{
            var d = new CustomDialog((JFrame) SwingUtilities.getWindowAncestor(this),"Add user",new AddUserView(),500,200);
        });
        this.add(globalPanel);
    }
}
