package minesweeper;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameBoardPanel extends JPanel {
   private static final long serialVersionUID = 1L;
   
   // Define named constants for the game properties
   public static final int ROWS = 10;
   public static final int COLS = 10;

   // Define named constants
   //Cell width and height, in pixels
   public static final int CELL_SIZE = 60;  

   // Define properties (package-visible)
   public static final int CANVAS_WIDTH  = CELL_SIZE * COLS; // Adjustable
   public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS; 
   Cell cells[][] = new Cell[ROWS][COLS];
   int numMines = 10;
   JLabel titleLabel;
   
   //Constructor
   public GameBoardPanel() {
	   
	   this.setLayout(null);
	   this.setBackground(Color.green);
	   
	   //JButton btnNewGame = new JButton("Reset");
	   setLayout(new GridLayout(ROWS, COLS, 2, 2));
	 
	   // Allocate the 2D array of Cell, and added into content-pane.
	   for (int row = 0; row < ROWS; ++row) {
		   for (int col = 0; col < COLS; ++col) {
			   cells[row][col] = new Cell(row, col);
			   this.add(cells[row][col]);
		   }
	   }
	   //Cell Listener
	   CellMouseListener listener = new CellMouseListener();
	   for (int row = 0; row < ROWS; ++row) {
    	   	for (int col = 0; col < COLS; ++col) {
    	   		cells[row][col].addMouseListener(listener); 
    	   	}
	   }	   
	   
	   //Call new game
	   newGame(); 
   
   }
   
   public void prepareComponents() {
		titleLabel = new JLabel();
		titleLabel.setText("Game screen");
		titleLabel.setBounds(100, 0, 100, 30);
		this.add(titleLabel);      
	   /*
	   setLayout(new BorderLayout());
	   this.add(btnNewGame, BorderLayout.SOUTH);
	      
	   btnNewGame.addActionListener(new ActionListener() {
	       @Override
	       public void actionPerformed(ActionEvent e) {
	            newGame();
	       }
	   });*/

   }

   // Initialize and re-initialize a new game
   public void newGame() {
      // Get a new mine map
      MineMap mineMap = new MineMap();
      mineMap.newMineMap(numMines);

      // Reset cells, mines, and flags
      for (int row = 0; row < ROWS; row++) {
         for (int col = 0; col < COLS; col++) {
            // Initialize each cell with/without mine
            cells[row][col].newGame(mineMap.isMined[row][col]);
         }
      }
   }

   // Return the number of mines [0, 8] in the 8 neighboring cells
   //  of the given cell at (srcRow, srcCol).
   private int getSurroundingMines(int srcRow, int srcCol) {
	   int numMines = 0;
       		for (int row = srcRow - 1; row <= srcRow + 1; row++) {
       			for (int col = srcCol - 1; col <= srcCol + 1; col++) {
       				if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
       					if (cells[row][col].isMined) numMines++;
       				}
       			}
       		}
      return numMines;
   }

   // Reveal the cell
   // If this cell has 0 mines, reveal the 8 neighboring cells recursively
   private void revealCell(int srcRow, int srcCol) {
	   int numMines = getSurroundingMines(srcRow, srcCol);
	   cells[srcRow][srcCol].setText(numMines + "");
	   cells[srcRow][srcCol].isRevealed = true;
	   cells[srcRow][srcCol].paint();
	   if (numMines == 0) {
		   for (int row = srcRow - 1; row <= srcRow + 1; row++) {
			   for (int col = srcCol - 1; col <= srcCol + 1; col++) {
				   if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
					   if (!cells[row][col].isRevealed) revealCell(row, col);
				   }
			   }
		   }
	   }
   }

   // Return true if the player has won (all cells have been revealed or were mined)
   public boolean hasWon() {
	   for (int row = 0; row < ROWS; row++) {
	         for (int col = 0; col < COLS; col++) {
	            if (!cells[row][col].isMined) {
	            	if (!cells[row][col].isRevealed) {
	            		return false;
	            	}
	            }
	         }
	      }
      return true;
   }

   private class CellMouseListener extends MouseAdapter {
	      @Override
	      public void mouseClicked(MouseEvent e) {         // Get the source object that fired the Event
	         Cell sourceCell = (Cell)e.getSource();
	         // For debugging
	         System.out.println("You clicked on (" + sourceCell.row + "," + sourceCell.col + ")");

	         // Left-click to reveal a cell; Right-click to plant/remove the flag.
	         if (e.getButton() == MouseEvent.BUTTON1) {  // Left-button clicked
	        	if (sourceCell.isMined) {  
	                sourceCell.setText("POTATO");
	                System.out.println("Game Over");
	                JOptionPane.showMessageDialog(null, "Game Over!");
	                
	            } else {
	            	revealCell(sourceCell.row, sourceCell.col);
	                cells[sourceCell.row][sourceCell.col].removeMouseListener(this);
	                //Check if the player has won
	                if (hasWon()) {
	                	JOptionPane.showMessageDialog(null, "YOU WON!");
	                }
	                
	            }
	         } else if (e.getButton() == MouseEvent.BUTTON3) { // right-button clicked
	        	 if (sourceCell.isFlagged) {
	        		sourceCell.setText("");
		            sourceCell.isFlagged=false;
		         } else {
		        	sourceCell.setText("|>");
		            sourceCell.isFlagged=true;
		         }
	         }

	      }
	   }
}

