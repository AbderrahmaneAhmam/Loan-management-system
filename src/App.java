import com.formdev.flatlaf.FlatLightLaf;
import managers.AppSDK;
import views.Layout;

import javax.swing.*;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            java.awt.EventQueue.invokeLater(()->{
                try {
                    new Layout();
                    new AppSDK();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
