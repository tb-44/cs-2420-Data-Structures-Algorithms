package queueAndDeque;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.introcs.StdOut;

/**
 * @author Trent Bennett
 * CS-2420 Assignment 2
 */

public class Deque<Item> implements Iterable<Item> {
    private int N;          
    private Node first;  
    private Node last;   
    
    /**
     * Private class for linked list of Deque
     */
 
    private class Node {
        private Item item;       
        Node next;  
        Node prev;  
    }
/**
 * Construct an empty queue
 */
    public Deque() {
        N = 0;
        first = null;
        last = null;
    }
    
  /**
   * Is the deque empty?
   * @return
   */
    public boolean isEmpty() {
    	return N == 0;}
  
    /**
     * return the number of items on the deque
     * @return
     */
    public int size()  {
    	return N;
    }
    
    /**
     * Insert the item at the front
     * @param item
     */
    
    public void addFirst(Item item){
        if (item == null) {
            throw new NullPointerException();
        }
        
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.prev = null;
        
        if (isEmpty()){
            last = first;
        }
        else{
            oldFirst.prev = first;
        }
        N++;
    }
    
    /**
     * Insert the item at the last
     * @param item
     */

    public void addLast(Item item){
        if (item == null){
        	throw new NullPointerException();
        }
        
        Node oldLast = last;  
        last = new Node();
        last.item = item;
        last.prev = oldLast; 
        
        if (isEmpty()){
            first = last;
        }
        else {
            oldLast.next = last;
        }
        
        N++;
    }
    
    /**
     * Delete and return the item at the front
     * @return
     */

    public Item removeFirst() {       
    	if (isEmpty()){
    		throw new NoSuchElementException();
    	}
        
        Node next = first.next;
        Item item = first.item;
        
        if(next != null){
            next.prev = null;
        }
        
        if(first == last){
            last = next;
        }
        
        first = next;        
        N--;
        return item;
    }
    
    /**
     * Delete and return the item at the end
     * @return
     */
 
    public Item removeLast(){
        if (isEmpty()){throw new NoSuchElementException();}
        
        Item item = last.item;
        
        Node prev = last.prev;
        if(prev != null){
            prev.next = null;
        }
        if(first == last){
            first = prev;
        }        
        
        last = prev;
        N--;
        return item;        
    }
    
    /**
     * Return an iterator over items in order
     * from front to end
     */

    public Iterator<Item> iterator(){
    	return new DequeIterator(first);
    }
    
    /**
     * Private class that constructs the function
     * for the iterator
     */

    private class DequeIterator implements Iterator<Item>{
        private Node current;  
 
        public DequeIterator(Node first){
        	current = first;
        }

        public boolean hasNext() {
        	return current != null;
        }
        
        //  throw exception when remove method is called 
        public void remove() {
        	throw new UnsupportedOperationException();
        }
        
        //  return current item and move to next node
        public Item next(){
            if(!hasNext())
            	throw new NoSuchElementException();           
            Item item = current.item;
            current = current.next;
            return item;
        }   
    }
    
    // main method for testing
    public static void main (String[] args){
        Deque<Integer> dq = new Deque<>();
        dq.addFirst(3);
        dq.addFirst(2);        
        dq.addFirst(1);
        dq.addLast(4);        
        dq.addFirst(0);
        dq.addLast(5);
        
        StdOut.println("Initial size: " + dq.size());
        StdOut.println("Removing the first value: " + dq.removeFirst() + ", New size: " + dq.size());       
        StdOut.println("Removing the last value: " + dq.removeLast() + ", New size: " + dq.size());
        
        StdOut.println("Elements left: ");
        for (Integer s : dq){
            StdOut.println(s);
        }
    }
}