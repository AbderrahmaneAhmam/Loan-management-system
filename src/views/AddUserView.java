package views;

import common.interfaces.AddNotification;
import controllers.AddUserController;
import models.UserModel;
import sun.misc.Signal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class AddUserView extends JPanel {
    private ArrayList<AddNotification> eventListeners;
    private final JTextField firstName,lastName,email;
    private final JLabel lfname,llname,lemail;
    private final JPanel p1_1,p1_2,p2_1,p2_2,p3_1,p3_2,p4;
    private final JButton btnAdd;
    private final AddUserController controller;

    public AddUserView() {
        eventListeners = new ArrayList<>();
        firstName = new JTextField(20);
        lastName = new JTextField(20);
        email = new JTextField(20);
        lfname = new JLabel("First name");
        llname = new JLabel("Last name");
        lemail = new JLabel("Email");
        btnAdd = new JButton("Add");
        p1_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p1_2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        p2_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p2_2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        p3_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p3_2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        controller = new AddUserController(this,eventListeners);
        initComponents();
    }
    private void initComponents(){
        this.setLayout(new GridLayout(4,2));
        this.setMinimumSize(new Dimension(500,300));
        p1_1.add(lfname);
        p1_2.add(firstName);
        p2_1.add(llname);
        p2_2.add(lastName);
        p3_1.add(lemail);
        p3_2.add(email);
        p4.add(btnAdd);
        this.add(p1_1);
        this.add(p1_2);
        this.add(p2_1);
        this.add(p2_2);
        this.add(p3_1);
        this.add(p3_2);
        this.add(new JLabel());
        this.add(p4);
        btnAdd.addActionListener(e-> controller.AddUserAction(new UserModel(0,firstName.getText(),lastName.getText(),email.getText())));
    }
    public void addEventListener(AddNotification notify){
        eventListeners.add(notify);
    }
}