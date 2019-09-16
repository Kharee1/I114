import java.util.*;

public class listHomework{
   
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
   }
}