package wordNet;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

/**
 * Shortest Ancestral Path
 * Date created: 04/13/15
 * Date last modified 05/01/15
 */

public class SAP {
	private final int pre = 0;
	private final int len = 1;
	private final Digraph digraph;
	private final DepthFirstOrder dfo;
	private final DirectedCycle dc;
	
	/**
	 * Constructor takes a digraph (not necessarily a DAG)
	 * @param G the digraph
	 */
     
	public SAP(Digraph G) {
		if (G == null) throw new NullPointerException("G cannot be null");
		digraph = new Digraph(G);
		dfo = new DepthFirstOrder(digraph);
		dc = new DirectedCycle(digraph);
	}

	/**
	 * Is the digraph a directed acyclic graph?
	 * @return is the digraph a directed acyclic graph?
	 */

	public boolean isDAG() {
		return !dc.hasCycle(); // ! to synchronize true/false interpretation
	}

	/**
	 * Is the digraph a rooted DAG?
	 * @return is the digraph a rooted DAG?
	 */

	public boolean isRootedDAG() {
		if (this.isDAG()) {
			Queue<Integer> postorder = new Queue<>();
			postorder = (Queue<Integer>) dfo.post();
			int root = postorder.dequeue();
			return digraph.outdegree(root) == 0; // rooted if index 0 in post order has outdegree 0
		}
		else return false;
	}

	/**
	 * Length of shortest ancestral path between v and w; 
	 * -1 if no such path
	 * @param v the first vertex
	 * @param w the second vertex
	 * @return length of the shortest ancestral path between v and w; 
	 * -1 if no such path
	 */

	public int length(int v, int w) {
		int[] values = ancestorEnhanced(v, w);
		if (values[pre] == -1) return -1;
		else return values[len]; // length
	}
	
	// Helper method that returns int[] of ancestor and length
	// int[0] == int[pre] == ancestor
	// int[1] == int[len] == length
	// Makes creating BreadthFirstDirectedPaths object more compact (minimal)
	// pre and len make code easier to read, both are final variables (see top)
	private int[] ancestorEnhanced(int v, int w) {
		int[] values = new int[2];
		int ancestor = -1;
		int length = Integer.MAX_VALUE;
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);
		Bag<Integer> ancestors = new Bag<>();
		for (int i = 0; i < digraph.V(); i++)
			if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i))
				ancestors.add(i);
		
		for (int a : ancestors) {
			int tempLength = bfsV.distTo(a) + bfsW.distTo(a);
			if (tempLength < length){
				length = tempLength;
				ancestor = a; }	
		}
		values[pre] = ancestor;
		values[len] = length;
		return values;
		
	}

	/**
	 * A common ancestor of v and w that participates in a 
	 * shortest ancestral path; -1 if no such path
	 * @param v the first vertex
	 * @param w the second vertex
	 * @return a common ancestor of v and w that participates in a 
	 * shortest ancestral path; -1 if no such path
	 */

	public int ancestor(int v, int w) {
		int[] values = ancestorEnhanced(v, w);
		return values[pre]; // ancestor
	}

	/**
	 * Length of shortest ancestral path between any vertex 
	 * in v and any vertex in w; -1 if no such path
	 * @param v the first vertex set 
	 * @param w the second vertex set
	 * @return Length of shortest ancestral path between any vertex 
	 * in v and any vertex in w; -1 if no such path
	 */

	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		if (v == null) throw new NullPointerException("v cannot be null");
		if (w == null) throw new NullPointerException("w cannot be null");
		int[] values = ancestorEnhanced(v, w);
		if (values[pre] == -1) return -1;
		else return values[len]; // length
	}
	
	// Helper method that returns int[] of ancestor and length
	// int[0] == int[pre] == ancestor
	// int[1] == int[len] == length
	// Makes creating BreadthFirstDirectedPaths object more compact (minimal)
	// pre and len make code easier to read, both are final variables (see top)
	private int[] ancestorEnhanced(Iterable<Integer> v, Iterable<Integer> w) {
		int[] values = new int[2];
		int ancestor = -1;
		int length = Integer.MAX_VALUE;
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);
		Bag<Integer> ancestors = new Bag<>();
		for (int i = 0; i < digraph.V(); i++)
			if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i))
				ancestors.add(i);
		
		for (int a : ancestors) {
			int tempLength = bfsV.distTo(a) + bfsW.distTo(a);
			if (tempLength < length){
				length = tempLength;
				ancestor = a; }	
		}
		values[pre] = ancestor;
		values[len] = length;
		return values;
		
	}

	/**
	 * A common ancestor that participates in shortest 
	 * ancestral path; -1 if no such path
	 * @param v the first vertex set
	 * @param w the second vertex set
	 * @return A common ancestor that participates in shortest 
	 * ancestral path; -1 if no such path
	 */

	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		if (v == null) throw new NullPointerException("v cannot be null");
		if (w == null) throw new NullPointerException("w cannot be null");
		int[] values = ancestorEnhanced(v, w);
		return values[pre]; // ancestor
	}

	// unit testing
	public static void main(String[] args) {
		String file = "src/wordNet/digraph2.txt";
		In in = new In(file);
		Digraph digraph = new Digraph(in);
		SAP sap = new SAP(digraph);
		int v = 5;
		int w = 4;
		Bag<Integer> vs = new Bag<>();
		vs.add(3);
		vs.add(4);
		Bag<Integer> ws = new Bag<>();
		ws.add(5);
		ws.add(2);
		StdOut.println(sap.isDAG());
		StdOut.println(sap.isRootedDAG());
		StdOut.println("Single Source: v = " + v + ", w = " + w);
		StdOut.println("Ancestor: " + sap.ancestor(v, w));
		StdOut.println("Length: " + sap.length(v, w) + "\n");
		StdOut.print("Multiple Sources: vs = ");
		for (int i : vs)
			StdOut.print(i + ", ");
		StdOut.print("ws = ");
		for (int i : ws)
			StdOut.print(i + ", ");
		StdOut.println();
		StdOut.println("Ancestor: " + sap.ancestor(vs, ws));
		StdOut.println("Length: " + sap.length(vs, ws));
	}
}