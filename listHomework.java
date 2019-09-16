package homework;


import java.util.*;

public class listHomework{
	Random num = new Random();
	
public void knuth(String[] word) {
	//setting variable to the one less than the length of the list
	//as long as variable value is greater than 0, subtract one to iterate through
	for(int i = word.length-1; i > 0; i--) {
		int a = num.nextInt(word.length-1);
		// creating a string temp that will hold the word of the initial index
		String temp = word[i];
		//swapping the initial index with second index 
		word[i] = word[a];
		//swapping the second index to the original word
		word[a] = temp;
		System.out.println(Arrays.toString(word));
	}
}
   
   public static void main(String[] args){
      List<String> hwStrings = new ArrayList<String>();
      
      //adding strings to the list
      
      hwStrings.add("Eel");
      hwStrings.add("Cat");
      hwStrings.add("Dog");
      hwStrings.add("Fish");
      hwStrings.add("Lizard");
      hwStrings.add("Ant");
      
      System.out.println("Default: " + hwStrings);
      
      //performing a reverse sort
      Collections.reverse(hwStrings);
      System.out.println("Reverse: " + hwStrings);
      
      //performing a shuffle
      Collections.shuffle(hwStrings);
      System.out.println("Shuffle: " + hwStrings);
      
      //creating list for numbers
      List<Integer> numList = new ArrayList<Integer>();
      
      for(int i = 0; i < 10; i++) { 
         numList.add(i);
      }
      System.out.println(numList);
      
      //creating variable double so it can handle total
      double total = 0;
      for(int i = 0; i < numList.size(); i++){
         total += numList.get(i);
         
         if ((i%2)==0){
            System.out.println(i + " is even");
         }
         if ((i%2)==1){
            System.out.println(i + " is odd");
         }
      }
      System.out.println(total);
      listHomework tester = new listHomework();
      String[] test = {"Dog", "Cat", "Bird", "Horse", "Snake", "Frog"};
      tester.knuth(test);
   }
}