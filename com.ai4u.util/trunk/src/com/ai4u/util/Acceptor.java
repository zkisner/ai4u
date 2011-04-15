/**
 * Created on 15/4/11
 */
package com.ai4u.util;

/**
 * An interface for accepting classes, used as filters or validators.
 * @param <T> The type of elements to test.
 * 
 * @author kreich
 */
public interface Acceptor<T> {

	/**
	 * @param obj The object to test.
	 * @return Whether the given object matches the accepting conditions.
	 */
	public boolean accept(T obj);
	
}
