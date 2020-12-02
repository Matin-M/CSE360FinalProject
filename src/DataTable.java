import java.util.*;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.Dimension;

public class DataTable extends JPanel implements TableModelListener {

    DataTableModel model;
    JScrollPane scrollPane;
    MainViewWindow window;
    JTable table;
    
    DataTable(MainViewWindow window, ArrayList<ArrayList<String>> roster)
    {
        this.window = window;
        model = new DataTableModel(roster);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        Dimension newSize = new Dimension(600,500);
        scrollPane.setPreferredSize(newSize);

        this.setColumnWidths();
        model.addTableModelListener(this);
        window.add(scrollPane);
        // ScrollPane fits to window
        window.pack();
        
    }

    public void setColumnWidths()
    {
        int count = table.getColumnCount();
        for (int i = 0; i < count; ++i)
        {
            String name = table.getColumnName(i);
            table.getColumn(name).setPreferredWidth(100);
        }

        table.getColumn("Program").setPreferredWidth(200);
    }

    public void update()
    {
        model.updateTable();
        this.setColumnWidths();
    }

    public void clearTable()
    {
        model.deleteAll();
        scrollPane.setEnabled(false);
        scrollPane.remove(this);
        window.remove(scrollPane);
    }

    @Override
    public void tableChanged(TableModelEvent e)
    {
    	
    }

}
