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
	private HashMap<String, String[]> roster;
	
	
	RosterManager()
	{
		roster = new HashMap<String, String[]>();
		chooser = new JFileChooser();
		filter = new FileNameExtensionFilter("csv files", "csv");
		chooser.addChoosableFileFilter(filter);
		chooser.setDialogTitle("Choose a file");
		this.getContentPane().add(chooser);
		chooser.setVisible(true);
	}
	
	public void openFile()
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
		    	String line, key;
		    	String[] tempArr;
		    	
		    	csvRoster = chooser.getSelectedFile();
		    	fReader = new FileReader(csvRoster);
		    	bReader = new BufferedReader(fReader);
	    		
		    	line = "";
	    		while ((line = bReader.readLine()) != null)
	    		{
	    			// Create an array of strings from the fields in the line
	    			// delimited by commas	    			
	    			tempArr = line.split(DELIMITER);
	    			// asurite becomes the key to be used for updating
	    			// attendance in the future
	    			key = tempArr[5];
	    			roster.put(key, tempArr);
	    		}
	    		
	    		bReader.close();
	    		fReader.close();
	    		
	    		// This exists for testing purposes and to demonstrate how to 
	    		// access items within the HashMap
	    		for (Map.Entry<String, String[]> entry : roster.entrySet())
	    		{
	    			key = entry.getKey();
	    			tempArr = entry.getValue();
	    			
	    			System.out.println(key + ": ");
	    			for (int i = 0; i < tempArr.length; ++i)
	    			{
	    				System.out.print(tempArr[i] + " ");
	    			}
	    			System.out.println();
	    		}
	    	}
	    	catch (IOException ioe)
	    	{
	    		System.out.println(ioe.getLocalizedMessage());
	    	}
	    }
	    
	}
}
