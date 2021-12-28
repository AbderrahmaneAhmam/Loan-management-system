package views;

import controllers.AddUserController;
import models.UserModel;

import javax.swing.*;
import java.awt.*;

public class AddUserView extends JPanel {
    private JTextField firstName,lastName,email;
    private JLabel lfname,llname,lemail;
    private JButton btnAdd;
    private AddUserController controller;

    public AddUserView() {
        firstName = new JTextField();
        lastName = new JTextField();
        email = new JTextField();
        lfname = new JLabel("First name");
        llname = new JLabel("Last name");
        lemail = new JLabel("Email");
        btnAdd = new JButton("Add");
        controller = new AddUserController(this);
        initComponents();
    }
    private void initComponents(){
        JPanel p1 = new JPanel();
        p1.setPreferredSize(new Dimension(0,100));
        JPanel p2 = new JPanel();
        p2.setPreferredSize(new Dimension(0,100));
        JPanel p3 = new JPanel();
        p3.setPreferredSize(new Dimension(0,100));
        var dim = new Dimension(200,25);
        firstName.setPreferredSize(dim);
        lfname.setPreferredSize(dim);
        lastName.setPreferredSize(dim);
        llname.setPreferredSize(dim);
        email.setPreferredSize(dim);
        lemail.setPreferredSize(dim);
        this.add(p1);
        this.add(lfname);
        this.add(firstName);
        this.add(p2);
        this.add(llname);
        this.add(lastName);
        this.add(p3);
        this.add(lemail);
        this.add(email);
        this.add(p1);
        this.add(btnAdd,"center");
        btnAdd.addActionListener(e-> controller.AddUserAction(new UserModel(0,firstName.getText(),lastName.getText(),email.getText())));
    }
}