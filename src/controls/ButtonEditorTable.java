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

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        if (isSelected) {
            btn.setForeground(table.getSelectionForeground());
            btn.setBackground(table.getSelectionBackground());
        }
        else
        {
            btn.setForeground(table.getForeground());
            btn.setBackground(table.getBackground());
        }
        return btn;
    }
}