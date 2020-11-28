import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;


public class DataPlotPanel extends JPanel {
	//Serial version ID.
	private static final long serialVersionUID = 6294689542092367723L;
	
	//DataPlot elements.
	XYSeriesCollection dataset;
	JFreeChart chart;
	ChartPanel panel;
	XYPlot plot;
	
	//Data
	String dataset1Name;
	String dataset2Name;
	XYSeries series1;
	XYSeries series2;

	/**
	 * Initialize datasets with names dataset1Name, dataset2Name
	 * @param dataset1Name
	 * @param dataset2Name
	 */
	public DataPlotPanel(String dataset1Name, String dataset2Name) {
		//Initialize class variables.
		this.dataset1Name = dataset1Name;
		this.dataset2Name = dataset2Name;
		dataset = new XYSeriesCollection();
		series1 = new XYSeries(dataset1Name);
		series2 = new XYSeries(dataset2Name);  
	}
	
	/**
	 * Once data has been added, call createPlotPanel.
	 * createPlotPanel will create the plot with the added data.
	 */
	public void createPlotPanel()
	{
		dataset.addSeries(series1);
		dataset.addSeries(series2); 
		chart = ChartFactory.createScatterPlot
				("Attendance Graph", "% of Attendance", "# of students", dataset);
		plot = (XYPlot)chart.getPlot();
		plot.setBackgroundPaint(new Color(255,228,196)); 
		panel = new ChartPanel(chart);
		
		//Add chartpanel to JPanel.
		add(panel);
		
	}
	
	/**
	 * addData adds a single pair of values to either dataset1 or dataset 2.
	 * 
	 * @param whichDataset
	 * @param val1
	 * @param val2
	 */
	public void addData(String whichDataset, double val1, double val2)
	{
		if(whichDataset.equals(dataset1Name))
		{
			series1.add(val1, val2);
		}
		else if(whichDataset.equals(dataset2Name))
		{
			series2.add(val1, val2);
		}else {
			System.err.println("Invalid dataset name!");
		}
	}

	/**
	 * This method is used to demonstrate adding data to the graph.
	 */
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

}
