package controllers;

import managers.AppSDK;
import models.LoansModel;
import models.MaterialModel;
import models.UserModel;
import views.AddUserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

import static common.validation.RegexValidation.emailValidation;
import static java.lang.Integer.parseInt;

public class AddLoanController {
    private final MaterialModel material;
    private final Component cp;

    public AddLoanController(MaterialModel material,Component c) {
        this.material = material;
        cp=c;
    }
    public void addAction(JPanel c, String duration, String email){
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
                view.addEventListener(user::set);
                c.add(view);
            }
            else{
                closeWindow();
            }
        }
        AppSDK.LoansManager.addLoan(new LoansModel(0,new Date(),null,parseInt(duration),material,user.get()));
        closeWindow();
    }
    private void closeWindow(){
        Window w = SwingUtilities.getWindowAncestor(cp);
        w.dispatchEvent(new WindowEvent(w,WindowEvent.WINDOW_CLOSING));
    }
}
