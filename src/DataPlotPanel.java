// Alexander Gaouette - agaouett
// Matin Massoudi - mmassou1
// David Ragipi - dragipi
// Orlando Rios - orios1
// Cameron Woehler - cwoehler
// CSE 360 - Section 70606
// Final Project
// DataPlotPanel.java

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
	ArrayList<String> dates;
	ArrayList<XYSeries> dataSeries; 

	/**
	 * Initialize datasets with names of dates.
	 * @param dataset1Name
	 * @param dataset2Name
	 */
	public DataPlotPanel(ArrayList<String> dates) {
		this.dates = dates;
		dataset = new XYSeriesCollection();
		dataSeries = new ArrayList<XYSeries>();
		for(int i = 0; i < dates.size(); i++) 
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
	 * @param x
	 * @param y
	 */
	public void addData(String whichDataset, double x, double y)
	{	
		for (int i = 0; i < dates.size(); i++) 
		{
			if(whichDataset.equals(dates.get(i)))
			{
				dataSeries.get(i).add(x, y);
				return;
			}
		}
		
		System.err.println("Could not find dataset!");
	}


}
