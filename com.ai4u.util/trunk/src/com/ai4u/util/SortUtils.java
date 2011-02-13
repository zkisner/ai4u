/**
 * Created 12/02/11
 */
package com.ai4u.util;

/**
 * @author kreich
 */
public class SortUtils {

	/**
	 * Sorts the array using the counting sort algorithm.
	 * All elements in the array must be between zero and k.
	 * 
	 * This sort is good when all the numbers in the array are in a close range.
	 * 
	 * @param arr An array of integers.
	 * @param k The range of numbers, should be ~ O(sizeof(arr))
	 */
	public static void countingSort(int[] arr, int k) {
		if (k < 2) {
			throw new IllegalArgumentException("k must be at least 2");
		}
		int[] count = new int[k];
		// step 1: count the numbers
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < 0 || arr[i] >= k) {
				throw new IllegalArgumentException("The numbers must be between 0 and k");
			}
			count[arr[i]]++;
		}
		// step 2: place the numbers back in the array
		int ptr = 0;
		for (int i = 0; i < k; i++) {
			while (count[i] > 0) {
				arr[ptr++] = i;
				--count[i];
			}
		}
	}
	
	/**
	 * Finds the K'th biggest element in the array. Changes the array!
	 * 
	 * @param <T> The type of elements in the array.
	 * @param array The array to search in.
	 * @param k The k to search.
	 * @return The k'th biggest element in the array.
	 */
	public static <T extends Comparable<? super T>> T findKthElement(T[] array,
			int k) {
		if (k < 0 || k >= array.length) {
			throw new IllegalArgumentException("Illegal k for search");
		}
		return select(array, 0, array.length - 1, k);
	}

	private static RandomUtils rand = new RandomUtils();
	
	private static <T extends Comparable<? super T>> T select(T[] arr,
			int first, int last, int i) {
		if (first > last) {
			throw new IllegalArgumentException("Illegal bounds");
		} else if (first == last) {
			if (i == 0) {
				return arr[first];
			}
			throw new IllegalArgumentException("Illegal i for search");
		}
		// choose a pivot object
		int pivot = rand.nextInt(first, last + 1);
		// put it as the first in the array
		swap(arr, first, pivot);
		
		int a = first + 1;
		int b = last;
		while (a < b) {
			while (a < b && arr[a].compareTo(arr[first]) <= 0) ++a;
			while (a < b && arr[b].compareTo(arr[first]) > 0) --b;
			swap(arr, a, b);
		}
		
		if (arr[a].compareTo(arr[first]) > 0) {
			--a;
		}
		swap(arr, a, first);
		// if the pivot is in the i-th place return it
		if (a - first == i) {
			return arr[a];
		} else if (a - first > i) {
			// there are more than i items on the left, search there
			return select(arr, first, a - 1, i);
		}
		// search the right otherwise
		return select(arr, a + 1, last, i - (a - first + 1));
	}

	private static <T extends Comparable<? super T>> void swap(T[] arr, int i,
			int j) {
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
}
