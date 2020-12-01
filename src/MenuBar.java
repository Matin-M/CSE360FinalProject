import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Calendar;
import java.util.Date;

public class MenuBar extends JFrame implements ActionListener{
	
	//Menubar items.
	JMenuBar menuBar;
	JMenu File;
	JMenuItem loadRoster, addAttendance, saveData, plotData, About;

	// Roster Manager and DataTable need to be class scope for now until we get
	// ActionListener implemented
	RosterManager rosterManager;
	AttendanceManager attendanceManager;
	ArrayList<ArrayList<String>> roster;
	DataTable table;
	
	//Main window view.
	MainViewWindow window = null;
	/**
	 * Default constructor.
	 */
	public MenuBar() {}
	
	/**
	 * accept window argument and add JMenuBar.
	 * @param window
	 */
	public MenuBar(MainViewWindow window)
	{
		//Assign window field.
		this.window = window;
	
		//Create menu bar object.
		menuBar = new JMenuBar();
		
		//Create menu bar items.
		File = new JMenu("File");
		File.addActionListener(this);
		menuBar.add(File);
		
		About = new JMenuItem("About");
		About.addActionListener(this);
		menuBar.add(About);
		
		
		loadRoster = new JMenuItem("Load Roster");
		loadRoster.addActionListener(this);
		File.add(loadRoster);
		
		addAttendance = new JMenuItem("Add Attendance");
		addAttendance.addActionListener(this);
		File.add(addAttendance);
		
		saveData = new JMenuItem("Save Data");
		saveData.addActionListener(this);
		File.add(saveData);
		
		plotData = new JMenuItem("Plot Data");
		plotData.addActionListener(this);
		File.add(plotData);
		
		window.setJMenuBar(menuBar);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(loadRoster))
		{
			if (rosterManager != null)
			{
				int option;
				String message = "Open new roster?"
					+ "\nChanges will not be saved!";
				String title = "Roster already loaded";
				option = JOptionPane.showOptionDialog(window, message, title, 
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, null, null);
				
				if (option == JOptionPane.YES_OPTION)
				{
					rosterManager = new RosterManager();
					rosterManager.openFile();
					roster = rosterManager.getRoster();
					table.clearTable();
					table = new DataTable(window, roster);
				}

			}
			else
			{
				rosterManager = new RosterManager();
				rosterManager.openFile();
				roster = rosterManager.getRoster();
				table = new DataTable(window, roster);
			}
		}
		
		if(e.getSource().equals(addAttendance))
		{
			System.out.println("hello");
			if (rosterManager != null)
			{
				
				DateWindow date = new DateWindow();
				attendanceManager = new AttendanceManager();
				attendanceManager.openAttendanceFile(roster);
				table.update();
				
			}
			else
			{
				String message = "Roster must be loaded before attendance"
					+ "\ndata can be added.";
				JOptionPane.showMessageDialog(window, message);
			}
		}
		
		if(e.getSource().equals(saveData))
		{
			if (rosterManager != null)
			{
				rosterManager.exportRoster();
			}
			else
			{
				String message = "Roster must be loaded before"
					+ "\ndata can be saved.";
				JOptionPane.showMessageDialog(window, message);
			}
		}
		
		if(e.getSource().equals(plotData))
		{
      //To be implemented by Matin.
		}
		
		if(e.getSource().equals(About))
		{

			JOptionPane.showMessageDialog(window, "Version 1.0 "
					+ "\n Created by: \nAlexander Gaouette\r\n"
					+ "Matin Massoudi\r\n"
					+ "David Ragipi\r\n"
					+ "Orlando Rios\r\n"
					+ "Cameron Woehler\r\n"
					+ "");
		}
		
	}
}
