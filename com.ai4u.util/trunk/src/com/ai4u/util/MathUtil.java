package com.ai4u.util;

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
