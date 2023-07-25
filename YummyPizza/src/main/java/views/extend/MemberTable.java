package views.extend;

import util.MemberCellRenderer;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

/**
 * @author Xiang Weng
 */
public class MemberTable extends JTable {


    public MemberTable() {

        //Format the table header
        JTableHeader tableHeader = getTableHeader();
        tableHeader.setFont(new Font(null, Font.BOLD, 16));
        tableHeader.setForeground(Color.RED);

        //Format the content in cells
        setFont(new Font(null, Font.PLAIN, 14));
        setForeground(Color.BLACK);
        setGridColor(Color.BLACK);
        setRowHeight(30);

        //allow multiple choosing
        getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    public void setTableModel(MemberTableModel model) {
        this.setModel(model);
    }

    //Format the columns
    public void renderRules() {
        Vector<String> columnNames = MemberTableModel.getColumnNames();
        MemberCellRenderer memberCellRenderer = new MemberCellRenderer();

        for (int i = 0; i < columnNames.size(); i++) {
            TableColumn column = getColumn(columnNames.get(i));
            column.setCellRenderer(memberCellRenderer);
            column.setResizable(false);
        }
    }
}
