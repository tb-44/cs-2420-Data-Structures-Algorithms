package percolation;

import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

/**
 * @author Trent Bennett
 * 
 * Class PercolationStats
 * Performs a series of computational experiments
 * Will print the values of the mean,
 * standard deviation, and 95% confidence high or low
 */

public class PercolationStats {
    private int N;
    private int T;
    private double[] site;
    
    /**
     * Performs T independent computational experiments 
     * on an N-by-N grid.  The constructor takes two
     * arguments N & T to perform T independent computational
     * experiments on an N-by-N grid, and prints the stats.
     * @param N
     * @param T
     */

    public PercolationStats(int N, int T) {
        if (N <= 0) { throw new IllegalArgumentException("N must be > 0"); }
        if (T <= 0) { throw new IllegalArgumentException("T must be > 0"); }
        this.N = N;
        this.T = T;
        this.site = new double[T];
        
        for (int i = 0; i < T; i++) {
            Percolation p = new Percolation(N);
            int opencount = 0;
            
            while (!p.percolates()) 
            {
                int row, col;
                row = StdRandom.uniform(0, N);
                col = StdRandom.uniform(0, N);

                if(!p.isOpen(row, col))
                {
                    p.open(row, col);
                    opencount++;
                }
            }
            this.addToSitesRequired(i, opencount);
        }
   
        StdOut.println("mean            " + "= " + this.mean());
        StdOut.println("stddev          " + "= " + this.stddev());
        StdOut.println("confidence low" + "  = " + this.confidenceLow());
        StdOut.println("confidence high" + " = " + this.confidenceHigh());
    }
    
    // adds an entry to the array of the ratio of sites required
    private void addToSitesRequired(int index, int numsites)
    { 
        this.site[index] = (double)numsites / (double)(N * N); 
    }
    
    // sample mean of percolation threshold
    public double mean()
    { 
        return StdStats.mean(site);
    }
    
    // sample standard deviation of percolation threshold
    public double stddev()
    { 
        return StdStats.stddev(site); 
    }
    
    // returns lower bound of the 95% confidence interval
    public double confidenceLow()
    { 
        return mean() - (1.96 * stddev()) / Math.sqrt(T); 
    }
    
    // returns upper bound of the 95% confidence interval
    public double confidenceHigh()
    { 
        return mean() + (1.96 * stddev()) / Math.sqrt(T); 
    }        
    
    public static void main(String[] args) {

        @SuppressWarnings("unused")
		PercolationStats ps = new PercolationStats(200, 100);
        
    }
}