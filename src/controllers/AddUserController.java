package controllers;

import common.validation.RegexValidation;
import managers.AppSDK;
import models.UserModel;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AddUserController {
    Component source;
    public AddUserController(Component source) {
        this.source=source;
    }

    public void AddUserAction(UserModel user){
        if(user.getFirstName().trim().equals("") || user.getLastName().trim().equals("") || !RegexValidation.emailValidation(user.getEmail())){
            JOptionPane.showMessageDialog(source,"Invalid input!!","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            if(AppSDK.UsersManager.addUser(user)){
                JOptionPane.showMessageDialog(source,"success","success",JOptionPane.INFORMATION_MESSAGE);
                var addedUser = AppSDK.UsersManager.getUserByEmail(user.getEmail());
            }else{
                JOptionPane.showMessageDialog(source,"email already exists","Error",JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(source,"try again","?",JOptionPane.ERROR_MESSAGE);
        }
    }
}
