/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author harshagwalani
 */
public class FrenchPolynesia {

    public Place[] islands;
  //  private int[] test;
    private int numOfIslands = 11;
    public int[][] flightInfo;

    public void initIslands() {
        
        islands = new Place[this.numOfIslands];
        flightInfo = new int[this.numOfIslands][this.numOfIslands];
        for (int i = 0; i < this.numOfIslands; i++) {
            islands[i] = new Place();
            islands[i].setId(i);
            for (int j = 0; j < this.numOfIslands; j++) {
                flightInfo[i][j] = 0;
            }
            //islands[i].initializePopulation(3,i);
        }
        islands[0].setName("Nuku-Hiva");
        islands[1].setName("Ua-Pou");
        islands[2].setName("Ua Huka");
        islands[3].setName("Hiva Oa");
        islands[4].setName("Tahiti");
        islands[5].setName("Moorea");
        islands[6].setName("Huahine");
        islands[7].setName("Raiatea");
        islands[8].setName("Bora Bora");
        islands[9].setName("Rurutu");
        islands[10].setName("Manihi");

        islands[0].setTotalPop(2966);
        islands[1].setTotalPop(2173);
        islands[2].setTotalPop(621);
        islands[3].setTotalPop(2190);
        islands[4].setTotalPop(183645);
        islands[5].setTotalPop(16191);
        islands[6].setTotalPop(6303);
        islands[7].setTotalPop(12545);
        islands[8].setTotalPop(8880);
        islands[9].setTotalPop(2322);
        islands[10].setTotalPop(680);

        flightInfo[1][0] = 40;
        flightInfo[1][2] = 32;
        flightInfo[1][3] = 40;
        flightInfo[1][4] = 48;

        flightInfo[2][0] = 24;
        flightInfo[2][1] = 32;
        flightInfo[2][3] = 24;
        flightInfo[2][4] = 40;

        flightInfo[3][0] = 56;
        flightInfo[3][1] = 40;
        flightInfo[3][2] = 24;
        flightInfo[3][4] = 64;

        flightInfo[4][0] = 64;
        flightInfo[4][1] = 40;
        flightInfo[4][2] = 24;
        flightInfo[4][3] = 64;
        flightInfo[4][5] = 144;
        flightInfo[4][6] = 192;
        flightInfo[4][7] = 320;
        flightInfo[4][8] = 456;
        flightInfo[4][9] = 32;
        flightInfo[4][10] = 24;

        flightInfo[5][4] = 72;
        flightInfo[5][6] = 40;
        flightInfo[5][7] = 48;
        flightInfo[5][8] = 128;

        flightInfo[6][4] = 184;
        flightInfo[6][5] = 8;
        flightInfo[6][7] = 104;
        flightInfo[6][8] = 56;

        flightInfo[7][4] = 280;
        flightInfo[7][6] = 64;
        flightInfo[7][8] = 112;
      
        flightInfo[8][4] = 424;
        flightInfo[8][5] = 56;
        flightInfo[8][6] = 32;
        flightInfo[8][7] = 80;
        
        flightInfo[9][4] = 32;
        flightInfo[10][4] = 24;
        for (int i = 0; i < this.numOfIslands; i++) {
            islands[i].initializePopulation(3,i);
            
        }
    }

    public int getNumOfIslands() {
        return numOfIslands;
    }

    public void setNumOfIslands(int numOfIslands) {
        this.numOfIslands = numOfIslands;
    }
    
}