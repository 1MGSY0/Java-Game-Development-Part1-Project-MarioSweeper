package minesweeper;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("unused")
public class TitlePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	//Define Component
	JLabel titleLabel;
	
	//Constructor
	public TitlePanel() {
		this.setLayout(null);
		this.setBackground(Color.red);
	}
	
	//Components
	public void prepareComponents() {
		titleLabel = new JLabel();
		titleLabel.setText("Title screen");
		titleLabel.setBounds(100, 0, 100, 30);
		this.add(titleLabel);
	}

}
