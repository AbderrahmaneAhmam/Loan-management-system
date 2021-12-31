package controllers;

import managers.AppSDK;
import models.MaterialModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
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

    public final ChartPanel Graph1() {

        DefaultCategoryDataset data = new DefaultCategoryDataset();
        try {
            ResultSet rs = AppSDK.LoansManager.LonsCount();
            while(rs.next()){
                data.setValue(new Double(rs.getString(2)),"value",rs.getString(1));
            }
            JFreeChart chart = ChartFactory.createBarChart("Loans In Month",
                    " ", " " , data);
            ChartPanel panel = new ChartPanel(chart,true);
            chart.setBackgroundPaint(new Color(127, 127, 127, 64));
            panel.setVisible(true);
            return panel;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
    public final ChartPanel Graph2() {

        DefaultPieDataset Pie = new DefaultPieDataset();
        try {
            ResultSet rs = AppSDK.LoansManager.LonsAvailable();
            rs.next();
            Pie.setValue("Materials Not Available", rs.getInt(2));
            Pie.setValue("Materials Available", rs.getInt(1));
            JFreeChart chart = ChartFactory.createPieChart("Materials available", Pie,
                    true, true , true);
            ChartPanel panel = new ChartPanel(chart,true);
            chart.setBackgroundPaint(new Color(127, 127, 127, 64));
            panel.setVisible(true);
            return panel;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
    }
