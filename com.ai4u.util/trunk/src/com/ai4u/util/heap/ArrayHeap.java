/**
 * Created on 11/02/11
 */
package com.ai4u.util.heap;

import com.ai4u.util.MathUtil;

/**
 * @param <T> The type of elements in the heap.
 *  
 * @author kreich
 */
public class ArrayHeap<T extends Comparable<? super T>> implements IHeap<T> {

	private static final int MIN_SIZE = 16;
	
	/** The internal array holding the items in the heap. */
	private Object[] items;
	/** A counter holding the current size of the heap. */
	private int size;
	
	/**
	 * Constructor.
	 * Initializes the heap with the given objects.
	 * 
	 * @param objs The objects to be put initially in the heap.
	 */
	public ArrayHeap(T ... objs) {
		items = new Object[((objs.length * 2) < MIN_SIZE) ?
				MIN_SIZE : (objs.length * 2)];
		System.arraycopy(objs, 0, items, 0, objs.length);
		size = objs.length;
		
		int heightOfInner = (int) Math.floor(MathUtil.log(2, size));
		int innerIndex = ((int)Math.pow(2, heightOfInner)) - 1;
		for (int i = innerIndex - 1; i >= 0; --i) {
			siftDown(i);
		}
	}
	
	@Override
	public void insert(T obj) {
		if (items.length == size) {
			resize(items.length * 2);
		}
		
		items[size] = obj;
		int parent = getParentIndex(size);
		++size;
		
		while (parent != 0) {
			siftDown(parent);
			parent = getParentIndex(parent);
		}
		siftDown(0);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T max() {
		return isEmpty() ? null : (T) items[0];
	}

	@Override
	public T deleteMax() {
		T max = max();
		if (max == null) {
			return null;
		}
		
		// put the last item on top and sift down
		--size;
		items[0] = items[size];
		items[size] = null;
		siftDown(0);
		
		// check whether the array should be cut
		if (size*4 < items.length) {
			resize(items.length / 2);
		}
		
		return max;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	private void resize(int size) {
		int newSize = Math.max(size, MIN_SIZE);
		Object[] newItems = new Object[newSize];
		System.arraycopy(items, 0, newItems, 0, Math.min(items.length, newSize));
		
		items = newItems;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void siftDown(int index) {
		int leftChildIndex = 2*index + 1;
		int rightChildIndex = 2*index + 2;
		
		Object self = items[index];
		Object leftChild = (leftChildIndex >= items.length) ?
				null : items[leftChildIndex];
		Object rightChild = (rightChildIndex >= items.length) ?
				null : items[rightChildIndex];
		if (rightChild == null) {
			if (leftChild == null) {
				// This is a leaf
				return;
			}
			if (((Comparable)leftChild).compareTo(self) > 0) {
				swap(leftChildIndex, index);
				siftDown(leftChildIndex);
			}
		} else {
			// both children are present
			if (((Comparable)leftChild).compareTo(self) > 0 ||
					((Comparable)rightChild).compareTo(self) > 0) {
				if (((Comparable)leftChild).compareTo(rightChild) > 0) {
					swap(leftChildIndex, index);
					siftDown(leftChildIndex);
				} else {
					swap(rightChildIndex, index);
					siftDown(rightChildIndex);
				}
			}
		}
	}

	private void swap(int i, int j) {
		Object temp = items[i];
		items[i] = items[j];
		items[j] = temp;
	}

	private int getParentIndex(int index) {
		return (index - 1) / 2;
	}

}
