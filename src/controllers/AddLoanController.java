package controllers;

import common.interfaces.ChangedNotification;
import managers.AppSDK;
import models.LoansModel;
import models.MaterialModel;
import models.UserModel;
import views.AddUserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicReference;

import static common.validation.RegexValidation.emailValidation;
import static java.lang.Integer.parseInt;

public class AddLoanController {
    private final MaterialModel material;
    private final Component component;
    private final ArrayList<ChangedNotification> events;

    public AddLoanController(MaterialModel material, Component c, ArrayList<ChangedNotification> events) {
        this.material = material;
        this.events = events;
        component =c;
    }
    public void addAction(JPanel c, String duration, String email) {
        int dur = parseInt(duration);
        if(dur<=0 || !emailValidation(email))
        {
            JOptionPane.showMessageDialog(c,"Invalid input!!","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        AtomicReference<UserModel> user = new AtomicReference<>(AppSDK.UsersManager.getUserByEmail(email));
        if(user.get() ==null){
            int dialogResult = JOptionPane.showConfirmDialog (null, "User doesn't exists\nWould You Like to add this user?","Warning",JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION){
                c.removeAll();
                c.revalidate();
                c.repaint();
                var view = new AddUserView();
                view.addEventListener(e->{
                    user.set(e);
                    addLonAndEvents(c, duration, user);
                });
                c.add(view);
            }
            else{
                closeWindow();
            }
        }
        else{
            addLonAndEvents(c, duration, user);
        }
    }

    private void addLonAndEvents(JPanel c, String duration, AtomicReference<UserModel> user) {
        try {
            AppSDK.LoansManager.addLoan(new LoansModel(0,new Date(Calendar.getInstance().getTimeInMillis()),null,parseInt(duration),material,user.get()));
            JOptionPane.showMessageDialog(c,"success","success",JOptionPane.INFORMATION_MESSAGE);
            closeWindow();
            events.forEach(ChangedNotification::onTableChange);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(c,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void closeWindow(){
        Window w = SwingUtilities.getWindowAncestor(component);
        w.dispatchEvent(new WindowEvent(w,WindowEvent.WINDOW_CLOSING));
    }
}
