package com.mt.examples.fs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class fileSystemSample {
	public void createFileAndGetDetails(String fileName) {
		try {
			File fileReference = new File(fileName);
			if(fileReference.createNewFile()) {
				System.out.println("The file didn't exist, system created a new file. If you want to enter text, type it below");
			}
			else{
				System.out.println("This file already exists. Enter text to overwrite it.");
			}
		}
		catch(IOException ie) {
			ie.printStackTrace();
		}
	}
	
	public void makeChanges(String fileName) {
		File fileReference = new File(fileName);
		if(fileReference.exists()) {
			try (FileWriter fw = new FileWriter(fileName)) {
				fw.write("This is a test written sample to see if what I want works");
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
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
	public void readFromFile(String fileName) {
		File file = new File(fileName);
		try(Scanner scan = new Scanner(file)){
			String content = "";
			while (scan.hasNextLine()) {
				String info = scan.nextLine();
		        content += info;
		        if(scan.hasNextLine()) {
		        	content += System.lineSeparator();
		        }
		       System.out.println(content);
		    }if(content.matches(".*\\d.*")) {
		    	String numMsg = content.replaceAll("\\D+", "");
		    	System.out.println("These are your numbers: " + numMsg);
		    }
		    if(content.matches("")) {
		    	System.out.println("Your message is blank :(");
		    }
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void appendToFile(String fileName, String msg) {
		try(FileWriter fw = new FileWriter(fileName, true);){
			fw.write(System.lineSeparator());
			fw.write(msg);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String fileName = "h1.txt";
		fileSystemSample fileTester = new fileSystemSample();
		fileTester.createFileAndGetDetails(fileName);
		fileTester.writeToFile(fileName, "This is a hw test");
		//fileTester.makeChanges(fileName);
		fileTester.readFromFile(fileName);
		fileTester.appendToFile(fileName, "This text is secretly written");
	}
}