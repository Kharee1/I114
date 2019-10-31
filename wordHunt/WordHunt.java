package wordHunt;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class WordHunt {
	//creating dimensions for the grid
	public static int Rows = 10;
	public static int Cols = 10;
	public static final int sizeofGride = Rows * Cols;
	char[][] item = new char[Rows][Cols];
	public static int totalWords = 11; 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length(); col++) {
				System.out.println();
			}
		}*/
		
		//creating file on system with content for game
		String filename = "gamedata.txt";
		String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int  letter = alph.length();
		
		
		Random n = new Random();
		
		for (int i = 0; i < 36; i++) {
			System.out.println(alph.charAt(n.nextInt(letter)));
		}
		
	}
	
	public static List<String> findWords(String filename){
		
		
		List<String> words = new ArrayList<>();
		
		//scanning the txt file 
		try(Scanner reader = new Scanner(new FileReader(filename))){
			while(reader.hasNext()) {
				//removing any spaces before and after, changing case to lower
				//all while there is content to be scanned from file
				String data = reader.next().trim().toLowerCase();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not find the game file on your coputer :(");
		}
		
		return words;
	}

		public static Grid wordSearch(List<String> words) {
			while (true) {
				Collections.shuffle(words);
				grid = new Grid();
				
			}
		}
}
