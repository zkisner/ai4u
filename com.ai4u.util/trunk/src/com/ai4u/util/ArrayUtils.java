/**
 * Created on 12/02/11
 */
package com.ai4u.util;

/**
 * @author kreich
 */
public class ArrayUtils {

	public static char[] concat(char[] ... arrays) {
		if (arrays.length < 2) {
			throw new IllegalArgumentException("Must get at least 2 arrays");
		}
		int sum = 0;
		for (int i = 0; i < arrays.length; i++) {
			sum += arrays[i].length;
		}
		
		char[] result = new char[sum];
		int offset = 0;
		for (int i = 0; i < arrays.length; i++) {
			System.arraycopy(arrays[i], 0, result, offset, arrays[i].length);
			offset += arrays[i].length;
		}
		return result;
	}

}
