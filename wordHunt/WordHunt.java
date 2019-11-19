package wordHunt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WordHunt{
	public static final int nROWS = 10;
	public static final int nCOLS = 10;
	
	public void fileFinder(String fileName) {
		File file = new File(fileName);
		try(Scanner scan = new Scanner(file)){
			String gameWords = "";
			while (scan.hasNextLine()) {
				String data = scan.nextLine();
		        gameWords += data;
		        if(scan.hasNextLine()) {
		        	gameWords += System.lineSeparator();
		        }
			}
			System.out.println(gameWords);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("The necessary game file wasn't found");
		}
	}
	
	

public static void main(String[] args) {
	
	String fileName = "gamedata.txt";
	WordHunt game = new WordHunt();
	game.fileFinder(fileName);
	
	JFrame frame = new JFrame("The Greetings Word Hunt");
	frame.setLayout(new BorderLayout());
	frame.setSize(new Dimension(600,600));
	frame.setMinimumSize(new Dimension(600,600));
	

	Dimension gridDimensions = new Dimension(400,400);
	JPanel grid = new JPanel();
	grid.setLayout(new GridLayout(nROWS, nCOLS));
	grid.setSize(gridDimensions);
	grid.setBorder(BorderFactory.createLineBorder(Color.black));
	
	
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
			bt.setText(randomLetter);
			bt.setLocation(x, y);
			bt.setSize(buttonSize);
	
	grid.add(bt);
	i++;
	    	} // prints 50 rand
	
		}
	frame.setVisible(true);
	frame.add(grid);
	//public static void highlight{}
	
	//}
	
	}
}