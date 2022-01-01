package controllers;

import managers.AppSDK;
import models.LoansModel;
import models.MaterialModel;
import models.UserModel;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConsultationUsersController {
    DefaultTableModel accountsTableModel;
    DefaultTableModel loansTableModel;
    public DefaultTableModel getAccountsTableModel() {
        return accountsTableModel;
    }
    public DefaultTableModel getDelaysTableModel() {
        return loansTableModel;
    }
    public ConsultationUsersController() {
        setUpTableModel();
    }

    public final void setUpTableModel() {
        accountsTableModel = new DefaultTableModel();
        accountsTableModel.addColumn("ID");
        accountsTableModel.addColumn("First name");
        accountsTableModel.addColumn("Last name");
        accountsTableModel.addColumn("Email");
        accountsTableModel.addColumn("");
        loansTableModel = new DefaultTableModel();
        loansTableModel.addColumn("ID");
        loansTableModel.addColumn("First name");
        loansTableModel.addColumn("Last name");
        loansTableModel.addColumn("Email");
        loansTableModel.addColumn("Material");
        loansTableModel.addColumn("Date loan");
        loansTableModel.addColumn("Duration");
        loansTableModel.addColumn("Delay");
        refreshTableUsers("");
        refreshTableLoans("");
    }

    public final void refreshTableUsers(String name) {
        accountsTableModel.getDataVector().clear();
        ArrayList<UserModel> users = new ArrayList<>();
        try {
            users = AppSDK.UsersManager.getUsersByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        users.forEach((user) -> accountsTableModel.addRow(new Object[]{
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        }));
        accountsTableModel.fireTableDataChanged();
    }
    public final void refreshTableLoans(String name) {
        loansTableModel.getDataVector().clear();
        ArrayList<LoansModel> loans = new ArrayList<>();
        try {
            loans = AppSDK.LoansManager.getDelayedLoans(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loans.forEach((loan) -> loansTableModel.addRow(new Object[]{
                loan.getId(),
                loan.getUser().getFirstName(),
                loan.getUser().getLastName(),
                loan.getUser().getEmail(),
                loan.getMaterial().getName(),
                loan.getLoanDate(),
                loan.getDuration(),
                loan.getDelay()
        }));
        loansTableModel.fireTableDataChanged();
    }
}
