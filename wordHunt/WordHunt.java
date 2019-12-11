package wordHunt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
		//System.out.println("Listening for input");
		try(Scanner si = new Scanner(System.in);
				PrintWriter out = new PrintWriter(server.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));){
			String line = "";
			while(true) {
				try {
					//System.out.println("Waiting for input");
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
		        	gameWords += System.lineSeparator();
		        }
			}
			wordsToFind.add(gameWords);
			scan.close();
			System.out.println("The words to  find are in the following list " + wordsToFind);
		
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("The necessary game file wasn't found. Please download the necessary game file first");
			return false;
		}
		return true;
	}
	
	public void wordComparison() {
		for (int i =0; i < wordsToFind.size(); i++) {
			System.out.println(wordsToFind.get(i));
		}
	}
	
	public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed");
    }

    public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
			System.out.println("Enter key was pressed");
	}
    
	 public String foundWords(ArrayList<String> wordCheck) {
		 StringBuilder builder = new StringBuilder();
		 System.out.println("" + wordCheck);
		 for(String ch : wordCheck) {
			 builder.append(ch);
			 System.out.println(builder);
		 }
		// if(builder.length() > 0){
		//	 builder.deleteCharAt(builder.length() - 1); 
		// }
		 //System.out.println(builder);
		 return builder.toString(); 
	 }


public static void main(String[] args) {
	
	String fileName = "gamedata.txt";
	WordHunt game = new WordHunt();
	WordHunt client = new WordHunt();
	client.connect("127.0.0.1", 3002);
	game.wordComparison();
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

		//List<Location> wordCheck = new LinkedList<Location>();
		
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
					}
				});
		

		canvas.add(bt);
		
		i++;

				}
			}
		frame.setVisible(true);
		frame.add(canvas);
		}
	}
}