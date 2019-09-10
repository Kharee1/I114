import java.util.Date;

public class MyFirstClass {
	//can be used everywhere
	public int myPublicInt;
	
	//accessed by the class that defines it
	private int myPrivateInt;
	
	//used in specific class itself and by inheriting and parent classes
	protected int myProtectedInt;
	
	public String myString;
	public char myChar;
	public Date myDate;
	
	public static void main(String[] args) {
		/*MyFirstClass myFirstClassObject = new MyFirstClass();
		myFirstClassObject.myPublicInt = 1;
		System.out.println(myFirstClassObject.myPublicInt);
		
		
		for (int k = 0; k < 10; k++) {
			int count = 0;
			float floatCount = 0.0f;
			
			//float total = 0f;
			
			for(int i =0; i < 10; i++) {
				count ++;
				floatCount += 0.1f;
			}
			System.out.println("Count: " + count);
			System.out.println("Float Count: " + floatCount);
			if (floatCount == 10) {
				System.out.println("It's actually 10!");
			}
		}*/
		
		//String name = null;
		String name = "Bob";
		if ("Bob".equals(name)) {
			System.out.println("Bob equals name");
		}
		if (name.equals("Bob")) {
			System.out.println("name equals Bob");
		}
		if ("Bob" == name) {
			System.out.println("Bob is name");
		}		
	}
}