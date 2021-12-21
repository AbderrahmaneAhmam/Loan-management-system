import com.formdev.flatlaf.FlatDarkLaf;
import managers.DBManager;
import views.Layout;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.err.println(e);
        }
        new Layout();
    }
}
