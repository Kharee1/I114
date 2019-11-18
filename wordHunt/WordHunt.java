package wordHunt;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WordHunt{
	public static final int nROWS = 10;
	public static final int nCOLS = 10;
	
	

public static void main(String[] args) {
	JFrame frame = new JFrame("The Greetings Word Hunt");
	frame.setLayout(new BorderLayout());
	frame.setMinimumSize(new Dimension(600,600));
	frame.setSize(new Dimension(600,600));
	frame.setVisible(true);
	
	
	
	Dimension gridDimensions = new Dimension(400,400);
	JPanel grid = new JPanel();
	grid.setLayout(new GridLayout(nROWS, nCOLS));
	grid.setSize(gridDimensions);
	frame.add(grid);
	
	Random num = new Random();
	int i = 0;
	Dimension buttonSize = new Dimension(10,10);
	
	for(int y = 0; y < nROWS; y++) {
		for(int x = 0; x < nCOLS; x++) {
			JButton bt = new JButton();
			
	
		    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		    char random = alphabet.charAt(num.nextInt(alphabet.length()));
		    String randomLetter = String.valueOf(random);
		    System.out.println(randomLetter);
			//String buttonText = String.format("Index: %1$s Coord: (%2$s, %3$s)", i, x, y);
			bt.setText(randomLetter);
			bt.setLocation(x, y);
			bt.setSize(buttonSize);
	
	grid.add(bt);
	i++;
	    	} // prints 50 rand
	
		}
	}
}