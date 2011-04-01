package com.ai4u.util.tests.disjointSet;

import org.junit.Test;
import static org.junit.Assert.*;

import com.ai4u.util.disjointSet.ArrayDisjointSet;
import com.ai4u.util.disjointSet.DisjointSet;

public class ArrayDisjointSetTest {

	@Test
	public void testArrayDisjointSet() {
		for (int i = 1; i < 100; i++) {			
			DisjointSet diset = new ArrayDisjointSet(i);
			for (int j = 0; j < i; j++) {
				assertEquals(j, diset.find(j));
			}
			for (int j = 1; j < i; j++) {
				assertEquals("set of size " + i, 1, diset.join(j-1, j));
			}
		}
	}
	
}
