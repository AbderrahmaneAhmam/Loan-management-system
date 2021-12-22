package views;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainView extends JPanel {
    JLabel label;

    public MainView() throws IOException {

        // --- On crée le conteneur d'onglets (partie gauche) ---
        JTabbedPane tabs = new JTabbedPane();
        // --- Premier onglet et son composant associé ---
        tabs.addTab( "Explorer", new JScrollPane( new JTree() ) );

        // --- Second onglet et son composant associé ---
        JEditorPane helpPane = new JEditorPane();
        helpPane.setEditable( false );
        //helpPane.setPage( "file:docs/index.html" );
        tabs.addTab( "Help", new JScrollPane( helpPane ) );

        // --- On crée un éditeur (partie droite) ---
        JTextArea editor = new JTextArea();
        JScrollPane scrollEditor = new JScrollPane( editor );

        // -- On assemble la partie gauche et la partir droite dans un splitter ---
        JSplitPane splitter = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, tabs, scrollEditor );
        this.add(tabs);
    }
}
