package autoComplete;

import java.util.Arrays;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

/**
 * Finds all matching terms given a search term.
 * @author Trent Bennett
 * Date created: 02/23/15
 * Date last modified: 03/05/15
 */

public class Autocomplete {
	public Term[] queries;

	/**
	 * Initialize the data structure from the given array of terms.
	 * @param terms the terms to initialize into the array
	 */
	public Autocomplete(Term[] terms) {
		if (terms == null) throw new java.lang.NullPointerException("terms cannot be null");
  	
		this.queries = terms; // N
		Arrays.sort(queries); // N log N
	}

	/**
	 * Return all terms that start with the given prefix, in descending order of weight.
	 * @param prefix the prefix to search for
	 * @return the terms matching the given prefix
	 */
	public Term[] allMatches(String prefix) 
    {
		if (prefix == null) throw new java.lang.NullPointerException("prefix cannot be null");
  	
		Term tm = new Term(prefix, 0);
		Term[] matches;
  	
		int i = BinarySearchDeluxe.firstIndexOf(queries, tm, Term.byPrefixOrder(prefix.length()));
		int j = BinarySearchDeluxe.lastIndexOf(queries, tm, Term.byPrefixOrder(prefix.length()));
		if (i == -1 || j == -1) 
        { 
			matches = new Term[0];
			return matches; 
		}
  	
		matches = new Term[j - i + 1];
		matches = Arrays.copyOfRange(queries, i, j+1); // need +1 to include j in copy
		Arrays.sort(matches, Term.byReverseWeightOrder());
		return matches;
	}
  
	/**
	 * Return the number of terms that start with the given prefix.
	 * @param prefix the prefix to search for
	 * @return the number of matching terms
	 */
	public int numberOfMatches(String prefix) 
    {
		if (prefix == null) throw new java.lang.NullPointerException("prefix cannot be null");
  	
		Term tm2 = new Term(prefix, 0);
		int i = BinarySearchDeluxe.firstIndexOf(queries, tm2, Term.byPrefixOrder(prefix.length()));
		int j = BinarySearchDeluxe.lastIndexOf(queries, tm2, Term.byPrefixOrder(prefix.length()));
		return j - i + 1;
	}

	// unit test
	public static void main(String[] args) 
    {
		// read in the terms from a file
		String filename = "src/autoComplete/movies.txt";
		In in = new In(filename);
		int N = in.readInt();
		Term[] terms = new Term[N];

		for (int i = 0; i < N; i++) 
        {
			double weight = in.readDouble();       // read the next weight
			in.readChar();                         // scan past the tab
			String query = in.readLine();          // read the next query
			terms[i] = new Term(query, weight);    // construct the term
		}

		// read in queries from standard input and print out the top k matching terms
		int k = 10;
		Autocomplete autocomplete = new Autocomplete(terms);
		while (StdIn.hasNextLine()) {
			String prefix = StdIn.readLine();
			Term[] results = autocomplete.allMatches(prefix); 
			for (int i = 0; i < Math.min(k, results.length); i++)
					StdOut.println(results[i]);
		}
	}
}