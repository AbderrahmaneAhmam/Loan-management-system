import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatPropertiesLaf;
import views.Layout;

import javax.swing.*;
import java.awt.geom.FlatteningPathIterator;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        try {

            UIManager.setLookAndFeel(new FlatDarkLaf());
            java.awt.EventQueue.invokeLater(()->{
                try {
                    new Layout();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
