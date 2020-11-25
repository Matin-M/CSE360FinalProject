import java.util.*;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;


public class DataTable implements TableModelListener{
    
    DataTable(MainViewWindow window, ArrayList<ArrayList<String>> roster)
    {
        JTable table = new JTable(new DataTableModel(roster));
        JScrollPane scrollPane = new JScrollPane(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumn("Program").setPreferredWidth(200);
        window.add(scrollPane);
        window.setVisible(true);       
        
    }

    @Override
    public void tableChanged(TableModelEvent e)
    {

    }

}
