package queueAndDeque;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;

/**
 * @author Trent Bennett
 * @param <Item>
 */

public class RandomizedQueue<Item> implements Iterable<Item> {
	private int N;  
    private Item[] randQ;
    
    /**
     * Construct an empty Randomized Queue
     */
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
        N = 0;
        randQ = (Item[])new Object[2];
    }
    
    /**
     * Is the Deque empty?
     * @return
     */
    public boolean isEmpty() {
    	return N == 0;}
    
    /**
     * Return the number of items on the queue
     * @return
     */
    public int size() {
    	return N; }
    
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        Item[] copy = (Item[])new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = randQ[i];
        } 
        randQ = copy;
    }
    
    /**
     * Add the item
     * @param item
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("null items not allowed.");
        }
        randQ[N++] = item;
        if(N == randQ.length) {
            resize(2 * randQ.length);
        }
    }
    
   /**
    * Delete and return the item
    * @return
    */
    
    public Item dequeue() {
    	if(isEmpty()){throw new NoSuchElementException();}
        
        int index = StdRandom.uniform(N);
        Item item = randQ[index];
        randQ[index] = randQ[--N];
        randQ[N] = null;
        if(N > 0 && N == randQ.length/4) {
            resize(randQ.length/2);
        }
        return item;
    }
    
    /**
     * Return (but do not delete) a random item
     * @return
     */
    
    public Item sample() {
        if(isEmpty()){throw new NoSuchElementException();}
        int index = StdRandom.uniform(N);
        return randQ[index];
    }
    
    /**
     * Return an independent iterator over items
     * in random order
     */
    
    public Iterator<Item> iterator() {
    	return new RandomizedQueueIterator();  }
    
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int current_index;
        private Item[] randQIt;
        @SuppressWarnings("unchecked")
        public RandomizedQueueIterator() {
        	current_index = 0;
            randQIt = (Item[])new Object[N];
            for(int i = 0; i < N; i++) {
                randQIt[i] = randQ[i];
            }
            StdRandom.shuffle(randQIt);    
        }
        public void remove(){
        	throw new UnsupportedOperationException();
        }

        public boolean hasNext() {
        	return current_index != N;
        }
        
        public Item next() {
        	if(!hasNext())
        		throw new NoSuchElementException();  
            Item item = randQIt[current_index++];
            return item;
        }
    }
    
    /**
     * main method to for unit testing
     * @param args
     */
    
    public static void main(String[] args) {
        RandomizedQueue<Integer> myRandomizedQueue = new RandomizedQueue<>();
        myRandomizedQueue.enqueue(1);
        myRandomizedQueue.enqueue(2);
        myRandomizedQueue.enqueue(3);
        myRandomizedQueue.enqueue(4);
        myRandomizedQueue.enqueue(5);
        myRandomizedQueue.enqueue(6);
        
        StdOut.print("Original Randomized Queue :");
        for(Integer i : myRandomizedQueue){
            StdOut.print(i+ ", ");
        }
        StdOut.println();
        
        StdOut.println("sample : " + myRandomizedQueue.sample());
        StdOut.println("dequeue: " + myRandomizedQueue.dequeue() + ", remaining size: " + myRandomizedQueue.size());
        StdOut.println("dequeue: " + myRandomizedQueue.dequeue()+ ", remaining size: " + myRandomizedQueue.size());    
        StdOut.println("dequeue: " + myRandomizedQueue.dequeue() + ", remaining size: " + myRandomizedQueue.size());
        
        StdOut.println("Elements left: ");
        for(Integer i : myRandomizedQueue){
            StdOut.println(i);
        }
    }
}