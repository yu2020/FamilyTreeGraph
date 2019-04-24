import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * A directed, weighted graph
 * @author jielun
 *
 */
public class Graph2 {
	private char[][] m;
	private PersonList personList = new PersonList();

	// read all the lines
	// find unique names and save them in the list
	// matrix size depends on the list size
	// then fill in the matrix 0s
	// sort the lines in terms of their names
	// Iterate the lines 
	// and find
	public int loadFile(String file) {
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			List<String> lines = new ArrayList<String>();
			String[] st;
			String s;
			
			while ((s= in.readLine()) != null) {
				lines.add(s);
				st = s.trim().split(",");
				// fact
				if(st.length == 2) {
					String[] names = st[0].trim().split(" ");
					boolean b;
					if(st[1].toLowerCase().equals("man"))
						b = true;
					else
						b = false;
					Person tempPerson = new Person(names[0], names[1], b);
					personList.add(tempPerson);
					
				}
				else if(st.length == 3) {
					String[] names = st[0].trim().split(" ");
					String[] names2 = st[2].trim().split(" ");
					Person tempPerson = new Person(names[0], names[1]);
					personList.add(tempPerson);
					Person tempPerson2 = new Person(names2[0], names2[1]);
					personList.add(tempPerson2);
				}
			}
			
			int size = personList.size();
			m = new char[size][size];
			
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					m[i][j] = '0';
				}
			}
			
			personList.sort();
			
			
			for(String line : lines) {
				st = line.trim().split(",");
				// ignore the facts...
				if(st.length == 3) {
					String[] names = st[0].trim().split(" ");
					String relationship = st[1].trim();
					String[] names2 = st[2].trim().split(" ");
					// i, j
					int row, column;
					char c;
					
					if(relationship.equals("father"))
						c = 'F';
					// assuming the input is correct
					else 
						c = 'M';
					
					row = personList.index(names[0], names[1]);
					column = personList.index(names2[0], names2[1]);
					m[row][column] = c;
				}
			}
			
		} catch (Exception ex) {
			System.out.println("Error in file ");
			return -1;
		}
		return 0;
	}
	
	public void printGraph() {
		for (int i = 0; i < personList.size(); i++) {
			Person tempPerson = personList.get(i);
			System.out.println(i + ". " + tempPerson.getFirstName() 
				+ " " + tempPerson.getSurname());
		}
		System.out.println("Matrix as below");
		for (int i = 0; i < m.length; i++) {
			System.out.print(i +".\t");
			for (int j = 0; j < m.length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	
	public boolean export(String file) {
		try(PrintWriter output = new PrintWriter(new FileWriter(file+".gv"))) {
			String arc = " -> ";
			output.println("digraph "+file+" {\n rankdir=LR;\n size=\"8,5\"");
			output.println("node [shape=circle] [color=blue];");
			
			for (int i = 0; i < m.length; i++) {
				for (int j = 0; j < m.length; j++) {
					if(m[i][j] != '0') {
						String relationship;
						if(m[i][j] == 'F')
							relationship = "father";
						else
							relationship = "mother";
						Person tempPerson1 = personList.get(i);
						String tempName1 = "\""+tempPerson1.getFirstName() + " " +
								tempPerson1.getSurname() + "\"";
						Person tempPerson2 = personList.get(j);
						String tempName2 = "\""+tempPerson2.getFirstName() + " " +
								tempPerson2.getSurname() + "\"";
						output.print(tempName1);
						output.print(arc);
						output.print(tempName2);
						output.println(" [label = \""+ relationship +"\"];");
					}
				}
			}
			output.println("}");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Cannot write output to file");
			return false;
		}
	}
}
