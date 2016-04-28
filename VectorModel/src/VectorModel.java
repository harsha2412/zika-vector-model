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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class VectorModel extends javax.swing.JFrame {

    private int numOfDays;
    private Place place;
    JFreeChart transmissionChart;
    JFreeChart transmissionChartV;
    final String series1 = "Susceptible";
    final String series2 = "Infectious";
    final String series3 = "Recovered ";
    final String series4 = "Total ";

    public VectorModel(Place place, int numOfDays) {
        this.place = place;
        this.numOfDays = numOfDays;
        /*  try {
            this.SIR();
        } catch (IOException ex) {
            Logger.getLogger(VectorModel.class.getName()).log(Level.SEVERE, null, ex);
        }*/

    }

    public void perDayAction() throws IOException {
        System.out.println("in per day action " + this.place.getName());

        ArrayList<Human> totalHPopulation2 = this.place.totalHPopulation;

        ArrayList<Vector> totalVPopulation2 = this.place.totalVPopulation;

        System.out.println("Initial Conditions--===================>");
        System.out.println("Total Human = " + this.place.getTotalHPopulation());
        System.out.println("Infected Human = " + this.place.getInfectedHPopulation());
        System.out.println("Susceptible Human = " + this.place.getSusceptibleHPopulation());
        System.out.println("Recovered Human = " + this.place.getRecoveredHPopulation());
        System.out.println("Total Vector = " + this.place.getTotalVPopulation());
        System.out.println("Infected Vector = " + this.place.getInfectedVPopulation());
        System.out.println("Susceptible Vector = " + this.place.getSusceptibleVPopulation());
        // System.out.println("Experiment begins \n\n!!!~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        //  this.place.cleanPopulation();
        int infectantCountVector = this.place.getInfectedVPopulation();
        int susceptibleCountVector = this.place.getSusceptibleVPopulation();
        int tHumans = this.place.getTotalHPopulation();
        int iHumans = this.place.getInfectedHPopulation();

        //  System.out.println("Dead Mosquitoes" + this.place.getTotalVPopulation());
        //int count =0;
        for (int k = 0; k < this.place.getTotalVPopulation(); k++) {
            if (this.place.totalVPopulation.get(k).isAdult) {
                if (this.place.totalVPopulation.get(k).lPeriod < this.place.totalVPopulation.get(0).getLifeSpan()) {
                    totalVPopulation2.get(k).lPeriod = this.place.totalVPopulation.get(k).lPeriod + 1;
                } else {

                    Vector v = this.place.totalVPopulation.get(k);
                    totalVPopulation2.remove(v);

                    /*   if (this.place.totalVPopulation.get(k).isInfected) {
                        // int ind = infectedVPopulation2.indexOf(totalVPopulation2.get(k));
                        infectedVPopulation2.remove(v);
                    } else {
                        //int ind = susceptibleVPopulation2.indexOf(totalVPopulation2.get(k));
                        //if(ind!=-1){
                        susceptibleVPopulation2.remove(v);
                        //   }
                    }*/
                    //   totalVPopulation2.remove(v);
                }
            }
        }
        //  System.out.println("Update growth period for egg Mosquitoes");
        for (int k = 0; k < this.place.getTotalVPopulation(); k++) {
            if (!this.place.totalVPopulation.get(k).isAdult) {
                if (this.place.totalVPopulation.get(k).gPeriod < this.place.totalVPopulation.get(0).getGrowthPeriod()) {
                    totalVPopulation2.get(k).gPeriod = this.place.totalVPopulation.get(k).gPeriod + 1;
                } else {

                    totalVPopulation2.get(k).isAdult = true;
                    //susceptibleVPopulation2.add(totalVPopulation2.get(k));

                }
            }
        }
        // int tHumans = this.place.getSusceptibleVPopulation();

        if (this.place.startOutbreak) {
            //    System.out.println("Updating infectiousPeriod for Humans");
            for (int k = 0; k < this.place.getTotalHPopulation(); k++) {
                Human h = this.place.totalHPopulation.get(k);
                if (h.isInfectious) {
                    int ind = totalHPopulation2.indexOf(h);
                    if (h.getiPeriod() < this.place.totalHPopulation.get(0).getAlpha()) {
                        if (ind != -1) {
                            totalHPopulation2.get(ind).setiPeriod(h.getiPeriod() + 1);
                        }
                    } else if (ind != -1) {
                        //infectedHPopulation2.get(ind).isInfectious = false;
                        totalHPopulation2.get(ind).isInfectious = false;
                    }

                }
            }
        }

        if (this.place.startOutbreak) {
            // System.out.println("Updating infectiousPeriod for Mosquitoes");
            for (int k = 0; k < this.place.getTotalVPopulation(); k++) {
                Vector v = this.place.totalVPopulation.get(k);
                if (v.isInfectious && v.isAdult) {
                    int ind = totalVPopulation2.indexOf(v);
                    if (v.iPeriod < this.place.totalVPopulation.get(0).getAlpha()) {
                        if (ind != -1) {
                            totalVPopulation2.get(ind).iPeriod = v.iPeriod + 1;
                        }
                    } else if (ind != -1) {
                        //infectedHPopulation2.get(ind).isInfectious = false;
                        totalVPopulation2.get(ind).isInfectious = false;
                    }

                }
            }
        }

        // 
        if (!this.place.endOutbreak && this.place.startOutbreak) {
            // System.out.println("updating recovery period");
            for (int k = 0; k < this.place.getTotalHPopulation(); k++) {
                Human h = this.place.totalHPopulation.get(k);
                if (h.isInfected) {
                    int ind = totalHPopulation2.indexOf(h);
                    if (h.getrPeriod() < this.place.totalHPopulation.get(0).getGamma()) {
                        if (ind != -1) {
                            totalHPopulation2.get(ind).setrPeriod(h.getrPeriod() + 1);
                        }
                    } else if (ind != -1) {
                        //infectedHPopulation2.get(ind).isInfectious = false;
                        totalHPopulation2.get(ind).isRecovered = true;
                        totalHPopulation2.get(ind).isInfectious = false;
                        totalHPopulation2.get(ind).isInfected = false;
                    }

                }
            }
        }

        //
        if (!this.place.endOutbreak && this.place.startOutbreak) {
            // System.out.println("infecting humans from mosquitoes");
            for (int j = 0; j < this.place.getInfectedVPopulation(); j++) {
                double prInfection = this.place.getBitesInfectious() * this.place.totalVPopulation.get(0).gettPr();
                Random rand = new Random();
                int hId = rand.nextInt(tHumans);
                if (!this.place.totalHPopulation.get(hId).isInfected && !this.place.totalHPopulation.get(hId).isRecovered) {
                    double p = rand.nextDouble();
                    if (p <= prInfection) {
                        totalHPopulation2.get(hId).isInfected = true;
                        totalHPopulation2.get(hId).isInfectious = true;
                    }
                }
            }
            //System.out.println("infecting mosquitoes from humans");
            for (int k = 0; k < this.place.getTotalVPopulation(); k++) {
                // System.out.println("infecting mosquitoes from humans");
                Vector v = this.place.totalVPopulation.get(k);
                if (v.isAdult && !v.isInfected && !v.isInfectious) {
                    double prInfection = this.place.getBitesSuspectible() * this.place.totalHPopulation.get(0).gettPr();
                    Random rand = new Random();
                    int hId = rand.nextInt(tHumans);
                    if (this.place.totalHPopulation.get(hId).isInfectious) {
                        double p = rand.nextDouble();
                        if (p <= prInfection) {
                            int ind = totalVPopulation2.indexOf(v);
                            if (ind != -1) {
                                totalVPopulation2.get(ind).isInfected = true;
                                totalVPopulation2.get(ind).isInfectious = true;
                                //infectedVPopulation2.add(totalVPopulation2.get(vId));
                            }

                        }
                    }

                }

            }
        }

        //  System.out.println("New born Humans");
        for (int j = 0; j < this.place.totalHPopulation.get(0).getBirthsPerDay(); j++) {
            Human h = new Human(tHumans + j);
            h.setBelongsTo(this.place.getId());
            totalHPopulation2.add(h);
            // susceptibleHPopulation2.add(h);

        }
        // System.out.println("Dead Humans");
        for (int j = 0; j < this.place.totalHPopulation.get(0).getDeathsPerDay(); j++) {
            Random rand = new Random();
            int hId = rand.nextInt(tHumans);
            Human h = this.place.totalHPopulation.get(hId);

            totalHPopulation2.remove(h);
        }

        //  System.out.println("New Mosquitoes");
        for (int j = 0; j < this.place.totalVPopulation.get(0).totalEggs; j++) {
            Vector v = new Vector(totalVPopulation2.size() + j);
            v.isAdult = false;
            totalVPopulation2.add(v);
        }

        this.place.totalHPopulation = totalHPopulation2;

        /*  System.out.println("END OF DAY :-\n\n");
        System.out.println("Total Human = " + this.place.getTotalHPopulation());
        System.out.println("Infected Human = " + this.place.getInfectedHPopulation());
        System.out.println("Susceptible Human = " + this.place.getSusceptibleHPopulation());
        System.out.println("Total Vector = " + this.place.getTotalVPopulation());
        System.out.println("Infected Vector = " + this.place.getInfectedVPopulation());
        System.out.println("Susceptible Vector = " + this.place.getSusceptibleVPopulation());*/
        if (this.place.getSusceptibleVPopulation() < 100 || this.place.getSusceptibleHPopulation() < 5 || (this.place.startOutbreak && this.place.getInfectedHPopulation() == 0 && this.place.getInfectedVPopulation() == 0) || this.place.getTotalHPopulation() == 0) {
            System.out.println("Outbreak ends for " + this.place.getName() + "\n\n\n\n");
            this.place.endOutbreak = true;

        }

    }

}
