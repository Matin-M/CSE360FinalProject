/**
 * MainViewWindow.java creates canvas and aggregates all the views.
 * 
 *
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainViewWindow extends JFrame{
	
	/**
	 * Default constructor handles configurations.
	 */
	MainViewWindow()
	{
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
