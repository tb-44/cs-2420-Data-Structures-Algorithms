package wordNet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

/**
 * WordNet set of hypernym synsets.
 * Date created: 04/13/15
 * Date last modified: 05/02/15
 */

public class WordNet {
	private final Digraph nyms;
	private final RedBlackBST<String, Stack<Integer>> stringKeys;
	private final RedBlackBST<Integer, String> intKeys;
	private final SAP sap;
	
	/**
	 * Constructor takes the name of the two input files
	 * @param synsets
	 * @param hypernyms
	 */

	public WordNet(String synsets, String hypernyms) {
		if (synsets == null)   throw new NullPointerException("synsets cannot be null");
		if (hypernyms == null) throw new NullPointerException("hypernyms cannot be null");
		stringKeys = new RedBlackBST<>();
		intKeys = new RedBlackBST<>();
		In in = new In(synsets);
		while (in.hasNextLine()) {
			String line = in.readLine();
			String[] tokens = line.split(",");
			int id = Integer.parseInt(tokens[0]);
			String synset = tokens[1];
			String[] syns = synset.split(" ");
			for (String noun : syns) {
				if (!stringKeys.contains(noun)) stringKeys.put(noun, new Stack<>());
				stringKeys.get(noun).push(id);
			}
			intKeys.put(id, synset);
		}
		nyms = new Digraph(intKeys.size());
		in = new In(hypernyms);
		while (in.hasNextLine()) {
			String line = in.readLine();
			String[] tokens = line.split(",");
			int id = Integer.parseInt(tokens[0]);
			for (int i = 1; i < tokens.length; i++) {
				int w = Integer.parseInt(tokens[i]);
				nyms.addEdge(id, w);
			}
		}
		sap = new SAP(nyms);
		
	}
	
	/**
	 * Returns all WordNet nouns
	 * @return all WordNet nounts
	 */

	public Iterable<String> nouns() {
		return stringKeys.keys();
	}
	
	/**
	 * Is the word a WordNet noun?
	 * @param word the word to look for
	 * @return is the word a WordNet noun?
	 */

	public boolean isNoun(String word) {
		if (word == null) throw new NullPointerException("word cannot be null");
		return stringKeys.contains(word);
	}
	
	/**
	 * Distance between nounA and nounB
	 * @param nounA first noun
	 * @param nounB second noun
	 * @return distance between noun A and noun B
	 */

	public int distance(String nounA, String nounB) {
		if (nounA == null)  throw new NullPointerException("nounA cannot be null");
		if (nounB == null)  throw new NullPointerException("nounB cannot be null");
		if (!isNoun(nounA)) throw new IllegalArgumentException(nounA + " must be a WordNet noun");
		if (!isNoun(nounB)) throw new IllegalArgumentException(nounB + " must be a WordNet noun");
		return sap.length(stringKeys.get(nounA), stringKeys.get(nounB));
	}
	
	/**
	 * A synset (second field of synsets.txt) that is the common ancestor 
	 * of nounA and nounB in a shortest ancestral path
	 * @param nounA first noun
	 * @param nounB second noun
	 * @return the synset of noun A and noun B
	 */

	public String sap(String nounA, String nounB) {
		if (nounA == null)  throw new NullPointerException("nounA cannot be null");
		if (nounB == null)  throw new NullPointerException("nounB cannot be null");
		if (!isNoun(nounA)) throw new IllegalArgumentException(nounA + " must be a WordNet noun");
		if (!isNoun(nounB)) throw new IllegalArgumentException(nounB + " must be a WordNet noun");
		return intKeys.get(sap.ancestor(stringKeys.get(nounA), stringKeys.get(nounB)));
	}
	
	// unit testing
	public static void main(String[] args) {
		String hypernyms = "src/wordNet/hypernyms100-subgraph.txt";
		String synsets = "src/wordNet/synsets100-subgraph.txt";
		WordNet wordNet = new WordNet(synsets, hypernyms);
		for (String s : wordNet.nouns())
			StdOut.println(s);
		StdOut.println();
		StdOut.println(wordNet.sap("molecule", "trypsin"));
		StdOut.println(wordNet.distance("molecule", "trypsin"));
	}
}