package controls;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;

public class CustomChartPanel extends ChartPanel {
    public CustomChartPanel(JFreeChart chart, boolean useBuffer) {
        super(chart, useBuffer);
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new GridLayout());
    }
}
