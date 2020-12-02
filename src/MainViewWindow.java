import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
