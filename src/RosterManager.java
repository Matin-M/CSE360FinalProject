/**
 * Responsible for loading, editing, and saving the roster csv file.
 *
 */

import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RosterManager extends JFrame {
	
	private JFileChooser chooser;
	private FileFilter filter;
	private ArrayList<ArrayList<String>> roster;
	
	
	RosterManager()
	{
		roster = new ArrayList<ArrayList<String>>();
		chooser = new JFileChooser();
		filter = new FileNameExtensionFilter("csv files", "csv");
		chooser.addChoosableFileFilter(filter);
		
	}
	
	public void openFile()
	{
		chooser.setDialogTitle("Choose a file");
		this.getContentPane().add(chooser);
		chooser.setVisible(true);
	    int returnVal = chooser.showOpenDialog(this);
	    
	    if (returnVal == JFileChooser.APPROVE_OPTION)
	    {	    	
	    	try 
	    	{
	    		File csvRoster;
		    	FileReader fReader;
		    	BufferedReader bReader;
		    	final String DELIMITER = ",";
		    	String line;
				ArrayList<String> tempList, columnNames;
		    	
		    	csvRoster = chooser.getSelectedFile();
		    	fReader = new FileReader(csvRoster);
		    	bReader = new BufferedReader(fReader);
	    		
				line = "";

				// Add column names to roster to be used in DataTable
				columnNames = new ArrayList<String>();
				columnNames.add("ID");
				columnNames.add("First Name");
				columnNames.add("Last Name");
				columnNames.add("Program");
				columnNames.add("Level");
				columnNames.add("ASURITE");
				roster.add(columnNames);

	    		while ((line = bReader.readLine()) != null)
	    		{
					// Create an ArrayList of Strings to hold all data fields
					// from the roster CSV file
					tempList = new ArrayList<String>();
					// Collections.addAll adds the array of strings created by
					// the line.split operation into the ArrayList.
	    			Collections.addAll(tempList, line.split(DELIMITER));
	    			// asurite becomes the key to be used for updating
	    			// attendance in the future;
	    			roster.add(tempList);
	    		}
	    		
	    		bReader.close();
	    		fReader.close();
	    		
				
	    	}
	    	catch (IOException ioe)
	    	{
				System.out.println(ioe.getLocalizedMessage());
	    	}
		}
		
	}


	public ArrayList<ArrayList<String>> getRoster()
	{
		return roster;
	}
}
