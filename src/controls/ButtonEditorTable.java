package controls;

import javax.swing.*;
import java.awt.*;

public class ButtonEditorTable extends DefaultCellEditor {
    private final JButton btn;
    public ButtonEditorTable(JCheckBox checkBox,JButton btn)
    {
        super(checkBox);
        this.btn=btn;
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column)
    {
        return btn;
    }
}