/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package vectormodel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author harshagwalani
 */
public class InfectionManager {

    private int noi;
    private int seedIsland;
    private FrenchPolynesia fp;
    public Place frenchPoly;

    public InfectionManager() {
        fp = new FrenchPolynesia();
        frenchPoly =new Place();
      //  frenchPoly.init();
        frenchPoly.setName("French Polynesia");
        fp.initIslands();
        this.noi = fp.getNumOfIslands();
        this.seedIsland = pickSeedIsland();
        fp.islands[this.seedIsland].seedInfection(2, 1);
        fp.islands[this.seedIsland].setStart(0);
    }

    private int pickSeedIsland() {
        Random rand = new Random();
        return rand.nextInt(this.noi);

    }

    public static void main(String args[]) throws IOException{
        InfectionManager im = new InfectionManager();
        int maxNumDays =100;
        for(int j=0;j<im.noi;j++){
               System.out.println("Fresh Run for main conditions \n Intial Conditions--===================>");
        System.out.println("Total Human = " + im.fp.islands[j].getTotalHPopulation());
        System.out.println("Infected Human = " + im.fp.islands[j].getInfectedHPopulation());
        System.out.println("Susceptible Human = " + im.fp.islands[j].getSusceptibleHPopulation());
         System.out.println("Recovered Human = " + im.fp.islands[j].getRecoveredHPopulation());
        System.out.println("Total Veector = " + im.fp.islands[j].getTotalVPopulation());
        System.out.println("Infected Vector = " + im.fp.islands[j].getInfectedVPopulation());
        System.out.println("Susceptible Vector = " +im.fp.islands[j].getSusceptibleVPopulation());
        }
        
        for(int i =0;i<maxNumDays;i++){
            System.out.println("==============================>>>>>>>>>>>>>>>>>>>>>>>>>Day Number #"+i);
            im.updatePopulationsAfterMigration();
            for(int j=0;j<im.noi;j++){
                
               // im.fp.islands[j].cleanPopulation();
                int count =0;
                boolean h = im.fp.islands[j].isPlaceInfected(i);
                    
                    System.out.println("Place Name :- "+ im.fp.islands[j].getName());
                   // im.fp.islands[j].setStart(i);
                        VectorModel vm = new VectorModel(im.fp.islands[j],20);
                        vm.perDayAction();
                         int rp = im.fp.islands[j].getTotalHPopulation() - im.fp.islands[j].getInfectedHPopulation() - im.fp.islands[j].getSusceptibleHPopulation();
                        
            if(h){
            im.fp.islands[j].transmissionDataset.setValue(im.fp.islands[j].getSusceptibleHPopulation(), im.fp.islands[j].series1, "" + i);
            im.fp.islands[j].transmissionDataset.setValue(im.fp.islands[j].getInfectedHPopulation(), im.fp.islands[j].series2, "" + i);
             im.fp.islands[j].transmissionDataset.setValue(rp,  im.fp.islands[j].series3, "" + i);
                   im.fp.islands[j].transmissionDatasetV.setValue(im.fp.islands[j].getSusceptibleVPopulation(), im.fp.islands[j].series1, "" + i);
             im.fp.islands[j].transmissionDatasetV.setValue(im.fp.islands[j].getInfectedVPopulation(), im.fp.islands[j].series2, "" + i);
            im.fp.islands[j].transmissionDatasetV.setValue(im.fp.islands[j].getTotalVPopulation(), im.fp.islands[j].series4, "" + i);
            int e= im.fp.islands[j].getEnd();
                         im.fp.islands[j].setEnd(e+1);
            }
                       // count++;
                    
                
               
               im.frenchPoly.totalPop = im.frenchPoly.totalPop+im.fp.islands[j].getTotalHPopulation();
                 im.frenchPoly.sPop = im.frenchPoly.sPop+im.fp.islands[j].getSusceptibleHPopulation(); 
                  im.frenchPoly.iPop = im.frenchPoly.iPop+im.fp.islands[j].getInfectedHPopulation(); 
                  im.frenchPoly.rPop = im.frenchPoly.iPop+rp; 
                 im.frenchPoly.tvPop = im.frenchPoly.tvPop+im.fp.islands[j].getTotalVPopulation();
                 im.frenchPoly.svPop = im.frenchPoly.svPop+im.fp.islands[j].getSusceptibleVPopulation(); 
                  im.frenchPoly.ivPop = im.frenchPoly.ivPop+im.fp.islands[j].getInfectedVPopulation(); 
                 
               
               // im.frenchPoly.totalVPopulation.addAll(im.fp.islands[j].totalVPopulation);
                        }
             
               im.frenchPoly.transmissionDataset.setValue(im.frenchPoly.sPop, im.frenchPoly.series1, "" + i);
            im.frenchPoly.transmissionDataset.setValue(im.frenchPoly.iPop, im.frenchPoly.series2, "" + i);
             im.frenchPoly.transmissionDataset.setValue(im.frenchPoly.rPop,  im.frenchPoly.series3, "" + i);
                   im.frenchPoly.transmissionDatasetV.setValue(im.frenchPoly.svPop, im.frenchPoly.series1, "" + i);
             im.frenchPoly.transmissionDatasetV.setValue(im.frenchPoly.ivPop,im.frenchPoly.series2, "" + i);
            im.frenchPoly.transmissionDatasetV.setValue(im.frenchPoly.tvPop,im.frenchPoly.series4, "" + i);
            
            
            
        }
        for(int j=0;j<im.noi;j++){
            im.frenchPoly.createChart();
                System.out.println("Place Name :- "+ im.fp.islands[j].getName());
                int start = im.fp.islands[j].getStart();
                int end = im.fp.islands[j].getEnd();
                int duration = end-start;
                System.out.println("Start day "+ start+"End Day "+end+" Duration = "+duration);
                try {
                    im.fp.islands[j].createChart();
                } catch (IOException ex) {
                    Logger.getLogger(InfectionManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
           
        
        
    }
    public ArrayList<Human> findHumansinIfromJ(int i, int j){
        ArrayList<Human> humans = new ArrayList<Human>();
        Place source = fp.islands[i];
        for(int n=0;n<source.getTotalHPopulation();n++){
            if(source.totalHPopulation.get(n).getBelongsTo()==j){
                humans.add(source.totalHPopulation.get(n));
            }
        }
        
        return humans;
    }
    //Every Day Function !!!
    public void updatePopulationsAfterMigration() {
       // FrenchPolynesia fp1 = new FrenchPolynesia();
        //fp1 = this.fp;
        int[][] flights = fp.flightInfo;
        for (int i = 0; i < this.noi; i++) {
            
            for (int j = 0; j < this.noi; j++) {
                if (flights[i][j] != 0) {
                    int availableSeats = flights[i][j];
                   ArrayList<Human> HumansFromJ = findHumansinIfromJ(i,j);
                   int t = HumansFromJ.size();
                   int toLeave = (int)0.5*t;
                   int rem = availableSeats-toLeave;
                   if(availableSeats<toLeave){
//                       //send all to j
                    for(int l=0;l<availableSeats;l++){
                        Random rand1 = new Random();
                        int k = rand1.nextInt(HumansFromJ.size());
                        Human h = HumansFromJ.get(k);
                        fp.islands[j].totalHPopulation.add(h);
                        fp.islands[i].totalHPopulation.remove(h);
                       // fp.islands[i].cleanPopulation();
                        
                    }
                       
                   }
                   else {
                        for(int l=0;l<toLeave;l++){
                        Random rand1 = new Random();
                        int k = rand1.nextInt(HumansFromJ.size());
                        Human h = HumansFromJ.get(k);
                        fp.islands[j].totalHPopulation.add(h);
                        fp.islands[i].totalHPopulation.remove(h);
                       // fp.islands[i].cleanPopulation();
                        
                    }
                        for(int k=0;k<rem;k++){
                            int totalH = fp.islands[i].getTotalHPopulation();
                            Random rand = new Random();
                            int r = rand.nextInt(totalH);
                            Human h = fp.islands[i].totalHPopulation.get(r);
                            if(h.getBelongsTo()==j){
                                k--;
                            }
                            else {
                                fp.islands[j].totalHPopulation.add(h);
                            }
                        }
                        
                        
                       
                   }
                  // fp.islands[i].cleanPopulation();
                  //  System.out.println("total pop in migration for island  "+i+" "+totalH);
                   /* for (int k = 0; k < flights[i][j]; k++) {
                         int totalH = fp.islands[i].getTotalHPopulation();
                         
                        // System.out.println(totalH+" Total population in migration in "+fp.islands[i].getName());
                       if(totalH>0){
                        Random rand = new Random();
                        int r = rand.nextInt(totalH);
                        Human h = fp.islands[i].totalHPopulation.get(r);
                        fp.islands[j].totalHPopulation.add(h);
                        if (h.isInfectious) {
                            fp.islands[j].infectedHPopulation.add(h);
                            fp.islands[i].infectedHPopulation.remove(h);
                        } else if (h.isRecovered) {
                            fp.islands[j].recoveredHPopulation.add(h);
                        } else {
                            fp.islands[j].susceptibleHPopulation.add(h);
                            fp.islands[i].susceptibleHPopulation.remove(h);
                        }
                        fp.islands[i].totalHPopulation.remove(h);
                    }
                    }*/
                }
            }
        }

    }
    
    public void createChart(){
        
        
        
    }

}