import java.util.*;


public class Queley {

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<TestKVP> q = new LinkedList<TestKVP>();
		List<String> t = new ArrayList<String>();
		t.add("a");
		t.add("b");
		t.add("c");
		t.add("d");
		for (int i = 0; i < 10; i++) {
			Collections.shuffle(t);
			String temp = ""+ t.chartAt(0);
			q.add(new TestKVP(i, temp));
		}
		System.out.println("Show queue: " + q);
		
		TestKVP first = q.remove();
		System.out.println("First: " + first);
		System.out.println("Altered Queue: " + q);
		
		TestKVP peek = q.peek();
		System.out.println("Viewing first: " + peek);
		System.out.println("Unaltered Queue: " + q);
		
	}

}

class TestKVP{
	public  int key;
	public String value;
	public TestKVP(int k, String v) {
		this.key = k;
		this.value = v;
	}
	public String toString() {
		return "{'key:'" + this.key +}
	}
}*/
	
	public static void main(String[] args) {
		Queue<CustomKeyValuePair> queue = new LinkedList<CustomKeyValuePair>();
		for(int i = 0; i < 10; i++) {
			queue.add(new CustomKeyValuePair(i, "A Value"));
		}
		
		System.out.println("Show queue: " + queue);
		
		CustomKeyValuePair first = queue.remove();
		System.out.println("Pulled first: " + first);
		System.out.println("Show altered queue: " + queue);
		
		CustomKeyValuePair peek = queue.peek();
		System.out.println("Just viewing: " + peek);
		System.out.println("Show unaltered queue: " + queue);
		
		
		}
	}
	class CustomKeyValuePair{
		public int key;
		public String value;
		public CustomKeyValuePair(int k, String v) {
			this.key = k;
			this.value = v;
		}
		@Override
		public String toString() {
			return "{'key':'" + this.key + "', 'value':'" + this.value + "'}";
		}
	}