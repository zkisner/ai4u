/**
 * Created on 12/02/11
 */
package com.ai4u.util.tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.ai4u.util.RandomUtils;
import com.ai4u.util.SortUtils;
import com.ai4u.util.junit.ExtendedRunner;
import com.ai4u.util.junit.Repeat;

/**
 * @author kreich
 */
@RunWith(ExtendedRunner.class)
public class SortUtilsTest {

	private RandomUtils rand = new RandomUtils();
	
	@Test
	@Repeat(100)
	public void testCountingSort() {
		int k = rand.nextInt(2, 1001);
		int[] arr = new int[rand.nextInt(1, 30000)];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(k);
		}
		
		int[] expected = new int[arr.length];
		System.arraycopy(arr, 0, expected, 0, arr.length);
		Arrays.sort(expected);
		
		SortUtils.countingSort(arr, k);
		
		assertArrayEquals(expected, arr);
	}
	
	@Test
	@Repeat(100)
	public void testFindKthElement() {
		int size = rand.nextInt(1, 1001);
		Integer[] arr = new Integer[size];
		for (int i = 0; i < size; i++) {
			arr[i] = rand.nextInt(10000);
		}
		
		Integer[] arr2 = new Integer[size];
		System.arraycopy(arr, 0, arr2, 0, size);
		
		Arrays.sort(arr);
		
		for (int i = 0; i < 100; i++) {
			int k = rand.nextInt(size);
			Integer elem = SortUtils.findKthElement(arr2, k);
			
			assertEquals(arr[k], elem);
			
			rand.shuffle(arr2);
		}		
	}
	
}
