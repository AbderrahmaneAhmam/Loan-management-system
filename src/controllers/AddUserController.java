package controllers;

import common.interfaces.AddNotification;
import managers.AppSDK;
import models.UserModel;
import sun.misc.Signal;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddUserController {
    Component source;
    ArrayList<AddNotification> eventListeners;
    public AddUserController(Component source, ArrayList<AddNotification> events) {
        this.source=source;
        eventListeners = events;
    }

    public void AddUserAction(UserModel user){

        if(user.getFirstName().trim().equals("") || user.getLastName().trim().equals("") || user.getEmail().trim().equals("")){
            JOptionPane.showMessageDialog(source,"field empty!!\nplease complete all fields","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            if(AppSDK.UsersManager.addUser(user)){
                JOptionPane.showMessageDialog(source,"success","success",JOptionPane.INFORMATION_MESSAGE);
                var addedUser = AppSDK.UsersManager.getUserByEmail(user.getEmail());
                eventListeners.forEach(e->e.onUserAdded(addedUser));
            }else{
                JOptionPane.showMessageDialog(source,"email already exists","Error",JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(source,"try again","?",JOptionPane.ERROR_MESSAGE);
        }
    }
}
