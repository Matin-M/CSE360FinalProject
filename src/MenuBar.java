import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuBar extends JFrame implements MenuListener, ActionListener{
	
	//Menubar items.
	JMenuBar menuBar;
	JMenu File, About;
	JMenuItem loadRoster, addAttendance, saveData, plotData;
	
	/**
	 * Default constructor.
	 */
	public MenuBar()
	{
		setLayout(new FlowLayout());
		
		//Create menu bar object.
		menuBar = new JMenuBar();
		
		File = new JMenu("File");
		File.addMenuListener(this);
		menuBar.add(File);
		
		About = new JMenu("About");
		About.addMenuListener(this);
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
		
		this.setJMenuBar(menuBar);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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
