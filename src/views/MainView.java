package views;

import controllers.MainController;
import controls.ButtonEditorTable;
import controls.ButtonRendererTable;
import controls.CustomDialog;
import models.MaterialModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainView extends JPanel {
    private final MainController controller;
    private final JPanel globalPanel;
    private final JPanel topPanel;
    private final JPanel searchPanel;
    private final JPanel btnsPanel;
    private final JTable table;
    private final JScrollPane scroller;
    private final JButton tableBtn;
    private final JButton btnSearch;
    private final JButton btnAddUser;
    private final JTextField txtSearch;
    public MainView(){
        controller = new MainController();
        table = new JTable();
        tableBtn = new JButton("Loan");
        scroller = new javax.swing.JScrollPane();
        globalPanel = new JPanel();
        topPanel = new JPanel();
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
        scroller.setViewportView(table);
        this.setLayout(new GridLayout(2,1));

        table.setModel(controller.getAccountsTableModel());
        table.getColumn("").setCellRenderer(new ButtonRendererTable("Loan"));
        table.getColumn("").setCellEditor(new ButtonEditorTable(new JCheckBox(),tableBtn));

        btnsPanel.add(btnAddUser);

        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        topPanel.add(btnsPanel);
        topPanel.add(searchPanel);

        globalPanel.add(topPanel,BorderLayout.PAGE_START);
        globalPanel.add(scroller,BorderLayout.CENTER);

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
