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

	public void addAttendance()
	{
		chooser.setDialogTitle("Choose a file");
		this.getContentPane().add(chooser);
		chooser.setVisible(true);
	    int returnVal = chooser.showOpenDialog(this);
	    
	    if (returnVal == JFileChooser.APPROVE_OPTION)
	    {	    	
	    	try 
	    	{
	    		File attendance;
		    	FileReader fReader;
		    	BufferedReader bReader;
		    	final String DELIMITER = ",";
		    	String line, date;
				String[] tempArr;
				int loc, i, rowSize, columnSize;
				boolean found;
		    	
		    	attendance = chooser.getSelectedFile();
		    	fReader = new FileReader(attendance);
				bReader = new BufferedReader(fReader);
				// Add the new column. We need a date picker for this
				roster.get(0).add("November 26");
				columnSize = roster.get(0).size();
				
				// All students start at zero minutes of attendance
				for (i = 1; i < roster.size(); ++i)
				{
					roster.get(i).add("0");
				}

				line = "";
	    		while ((line = bReader.readLine()) != null)
	    		{
					// Create an array of Strings to hold asurite and time connected
					// from the attendance CSV file
					tempArr = line.split(DELIMITER);

					
					// We don't need to check the column names
					i = 1;
					found = false;

					while (found != true && i != roster.size())
					{
						// asurite matches
						if (tempArr[0].equals(roster.get(i).get(5)))
						{
							found = true;
							int currentVal = Integer.parseInt(roster.get(i).get(columnSize - 1));
							currentVal += Integer.parseInt(tempArr[1]);
							String updated = Integer.toString(currentVal);
							roster.get(i).set(columnSize - 1, updated);
						}
						++i;
					}  			
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
