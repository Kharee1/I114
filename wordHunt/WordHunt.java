package wordHunt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WordHunt {
	//creating rows and cols variable
	public static final int nROWS = 10;
	public static final int nCOLS = 10;
	public static final int gridSize = nROWS * nCOLS;
	public ArrayList<String> wordsToFind = new ArrayList<String>();
		
	Socket server;
	public WordHunt() {
		
	}
	public void connect(String address, int port) {
		try {
			server = new Socket(address, port);
			System.out.println("User connected. Initiating game");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void start() throws IOException {
		if(server == null) {
			return;
		}
		try(Scanner si = new Scanner(System.in);
				PrintWriter out = new PrintWriter(server.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));){
			String line = "";
			while(true) {
				try {
					line = si.nextLine();
					if(!"quit".equalsIgnoreCase(line)) {
						out.println(line);
					}
					else {
						break;
					}
					line = "";
				}
				catch(Exception e) {
					System.out.println("Connection dropped");
					break;
				}
			}
			System.out.println("Exited loop");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	private void close() {
		if(server != null) {
			try {
				server.close();
				System.out.println("Closed socket");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean fileFinder(String fileName) {
		
		File file = new File(fileName);
		try(Scanner scan = new Scanner(file)){
			String gameWords = "";
			while (scan.hasNextLine()) {
				String data = scan.nextLine();
		        gameWords += data;
		        if(scan.hasNextLine()) {
		        	gameWords += ", ";
		        }
			}
			wordsToFind.add(gameWords);
			scan.close();
			System.out.println("The words to find are the following: " + gameWords + "\n" + "Good Luck!");
		
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("The necessary game file wasn't found. Please download the necessary game file first");
			return false;
		}
		return true;
	}
	
	public void getLetters(){
		//char ch;
		char comma = ',';
		for(String word: wordsToFind) {
			for(int i = 0; i < word.length(); i++) {
				if (word.charAt(i) == comma) {
				}
				else
					System.out.println(word.charAt(i));
			}
		}
	}
    
	 public String foundWords(ArrayList<String> wordCheck) {
		 StringBuilder builder = new StringBuilder();
		 for(String ch : wordCheck) {
			 builder.append(ch);
		 }
	System.out.println("You created this word " + builder.toString());
	return builder.toString(); 
	 }
	 
	 public boolean compareWords(String builder) {
		 for (String createdWord: wordsToFind) {
			 if (wordsToFind.contains(createdWord)){
				 System.out.println("Your word was found: " + createdWord);
			 }
			 //return createdWord;
		 }
		 return true;
	 }


public static void main(String[] args) {
	
	String fileName = "gamedata.txt";
	WordHunt game = new WordHunt();
	WordHunt client = new WordHunt();
	client.connect("127.0.0.1", 3002);
	//game.wordComparison(foundWords(null), wordsToFind);
	if(game.fileFinder(fileName)) {
		System.out.println("\n" + "Beginning game shortly.");
		
		JFrame frame = new JFrame("The Greetings Word Hunt");
		frame.setLayout(new BorderLayout());
		frame.setSize(new Dimension(600,600));
		frame.setMinimumSize(new Dimension(600,600));
		
		Dimension gridDimensions = new Dimension(400,400);
		JPanel canvas = new JPanel();
		canvas.setLayout(new GridLayout(nROWS, nCOLS));
		canvas.setSize(gridDimensions);
		canvas.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel container = new JPanel();
		container.setSize(new Dimension(800,800));
		container.setMinimumSize(new Dimension(600, 600));
		container.setBorder(BorderFactory.createLineBorder(Color.red));
		
		JPanel btnholder = new JPanel();
		btnholder.setSize(new Dimension(400,400));
		
		JButton quitButton = new JButton();
		quitButton.setSize(new Dimension(30,20));
		quitButton.setText("Quit");
		
		JButton submitButton = new JButton();
		submitButton.setSize(new Dimension(30,20));
		submitButton.setText("Submit");	
		
			/*
			 * submitButton.addActionListener(new ActionListener() {
			 * 
			 * @Override
			 * 
			 * });
			 */
		
			/*
			 * quitButton.addActionListener(new ActionListener() {
			 * 
			 * @Override public void actionPerformed(ActionEvent e) {
			 * //interaction.client.disconnect(); } });
			 */
		
		ArrayList<String> wordCheck = new ArrayList<String>();
		
		Random num = new Random();
		int i = 0;
		Dimension buttonSize = new Dimension(10,10);
	    
		for(int y = 0; y < nROWS; y++) {
			for(int x = 0; x < nCOLS; x++) {
				JButton bt = new JButton();
			    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			    char random = alphabet.charAt(num.nextInt(alphabet.length()));
			    String randomLetter = String.valueOf(random);
				bt.setText(randomLetter);
				bt.setLocation(x, y);
				bt.setSize(buttonSize);
				
				bt.setBackground(Color.white);
				bt.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						((JButton)e.getSource()).setBackground(Color.red);
						System.out.println(e.getSource());
						wordCheck.add(((JButton)e.getSource()).getText());
						System.out.println(wordCheck.toString());
						game.foundWords(wordCheck);
						//game.wordComparison(foundWords, wordsToFind);
						
					}
				});
		

		canvas.add(bt);
		
		i++;

				}
			}
		frame.setVisible(true);
		frame.add(canvas);
		frame.add(btnholder, BorderLayout.SOUTH);
		btnholder.add(submitButton);
		btnholder.add(quitButton);
		game.getLetters();
		
		}
	}
}