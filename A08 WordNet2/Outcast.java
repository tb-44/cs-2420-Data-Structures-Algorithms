package wordNet2;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;
/**
 * Least related outcast
 * Date created: 05/01/15
 * Date last modified 05/01/15
 */

public class Outcast {
	private final WordNet wordNet;
	
	/**
	 * Constructor takes a WordNet object
	 * @param wordnet the WordNet
	 */

	public Outcast(WordNet wordnet) {
		this.wordNet = wordnet;
	}
	
	/**
	 * Given an array of WordNet nouns, return an outcast
	 * @param nouns the array of WordNet nouns to use
	 * @return String the outcast
	 */
     
	public String outcast(String[] nouns) {
		int maxDistance = 0;
		String outcast = null;
		for (String nounA : nouns) {
            int distance = 0;
            for (String nounB : nouns)
            	distance += wordNet.distance(nounA, nounB);
            if (distance > maxDistance) {
                maxDistance = distance;
                outcast = nounA; }
        }
        return outcast;
	}
	
	// unit testing
	public static void main(String[] args) {
		WordNet wordnet = new WordNet("src/wordNet2/synsets.txt", "src/wordNet2/hypernyms.txt");
	    Outcast outcast = new Outcast(wordnet);
	    String[] outcasts = {"src/wordNet2/outcast17.txt", "src/wordNet2/outcast8.txt", "src/wordNet2/outcast11.txt"};
	    for (int t = 0; t < outcasts.length; t++) {
	        In in = new In(outcasts[t]);
	        String[] nouns = in.readAllStrings();
	        StdOut.println(outcasts[t] + ": " + outcast.outcast(nouns));
	    }
	}
}