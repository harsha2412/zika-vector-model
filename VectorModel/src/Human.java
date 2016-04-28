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
public class Human {

   private int iPeriod=0;
   private int rPeriod = 0;
    private int alpha; //infectiousParameter = 1/7
    private int gamma; //recovery parameter = 1/5
    private double tPr; //transmissionProbabililty = 0.75
    private double deathsPerDay; //per day = 4 for FRench Polynesia
    private double birthsPerDay; // = 13 for French Poly !!
    public boolean isInfected;
    public boolean isInfectious;
    public boolean isRecovered;
    private int id;
    private int belongsTo;
    public int visitingCounter = -1;
    
     public Human(int id) {
         this.id =id;
         this.alpha = 7;
        this.gamma = 9;
        this.tPr = 0.75;
        this.deathsPerDay = 4;
        this.birthsPerDay = 9;
        this.isInfected=false;
        this.isInfectious=false;
        this.isRecovered=false;
    }

    public int getId() {
        return id;
    }

    public int getiPeriod() {
        return iPeriod;
    }

    public int getrPeriod() {
        return rPeriod;
    }

    public void setiPeriod(int iPeriod) {
        this.iPeriod = iPeriod;
    }

    public void setrPeriod(int rPeriod) {
        this.rPeriod = rPeriod;
    }
    
    

    public double getAlpha() {
        return alpha;
    }

    public double getGamma() {
        return gamma;
    }

    public double gettPr() {
        return tPr;
    }

    public double getDeathsPerDay() {
        return deathsPerDay;
    }

    public double getBirthsPerDay() {
        return birthsPerDay;
    }

    public int getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(int belongsTo) {
        this.belongsTo = belongsTo;
    }

   

}