package util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * @author Xiang Weng
 */
public class MemberCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // change the color to grey if it is an even row (doesn't work somehow)
//        if (row % 2 == 0) setForeground(Color.GRAY);
//        else setForeground(Color.WHITE);
        // Center the cell content
        setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
