
public class classwork3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object test = 1;
		System.out.println("My type is a " + test.getClass().getName() + " change my value and rereun");
		
		String sample = "hi";
		Object test2 = sample;
		System.out.println("My val: " + test2);

		Object test3 = "bye";
		String sample2 = (String)test3;
		System.out.println("My val: " + sample2);
		
		String numberString = "20";
		int num = -1;
		
		try{
			int number = Integer.parseInt(numberString);
		}
		catch(Exception e){
			
		}
		System.out.println("My num: "+ number);
		System.out.println("");
		String ImBroken = null;
		System.out.println("Broken String: " + ImBroken);
		
	
	}

}
