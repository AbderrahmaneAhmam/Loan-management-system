package controllers;

import managers.AppSDK;
import models.MaterialModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

    public final JFreeChart Graph1() {
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        try {
            ResultSet rs = AppSDK.LoansManager.LonsCount();
            while(rs.next()){
                data.setValue(new Double(rs.getString(2)),"Count loans",rs.getString(1));
            }
            var chart = ChartFactory.createBarChart("Loans In Month",
                    " ", " " , data, PlotOrientation.VERTICAL,true,true,false);
            chart.setBackgroundPaint(UIManager.getColor ("Panel.background"));
            chart.getPlot().setBackgroundPaint( UIManager.getColor ("Panel.background"));
            chart.getTitle().setPaint(UIManager.getColor ("Label.foreground"));
            chart.getPlot().setOutlinePaint(UIManager.getColor ("Panel.background"));
            chart.getLegend().setBackgroundPaint(UIManager.getColor ("Panel.background"));
            chart.getLegend().setFrame(new BlockBorder(UIManager.getColor ("Label.foreground")));
            chart.getLegend().setItemPaint(UIManager.getColor ("Label.foreground"));
            var plot = (CategoryPlot)chart.getPlot();
            var range = plot.getRangeAxis();
            var domain = plot.getDomainAxis();
            domain.setTickLabelPaint(UIManager.getColor ("Label.foreground"));
            range.setTickLabelPaint(UIManager.getColor ("Label.foreground"));
            UIManager.addPropertyChangeListener(e->{
                chart.setBackgroundPaint(UIManager.getColor ("Panel.background"));
                chart.getPlot().setBackgroundPaint( UIManager.getColor ("Panel.background"));
                chart.getTitle().setPaint(UIManager.getColor ("Label.foreground"));
                chart.getPlot().setOutlinePaint(UIManager.getColor ("Panel.background"));
                domain.setTickLabelPaint(UIManager.getColor ("Label.foreground"));
                range.setTickLabelPaint(UIManager.getColor ("Label.foreground"));
                chart.getLegend().setBackgroundPaint(UIManager.getColor ("Panel.background"));
                chart.getLegend().setFrame(new BlockBorder(UIManager.getColor ("Label.foreground")));
                chart.getLegend().setItemPaint(UIManager.getColor ("Label.foreground"));
            });
            return chart;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
    public final JFreeChart Graph2() {

        DefaultPieDataset Pie = new DefaultPieDataset();
        try {
            ResultSet rs = AppSDK.LoansManager.LonsAvailable();
            rs.next();
            Pie.setValue("Materials Not Available", rs.getInt(2));
            Pie.setValue("Materials Available", rs.getInt(1));
            var chart = ChartFactory.createPieChart("Materials available", Pie,
                    true, true , true);
            chart.setBackgroundPaint(UIManager.getColor ("Panel.background"));
            chart.getPlot().setBackgroundPaint( UIManager.getColor ("Panel.background"));
            chart.getTitle().setPaint(UIManager.getColor ("Label.foreground"));
            chart.getPlot().setOutlinePaint(UIManager.getColor ("Panel.background"));
            chart.getLegend().setBackgroundPaint(UIManager.getColor ("Panel.background"));
            chart.getLegend().setFrame(new BlockBorder(UIManager.getColor ("Label.foreground")));
            chart.getLegend().setItemPaint(UIManager.getColor ("Label.foreground"));
            UIManager.addPropertyChangeListener(e->{
                chart.setBackgroundPaint(UIManager.getColor ("Panel.background"));
                chart.getPlot().setBackgroundPaint( UIManager.getColor ("Panel.background"));
                chart.getTitle().setPaint(UIManager.getColor ("Label.foreground"));
                chart.getPlot().setOutlinePaint(UIManager.getColor ("Panel.background"));
                chart.getLegend().setBackgroundPaint(UIManager.getColor ("Panel.background"));
                chart.getLegend().setFrame(new BlockBorder(UIManager.getColor ("Label.foreground")));
                chart.getLegend().setItemPaint(UIManager.getColor ("Label.foreground"));
            });
            return chart;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
