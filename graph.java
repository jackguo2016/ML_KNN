package com.ZiyanGuo;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JFrame;
import java.text.SimpleDateFormat;
import java.util.Random;

public class graph extends ApplicationFrame{
    public graph( String applicationTitle , String chartTitle,double [] kvalues ) {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Y value","Error rate",
              createDataset(kvalues),
                PlotOrientation.VERTICAL,
                true,true,false);
        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 960 , 500 ) );
        setContentPane( chartPanel );

    }
    public DefaultCategoryDataset createDataset(double [] kvalues ) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

        for(int i =0; i< kvalues.length;i++){

            dataset.addValue( kvalues[i] , "Error rate" , Integer.toString((i+1)) );
        }

        return dataset;
    }




}
