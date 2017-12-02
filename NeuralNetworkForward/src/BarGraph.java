import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYBarDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class BarGraph extends ApplicationFrame {

   public BarGraph( String applicationTitle , String chartTitle, XYSeriesCollection data) {
      super(applicationTitle);
      JFreeChart lineChart = ChartFactory.createXYBarChart(
    		  chartTitle, 
    		  "Days",
    		  false,
    		  "Price", 
    		   new XYBarDataset(data, 0.5),
    		   PlotOrientation.VERTICAL,
    		   true,
    		   false,
    		   false);
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      setContentPane( chartPanel );
      pack();
   }

   private DefaultCategoryDataset createDataset( ) {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      dataset.addValue( 15 , "schools" , "1970" );
      dataset.addValue( 30 , "schools" , "1980" );
      dataset.addValue( 60 , "schools" ,  "1990" );
      dataset.addValue( 120 , "schools" , "2000" );
      dataset.addValue( 240 , "schools" , "2010" );
      dataset.addValue( 300 , "schools" , "2014" );
      return dataset;
   }

   public static void main( String[ ] args ) {

       XYSeries series1 = new XYSeries("one");
       XYSeries series2 = new XYSeries("two");

       series1.add(0.0, 5.5);
       series1.add(1, -10);
       series1.add(2, 5.5);
       
      // series1.updateByIndex(1, 5.5);

       series2.add(0.5, 15.5);
       series2.add(1.5, -1);
       series2.add(2.5, 15.5);

       XYSeriesCollection data = new XYSeriesCollection();

       data.addSeries(series1);
       data.addSeries(series2);
	   
	   BarGraph chart = new BarGraph(
         "School Vs Years" ,
         "Numer of Schools vs years", data);

      RefineryUtilities.centerFrameOnScreen( chart );
      chart.setVisible( true );
      
   }
}