import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 *  The MenuBar class is responsible for unifying all UI elements together.
 *  It contains global variables used for maintaining databases and JPanels.
 *  The MenuBar class also contains implementation for menubar elements, which are used to toggle 
 *  different views. 
 * 
 */
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
		
		//Create and add submenu items.
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

	/**
	 * actionPerformed is an overridden method used to provide interactive functionality to menubar button elements.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//Handle roster interaction.
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
		 //Handle attendance interaction.
		if(e.getSource().equals(addAttendance))
		{
			if (rosterManager != null)
			{
				//Create a datepicker object that handles attendance.
				DatePicker datePicker = new DatePicker(this);	
			}
			else
			{
				String message = "Roster must be loaded before attendance"
					+ "\ndata can be added.";
				JOptionPane.showMessageDialog(window, message);
			}
		}
		//Handle savedata interaction.
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
		//handle graph interaction.
		if(e.getSource().equals(plotData))
		{
			//Diagnostics:
			System.out.println(roster.get(0).size());
			System.out.println(roster.size());
			
			JFrame plotFrame = new JFrame();
			ArrayList<String> dates = new ArrayList<String>();
			
			//Retrieve dates.
			for(int i = 6; i < roster.get(0).size(); i++)
			{
				dates.add(roster.get(0).get(i));
			}
			
			//Create anew plotPanel with dates.
			DataPlotPanel plotPanel = new DataPlotPanel(dates);
			
			//Percentage Groupings.
			int p10 = 0, p20 = 0, p30 = 0, p40 = 0, p50 = 0, 
					p60 = 0, p70 = 0, p80 = 0, p90 = 0, p100 = 0;
			
			//Plot attendance data on the graph.
			double percentAttendance = 0;
			for(int j = 6; j < roster.get(0).size(); j++)
			{
				for(int i = 1; i < roster.size(); i++) {
					percentAttendance = (Double.parseDouble(roster.get(i).get(j)) / 75.0) * 100;
					//Group attendance data to percentage categories.
					if(percentAttendance > 5.0 && percentAttendance < 15.0) {p10++;}
					else if(percentAttendance > 15.0 && percentAttendance < 25.0) {p20++;}
					else if(percentAttendance > 25.0 && percentAttendance < 35.0) {p30++;}
					else if(percentAttendance > 35.0 && percentAttendance < 45.0) {p40++;}
					else if(percentAttendance > 45.0 && percentAttendance < 55.0) {p50++;}
					else if(percentAttendance > 55.0 && percentAttendance < 65.0) {p60++;}
					else if(percentAttendance > 65.0 && percentAttendance < 75.0) {p70++;}
					else if(percentAttendance > 75.0 && percentAttendance < 85.0) {p80++;}
					else if(percentAttendance > 85.0 && percentAttendance < 95.0) {p90++;}
					else if(percentAttendance > 95.0 && percentAttendance < 100.0) {p100++;}
				}
				//Add Data.
				plotPanel.addData(roster.get(0).get(j), 10, p10);
				plotPanel.addData(roster.get(0).get(j), 20, p20);
				plotPanel.addData(roster.get(0).get(j), 30, p30);
				plotPanel.addData(roster.get(0).get(j), 40, p40);
				plotPanel.addData(roster.get(0).get(j), 50, p50);
				plotPanel.addData(roster.get(0).get(j), 60, p60);
				plotPanel.addData(roster.get(0).get(j), 70, p70);
				plotPanel.addData(roster.get(0).get(j), 80, p80);
				plotPanel.addData(roster.get(0).get(j), 90, p90);
				plotPanel.addData(roster.get(0).get(j), 100, p100);
				
				//Reset counters.
				p10 = 0; p20 = 0; p30 = 0; p40 = 0; p50 = 0; 
				p60 = 0; p70 = 0; p80 = 0; p90 = 0; p100 = 0;
			}
			
			//Finalize data and create JFrame
            plotPanel.createPlotPanel();
            window.add(plotPanel);
            window.setVisible(true);
            plotFrame.add(plotPanel);
            plotFrame.setSize(700,500);
            plotFrame.setVisible(true);
		}
		//About tab.
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
