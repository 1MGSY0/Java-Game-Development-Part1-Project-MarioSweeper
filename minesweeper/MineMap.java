package minesweeper;
/**
 * Locations of Mines
 */
public class MineMap {

   int numMines;
   boolean[][] isMined = new boolean[GameBoardPanel.ROWS][GameBoardPanel.COLS];
   
   //Constructor
   public MineMap() {
      super();
   }

   public void newMineMap(int numMines) {
      this.numMines = numMines;
      isMined[0][0] = true;
      isMined[5][2] = true;
      isMined[9][5] = true;
      isMined[6][7] = true;
      isMined[8][2] = true;
      isMined[2][4] = true;
      isMined[5][7] = true;
      isMined[7][7] = true;
      isMined[3][6] = true;
      isMined[4][8] = true;
   }
}
