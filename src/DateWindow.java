import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class DateWindow implements ActionListener{
	JFrame frame;
    JPanel buttonPane, fieldsPanel;
    JLabel day, month;
    JTextField dayField, monthField;
    JButton ok;
	DateWindow(){
		frame = new JFrame("Attendance Date");
        buttonPane = new JPanel();
        fieldsPanel = new JPanel();
        day = new JLabel("Day");
        month = new JLabel("Month");
        dayField = new JTextField("");
        monthField = new JTextField("");
        ok = new JButton("OK");
        ok.addActionListener(this);
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));
        buttonPane.setLayout(new FlowLayout());

        fieldsPanel.add(day);
        fieldsPanel.add(dayField);
        fieldsPanel.add(month);
        fieldsPanel.add(monthField);
        buttonPane.add(ok);
        frame.add(fieldsPanel, BorderLayout.PAGE_START);
        frame.add(buttonPane, BorderLayout.PAGE_END);
        //frame.setSize();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(getDate());
		boolean error = false;
		int month;
		try
	    { 
	      month = Integer.parseInt(monthField.getText());
	      if(!(month > 0 && month < 13)) { 
	    	  errorPopUp();
	    	  error = true;
	      }
	    }
	    catch (NumberFormatException nfe)
	    {
	    	errorPopUp();
	    	error = true;
	    }
		try
	    { 
	      int day = Integer.parseInt(dayField.getText());
	      if(!(day > 0 && day < 32)) {
	    	  errorPopUp();
	    	  error = true;
	      }
	      
	    }
	    catch (NumberFormatException nfe)
	    {
	    	errorPopUp();
	    	error = true;
	    }	
		
		if(error == false) {
			frame.dispose();
			System.out.println(getDate());
		}
	}
	String getDate() {
		return dayField.getText() + "," + monthField.getText();
	}
	void errorPopUp() {
		JFrame whatever = new JFrame();
		JOptionPane.showMessageDialog(whatever,"Hey idiot enter a proper date");
	}
}
