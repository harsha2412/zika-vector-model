/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package vectormodel;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author harsha
 */
public class Place extends javax.swing.JFrame {
    
    public int totalPop; //initial population
    public int sPop;
    public int iPop;
    public int rPop;
    public int tvPop;
    public int svPop;
    public int ivPop;
    public ArrayList<Human> totalHPopulation;
  
    public ArrayList<Vector> totalVPopulation;
  
    private int id;
    private String name;
    public int start;
    public int end;
    public boolean startOutbreak=false;
    public boolean endOutbreak=false;
    //   private int mosquitoesPerPerson ;
    //public ArrayList<Integer[]> recoveredVPopulation;
    private double bitesSuspectible;
    private double bitesInfectious;
   public JFreeChart transmissionChart;
   public  JFreeChart transmissionChartV;
    private double p; //immunity factor !
     final String series1 = "Susceptible";
    final String series2 = "Infectious";
    final String series3 = "Recovered ";
    final String series4 = "Total ";
    DefaultCategoryDataset transmissionDataset;
    DefaultCategoryDataset transmissionDatasetV;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalPop() {
        return this.totalPop;
    }

    public void setTotalPop(int totalPop) {
        this.totalPop = totalPop;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
  
  public void init(){
          totalHPopulation = new ArrayList<Human>();
       
        totalVPopulation = new ArrayList<Vector>();
  }
    public void initializePopulation(int mosquitoesPerPerson,int pid){
       int totalHumans = getTotalPop();
        //System.out.println(totalHumans+" "+infectedHumans+" "+mosquitoesPerPerson+"\n In Place Constructor");
        bitesSuspectible =0.5;
        bitesInfectious= 1;
        //Initialization for Day 1
        totalHPopulation = new ArrayList<Human>();
       
        totalVPopulation = new ArrayList<Vector>();
        
        for (int i = 0; i < totalHumans; i++) {
            Human h = new Human(i);
            h.setBelongsTo(pid);
            totalHPopulation.add(h);
           
        }
        for (int i = 0; i < totalHumans * mosquitoesPerPerson; i++) {
            Vector v = new Vector(i);
            v.isAdult=true;
            totalVPopulation.add(v);
       
        }
        System.out.println("Total Humans in pid "+totalHumans+" "+pid);

     
    }
   public void seedInfection(int infectedHumans, int infectedVectors){
       int totalHumans = this.getTotalHPopulation();
          int count = 0;
        while (count < infectedHumans) {
            Random rand = new Random();
            int value = rand.nextInt(totalHumans);
            if (!totalHPopulation.get(value).isInfectious) {
                totalHPopulation.get(value).isInfected = true;
                totalHPopulation.get(value).isInfectious = true;
                
                count++;
            }

        }
        count = 0;
        while (count < infectedVectors) {
            Random rand = new Random();
            int value = rand.nextInt(getTotalVPopulation());
            if (!totalVPopulation.get(value).isInfectious) {
                totalVPopulation.get(value).isInfectious = true;
                 totalVPopulation.get(value).isInfected = true;
                 
                count++;
            }

        }
       
   }
   
   public Place(){
       transmissionDataset = new DefaultCategoryDataset();
       transmissionDatasetV = new DefaultCategoryDataset();
       
   }


    public int getTotalHPopulation() {
        return totalHPopulation.size();
    }

    public int getSusceptibleHPopulation() {
        int count =0;
        for(int i=0;i<totalHPopulation.size();i++){
            Human h = totalHPopulation.get(i);
            if(!h.isInfected && !h.isInfectious && !h.isRecovered){
                count++;
            }
        }
        return count;
       
    }

    public int getInfectedHPopulation() {
       int count =0;
        for(int i=0;i<totalHPopulation.size();i++){
            Human h = totalHPopulation.get(i);
            if(h.isInfectious || h.isInfected){
                count++;
            }
        }
        return count;
    }

    public int getRecoveredHPopulation() {
        int count =0;
        for(int i=0;i<totalHPopulation.size();i++){
            Human h = totalHPopulation.get(i);
            if(h.isRecovered){
                count++;
            }
        }
        return count;
    }

    public int getTotalVPopulation() {
        return totalVPopulation.size();
    }

    public int getSusceptibleVPopulation() {
         int count =0;
        for(int i=0;i<totalVPopulation.size();i++){
            Vector v = totalVPopulation.get(i);
            if(!v.isInfected && !v.isInfectious && v.isAdult){
                count++;
            }
        }
        return count;
    }

    public int getInfectedVPopulation() {
       int count =0;
        for(int i=0;i<totalVPopulation.size();i++){
            Vector v = totalVPopulation.get(i);
            if(v.isInfected && v.isInfectious && v.isAdult ){
                count++;
            }
        }
        return count;
    }

   

    public double getBitesSuspectible() {
        return bitesSuspectible;
    }

    public void setBitesSuspectible(double bitesSuspectible) {
        this.bitesSuspectible = bitesSuspectible;
    }

    public double getBitesInfectious() {
        return bitesInfectious;
    }

    public void setBitesInfectious(double bitesInfectious) {
        this.bitesInfectious = bitesInfectious;
    }

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }
    
    public boolean isPlaceInfected(int start){
        if(this.getInfectedHPopulation()>0 && this.endOutbreak==false){
            if(!this.startOutbreak ){
                System.out.println("Outbreak for "+this.getName()+" starts on day "+start);
                this.start=start;
            this.startOutbreak = true;
            
            }
            return true;
        }
        else
            return false;
       
    }
    
    public void createChart() throws IOException{
        JFrame myFrame = new JFrame("Transmission Dynamics in Humans in "+this.getName());
          int width = 640;
        /* Width of the image */
        int height = 480;
        transmissionChart = ChartFactory.createLineChart("Transmission Dynamics in Humans for " + this.getName(), "Days", "Population", transmissionDataset, PlotOrientation.VERTICAL, true, true, false);
        File lineChart = new File("LineChartH_main_" + this.getName() + ".jpeg");
        ChartUtilities.saveChartAsJPEG(lineChart, transmissionChart, width, height);
        transmissionChartV = ChartFactory.createLineChart("Transmission Dynamics in Vectors", "Days", "Population", transmissionDatasetV, PlotOrientation.VERTICAL, true, true, false);

        /* Height of the image */
        File lineChartV = new File( "LineChartV_main_" + this.getName() + ".jpeg" ); 
         ChartUtilities.saveChartAsJPEG(lineChartV ,transmissionChartV, width ,height);
        System.out.println("\nCreating the Frame !!! \n");

        ChartPanel chartPanel = new ChartPanel(transmissionChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(chartPanel, BorderLayout.NORTH);
        myFrame.add(jPanel);
        myFrame.pack();
        myFrame.setVisible(true);
        System.out.println("\n\n\n\n\n\nThe End\n\n\n\n\n");
    }
  
    

}