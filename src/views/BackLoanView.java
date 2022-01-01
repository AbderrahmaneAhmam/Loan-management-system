package views;

import controllers.BackLoanController;
import controls.ButtonEditorTable;
import controls.ButtonRendererTable;
import managers.AppSDK;
import models.LoansModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BackLoanView extends JPanel {
    private final BackLoanController controller;
    private final JPanel globalPanel,topPanel,mainPanel;
    private final JTable table;
    private final JScrollPane scroller;
    private final JButton tableBtn,btnSearch;
    private final JTextField txtSearch;
    public BackLoanView(){
        controller = new BackLoanController(this);
        table = new JTable();
        tableBtn = new JButton("Return");
        scroller = new javax.swing.JScrollPane(table);
        globalPanel = new JPanel();
        topPanel = new JPanel();
        mainPanel = new JPanel();
        btnSearch = new JButton("Search");
        txtSearch = new JTextField("",20);
        initComponents();
    }
    private void initComponents(){
        globalPanel.setLayout(new BorderLayout());
        topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        mainPanel.setLayout(new GridLayout());
        table.setAutoCreateRowSorter(true);
        this.setLayout(new GridLayout(1,1));
        table.setModel(controller.getTableModel());
        table.getColumn("").setCellRenderer(new ButtonRendererTable("Back"));
        table.getColumn("").setCellEditor(new ButtonEditorTable(new JCheckBox(),tableBtn));
        topPanel.add(txtSearch);
        topPanel.add(btnSearch);
        mainPanel.add(scroller);
        globalPanel.add(topPanel,BorderLayout.PAGE_START);
        globalPanel.add(mainPanel,BorderLayout.CENTER);

        tableBtn.addActionListener((e)->{
            var row = ((DefaultTableModel)table.getModel()).getDataVector().elementAt(table.getSelectedRow());
            var loan = new LoansModel((int)row.get(0));
            controller.returnLoan(loan);
        });
        AppSDK.LoansManager.addChangesListener(b->{
            controller.refreshTable(txtSearch.getText());
        });
        btnSearch.addActionListener(e-> controller.refreshTable(txtSearch.getText()));
        this.add(globalPanel);
    }
}
