import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * RosterManager is responsible for loading a roster from a CSV file and
 * exporting it after changes have been made. Utilizes the JFileChooser
 * class to select a file to be loaded as well as saved. Extends JFrame.
 */
public class RosterManager extends JFrame {
	
	private JFileChooser chooser;
	private FileFilter filter;
	private ArrayList<ArrayList<String>> roster;
	
	/**
	 * Constructor for the RosterManager class. Instantiates private variables
	 * for use within the class.
	 */
	RosterManager()
	{
		roster = new ArrayList<ArrayList<String>>();
		chooser = new JFileChooser();
		filter = new FileNameExtensionFilter("csv files", "csv");
		chooser.addChoosableFileFilter(filter);
		
	}
	
	/**
	 * Oepns and parses a CSV file to fill an ArrayList of ArrayList<Strings>
	 * to represent the roster. The CSV file must contain lines that consist of
	 * six fields: ID, First Name, Last Name, Program, Academic Level, and 
	 * the student's ASURITE id. The roster is created with Strings of these
	 * field names occupying the ArrayList of Strings at index 0 of the
	 * ArrayList roster. 
	 */
	public void openFile()
	{
		chooser.setDialogTitle("Choose a file");
		this.getContentPane().add(chooser);
		chooser.setVisible(true);
	    int returnVal = chooser.showOpenDialog(this);
		
		// After the user chooses a file
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
				// This becomes the first list within the list
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

	/**
	 * This method returns the roster to be used by other classes.
	 * @return roster - An ArrayList of ArrayList<Strings> containing
	 * information about students in a class imported from a CSV file
	 */
	public ArrayList<ArrayList<String>> getRoster()
	{
		return roster;
	}

	/**
	 * This method asks the user to select a CSV file to save the roster data
	 * to. After the user selects the file each ArrayList<String> within the 
	 * ArrayList is written to the file, with each String being seperated by a
	 * comma unless it is the last string in the ArrayList<String> 
	 */
	public void exportRoster()
	{
		chooser.setDialogTitle("Choose where to save file");
		this.getContentPane().add(chooser);
		chooser.setVisible(true);
		int returnVal = chooser.showSaveDialog(this);
		
		// After the user selects the file to be written to
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			try 
	    	{
				File csvExport;
				csvExport = chooser.getSelectedFile();
				BufferedWriter writer = new BufferedWriter(new PrintWriter(csvExport));

				// For every list within the list of lists
				for (int i = 0; i < roster.size(); ++i)
				{
					// For every item within the list 
					for (int j = 0; j < roster.get(0).size(); ++j)
					{
						// Write the data
						writer.write(roster.get(i).get(j));
						// If the column is not the last within the list
						if (j != roster.get(0).size() - 1)
						{
							// Add a comma
							writer.write(",");
						}
					}

					// If the list is not the last within the list of lists
					if (i != roster.size() - 1)
					{
						// Add a newline
						writer.write("\n");
					}
										
				}
				writer.close();
				
				// Notify the user of succesful save operation
				String label = "The file " + csvExport.getName() + " has been saved!";
				JOptionPane.showMessageDialog(null, label);
			}

			catch (IOException ioe)
	    	{
				System.out.println(ioe.getLocalizedMessage());
	    	}
		}

	}
}
