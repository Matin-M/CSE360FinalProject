// Alexander Gaouette - agaouett
// Matin Massoudi - mmassou1
// David Ragipi - dragipi
// Orlando Rios - orios1
// Cameron Woehler - cwoehler
// CSE 360 - Section 70606
// Final Project
// MainViewWindow.java

import javax.swing.*;
import java.awt.*;

/**
 * 
 * The MainViewWindow class initializes a JFrame to be used by all other UI elements.
 *
 */
public class MainViewWindow extends JFrame{
	
	/**
	 * Default constructor handles configurations.
	 */
	MainViewWindow()
	{
		//Set layout to borderlayout.
		setLayout(new BorderLayout());
		pack();
		setVisible(true);
	}

	/**
	 * Main Method
	 * @param args
	 */
	public static void main(String[] args) {
		//Create new window/menubar object, add window to menubar.
		MainViewWindow window = new MainViewWindow();
		MenuBar menuBar = new MenuBar(window);
		
		//Set window sizes.
		window.setSize(700,600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		 
	}

}
