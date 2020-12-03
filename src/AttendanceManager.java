// Alexander Gaouette - agaouett
// Matin Massoudi - mmassou1
// David Ragipi - dragipi
// Orlando Rios - orios1
// Cameron Woehler - cwoehler
// CSE 360 - Section 70606
// Final Project
// AttendanceManager.java


import java.io.*;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The class AttendanceManager modifies an existing roster of student data 
 * by having the user select a CSV file containing a student's ASURITE ID
 * and an integer representing the duration the student attended the class 
 * session. A student can connect more than once to the session; additional
 * connections are appended to the roster at the correct postion.
 * AttendanceManager also notifies the user of students who connected to the
 * session that are not found on the roster.
 */
public class AttendanceManager extends JFrame {
	
	private JFileChooser chooser;
	private FileFilter filter;
	private ArrayList<ArrayList<String>> attendanceRoster;

	/**
	 * Constructor for the AttendanceManager class.
	 */
	public AttendanceManager() {
		chooser = new JFileChooser();
		filter = new FileNameExtensionFilter("csv files", "csv");
		chooser.addChoosableFileFilter(filter);
	}
	
	/**
	 * Opens an parses a CSV file of the format: ASURITE, ConnectionTime.
	 * Multiple connections from the same ASURITE ID are handled by adding
	 * the sum of all connections to the roster.
	 * @param roster - An ArrayList of ArrayList<String> that represents
     * information about students in a class. The ArrayList<String> at index 0
     * of the ArrayList must contain the column header names. 
	 * @param date - A String representing the date in the form of "MM/dd".
	 * For example, the date October 9th should be passed as "10/09"
	 */
	public void openAttendanceFile(ArrayList<ArrayList<String>> roster, String date)
	{
		attendanceRoster = roster;
		chooser.setDialogTitle("Choose a file");
		this.getContentPane().add(chooser);
		chooser.setVisible(true);
	    int returnVal = chooser.showOpenDialog(this);
		
		// After the user chooses a file
	    if (returnVal == JFileChooser.APPROVE_OPTION)
	    {	    	
	    	try 
	    	{
	    		File attendance;
		    	FileReader fReader;
		    	BufferedReader bReader;
		    	final String DELIMITER = ",";
		    	String line;
				String[] tempArr;
				int i, columnSize;
				ArrayList<String> students = new ArrayList<String>();
				ArrayList<String[]> visitors = new ArrayList<String[]>();
				boolean found;
		    	
		    	attendance = chooser.getSelectedFile();
		    	fReader = new FileReader(attendance);
				bReader = new BufferedReader(fReader);
				

				// Add the date passed as argument
				attendanceRoster.get(0).add(date);
				// Get the size of the roster for indexing
				columnSize = attendanceRoster.get(0).size();
				
				// All students start at zero minutes of attendance
				for (i = 1; i < attendanceRoster.size(); ++i)
				{
					attendanceRoster.get(i).add("0");
					
				}
				

				line = "";
	    		while ((line = bReader.readLine()) != null)
	    		{
					// Create an array of Strings to hold asurite and time connected
					// from the attendance CSV file
					tempArr = line.split(DELIMITER);

					
					// We don't need to check the column names, start at 1
					i = 1;
					found = false;

					// Searching for matches of asurite in the roster and 
					// the attendance file
					while (found != true && i != attendanceRoster.size())
					{
						String asurite = attendanceRoster.get(i).get(5);
						
						// asurite matches
						if (tempArr[0].equals(asurite))
						{
							// Keep track of which students are added from
							// attendance file
							if (students.contains(asurite) == false)
							{
								students.add(asurite);
							}
							found = true;
							int currentVal = Integer.parseInt(attendanceRoster.get(i).get(columnSize - 1));
							// Add to the current total of student connected time
							currentVal += Integer.parseInt(tempArr[1]);
							String updated = Integer.toString(currentVal);
							attendanceRoster.get(i).set(columnSize - 1, updated);
						}
						++i;
					}
					// Visitor is in classroom
					if (found == false)
					{
						// Add their asurite and their time connected
						visitors.add(tempArr);
					}
	    		}
	    		bReader.close();
				fReader.close();

				String label = "";

				// Build a string to display in the dialog box
				// Total number of valid students found in the file
				label += "Attendance data added for " + students.size() + " student(s)\n";
				// If additional ASURITE ids were in the file but not the roster
				if (visitors.size() != 0)
				{	
					// Only one visitor was found
					if (visitors.size() == 1)
					{
						label += visitors.size() + " additional attendee was found:\n\n";
						label += visitors.get(0)[0] + " connected for " + visitors.get(0)[1] + " minute(s)\n";
					}
					// Additional visitors were found
					else
					{
						label += visitors.size() + " additional attendees were found:\n\n";
						for (int j = 0; j < visitors.size(); ++j)
						{
							label += visitors.get(j)[0] + " connected for " + visitors.get(j)[1] + " minute(s)\n";
						}
					}
				}

				// Display the information to the user
				JOptionPane.showMessageDialog(null, label);
				
	    	}
	    	catch (IOException ioe)
	    	{
				System.out.println(ioe.getLocalizedMessage());
			}
		}
	}
}
