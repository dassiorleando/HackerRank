package com.hacker.kuldeep;

import java.util.HashMap;

/**
 * There is an integer array d which does not contain more 
 * than two elements of the same value. How many distinct 
 * ascending triples (d[i] < d[j] < d[k], i < j < k) are present? 
 * 
 * @algorithm
 * Hold an element in array and take all the elements which are 
 * left to it and smaller. Similarly in right check for elements which 
 * are larger than this element.
 * 
 * @author kuldeep
 *
 */
public class AscendingTripletsFinder {

	/**
	 * The input array.
	 */
	private int[] array;
	
	/**
	 * Hashmap to keep 
	 */
	private HashMap<Integer, HashMap<Integer, Integer>> smallerNumbers;
	
	private HashMap<Integer, HashMap<Integer, Integer>> largerNumbers;
	
	public AscendingTripletsFinder(int[] array) {
		this.array = array;
		this.smallerNumbers = new HashMap<Integer, HashMap<Integer,Integer>>();
		this.largerNumbers = new HashMap<Integer, HashMap<Integer,Integer>>();
	}
	
	/**
	 * total number of triplets.
	 * @return total number of triplets in array.
	 */
	public int triplets() {
		
		int tripletsCount = 0;
		for (int midIndex = 0; midIndex < array.length; midIndex++) {
			HashMap<Integer, Integer> smaller = smallerNumbers.get(array[midIndex]);
			HashMap<Integer, Integer> larger = largerNumbers.get(array[midIndex]);
			if (smaller == null) {
				tripletsCount += firstLookUpForTriplets(midIndex);
			} else {
				tripletsCount += secondLookUpForTriplets(midIndex, smaller, larger);
			}
		}
		
		return tripletsCount;
	}
	
	/**
	 * 
	 * @param midIndex index of middle element 
	 * @return number of triplets for this element
	 */
	private int firstLookUpForTriplets(int midIndex) {
		HashMap<Integer, Integer> smaller = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> larger = new HashMap<Integer, Integer>();
		
		int i;
		for (i = 0; i < midIndex; i++) {
			
			if (array[i] < array[midIndex] && !smaller.containsKey(array[i])) {
				smaller.put(array[i], 1);
			}
		}
		
		i++;
		for ( ; i < array.length; i++) {
			if (array[i] > array[midIndex] && !larger.containsKey(array[i])) {
				larger.put(array[i], 1);
			}
		}
		smallerNumbers.put(array[midIndex], smaller);
		largerNumbers.put(array[midIndex], larger);
		
		return smaller.size() * larger.size();
	}
	
	/**
	 * 
	 * @param midIndex index of middle elements of a triplet.
	 * @param smaller hash containing smaller elements
	 * @param larger hash containing larger elements
	 * @return
	 */
	private int secondLookUpForTriplets (int midIndex, 
			HashMap<Integer, Integer> smaller,
			HashMap<Integer, Integer> larger) {
		
		int newlySeenSmaller = 0;
		int allLargeElements = 0;
		
		int i;
		for (i = 0; i < midIndex; i++) {
			if (array[i] < array[midIndex] && !smaller.containsKey(array[i])) {
				newlySeenSmaller++;
			}
		}
		
		i++;
		for ( ; i < array.length; i++) {
			if (array[i] > array[midIndex]) {
				allLargeElements++;
			}
		}
		return newlySeenSmaller * allLargeElements;
	}
	
}
