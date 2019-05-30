package autoComplete;

import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.introcs.StdOut;

/**
 * Deluxe Binary Search. Finds either the first instance of
 * some key or the last in an array.
 * @author Trent Bennett
 * Date created: 02/23/15
 * Date last modified: 03/05/15
 */

public class BinarySearchDeluxe {

    /** 
     * Return the index of the first key in a[] that equals the search key, or -1 if no such key.
     * @param a the array to search
     * @param key the key to search for
     * @param comparator the comparator to use against a[i] and key
     * @return the index of the first key in a[] or -1 if no such key
     */
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) 
    {
    	if (a == null) throw new NullPointerException("a cannot be null");
    	if (key == null) throw new NullPointerException("key cannot be null");
    	if (comparator == null) throw new NullPointerException("comparator cannot be null");
    	int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) 
        {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            int cmp = comparator.compare(key, a[mid]);
            // check element before mid, if equal keep searching
            int before = (mid-1 != -1) ? comparator.compare(key, a[mid-1]) : -1;
            if      (cmp < 0 || before == 0)	hi = mid - 1;
            else if (cmp > 0) 					lo = mid + 1;
            else return mid;
        }
        return -1;
    	
    }

    /**
     * Return the index of the last key in a[] that equals the search key, or -1 if no such key.
     * @param a the array to search
     * @param key the key to search for
     * @param comparator the comparator to use against a[i] and key
     * @return the index of the last key in a[] or -1 if no such key
     */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) 
    {
    	if (a == null) throw new NullPointerException("a cannot be null");
    	if (key == null) throw new NullPointerException("key cannot be null");
    	if (comparator == null) throw new NullPointerException("comparator cannot be null");
    	int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) 
        {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            int cmp = comparator.compare(key, a[mid]);
            // check element after mid, if equal keep searching
            int after = (mid+1 != a.length) ? comparator.compare(key, a[mid+1]) : -1;
            if      (cmp < 0)				hi = mid - 1;
            else if (cmp > 0 || after == 0)	lo = mid + 1;
            else return mid;
        }
        return -1;
    }
    
    // unit test
    public static void main(String[] args) 
    {
		String[] strings = {"cat", "dog", "chicken", "cat", "cat", "dog", "chicken", "cat", "cat", "dog", "chicken"};
		Arrays.sort(strings);
		for (String s : strings)
			StdOut.println(s);
		
		StdOut.println("firstIndexOf cat: " + firstIndexOf(strings, "cat", String.CASE_INSENSITIVE_ORDER));
		StdOut.println("lastIndexOf cat: " + lastIndexOf(strings, "cat", String.CASE_INSENSITIVE_ORDER));
		
		StdOut.println("firstIndexOf dog: " + firstIndexOf(strings, "dog", String.CASE_INSENSITIVE_ORDER));
		StdOut.println("lastIndexOf dog: " + lastIndexOf(strings, "dog", String.CASE_INSENSITIVE_ORDER));
		
		StdOut.println("firstIndexOf chicken: " + firstIndexOf(strings, "chicken", String.CASE_INSENSITIVE_ORDER));
		StdOut.println("lastIndexOf chicken: " + lastIndexOf(strings, "chicken", String.CASE_INSENSITIVE_ORDER));
	}
}