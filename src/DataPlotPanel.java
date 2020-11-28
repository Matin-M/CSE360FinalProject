import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class DataPlotPanel extends JPanel {
	//Serial version ID.
	private static final long serialVersionUID = 6294689542092367723L;
	
	//DataPlot elements.
	XYSeriesCollection dataset;
	JFreeChart chart;
	ChartPanel panel;
	XYPlot plot;
	
	//Data
	ArrayList<XYSeries> dataSeries; 

	/**
	 * Initialize datasets with names of dates.
	 * @param dataset1Name
	 * @param dataset2Name
	 */
	public DataPlotPanel(ArrayList<String> dates) {
		dataset = new XYSeriesCollection();
		for(int i = 0; i < dataSeries.size(); i++) 
		{
			dataSeries.add(new XYSeries(dates.get(i)));
		}
	}
	
	/**
	 * Once data has been added, call createPlotPanel.
	 * createPlotPanel will create the plot with the added data.
	 */
	public void createPlotPanel()
	{
		for (int i = 0; i  < dataSeries.size(); i++) 
		{
			dataset.addSeries(dataSeries.get(i));
		}
		chart = ChartFactory.createScatterPlot
				("Attendance Graph", "% of Attendance", "# of students", dataset);
		plot = (XYPlot)chart.getPlot();
		plot.setBackgroundPaint(new Color(255,228,196)); 
		panel = new ChartPanel(chart);
		
		//Add chartpanel to JPanel.
		add(panel);
	}
	
	/**
	 * Adds data to a specific dataset.
	 * 
	 * @param whichDataset
	 * @param val1
	 * @param val2
	 */
	public void addData(String whichDataset, double val1, double val2)
	{	
		for (int i = 0; i < dataSeries.size(); i++) 
		{
			if(whichDataset.equals(dataSeries.get(i)))
			{
				dataSeries.get(i).add(val1, val2);
				return;
			}
		}
		
		System.err.println("Could not find dataset!");
	}

	/**
	 * This method is used to demonstrate adding data to the graph.
	 
	public void createTestDataset() {
		// TODO Auto-generated method stub
	    series1.add(1, 72.9);  
	    series1.add(2, 81.6);  
	    series1.add(3, 88.9);  
	    series1.add(4, 96);  
	    series1.add(5, 102.1);  
	    series1.add(6, 108.5);  
	    series1.add(7, 113.9);  
	    series1.add(8, 119.3);  
	    series1.add(9, 123.8);  
	    series1.add(10, 124.4);  
	  
	    series2.add(1, 72.5);  
	    series2.add(2, 80.1);  
	    series2.add(3, 87.2);  
	    series2.add(4, 94.5);  
	    series2.add(5, 101.4);  
	    series2.add(6, 107.4);  
	    series2.add(7, 112.8);  
	    series2.add(8, 118.2);  
	    series2.add(9, 122.9);  
	    series2.add(10, 123.4);  
	   
	}
	
	 */

}
