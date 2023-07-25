package views.extend;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/**
 * @author Xiang Weng
 */
public class MemberTableModel extends DefaultTableModel {
    private static MemberTableModel memberTableModel;


    // Table header will not change, so I make it static.
    static Vector<String> columnNames = new Vector<>();
    // column names
    static {
        columnNames.add("ID");
        columnNames.add("Nickname");
        columnNames.add("Email");
        columnNames.add("Phone Number");
    }

    // private constructor for supporting singleton pattern
    private MemberTableModel() {
        super(null, columnNames);
    }

    //Set data in the table model
    public static MemberTableModel assembleModel(Vector<Vector<Object>> data) {
        if (memberTableModel == null) memberTableModel = new MemberTableModel();
        memberTableModel.setDataVector(data, columnNames);
        return memberTableModel;
    }

    public static void updateModel(Vector<Vector<Object>> data) {
        memberTableModel.setDataVector(data, columnNames);
    }


    //Prohibit Editing table content.
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public static Vector<String> getColumnNames() {
        return columnNames;
    }

}
