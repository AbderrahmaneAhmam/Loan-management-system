package controllers;

import managers.AppSDK;
import models.LoansModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class BackLoanController {

    DefaultTableModel backsTableModel;
    private Component comp;

    public DefaultTableModel getTableModel() {
        return backsTableModel;
    }

    public BackLoanController(Component comp) {
        this.comp = comp;
        setUpBacksTableModel();
    }

    public final void setUpBacksTableModel() {
        backsTableModel = new DefaultTableModel();
        backsTableModel.addColumn("ID");
        backsTableModel.addColumn("First name");
        backsTableModel.addColumn("Last name");
        backsTableModel.addColumn("Email");
        backsTableModel.addColumn("Material");
        backsTableModel.addColumn("Date loan");
        backsTableModel.addColumn("Duration");
        backsTableModel.addColumn("Delay");
        backsTableModel.addColumn("");
        refreshTable("");
    }

    public final void refreshTable(String name) {
        backsTableModel.getDataVector().clear();
        ArrayList<LoansModel> loans = new ArrayList<>();
        try {
            loans = AppSDK.LoansManager.getCurrentLoans(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loans.forEach((loan) -> backsTableModel.addRow(new Object[]{
                loan.getId(),
                loan.getUser().getFirstName(),
                loan.getUser().getLastName(),
                loan.getUser().getEmail(),
                loan.getMaterial().getName(),
                loan.getLoanDate(),
                loan.getDuration(),
                loan.getDelay()
        }));
        backsTableModel.fireTableDataChanged();
    }
    public final void returnLoan(LoansModel loan){
        try {
            int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to return this material?","Warning",JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION){
                AppSDK.LoansManager.updateLoan(loan);
                JOptionPane.showMessageDialog(comp,"success","success",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
