package com.ZiyanGuo;
import org.jfree.ui.RefineryUtilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;


public class Main {
    private static List<Data> list = new ArrayList<Data>() ; //创造表里的数据的实例放在这个列表里
    private static int errorcount = 0;
    private static int k = 0;


    public static void main(String[] args) {

//        Scanner myObj = new Scanner(System.in);  // 创建Scanner对象
//        System.out.println("Please enter the K you want to use: ↓");
//
//        k = Integer.parseInt(myObj.nextLine());  // 读取用户输入
//        System.out.println("This turn K = "+k);


        long startTime = System.nanoTime();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/com/ZiyanGuo/diabetes.csv"));//换成你的文件名
            reader.readLine();//第一行信息，为标题信息，不用，如果需要，注释掉
            String line = null;
            while((line=reader.readLine())!=null){
                //System.out.println(line);
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分

                String last = item[0];//这就是你要的数据了
                //System.out.println(item.length);
                Double value = Double.parseDouble(last);//如果是数值，可以转化为数值
                //System.out.println(value);
                list.add(new Data(
                        Double.parseDouble(item[0]),
                        Double.parseDouble(item[1]),
                        Double.parseDouble(item[2]),
                        Double.parseDouble(item[3]),
                        Double.parseDouble(item[4]),
                        Double.parseDouble(item[5]),
                        Double.parseDouble(item[6]),
                        Double.parseDouble(item[7]),
                        Double.parseDouble(item[8])
                ));

            }
            System.out.println("数据库的"+list.size());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //到这读取数据，创建列表的工作结束
        Main mm = new Main();
        double [] errorratearray = new double[50];
        for(int i = 1; i<50;i++){
            k = i;
            errorcount = 0;
            errorratearray[i]= mm.knnFunction();
        }
        //mm.knnFunction();
        graph chart = new graph(
                "K values Vs Error rate" ,
                "K values Vs Error rate",errorratearray);
        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
        //上面这个是用来画折线图的
        mm.makesdGraph();
        long endTime = System.nanoTime();

        long duration = (endTime - startTime);
        System.out.println("Totoal time use: "+duration/1000000000+"S");

}

    public double knnFunction(){
        int tp=0,tn=0,fp=0,fn=0;//true postive,true negitive, false postive,false negitive
        List<Data> testlist = new ArrayList<Data>() ;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/com/ZiyanGuo/testt.csv"));//换成你的文件名
            reader.readLine();//第一行信息，为标题信息，不用，如果需要，注释掉
            String line = null;
            while((line=reader.readLine())!=null){
                //System.out.println(line);
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分

                String last = item[0];//这就是你要的数据了
                //System.out.println(item.length);
                Double value = Double.parseDouble(last);//如果是数值，可以转化为数值
                //System.out.println(value);
                testlist.add(new Data(
                        Double.parseDouble(item[0]),
                        Double.parseDouble(item[1]),
                        Double.parseDouble(item[2]),
                        Double.parseDouble(item[3]),
                        Double.parseDouble(item[4]),
                        Double.parseDouble(item[5]),
                        Double.parseDouble(item[6]),
                        Double.parseDouble(item[7]),
                        Double.parseDouble(item[8])
                ));

            }
            //System.out.println(testlist.size());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //要测试的数据也都加入了,现在开始计算每个测试点到样本点的距离，把距离算好的数据点加到一个list里面·

        for(Data testPoint: testlist){
            double[] vector1 = testPoint.getall();
            List<Data> anslist = new ArrayList<Data>() ; //算好距离的数据点就存进去
            for (Data dataPoint: list){
                double[] vector2 = dataPoint.getall();
                double mhd = ma_distance(vector1,vector2);
                double osd = sim_distance(vector1,vector2);
                double mk_distance = ma_distance(vector1,vector2);
                dataPoint.setDistance(mhd);
                dataPoint.setMkDistance(mk_distance);
                dataPoint.setSiistance(osd);
                anslist.add(dataPoint);
            }
            //现在选用的是曼哈顿距离，后面看看要不要改变一下

            Collections.sort(anslist, Comparator.comparingDouble(Data::getMkDistance));//排序了list从小到大的距离顺序


            double count = 0;
            double gaseoutcome = 0.0;//猜测结果

            int ii = k;
            while(ii>0){
                count += anslist.get(ii-1).getOutcome();//把k个结果都加起来，如果没有超过半数就说明结果是0，反之就是1
                ii--;
               // System.out.println("这次的count取值 ="+count);
            }
            if (count<k/2){//如果没有超过半数就说明结果是0
               gaseoutcome = 0.0;
               // System.out.println("这次的取值 ="+gaseoutcome);
            }
            else{//反之就是1
               gaseoutcome = 1.0;
//               System.out.println("这次的取值 ="+gaseoutcome);
            }
            if(gaseoutcome != testPoint.getOutcome()){
                errorcount ++;
            }

            //在这写那个矩阵的计数器下面来打印出来
            if(gaseoutcome != testPoint.getOutcome()&&testPoint.getOutcome() == 1.0){
                fp+=1;
            }
            if(gaseoutcome != testPoint.getOutcome()&&testPoint.getOutcome() == 0.0){
                fn+=1;
            }
            if(gaseoutcome == testPoint.getOutcome()&&testPoint.getOutcome() == 1.0){
                tp+=1;
            }
            if(gaseoutcome == testPoint.getOutcome()&&testPoint.getOutcome() == 0.0){
                tn+=1;
            }






        }
        //以上是KNN的主要部分也就是计算距离和排序再加上通过k值来决定结果，在进行判断是否错误或者正确来改变errorcount.
        System.out.println("=========================================================================");
        System.out.println("This round k is: "+ k);
        System.out.println("Number of error is: "+ errorcount);
        String errorRate = String.format("%.2f", (float) errorcount / (float) (testlist.size()) * 100);
        System.out.println("this time the error rate is: "+ errorRate+"%");
        System.out.println("        || Positive | Negative");
        System.out.println("------------------------------");
        System.out.println("Positive|| "+tp +"        |    "+ fp);
        System.out.println("------------------------------");
        System.out.println("Negative|| "+fn +"        |    "+ tn);
        double acc = (float)(tp + tn) / (float)(tp + tn + fp + fn);
        System.out.println("Accuracy is "+ acc);


        System.out.println("=========================================================================");
        return Double.parseDouble(errorRate);
    }

    public double sim_distance(double[] vector1, double[] vector2) { //欧式距离
        double distance = 0;

        if (vector1.length == vector2.length) {
            for (int i = 0; i < vector1.length; i++) {
                double temp = Math.pow((vector1[i] - vector2[i]), 2);
                distance += temp;
            }
            distance = Math.sqrt(distance);
        }
        return distance;
    }

    public double ma_distance(double[] vector1, double[] vector2) { //曼哈顿距离
        double distance = 0;

        if (vector1.length == vector2.length) {
            for (int i = 0; i < vector1.length; i++) {
                double temp =  Math.abs(vector1[i] - vector2[i]);
                distance += temp;
            }
            distance = Math.sqrt(distance);
        }
        return distance;
    }

    public double mk_distance(double[] vector1, double[] vector2) { //闵可夫斯基距离
        //List<Double> distance = new ArrayList<Double>();
        Double max = 0.0 ;
        if (vector1.length == vector2.length) {
            for (int i = 0; i < vector1.length; i++) {
                double temp =  Math.abs(vector1[i] - vector2[i]);
                //distance.add(temp);
                if(temp >max){
                    max = temp;
                }
            }

        }
        return max;
    }

    public void makesdGraph(){//散点图
        //设置第一个
        XYSeries firefox = new XYSeries("Outcome 0");
        XYSeries chrome = new XYSeries("Outcome 1");
        for(Data graphData:list){
            if (graphData.getOutcome() == 0.0){

                firefox.add(graphData.getPregnancies(), graphData.getInsulin());
            }
            else{

                chrome.add(graphData.getPregnancies(), graphData.getInsulin());
            }
        }


        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(firefox);
        dataset.addSeries(chrome);
        JFreeChart freeChart = ChartFactory.createScatterPlot(
                "Pregnancies vs Insulin",// 图表标题
                "Pregnancies",//y轴方向数据标签
                "Insulin",//x轴方向数据标签
                dataset,//数据集，即要显示在图表上的数据
                PlotOrientation.VERTICAL,//设置方向
                true,//是否显示图例
                true,//是否显示提示
                false//是否生成URL连接
        );

        //以面板显示
        ChartPanel chartPanel = new ChartPanel(freeChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 400));

        //创建一个主窗口来显示面板
        JFrame frame = new JFrame("scatter plots");
        frame.setLocation(500, 400);
        frame.setSize(600, 500);

        //将主窗口的内容面板设置为图表面板
        frame.setContentPane(chartPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }




}
