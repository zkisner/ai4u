/**
 * Created on 12/02/11
 */
package com.ai4u.util;

/**
 * @author kreich
 */
public class MathUtil {

	/**
	 * Returns a log of a number in a given base.
	 * 
	 * @param base The base to calculate the log in.
	 * @param number The number to find the log of.
	 * @return The log of the number in the given base.
	 */
	public static double log(double base, double number) {
		return Math.log(number) / Math.log(base);
	}
	
}
