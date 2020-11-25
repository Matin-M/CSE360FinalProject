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
		chooser.setDialogTitle("Choose a file");
		this.getContentPane().add(chooser);
		chooser.setVisible(true);
	}
	
	public ArrayList<ArrayList<String>> openFile()
	{
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
		    	ArrayList<String> tempList;
		    	
		    	csvRoster = chooser.getSelectedFile();
		    	fReader = new FileReader(csvRoster);
		    	bReader = new BufferedReader(fReader);
	    		
		    	line = "";
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
		
		return roster;
	}
}
