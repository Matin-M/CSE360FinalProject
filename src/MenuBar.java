import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MenuBar extends JFrame implements MenuListener, ActionListener{
	
	//Menubar items.
	JMenuBar menuBar;
	JMenu File;
	JMenuItem loadRoster, addAttendance, saveData, plotData, About;
	RosterManager rosterManager;
	DataTable table;
	DataPlotPanel plotPanel;
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
		File.addMenuListener(this);
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
			rosterManager = new RosterManager();
			rosterManager.openFile();
			ArrayList<ArrayList<String>> roster = rosterManager.getRoster();
			table = new DataTable(window, roster);
			if(plotPanel.isVisible()) {
				plotPanel.setVisible(false);
			}
			window.setVisible(true);
			
		}
		
		if(e.getSource().equals(addAttendance))
		{
			if (rosterManager != null)
			{
				rosterManager.addAttendance();
				ArrayList<ArrayList<String>> roster = rosterManager.getRoster();
				table = new DataTable(window, roster);
			}
			else
			{
				// We need a dialog box that roster has not been loaded yet
				System.out.println("Roster has not been loaded");
			}
		}

		
		if(e.getSource().equals(addAttendance))
		{
			//Do something
		}
		
		if(e.getSource().equals(saveData))
		{
			//Do something
		}
		
		if(e.getSource().equals(plotData))
		{
			//This adds an example graph using test data provided.
			plotPanel = new DataPlotPanel("Val1","Val2");
			plotPanel.createTestDataset();
			plotPanel.addData("Val1", 2.3, 1.2);
			plotPanel.addData("Val1", 12, 13);
			plotPanel.addData("Val1", 1, 9);
			plotPanel.createPlotPanel();
			
			if(table != null && table.isVisible()) {
				System.out.println("dfsadffssdf");
				
				window.dispose();
				window.setVisible(false);
			}
			
			window.add(plotPanel);
			window.setVisible(true);
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

	@Override
	public void menuSelected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}
}
