package minesweeper;

public class Main {
	static MineSweeperMain mainWindow;
	
	public static void main(String[] args) {
	      mainWindow = new MineSweeperMain();
	      mainWindow.preparePanels();
	      mainWindow.prepareComponents();
	      mainWindow.setFrontScreenAndFocus(ScreenMode.TITLE);
	      mainWindow.setVisible(true);
	}

}
