package minesweeper;
import java.awt.*;       
import java.awt.event.*;
import javax.swing.*; 

@SuppressWarnings("unused")
public class MineSweeperMain extends JFrame {
   private static final long serialVersionUID = 1L;
   
   //Fixed Window size
   public static final int WIDTH = 800; 
   public static final int HEIGHT = 800;
   
   //Define Field
   JFrame frame = new JFrame("MineSweeperMain");
   ScreenMode screenMode = ScreenMode.TITLE;

   //Define Panels
   GameBoardPanel game = new GameBoardPanel();
   TitlePanel title = new TitlePanel();
   
   //Define Components
   CardLayout layout = new CardLayout();
   
   // Set the size of the content-pane and pack all the components
   //  under this container.
   

   // Constructor
   public MineSweeperMain() {
	   
	  //Design
      setTitle("Mariosweeper");
      this.setLayout(layout);
      ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Block.png"));
      this.setIconImage(icon.getImage());
 
      //Settings
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setResizable(false);
      setPreferredSize(new Dimension(WIDTH, HEIGHT));
      pack();
      setLocationRelativeTo(null);
      setVisible(true);
   }
   
   public void preparePanels(){
	   this.add(game,"Game screen");
	   this.add(title,"Title screen");
	   this.pack();
   }
   
   public void prepareComponents() {
	   game.prepareComponents();
	   title.prepareComponents();
   }
   
   public void setFrontScreenAndFocus(ScreenMode s) {
	   screenMode = s;
	   
	   switch(screenMode) {
	   case TITLE:
	   		layout.show(this,"Title screen");
	   		title.requestFocus();
	   case GAME:
	   		layout.show(this,"Game screen");
	   		game.requestFocus();
	   break;
	   }
   }
}