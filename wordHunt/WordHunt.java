package wordHunt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

class MoveAction extends AbstractAction{
	//passed in direction we want to move
	int x,y;
	boolean pressed = false;
	MoveAction(boolean pressed, int x, int y){
		this.x = x;
		this.y = y;
		this.pressed = pressed;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!pressed) {
			//in this example we only care about pressed = true
			//so we return here if it's false (when the key is up)
			return;
		}
		
		//This creates a new point so we don't affect the original until we want to
		Point previous = new Point(BasicGrid.testPoint.x, BasicGrid.testPoint.y);
		Point next = new Point(previous.x, previous.y);
		if(x != 0) {
			next.x += x;
		}
		if(y != 0) {
			next.y += y;
		}
		//AzSystem.out.println("Next Coord: " + next);
		//check if point exists in our grid mapping, if so update the position's color
		if(BasicGrid.lazyGrid.containsKey(next)) {
			BasicGrid.lazyGrid.get(next).setBackground(Color.red);
			BasicGrid.testPoint = next;
		}
		else {
			//reset color for previous point
			BasicGrid.lazyGrid.get(previous).setBackground(Color.red);
		}
		System.out.println("TestPoint Coord: " + BasicGrid.testPoint);
	}
}

public class WordHunt {
	//creating dimensions for the grid
	public static int Rows = 10;
	public static int Cols = 10;
	public static final int sizeofGrid = Rows * Cols;
	char[][] gamechar = new char[Rows][Cols];
	List <String> AnswerKey = new ArrayList<>();
	public static int totalWords = 11; 
	public static List<String> wordsFound;
	public String buttonPress; 
	
	//creating the directions user can move
	public static int[][] directions = {
			{0,0},{0,1},{1,0},{1,1},{-1,0},{0,-1},{-1,-1},{-1,1}
	};

	public void writeToFile(String fileName, String msg) {
		// this function will write words of the game file if it doesn't exist on the computer 
		try(FileWriter fw = new FileWriter(fileName)){
			Scanner scan = new Scanner(System.in);
			msg = scan.nextLine();
			fw.write("The content of the file is: " + msg);
			scan.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<String> findWords(String filename){
		
		List<String> words = new ArrayList<>();
		
		//scanning the txt file 
		try(Scanner reader = new Scanner(new FileReader(filename))){
			int maxLetterLength = 12;
			while(reader.hasNext()) {
				//removing any spaces before and after, changing case to lower
				//all while there is content to be scanned from file
	  			String data = reader.next().trim().toLowerCase();
	  			
	  			//checking if letters fall in range of shortest word and longest
	  			if(data.matches("^[a-z]{3," + maxLetterLength + "}$")) {
	  				words.add(data.toUpperCase());
	  			}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not find the game file on your coputer :(");
		}
		
		wordsFound=words;
		return words;
	}
	
	public static int wordLocation(JPanel grid, String word) {
		Random num = new Random();
		//creating variable to hold direction user can move. 8 possible directions
		int anyDirection = num.nextInt(directions.length);
		//arring the grid so letters can be distributed anywhere on the grid
		int anyPosition = num.nextInt(sizeofGrid);
		
		for (int pattern = 0; pattern < directions.length; pattern++) {
			pattern = (pattern + anyDirection) % directions.length;
			
			for (int pos = 0; pos < sizeofGrid; pos++) {
				pos = (pos + anyPosition) % sizeofGrid;
				int letterPlacement = locationAttempt(grid, word, dir, pos);
				
				if (letterPlacement > 0)
					return letterPlacement;
			}
		}
		
		return 0;
	}
	
	public static int LocationAttempt(JPanel grid, String word, int dir, int pos) {
		int r = pos / Cols;
		int c = pos % Cols;
		int length = word.length();
		
		if((directions[dir][0] == 1 && (length + c) > Cols)
				|| (directions[dir][0] == -1 && (length -1) > c)
				|| (directions[dir][1] == 1 && (length + r) > Rows)
				|| (directions[dir][1] == -1 && (length + -1) > r))
			return 0;
		int i ,rr, cc, overlaps = 0;
		
		for (int i = 0, rr = r, cc = c; i < length; i++) {
			if (grid.gamechar[rr][cc] != 0 && grid.gamechar[rr][cc] != word.charAt(i))
				return 0;
			
			cc+= directions[dir][0];
			rr += directions[dir][1];
		}
		
		for (int i = 0, rr = r, cc = c; i < length; i++) {
			if (grid.gamechar[rr][cc] == word.charAt(i))
				overlaps++;
			else grid.gamechar[rr][cc] = word.charAt(i);
			
			if (i < length - 1) {
				cc += directions[dir][0];
				rr += directions[dir][1];
			}
		}
		
		int placedLetters = length - overlaps;
		
		if(placedLetters > 0)
			grid.AnswerKey.add(String.format("%d, %d)(%d, %d)", word, c, r, cc, rr));
		return placedLetters;
	
	}
	
	public static void Results(Jpanel grid) {
		if(grid == null)
			System.out.println("Can't generate grid");
			return;
	}
	
	int size = grid.AnserKey.size();
	
	public static void generate(List<String> words) {
		JPanel grid = new JPanel("The Greetings Word Hunt");
		grid.setLayout(new GridLayout(Rows,Cols));
		grid.setLayout(new BorderLayout());
		grid.setSize(new Dimension(600,600));
		grid.setMinimumSize(new Dimension(600,600));
		
	}
	
	public static boolean stringCheck(String words) {
		boolean b = false;
		String check = words;
		for (String s : wordsFound) {
			if (s == check){
				b = true;
			}
		}
		return b;
	}
	
	public static void keyPress(InputMap im, ActionMap am) {
		
		//bind key actions to action map
		im.put(KeyStroke.getKeyStroke("pressed UP"), "UAD");
		im.put(KeyStroke.getKeyStroke("pressed DOWN"), "DAD");
		im.put(KeyStroke.getKeyStroke("pressed LEFT"), "LAD");
		im.put(KeyStroke.getKeyStroke("pressed RIGHT"), "RAD");
		im.put(KeyStroke.getKeyStroke("pressed ENTER"), "EAD");
		
		im.put(KeyStroke.getKeyStroke("released UP"), "UAU");
		im.put(KeyStroke.getKeyStroke("released DOWN"), "DAU");
		im.put(KeyStroke.getKeyStroke("released LEFT"), "LAU");
		im.put(KeyStroke.getKeyStroke("released RIGHT"), "RAU");
		im.put(KeyStroke.getKeyStroke("released ENTER"), "EAU");
		
		
		im.put(KeyStroke.getKeyStroke("pressed SPACE"), "SPACE");
		
		am.put("UAD", new MoveAction(true, 0, -1));
		am.put("DAD", new MoveAction(true, 0, 1));
		am.put("LAD", new MoveAction(true, -1,0));
		am.put("RAD", new MoveAction(true, 1, 0));
		am.put("EAD", new MoveAction(true, 0,0));
		//technically we don't need this, we're just listening for keydown
		//but include for a complete example
		am.put("UAU", new MoveAction(false, 0, -1));
		am.put("DAU", new MoveAction(false, 0, 1));
		am.put("LAU", new MoveAction(false, -1,0));
		am.put("RAU", new MoveAction(false, 1, 0));
		am.put("EAU",  new MoveAction(false, 0,0));
	}

	public static void main(String[] args) {
		//creating file on system with content for game
		String filename = "gamedata.txt";
		findWords(filename);
		String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int letter = alph.length();
		
		Random n = new Random();
		
		//for (int i = 0; i < 36; i++) {
		//	System.out.println(alph.charAt(n.nextInt(letter)));
		//}
	}
}

