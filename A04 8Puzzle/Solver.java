package eightPuzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

/** Solver class that finds the solution
 * to the puzzle problems given
 * @author Trent Bennett */

public class Solver {
	private MinPQ<SearchNode> pq;
	private Stack<Board> solve;
	  
	/** Find a solution to the initial board(using the A* Algorithm) */
	public Solver(Board initial) {
		if (initial == null) throw new NullPointerException("Cannot be null");
		if (!initial.isSolvable()) throw new IllegalArgumentException("Illegal argument");
	    
	    pq = new MinPQ<SearchNode>();
	    solve = new Stack<Board>();
	    
	    SearchNode sn = new SearchNode(initial, null);
	    pq.insert(sn);
	    
	    while (!sn.board.isGoal()) {
	      sn = pq.delMin(); 
	      Stack<Board> sb = (Stack<Board>) sn.board.neighbors();
	      for (Board b : sb) {
	        SearchNode a = new SearchNode(b, sn);
	        if (sn.previous == null) {
	          pq.insert(a); 
	          continue;
	        }
	        
	        if (!a.board.equals(sn.previous.board)) 
	          pq.insert(a); 
	      }
	    }
	    
	    while (sn != null) {
	      Board st = sn.board;
	      solve.push(st);
	      sn = sn.previous;
	    } 
	  }
	  
	  /** Minimum number of moves to solve initial board */
	  public int moves() 
	  {  return solve.size() - 1;  }
	  
	  /**Sequence of boards in the shortest solution*/
	  public Iterable<Board> solution() 
	  {  return solve;  }
	  
	  /** Private SearchNode class that compares
	   * SearchNodes according to manhattan priority*/
	  private class SearchNode implements Comparable<SearchNode> {
	    private Board board; 
	    private int moves; 
	    private int priority; 
	    private SearchNode previous; 
	    
	    public SearchNode(Board b, SearchNode p) {
	      board = b;
	      previous = p;

	      if (previous == null) {
	        moves = 0;
	        priority = b.manhattan();
	      }
	      
	      else {
	        moves = p.moves + 1;
	        priority = b.manhattan() + moves;
	      }
	    }
	    
	    public int compareTo(SearchNode that) {
	      if (this.priority > that.priority) 
	    	  return +1;
	      if (this.priority < that.priority) 
	    	  return -1;
	      		
	      return 0;
	    }
	  }
	  
	  /** Solve a slider puzzle (given below) */
	  public static void main(String[] args) {
		
		// create initial board from file 
		String filename = args[0];
	    In in = new In(filename);
	    int N = in.readInt();
	    int[][] blocks = new int[N][N];
	    for (int i = 0; i < N; i++)
	      for (int j = 0; j < N; j++)
	      blocks[i][j] = in.readInt();
	    Board initial = new Board(blocks);
	    
	    // check if puzzle is solvable; if so, solve it and output solution
	    if (initial.isSolvable()) {
	      Solver solver = new Solver(initial);
	      StdOut.println("Minimum number of moves: " + solver.moves());
	      for (Board board : solver.solution())
	        StdOut.println(board);
	    }
	    
	    // if not, report unsolvable
	    else {
	      StdOut.println("Unsolvable puzzle");
	    }
	  }
	}