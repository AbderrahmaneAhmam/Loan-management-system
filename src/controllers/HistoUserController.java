package controllers;

import managers.AppSDK;
import models.LoansModel;
import models.UserModel;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class HistoUserController {
    DefaultTableModel histoTableModel;
    private UserModel user;

    public DefaultTableModel getAccountsTableModel() {
        return histoTableModel;
    }

    public HistoUserController(UserModel user) {
        this.user = user;
        setUpTableModel();
    }

    public final void setUpTableModel() {
        histoTableModel = new DefaultTableModel();
        histoTableModel.addColumn("ID");
        histoTableModel.addColumn("Name");
        histoTableModel.addColumn("Date loan");
        histoTableModel.addColumn("Duration");
        histoTableModel.addColumn("Date back");
        histoTableModel.getDataVector().clear();
        ArrayList<LoansModel> loans = new ArrayList<>();
        try {
            loans = AppSDK.LoansManager.getLoansByUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loans.forEach((loan) -> histoTableModel.addRow(new Object[]{
                loan.getId(),
                loan.getMaterial().getName(),
                loan.getLoanDate(),
                loan.getDuration(),
                loan.getBackDate()
        }));
        histoTableModel.fireTableDataChanged();
    }
}
