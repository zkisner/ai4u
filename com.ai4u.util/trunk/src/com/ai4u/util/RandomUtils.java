/**
 * Created on 13/06/09
 */
package com.ai4u.util;

import java.util.*;

/**
 * This is an extension for the {@link Random} file.
 * 
 * @author kreich
 */
public class RandomUtils extends Random {

	private static final long serialVersionUID = 1L;
	public static final char[] a_z = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
		'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
		'w', 'x', 'y', 'z' };
	
	public static final char[] A_Z = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
		'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
		'W', 'X', 'Y', 'Z' };
	
	public static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6',
		'7', '8', '9' };
	
	public static final char[] A_Za_z0_9 = ArrayUtils.concat(A_Z, a_z, DIGITS);
	
	
	/**
	 * Generates a random int between <code>from</code> (inclusive) and
	 * <code>to</code> (exclusive).
	 * 
	 * @param from The lowest bound.
	 * @param to The highest bound.
	 * @return A random integer between the bounds.
	 */
	public int nextInt(int from, int to) {
		if (to < from) {
			throw new IllegalArgumentException("to must not be lower than from");
		}
		return from + nextInt(to - from + 1);
	}

	/**
	 * Picks a random element from the given {@link Collection}.
	 * 
	 * @param <T> The type of the elements in the {@link Collection}.
	 * @param collection The {@link Collection} to pick a random element from.
	 * @return A random element from the {@link Collection}.
	 */
	public <T> T pickRandom(Collection<T> collection) {
		if (collection.isEmpty()) return null;
		
		Iterator<T> iter = collection.iterator();
		int randIndex = nextInt(collection.size());
		
		for (int i = 0; i < randIndex - 1; i++) iter.next();
		return iter.next();
	}
	
	/**
	 * Picks a random element from the given array.
	 * 
	 * @param <T> The type of the elements in the array.
	 * @param array The array to pick a random element from.
	 * @return A random element from the array.
	 */
	public <T> T pickRandom(T[] array) {
		if (array.length == 0) return null;
		
		return array[nextInt(array.length)];
	}
	
	/**
	 * Picks a random char from the given array.
	 * 
	 * @param array The array to pick a random char from.
	 * @return A random char from the array.
	 */
	public char pickRandom(char[] array) {
		if (array.length == 0) throw new IllegalArgumentException(
				"Can't pick from an empty array");
		
		return array[nextInt(array.length)];
	}
	
	/**
	 * Picks a random value from the Enum's values.
	 * 
	 * @param <E> The type of the Enum.
	 * @param e The enum's class.
	 * @return A random instance of the Enum.
	 */
	@SuppressWarnings("unchecked")
	public <E extends Enum<E>> E pickRandom(Class<E> e) {
		try {
			E[] values = (E[]) e.getMethod("values").invoke(null);
			return values[nextInt(values.length)];
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Constructs a random string from the given charset.
	 * @param length The length of the string to construct.
	 * @param charset The characters to build the string from.
	 * @return A random string from the given charset.
	 */
	public String nextString(int length, char[] charset) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			builder.append(pickRandom(charset));
		}
		return builder.toString();
	}

	/**
	 * Shuffles the elements in the given array.
	 * @param array The array to shuffle.
	 */
	public <T> void shuffle(T[] array) {
		for (int i = array.length - 1; i > 0; --i) {
			int elem = nextInt(i + 1);
			T temp = array[elem];
			array[elem] = array[i];
			array[i] = temp;
		}
	}
}
