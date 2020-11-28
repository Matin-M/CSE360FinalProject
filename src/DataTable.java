import java.util.*;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.Dimension;

public class DataTable extends JPanel implements TableModelListener {

    DataTableModel model;
    
    
    DataTable(MainViewWindow window, ArrayList<ArrayList<String>> roster)
    {
        model = new DataTableModel(roster);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumn("Program").setPreferredWidth(200);
        table.getColumn("ID").setPreferredWidth(95);
        Dimension newSize = new Dimension(600,500);
        if(scrollPane != null) {
            scrollPane.getViewport().setSize(newSize);
            scrollPane.getViewport().setPreferredSize(newSize);
            scrollPane.getViewport().setMinimumSize(newSize);
            scrollPane.setSize(newSize);
            scrollPane.setPreferredSize(newSize);
            scrollPane.setMinimumSize(newSize);
    }
        model.addTableModelListener(this);
        window.add(scrollPane);

        
        
    }

    public void update()
    {
        model.updateTable();
        
    }

    @Override
    public void tableChanged(TableModelEvent e)
    {
    	
    }

}
