package wordHunt;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;

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
	
	for(int y = 0; y < nCOLS; y++) {
		for(int x = 0; x < nROWS; x++) {
		Random num = new Random();

	    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    Component letter = alphabet.charAt(num.nextInt(alphabet.length()));
	    frame.add(letter);
	    	} // prints 50 rand
	
		}
	}
}