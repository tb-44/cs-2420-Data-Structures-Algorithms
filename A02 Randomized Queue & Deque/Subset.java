package queueAndDeque;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

/**
 * @author Trent Bennett
 * CS-2420 Assignment 2 (Randomized Queue & Deque)
 */

public class Subset {

		public static void main(String[] args) {
			
			int myArgumentNumber = Integer.parseInt(args[0]);
			RandomizedQueue<String> myRandomizedQueue = new RandomizedQueue<String>();        
			try{
			String input = StdIn.readLine();
	        String[] myRegex = input.split("[\\s]+");

	        for (String x: myRegex){
	        	myRandomizedQueue.enqueue(x);
	        }
	        
	        for (int x = 0; x < myArgumentNumber; x++){
	        	StdOut.println(myRandomizedQueue.dequeue());
	        }
	        }
	        catch (Exception e) {
				System.err.println("Total number of items entered is less than the argument number of : " + myArgumentNumber);
				
			}	
		}
	}