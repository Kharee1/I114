import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class listPractice {



public static void main(String[] args) {
	List<String> myStrings = new ArrayList<String>();
	String[] stringArray = new String[3];
	
	myStrings.add("Hi");
	myStrings.add("Bye");
	myStrings.add("Hi again");
	myStrings.add("And I'm still here");
	myStrings.add("See you tomorrow");
	
	
	//make it sort alphabetically
	Collections.sort(myStrings);
	//make it sort reverse alphabetically
	Collections.reverse(myStrings);
	//make it shuffle
	Collections.shuffle(myStrings);
	
	List<Integer> myInts = new ArrayList<Integer>();
	for(int i = 0; i < 10; i++) {
		myInts.add(i);
	}
	int total = 0;
	for (int i = 0; i < myInts.size(); i++) {
		total += myInts.get(i);
	}
	
	System.out.println("My Total: " + total);
	
	String temp = stringArray[2];
	stringArray[2]= stringArray[0];
	stringArray[0] = temp;
	
	//myStrings.add(1);;
	int myInt = 1;
	String testString = myInt+"";
	System.out.println(testString);
	if(testString.equals(myInt)) {
		System.out.println("testString = myInt");
	}
	else {
		System.out.println("testString != myInt");
	}
	myStrings.set(2,  "It's me");
	int size = myStrings.size();
	for (int i=0; i<size; i++) {
		/*if(i==1) {
			myStrings.remove(i);
		}*/
		System.out.println("Index[" + i + "] =>" + myStrings.get(i));
	}
	
	}
}
