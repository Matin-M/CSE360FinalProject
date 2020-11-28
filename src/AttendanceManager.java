import java.io.*;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AttendanceManager extends JFrame {
	
	private JFileChooser chooser;
	private FileFilter filter;
	private ArrayList<ArrayList<String>> attendanceRoster;

	public AttendanceManager() {
		attendanceRoster = new ArrayList<ArrayList<String>>();
		chooser = new JFileChooser();
		filter = new FileNameExtensionFilter("csv files", "csv");
		chooser.addChoosableFileFilter(filter);
	}
	
	public void openAttendanceFile()
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
				attendanceRoster.get(0).add("November 26");
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
						// asurite matches
						if (tempArr[0].equals(attendanceRoster.get(i).get(5)))
						{
							found = true;
							int currentVal = Integer.parseInt(attendanceRoster.get(i).get(columnSize - 1));
							currentVal += Integer.parseInt(tempArr[1]);
							String updated = Integer.toString(currentVal);
							attendanceRoster.get(i).set(columnSize - 1, updated);
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
	

	public ArrayList<ArrayList<String>> getAttendanceRoster() {
		// TODO Auto-generated method stub
		return attendanceRoster;
	}

}
