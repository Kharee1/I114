package wordHunt;

import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class WordHunt {
	//creating dimensions for the grid
	public static int Rows = 10;
	public static int Cols = 10;
	public static final int sizeofGride = Rows * Cols;
	char[][] gamechar = new char[Rows][Cols];
	List <String> AnswerKey = new ArrayList<>();
	public static int totalWords = 11; 
	
	//creating the directions user can move
	public static int[][] directions = {
			{0,0},{0,1},{1,0},{1,1},{-1,0},{0,-1},{-1,-1},{-1,1}
	};

	public void writeToFile(String fileName, String msg) {
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
	  			
	  			//checking if letters fall in range of shortest word and longer
	  			if(data.matches("^[a-z]{3," + maxLetterLength + "}$")) {
	  				words.add(data.toUpperCase());
	  			}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not find the game file on your coputer :(");
		}
		
		return words;
	}

	
	public static void main(String[] args) {
		//creating file on system with content for game
		String filename = "gamedata.txt";
		String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int letter = alph.length();
		
		
		Random n = new Random();
		
		for (int i = 0; i < 36; i++) {
			System.out.println(alph.charAt(n.nextInt(letter)));
		}
	}
}

