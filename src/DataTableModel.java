import java.util.*;
import javax.swing.table.AbstractTableModel;


public class DataTableModel extends AbstractTableModel {

    List<ArrayList<String>> data;
    ArrayList<String> columns;

    DataTableModel(ArrayList<ArrayList<String>> roster)
    {
        data = roster.subList(1, roster.size());
        columns = roster.get(0);
    }
    

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        if (data.size() != 0) {
            return data.get(0).size();
        } else {
            return 0;
        }
    }

    @Override
    public String getColumnName(int col) {
        return columns.get(col);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex).get(columnIndex);
    }

    public void updateTable()
    {
        fireTableStructureChanged();
    }
    

}

