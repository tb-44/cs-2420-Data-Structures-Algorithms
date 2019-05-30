package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author Trent Bennett 
 * 
 * Class Percolation
 * Model a percolation system using N-by-N grid
 * checking to see if the site is open or blocked
 * The system percolates if there is a full site from
 * the top row to the bottom 
 */

public class Percolation {
    private int[][] grid; 
    private int gridsize; 
    private WeightedQuickUnionUF sites, backwash; 
    
    /**
     * Create N-by-N grid, with all sites blocked  
     * @param N
     */
    public Percolation(int N) {
        this.grid = new int[N][N];
        this.gridsize = N;
        this.sites = new WeightedQuickUnionUF(N * N + 2);
        this.backwash = new WeightedQuickUnionUF(N * N + 2);
    }
    
    /**
     * Point returns the 1D representation of the point (i,j)
     * @param i
     * @param j
     */
    private int point(int i, int j)
    {  
        return i * this.gridsize + j;
    }
    
    /**
     * Validates if i or j is within the specified bounds
     * If it does not, it will throw an IndexOutOfBoundsException
     * @param i
     * @param j
     */

    private void validate(int i, int j) {
        if (i < 0 || i >= this.gridsize) 
        {
            throw new IndexOutOfBoundsException("row index " + i + " out of bounds");
        }
        if (j < 0 || j >= this.gridsize) 
        {
            throw new IndexOutOfBoundsException("column index " + j + " out of bounds");
        }
    }
    
    /**
     * Opens the site (row i, column j) if it
     * is not already open
     */

    public void open(int i, int j) {
        validate(i, j);

        if (!isOpen(i, j)) {
            this.grid[i][j] = 1;
           
            if (i == 0) 
            {
                sites.union(0, point(i, j));
                backwash.union(0, point(i, j));
            }
            
            if (i == this.gridsize - 1) 
            {
                backwash.union(point(i, j), this.gridsize * this.gridsize + 1);
            }
            
            // connect to other open neighbors
            if (i > 0 && isOpen(i - 1, j)) 
            {
                sites.union(point(i - 1, j), point(i, j));
                backwash.union(point(i - 1, j), point(i, j));
            }
            if (i < this.gridsize - 1 && isOpen(i + 1, j)) 
            {
                sites.union(point(i + 1, j), point(i, j));
                backwash.union(point(i + 1, j), point(i, j));
            }
            if (j > 0 && isOpen(i, j - 1)) 
            {
                sites.union(point(i, j - 1), point(i, j));
                backwash.union(point(i, j - 1), point(i, j));
            }
            if (j < this.gridsize - 1 && isOpen(i, j + 1)) 
            {
                sites.union(point(i, j + 1), point(i, j));
                backwash.union(point(i, j + 1), point(i, j));
            }
        }
    }
    
    /**
     * Check to see if the site is open
     * for (row i, column j)
     */

    public boolean isOpen(int i, int j) 
    {
        validate(i, j);
        return (this.grid[i][j] == 1);
    }
    
    /**
     * Checks to see if the site is full
     * for (row i, column j)
     */

    public boolean isFull(int i, int j) 
    {
        validate(i, j);
        return this.sites.connected(point(i, j), 0) && this.isOpen(i, j);
    }
    
    /**
     * Checks to see if the system percolates or not
     */
    public boolean percolates()
    { 
        return this.backwash.connected(0, this.gridsize * this.gridsize + 1);
    }
}
