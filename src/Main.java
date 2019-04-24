import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// mainly to see if duplicates are allowed
//		PersonList pl = new PersonList();
//		Person p1 = new Person("Jielun", "Yu");
//		Person p2 = new Person("Super", "Yu");
//		Person p3 = new Person("Super1", "Yu");
//		Person p4 = new Person("Super2", "Yu");
//		Person p5 = new Person("Super3", "Yu");
//		Person p6 = new Person("Super4", "Yu");
//		pl.add(p1);
//		pl.add(p2);
//		pl.add(p3);
//		pl.add(p4);
//		pl.add(p5);
//		pl.add(p6);pl.add(p6);
//		pl.sort();
//		System.out.println(pl.size());
		
		Graph2 g = new Graph2();
		g.loadFile("Input.txt");
		g.printGraph();
		g.export("Output");
		
	}

}
