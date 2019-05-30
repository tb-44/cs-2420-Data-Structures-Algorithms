package eightPuzzle;

import edu.princeton.cs.algs4.Stack;

/** Board class that constructs a puzzle board for
 * an N x N array of blocks
 * @author Trent Bennett */

public class Board {
	  private int N; 
	  private int ham;
	  private int man; 
	  private int[][] tile; 
	  private Stack<Board> neighbors; 
	  
	  /** Construct a board from an N x N array of blocks
	   * (where blocks [i][j] = block in row i, column j) */
	  public Board(int[][] blocks) {
	    
	    N = blocks.length;
	    tile = new int[N][N];
	    
	    for (int i = 0; i < N; i++) {
	      for (int j = 0; j < N; j++) 
	        tile[i][j] = blocks[i][j];
	    }
	    
	    ham = hamming();
	    man = manhattan();
	  }
	  
	  /** Board size N */
	  public int size() 
	  {  
          return N * N;  
      }
	  
	  /** Number of blocks out of place (hamming) */
	  public int hamming() {
	    ham = 0;
	    for (int i = 0; i < N; i++) {
	      for (int j = 0; j < N; j++) {
	        if (tile[i][j] == 0) continue;
	        if (tile[i][j] != convert(i, j)) ham++; 
	      }
	    }
	    
	    return ham;
	  }
	  
	  /** Sum of Manhattan distances between blocks and goal */
	  public int manhattan() {
	    man = 0;
	    for (int i = 0; i < N; i++) {
	      for (int j = 0; j < N; j++) {
	        if (tile[i][j] == 0) continue; 
	        
	        if (tile[i][j] != convert(i, j)) { 
	          int val = tile[i][j] - 1;  
	          
	          // (x, y) is proper coordinate of tiles[i][j]
	          int x = val / tile[0].length;
	          int y = val % tile[0].length;
	          
	          // distance between (i, j) and (x, y)
	          man += Math.abs(x - i) + Math.abs(y - j); 
	        }
	      }
	    }
	    
	    return man;
	  }
	  
	  /** Is this board the goal board? */
	  public boolean isGoal() 
	  {  return man == 0;  }
	  
	  /** Is this board solvable? */
	  public boolean isSolvable() {
	    if (size() == 0 || size() == 1)
	      return true;
	    
	    // inversion is any pair of blocks i and j where i < j
	    int inv = 0; 
	    int x = -1; 
	    
	    // compare tile (i, j) to all successive tiles (k, m)
	    for (int i = 0; i < N; i++) {
	      for (int j = 0; j < N; j++) {
	        if (tile[i][j] == 0) { 
	          x = i;
	          continue;
	        }
	        
	        for (int k = i; k < N; k++) {
	          for (int m = 0; m < N; m++) {
	            if (i == k && m < j) 
	              m = j; 
	            if (tile[k][m] == 0) 
	              continue;
	            if (tile[i][j] > tile[k][m]) 
	              inv++;
	          }
	        }
	      }
	    }
	    
	    assert (x != -1);
	    
	    // board size is odd and number of inversions is odd
	    if (this.size() % 2 == 1 && inv % 2 == 1) 
	    	return false;
	    
	    // board size is even and number of inversions is even
	    if (this.size() % 2 == 0 && (inv + x) % 2 == 0) 
	    	return false;
	    
	    return true;
	  }
	  
	  /** Does this board equal y? */
	  public boolean equals(Object y) {
	    if (y == this)                       
	    	return true;
	    if (y == null)                       
	    	return false;
	    if (y.getClass() != this.getClass()) 
	    	return false;
	    
	    Board b = (Board) y;
	    if (b.size() != this.size())         
	    	return false;
	    
	    for (int i = 0; i < N; i++) {
	      for (int j = 0; j < N; j++) {
	        if (this.tile[i][j] != b.tile[i][j])
	          return false;
	      }
	    }
	    
	    return true;
	  }
	  
	  /** All neighboring boards */
	  public Iterable<Board> neighbors() {
	    neighbors = new Stack<Board>();
	    int x = 0; 
	    int y = 0; 
	    
	    for (int i = 0; i < N; i++) { 
	      for (int j = 0; j < N; j++) {
	        if (tile[i][j] == 0) {
	          x = i;
	          y = j;
	          break;
	        }
	      }
	    }
	    
	    // lower neighbor
	    if (valid(x + 1, y)) {
	      Board neighbor = new Board(this.tile); 
	      neighbor.tile[x][y] = neighbor.tile[x + 1][y];
	      neighbor.tile[x + 1][y] = 0;
	      neighbors.push(neighbor);
	    }
	    
	    // right neighbor
	    if (valid(x, y + 1)) {
	      Board neighbor = new Board(this.tile); 
	      neighbor.tile[x][y] = neighbor.tile[x][y + 1];
	      neighbor.tile[x][y + 1] = 0;
	      neighbors.push(neighbor);
	    }
	    
	    // left neighbor
	    if (valid(x, y - 1)) {
	      Board neighbor = new Board(this.tile); 
	      neighbor.tile[x][y] = neighbor.tile[x][y - 1];
	      neighbor.tile[x][y - 1] = 0;
	      neighbors.push(neighbor);
	    }
	    
	    // upper neighbor
	    if (valid(x - 1, y)) {
	      Board neighbor = new Board(this.tile); 
	      neighbor.tile[x][y] = neighbor.tile[x - 1][y];
	      neighbor.tile[x - 1][y] = 0;
	      neighbors.push(neighbor);
	    }
	    
	    return neighbors; 
	  }
	  
	  /** String representation of this board (in the output format specified below) */
	  public String toString() {
	    StringBuilder s = new StringBuilder();
	    s.append(N + "\n");
	    for (int i = 0; i < N; i++) {
	      for (int j = 0; j < N; j++) {
	        s.append(String.format("%2d ", tile[i][j]));
	      }
	      
	      s.append("\n");
	    }
	    
	    return s.toString();
	  }
	  
	  // convert 2D coordinates to board coordinates
	  private int convert(int i, int j) {
	    int x = i * N + j + 1; 
	    return x;
	  }
	  
	  // test if (i, j) is within the bounds of the grid
	  private boolean valid(int i, int j) {
	    if (i < 0 || i >= N) return false;
	    if (j < 0 || j >= N) return false;
	    return true;
	  }
	  
	  // unit testing (removed for clarity)
	  public static void main(String[] args) {
	  
	}
}