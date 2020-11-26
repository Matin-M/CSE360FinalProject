import javax.swing.JPanel;
import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class DataPlotPanel extends JPanel {
	//Serial version ID.
	private static final long serialVersionUID = 6294689542092367723L;
	//DataPlot elements.
	XYDataset dataset;
	JFreeChart chart;
	ChartPanel panel;
	XYPlot plot;

	/**
	 * Configure scatter plot panel.
	 */
	public DataPlotPanel() {
		//Initialize class variables.
		dataset = createDataset();
		chart = ChartFactory.createScatterPlot
				("Attendance Graph", "X-Axis", "Count", dataset);
		plot = (XYPlot)chart.getPlot();
		plot.setBackgroundPaint(new Color(255,228,196)); 
		panel = new ChartPanel(chart);
		
		//Add chartpanel to JPanel.
		add(panel);
	}

	private XYDataset createDataset() {
		// TODO Auto-generated method stub
		XYSeriesCollection dataset = new XYSeriesCollection();
		
		//Test Data
	    XYSeries series1 = new XYSeries("Data 1");  
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
	  
	    dataset.addSeries(series1);  
	      
	    XYSeries series2 = new XYSeries("Data 2");  
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
	  
	    dataset.addSeries(series2);  
	  
		
		return dataset;
	}

}
