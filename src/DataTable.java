import java.util.*;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.Dimension;

/**
 * The class DataTable extends JPanel and implements the TableModelListener
 * interface. This class creates a JTable using a specialized table model,
 * DataTableModel, which requires an ArrayList of ArrayList<String>. The table
 * is added to a JScrollPane which is then added to the JFrame passed as
 * the argument.
 */
public class DataTable extends JPanel implements TableModelListener {

    DataTableModel model;
    JScrollPane scrollPane;
    MainViewWindow window;
    JTable table;
    
    /**
     * Constructor for the DataTable class.
     * @param window - An instance of a MainViewWindow which extends the JFrame
     * class. This window will become the parent of the JScrollPane that houses
     * the JTable
     * @param roster - An ArrayList of ArrayList<String> that represents
     * information about students in a class. The ArrayList<String> at index 0
     * of the ArrayList must contain the column header names. 
     */
    DataTable(MainViewWindow window, ArrayList<ArrayList<String>> roster)
    {
        this.window = window;
        model = new DataTableModel(roster);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        // Table doesn't need to resize because we accomplish this manually
        // after every table update
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        Dimension newSize = new Dimension(600,500);
        scrollPane.setPreferredSize(newSize);

        this.setColumnWidths();
        model.addTableModelListener(this);
        window.add(scrollPane);
        // ScrollPane fits to window
        window.pack();
        
    }

    /**
     * This method ensures uniformity of column widths and is only used
     * internally
     */
    private void setColumnWidths()
    {
        int count = table.getColumnCount();
        // Iterate through every column to get its name and set
        // the column's width
        for (int i = 0; i < count; ++i)
        {
            String name = table.getColumnName(i);
            table.getColumn(name).setPreferredWidth(100);
        }
        // Program needs to be longer to account for long program names
        table.getColumn("Program").setPreferredWidth(200);
    }

    /**
     * This method forces the table to update to check for changes within the
     * table model's data
     */
    public void update()
    {

        model.updateTable();
        // If new columns added this will resize the column widths
        this.setColumnWidths();
    }

    /**
     * This method is used to clear all data from the table, hide the
     * JScrollPane, and remove the JScrollPane from the MainViewWindow.
     */
    public void clearTable()
    {
        // Have the model clear all data and columns
        model.deleteAll();
        scrollPane.setEnabled(false);
        scrollPane.remove(this);
        window.remove(scrollPane);
    }

    /**
     * This method overrides the tableChanged() method of the AbstractTableModel
     * class.
     */
    @Override
    public void tableChanged(TableModelEvent e)
    {
    	
    }

}
