import java.io.*;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Random;

public class AttendanceManager extends JFrame {
	
	private JFileChooser chooser;
	private FileFilter filter;
	private ArrayList<ArrayList<String>> attendanceRoster;

	public AttendanceManager() {
		chooser = new JFileChooser();
		filter = new FileNameExtensionFilter("csv files", "csv");
		chooser.addChoosableFileFilter(filter);
	}
	
	public void openAttendanceFile(ArrayList<ArrayList<String>> roster)
	{
		attendanceRoster = roster;
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
				int loc, i, rowSize, columnSize, tempDate;
				ArrayList<String> students = new ArrayList<String>();
				ArrayList<String[]> visitors = new ArrayList<String[]>();
				boolean found;
		    	
		    	attendance = chooser.getSelectedFile();
		    	fReader = new FileReader(attendance);
				bReader = new BufferedReader(fReader);
				// Add the new column. We need a date picker for this
				Random test = new Random();
				tempDate = test.nextInt(30) + 1;
				attendanceRoster.get(0).add("November " + tempDate);
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

					
					// We don't need to check the column names
					i = 1;
					found = false;

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
							currentVal += Integer.parseInt(tempArr[1]);
							String updated = Integer.toString(currentVal);
							attendanceRoster.get(i).set(columnSize - 1, updated);
						}
						++i;
					}
					// Visitor is in classroom
					if (found == false)
					{
						visitors.add(tempArr);
					}
	    		}
	    		bReader.close();
				fReader.close();

				String label = "";

				label += "Attendance data added for " + students.size() + " student(s)\n";
				if (visitors.size() != 0)
				{	
					if (visitors.size() == 1)
					{
						label += visitors.size() + " additional attendee was found:\n\n";
						label += visitors.get(0)[0] + " connected for " + visitors.get(0)[1] + " minute(s)\n";
					}
					else
					{
						label += visitors.size() + " additional attendees were found:\n\n";
						for (int j = 0; j < visitors.size(); ++j)
						{
							label += visitors.get(j)[0] + " connected for " + visitors.get(j)[1] + " minute(s)\n";
						}
					}
				}

				JOptionPane.showMessageDialog(null, label);
				
	    	}
	    	catch (IOException ioe)
	    	{
				System.out.println(ioe.getLocalizedMessage());
			}
		}
	}
}
