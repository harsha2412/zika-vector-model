/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package vectormodel;

/**
 *
 * @author harsha
 */
public class Vector {

  public int iPeriod =0;
   private int alpha; //infectiousParameter = 1/7
   //private double gamma; //recovery parameter = 1/5
    private double tPr; //transmissionProbabililty = 0.75
    private int lifeSpan; //per day = 4 for FRench Polynesia
    private int growthPeriod ;
    public int gPeriod = 0;
    public boolean isAdult;
    //private double birthsPerDay; // = 13 for French Poly !!
    public boolean isInfected;
    public boolean isInfectious;
   // private int birthsPerDay;
    private int id;
    public int totalEggs = 5;
    public int lPeriod ;
   // public boolean isRecovered;
    
     public Vector(int id) {
        this.alpha =7;
        this.id = id;
        this.tPr = 0.75;
        this.lifeSpan = 22;
        this.lPeriod=0;
        this.isInfected=false;
        this.growthPeriod = 8;
        this.isInfectious=false;
        
        
    }

    public int getLifeSpan() {
        return lifeSpan;
    }

    public int getId() {
        return id;
    }
    
    

    public double getAlpha() {
        return alpha;
    }

    public int getGrowthPeriod() {
        return growthPeriod;
    }

  

    public double gettPr() {
        return tPr;
    }


   
    
}