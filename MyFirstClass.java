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
		MyFirstClass myFirstClassObject = new MyFirstClass();
		myFirstClassObject.myPublicInt = 1;
		System.out.println(myFirstClassObject.myPublicInt);
	}
}