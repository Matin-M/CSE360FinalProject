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
			RosterManager rosterManager = new RosterManager();
			ArrayList<ArrayList<String>> roster = rosterManager.openFile();
			DataTable table = new DataTable(window, roster);
			
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
			DataPlotPanel plotPanel = new DataPlotPanel("Val1","Val2");
			plotPanel.createTestDataset();
			plotPanel.addData("Val1", 2.3, 1.2);
			plotPanel.addData("Val1", 12, 13);
			plotPanel.addData("Val1", 1, 9);
			plotPanel.createPlotPanel();
			window.add(plotPanel);
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
