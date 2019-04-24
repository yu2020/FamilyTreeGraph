import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Family Tree - A directed and weighted graph
 * @author u1711101
 *
 */
public class Graph {
	protected boolean directed=true; // set true for directed graphs, false for undirected
	protected boolean weighted=false;
	protected int[][] m; // contains the weights
	//given the node name return the row in m in constant time
	protected HashMap<String,Integer> map = new HashMap<String,Integer>();
	//given the row return the node name in constant time
	protected ArrayList<String> nodes = new ArrayList<String>();
	protected int size; //number of nodes
	
	// how to determine the size of the matrix of the family graph...
	private static final int SIZE = 60;
	
	// add an arc (edge) in a weighted graph v1->V2 
    public  void addEdge(String node1, String node2, int weight) {
    	int row=-1, column=-1;
    	if (!map.containsKey(node1)) { //new node
    		//list size will return the current free index at the end
    		row = nodes.size();
    		map.put(node1, row);
    		nodes.add(node1);
    	}
    	else  // node has been already inserted
    		row = map.get(node1);
    	
    	if (!map.containsKey(node2)) {
    	//list size will return the current free index at the end
    		column = nodes.size();
    		map.put(node2, column);
    		nodes.add(node2);
    	}
    	else column = map.get(node2);
    	
    	m[row][column]= weight;
    	if (!directed) 
    		m[column][row] =weight;
    }
	
    
	public void printGraph() {
		int maxlength=0;
		for (String s : nodes) {
			if  (s.length()>maxlength) maxlength=s.length();
		}
		for (int i=0; i<maxlength+5;i++)
				System.out.print(" ");
		for (int i=0; i < size ; i++ )
			System.out.format("%2d ", i);
		System.out.println();
		String s;
		for (int i=0; i < m.length ; i++ ) {
			s = nodes.get(i); System.out.print(s);
			for (int j=s.length();j<=maxlength; j++)
					System.out.print(" ");
			System.out.format("%2d: ", i);
			// changed 
			// was for (int j=0; j < m.length ; j++ ) {
			for (int j=0; j < 11 ; j++ ) {
				System.out.format("%2d ", m[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * notes - may need to change this later
	 * according to the requirement,
	 * "Each line contains a fact about a person or a relationship between two persons as a
		comma separated list; a fact contains two strings in the line, while a relationship contains
		three strings in the line."
		
	 * @param file
	 * @return
	 */
	//load graph from a file
    public int loadFile(String file) {
    	try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			//read the file line by line
			String[] st; String node1, node2, relationship; int arc;
			String s= in.readLine(); //read first line
			
			
			
			m = new int[SIZE][SIZE]; //create adjacency matrix 
			while ((s= in.readLine()) != null) {
				st = s.trim().split(",");
				// fact
				if(st.length == 2) {
					// ignore this at the moment
				}
				// relationship
				else if (st.length == 3) {
					node1 = st[0].trim();
					relationship = st[1].trim();
					node2 = st[2].trim();
					relationship = relationship.toLowerCase();
//					switch(relationship){
//					case "father":
//						arc= 1;break;
//					case "mother":
//						arc = 2; break;
//					}
					if(relationship.equals("father"))
						arc = 1;
					else if(relationship.equals("mother"))
						arc = 2;
					else 
						arc = 0;
					addEdge(node1, node2, arc);
				} 
		
			}
			in.close();
		} catch (Exception ex) {
			System.out.println("Error in file ");
			return -1;
		}
    	return 0;
    }
    
    // --- may need to change this
    ///export to DOT format for GraphViz visualization
    public boolean export(String file) {
    	PrintWriter output;
    	try {
			output = new PrintWriter(new FileWriter(file+".gv"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Cannot write output to file");
			return false;
		}
    	String arc=" -- ";
    	if (directed) {
    		output.print("di");
    		arc = " -> ";
    	}
    	output.println("graph "+file+" {\n rankdir=LR;\n size=\"8,5\"");
    	output.println("node [shape=circle] [color=blue];");
    	
    	for (int i=0; i<m.length;i++) {
    		// if directed graph visit all matrix
    		// if undirected then visit only half of it (triangular matrix)
    		// as weight is duplicated: m[i][j] == m[j][i]
    	  for (int j=0; (directed && j<m.length) || (!directed && j<i); j++) {
    		if (m[i][j]!=0) { // if there is an arc between i and j
    			output.print(nodes.get(i));
    			output.print(arc);
    			output.print(nodes.get(j));
    			if (weighted) output.println(" [ label = \""+ m[i][j] +"\"];");
    			else output.println(";");
    		}
    	  }
    	}
    	output.println("}");
    	output.close();
    	return true;
    }
    
    /**
     * The number of nodes that are going towards the node 
     * @param node
     * @return
     */
    public int inDegree(String node) {
    	// not a directed graph, no direction so return 0
    	if(!directed)
    		return 0;
    	else
    	{
    		int a = map.get(node);
    		int sum = 0;
    		for (int i = 0; i < m.length; i++) {
        		int b = m[i][a];
        		if(b != 0)
        			sum = sum + 1;
    		}
    		return sum;
    	}
    }
    
    /**
     * The number of nodes that are going from the node 
     * @param node
     * @return
     */
    public int outDegree(String node) {
    	// not a directed graph, no direction so return 0
    	if(!directed)
    		return 0;
    	else
    	{
    		int a = map.get(node);
    		int sum = 0;
    		for (int i = 0; i < m.length; i++) {
        		int b = m[a][i];
        		if(b != 0)
        			sum = sum + 1;
    		}
    		return sum;
    	}
    }
    
    /**
     * This method should find the relationship between a and b,
     * and return a message
     * 
     * @param a - first person object
     * @param b - second person object
     * @return a message stating their relationship
     */
    public String findRelationship(Person a, Person b) {
		return null;
    	
    }
}
