import java.util.*;


public class Queley {

	public static void main(String[] args) {
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
}