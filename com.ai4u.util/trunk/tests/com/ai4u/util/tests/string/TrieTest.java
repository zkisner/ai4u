/**
 * Created on 12/02/11
 */
package com.ai4u.util.tests.string;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.ai4u.util.RandomUtils;
import com.ai4u.util.junit.ExtendedRunner;
import com.ai4u.util.junit.Repeat;
import static org.junit.Assert.*;
import com.ai4u.util.string.Trie;

/**
 * @author kreich
 */
@RunWith(ExtendedRunner.class)
public class TrieTest {

	private RandomUtils rand = new RandomUtils();
	
	@Test
	@Repeat(100)
	public void testTrie() {
		Trie trie = new Trie();
		
		int size = rand.nextInt(1000);
		String[] strs = new String[size];
		for (int i = 0; i < size; i++) {
			strs[i] = rand.nextString(rand.nextInt(50), RandomUtils.A_Za_z0_9);
			trie.add(strs[i]);
		}
		
		rand.shuffle(strs);
		
		for (int i = 0; i < size; i++) {
			assertTrue(trie.exists(strs[i]));
		}
	}
	
	@Test
	@Repeat(100)
	public void testSuffixTree() {
		String text = rand.nextString(rand.nextInt(10000), RandomUtils.A_Za_z0_9);
		
		Trie suffixTree = Trie.generateSuffixTree(text);
		for (int i = 0; i < 500; i++) {
			int beginIndex = rand.nextInt(text.length());
			int endIndex = rand.nextInt(beginIndex, text.length());
			String substr = text.substring(beginIndex, endIndex);
			assertTrue(suffixTree.findPrefix(substr));
		}
	}
	
}
