/**
 * Created on 12/02/11
 */
package com.ai4u.util.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kreich
 */
public class Trie {

	/**
	 * Generates a suffix tree for the given text.
	 * 
	 * @param text The text to analyze.
	 * @return The Trie that represents the suffix tree.
	 */
	public static Trie generateSuffixTree(String text) {
		Trie trie = new Trie();
		for (int i = 0; i < text.length(); i++) {
			trie.add(text.substring(i));
		}
		return trie;
	}
	
	private TrieVertex root = new TrieVertex();
	
	/**
	 * @param str The string to add to the try.
	 */
	public void add(String str) {
		add(str, root);
	}
	
	/**
	 * @param str The string to search for.
	 * @return Whether this exact string is found in the tree.
	 */
	public boolean exists(String str) {
		return exists(str, root);
	}
	
	/**
	 * @param str The string to search for.
	 * @return Whether this string is found in the tree as a prefix.
	 */
	public boolean findPrefix(String str) {
		return findPrefix(str, root);
	}
	
	private void add(String str, TrieVertex vertex) {
		// check whether the string ended
		if (str.isEmpty()) {
			vertex.end  = true;
			return;
		}
		// now check whether there is a string starting with the same prefix
		char first = str.charAt(0);
		// handle a new suffix
		if (vertex.getChild(first) == null) {
			vertex.setChild(first, new TrieVertex(str, true));
			return;
		}
		// handle an old suffix
		TrieVertex child = vertex.getChild(first);
		int i = 0;
		while (i < str.length() && i < child.value.length() &&
				str.charAt(i) == child.value.charAt(i)) {
			++i;
		}
		if (i == child.value.length()) {
			// the new string includes the old one, so just add the substring
			add(str.substring(i), child);
		} else if (i == str.length()) {
			// the old string includes the new one, so add a new vertex,
			// remove the substring from the old vertex, and add it as a child
			// of the new one
			vertex.setChild(first, new TrieVertex(str, true));
			child.value = child.value.substring(i);
			vertex.getChild(first).setChild(child.value.charAt(0), child);
		} else {
			// the to strings have the same prefix but different suffixes
			// add the mutual prefix as a vertex, and add the suffixes as
			// children
			vertex.setChild(first, new TrieVertex(str.substring(0, i), false));
			
			child.value = child.value.substring(i);
			vertex.getChild(first).setChild(child.value.charAt(0), child);
			
			add(str.substring(i), vertex.getChild(first));
		}
	}

	private boolean exists(String str, TrieVertex vertex) {
		if (str.isEmpty()) {
			return vertex.end;
		}
		TrieVertex child = vertex.getChild(str.charAt(0));
		if (child == null) {
			return false;
		}
		if (str.startsWith(child.value)) {
			return exists(str.substring(child.value.length()), child);
		}
		return false;
	}
	
	private boolean findPrefix(String str, TrieVertex vertex) {
		if (str.isEmpty()) {
			return true;
		}
		TrieVertex child = vertex.getChild(str.charAt(0));
		if (child == null) {
			return false;
		}
		if (str.startsWith(child.value)) {
			return findPrefix(str.substring(child.value.length()), child);
		} else if (child.value.startsWith(str)) {
			return true;
		}
		return false;
	}

	private class TrieVertex {

		public String value;
		public boolean end;
		private Map<Character, TrieVertex> children =
			new HashMap<Character, TrieVertex>();
		
		public TrieVertex() {
			this("");
		}
		
		public TrieVertex(String str) {
			this(str, false);
		}

		public TrieVertex(String str, boolean isEnd) {
			this.value = str;
			this.end = isEnd;
		}
		
		public TrieVertex getChild(char c) {
			return children.get(c);
		}
		
		public void setChild(char c, TrieVertex vertex) {
			children.put(c, vertex);
		}
		
		@Override
		public String toString() {
			return value + (end ? "$" : "");
		}
		
	}
	
}
