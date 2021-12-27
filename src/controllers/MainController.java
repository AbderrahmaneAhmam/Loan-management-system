package controllers;

import managers.AppSDK;
import models.MaterialModel;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainController {

    DefaultTableModel accountsTableModel;
    public DefaultTableModel getAccountsTableModel() {
        return accountsTableModel;
    }

    public MainController() {
        setUpAccountsTableModel();
    }

    public final void setUpAccountsTableModel() {
        accountsTableModel = new DefaultTableModel();
        accountsTableModel.addColumn("ID");
        accountsTableModel.addColumn("Name");
        accountsTableModel.addColumn("");
        refreshTable("");
    }

    public final void refreshTable(String name) {
        accountsTableModel.getDataVector().clear();
        ArrayList<MaterialModel> materials = new ArrayList<>();
        try {
            materials = AppSDK.MaterialsManager.getAvailableMaterialsByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        materials.forEach((material) -> accountsTableModel.addRow(new Object[]{
                material.getId(),
                material.getName()
        }));
        accountsTableModel.fireTableDataChanged();
    }
}
