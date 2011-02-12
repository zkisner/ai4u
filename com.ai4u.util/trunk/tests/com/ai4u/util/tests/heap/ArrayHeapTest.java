/**
 * Created on 11/02/11
 */
package com.ai4u.util.tests.heap;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.ai4u.util.RandomUtils;
import com.ai4u.util.heap.ArrayHeap;
import com.ai4u.util.heap.Heap;
import com.ai4u.util.junit.ExtendedRunner;
import com.ai4u.util.junit.Repeat;


/**
 * @author kreich
 */
@RunWith(ExtendedRunner.class)
public class ArrayHeapTest {

	private RandomUtils rand = new RandomUtils();
	
	@Test
	@Repeat(500)
	public void testHeapCreate() {
		int size = rand.nextInt(100);
		TestClass[] arr = new TestClass[size];
		for (int i = 0; i < size; i++) {
			arr[i] = new TestClass(rand.nextInt(10000));
		}
		Heap<TestClass> heap = new ArrayHeap<ArrayHeapTest.TestClass>(arr);
		
		Arrays.sort(arr);
		for (int i = arr.length - 1; i >= 0; --i) {
			assertEquals(arr[i], heap.deleteMax());
		}
	}
	
	@Test
	@Repeat(500)
	public void testHeapInsert() {
		int size1 = rand.nextInt(100);
		TestClass[] arr = new TestClass[size1];
		for (int i = 0; i < size1; i++) {
			arr[i] = new TestClass(rand.nextInt(10000));
		}
		Heap<TestClass> heap = new ArrayHeap<ArrayHeapTest.TestClass>(arr);
		
		int size2 = rand.nextInt(100);
		TestClass[] arr2 = new TestClass[size1 + size2];
		System.arraycopy(arr, 0, arr2, 0, size1);
		for (int i = size1; i < size1 + size2; i++) {
			arr2[i] = new TestClass(rand.nextInt(10000));
			heap.insert(arr2[i]);
		}
		
		Arrays.sort(arr2);
		for (int i = arr2.length - 1; i >= 0; --i) {
			assertEquals(arr2[i], heap.deleteMax());
		}
	}
	
	@Test
	@Repeat(500)
	public void testHeapDelete() {
		int size = rand.nextInt(100) + 1;
		TestClass[] arr = new TestClass[size];
		for (int i = 0; i < size; i++) {
			arr[i] = new TestClass(rand.nextInt(10000));
		}
		Heap<TestClass> heap = new ArrayHeap<ArrayHeapTest.TestClass>(arr);
		
		for (int i = 0; i < 500; i++) {			
			Arrays.sort(arr);
			assertEquals(arr[size - 1], heap.deleteMax());
			
			arr[size - 1] = new TestClass(rand.nextInt(10000));
			heap.insert(arr[size - 1]);
		}
	}
	
	public class TestClass implements Comparable<TestClass> {

		private int priority;
		
		public TestClass(int priority) {
			this.priority = priority;
		}
		
		@Override
		public int compareTo(TestClass o) {
			return priority - o.priority;
		}
		
		@Override
		public boolean equals(Object obj) {
			return priority == ((TestClass)obj).priority;
		}
		
		@Override
		public String toString() {
			return Integer.toString(priority);
		}
		
	}
	
}
