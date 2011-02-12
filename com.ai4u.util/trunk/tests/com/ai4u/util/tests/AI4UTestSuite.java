/**
 * Created on 12/02/11
 */
package com.ai4u.util.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ai4u.util.tests.heap.ArrayHeapTest;
import com.ai4u.util.tests.string.TrieTest;

/**
 * @author kreich
 */
@RunWith(Suite.class)
@SuiteClasses({
	ArrayHeapTest.class,
	TrieTest.class,
	//SortUtilsTest.class
})
public class AI4UTestSuite {

}
