package views;

import controllers.AddLoanController;
import models.MaterialModel;
import javax.swing.*;
import java.awt.*;

public class AddLoanView extends JPanel {
    private final JTextField txtDur;
    private final JTextField txtEmail;
    private final JLabel labdur,labemail;
    private final JPanel p1_1,p1_2,p2_1,p2_2,p3,globalPanel,insidePanel;
    private final JButton btnAdd;
    private final AddLoanController controller;

    public AddLoanView(MaterialModel material) {
        txtDur = new JTextField(20);
        txtEmail = new JTextField(20);
        labdur = new JLabel("Duration");
        labemail = new JLabel("User email");
        btnAdd = new JButton("Add");
        p1_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p1_2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        p2_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p2_2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        globalPanel = new JPanel();
        insidePanel = new JPanel();
        controller = new AddLoanController(material,this);
        initComponents();
    }
    private void initComponents(){
        insidePanel.setLayout(new GridLayout(3,2));
        globalPanel.setLayout(new CardLayout());
        p1_1.add(labdur);
        p1_2.add(txtDur);
        p2_1.add(labemail);
        p2_2.add(txtEmail);
        p3.add(btnAdd);
        insidePanel.add(p1_1);
        insidePanel.add(p1_2);
        insidePanel.add(p2_1);
        insidePanel.add(p2_2);
        insidePanel.add(new JLabel());
        insidePanel.add(p3);
        globalPanel.add(insidePanel);
        this.add(globalPanel);
        btnAdd.addActionListener(e-> controller.addAction(globalPanel,txtDur.getText(),txtEmail.getText()));
    }
}
