package controllers;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import views.Layout;
import views.MainView;

import javax.swing.*;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LayoutController {
    private final Layout layout;
    final Map<String,JPanel> views = new HashMap<String, JPanel>();

    public LayoutController(Layout layout) throws IOException {
        this.layout = layout;
        views.put("main",new MainView());
    }

    public void themeChange(String name){
        try {
            if(name.equals("Dark"))
                UIManager.setLookAndFeel(new FlatDarkLaf());
            else
                UIManager.setLookAndFeel(new FlatLightLaf());
            SwingUtilities.updateComponentTreeUI(layout);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
