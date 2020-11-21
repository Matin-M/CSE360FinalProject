/**
 * MainViewWindow.java creates canvas and aggregates all the views.
 * 
 *
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainViewWindow extends JFrame{
	
	/**
	 * Default constructor.
	 */
	MainViewWindow()
	{
		setLayout(new FlowLayout());
		//Add new elements here.
		
		pack();
		setVisible(true);
		
	}
	

	/**
	 * Main Method
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainViewWindow window = new MainViewWindow();
		MenuBar menuBar = new MenuBar(window);
		
		//Set window sizes.
		window.setSize(500,500);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
	}

}
