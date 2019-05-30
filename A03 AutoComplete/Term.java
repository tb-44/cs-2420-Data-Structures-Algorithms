package autoComplete;

import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.introcs.StdOut;

/**
 * Term represents an Autocomplete term containing a 
 * String query and some search weight. Supports comparison
 * by query, reverse weight order and by query prefix order 
 * for the first r characters in query.
 * @author Trent Bennett
 * Date created: 02/23/15
 * Date last modified: 03/04/15
 */

public class Term implements Comparable<Term> {
	private String query;
	private double weight;

    /**
     * Initialize a term with the given query string and weight.
     * @param query the search query term
     * @param weight the weight of the search query
     * @throws NullPointerException if query is null
     * @throws IllegalArgumentException if weight is negative
     */

    public Term(String query, double weight) 
    {
    	if (query == null) throw new NullPointerException("query cannot be null");
    	if (weight < 0) throw new IllegalArgumentException("weight must be non negative");
    	this.query = query;
    	this.weight = weight;
    }

    /**
     * Compare the terms in descending order by weight.
     * @return a reverse weight order comparator
     */

    public static Comparator<Term> byReverseWeightOrder() 
    {
    	return new byReverseWeightComparator();
    }
    
    private static class byReverseWeightComparator implements Comparator<Term>
    {
		@Override
		public int compare(Term term1, Term term2) {
			return (int) (-1 * (term1.weight - term2.weight));
		}
	}

    /**
     * Compare the terms in lexicographic order but using only the first r characters of each query.
     * @param r first r characters to compare in query
     * @return a prefix order comparator
     * @throws IllegalArgumentException if r is negative
     */
     
    public static Comparator<Term> byPrefixOrder(int r) 
    {
    	if (r < 0) throw new IllegalArgumentException("prefix r must be non negative");
    	return new byPrefixComparator(r);
    }
    
    private static class byPrefixComparator implements Comparator<Term> 
    {
    	private int r;
		public byPrefixComparator(int r) {
			this.r = r;
		}

		@Override
		public int compare(Term term1, Term term2) 
        {
			String s1 = term1.query;
            String s2 = term2.query;
            int minlength = (s1.length() < s2.length()) ? s1.length() : s2.length();
            if (minlength >= r)
                return s1.substring(0, r).compareTo(s2.substring(0, r));
            else return s1.substring(0, minlength).compareTo(s2.substring(0, minlength));
		}
	}

    /**
     * Compare the terms in lexicographic order by query.
     * @return an int less than 0 if this is less than that, 
     * 0 if this is equal to that and an int greater than 0 of this 
     * is greater than that
     */
    @Override
    public int compareTo(Term that) 
    {
    	return this.query.compareTo(that.query);
    }

    /**
     * Return a string representation of the term in the following format:
     * the weight, followed by a tab, followed by the query.
     * @return string representation of the Term
     */
    @Override
    public String toString() {
		return this.weight + "\t" + this.query;
    }
    
    // unit test
    public static void main(String[] args) 
    {
		Term t0 = new Term("cat", 45);
		Term t1 = new Term("dog", 62);
		Term t2 = new Term("doghouse", 3);
		Term t3 = new Term("chicken", 99);
		Term[] terms = {t0, t1, t2, t3};
		
		Arrays.sort(terms);
		
		for (Term t : terms) 
			StdOut.println(t);

		StdOut.println(t0.compareTo(t1));
		
		Arrays.sort(terms, Term.byReverseWeightOrder());
		
		for (Term t : terms) 
			StdOut.println(t);
		
		StdOut.println();
		Arrays.sort(terms, Term.byPrefixOrder(3));
		
		for (Term t : terms) 
			StdOut.println(t);
	}
}
