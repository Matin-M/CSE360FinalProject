import java.util.*;
import javax.swing.table.AbstractTableModel;

/**
 * DataTableModel extends the abstract class AbstractTableModel. It is used to
 * pass information to a JTable on how table data and columns is accessible. 
 * DataTableModel uses a List of type ArrayList<String> and columns are in the
 * form of ArrayList<String>.
 */
public class DataTableModel extends AbstractTableModel {

    List<ArrayList<String>> data;
    ArrayList<String> columns;

    /**
     * Constructor for DataTableModel.
     * @param roster - an ArrayList of ArrayList<String> that represents
     * information about students in a class. The ArrayList<String> at index 0
     * of the ArrayList must contain the column header names.
     */
    DataTableModel(ArrayList<ArrayList<String>> roster)
    {
        // Data is the roster without the column names
        data = roster.subList(1, roster.size());
        // Columns is just the first ArrayList of strings within the
        // ArrayList
        columns = roster.get(0);
    }
    
    /**
     * Overrides the getRowCount() method of the AbstractTableModel.
     * @reutrn Returns the number of rows within the table model
     */
    @Override
    public int getRowCount() {
        return data.size();
    }

    /**
     * Overrides the getColumnCount() method of the AbstractTableModel.
     * @return Reutrns the number of columns within the table model
     */
    @Override
    public int getColumnCount() {
        if (data.size() != 0) {
            return data.get(0).size();
        } else {
            return 0;
        }
    }

    /**
     * Overrides the getColumnName() method of the AbstractTableModel.
     * @param col - The index of the column to return the name of. Starts at 0
     * @return Returns the name of the column at the given index
     */
    @Override
    public String getColumnName(int col) {
        return columns.get(col);
    }

    /**
     * Overrides the getValueAt() method of the AbstractTableModel.
     * @param rowIndex - The index of the row where the desired value lies
     * @param columnIndex - The index of the column where the desire value lies
     * @return Returns a value of type Object, in this case a String at the
     * given position
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex).get(columnIndex);
    }

    /**
     * This method clears all data from the table and notifies listeners the
     * table has changed
     */
    public void deleteAll()
    {
        data.clear();
        columns.clear();
        fireTableStructureChanged();
    }

    /**
     * This method fires a notification that the table structure has changed
     */
    public void updateTable()
    {
        fireTableStructureChanged();
    }
    

}

