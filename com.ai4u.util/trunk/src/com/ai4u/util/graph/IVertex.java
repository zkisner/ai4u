/**
 * Created on 15/04/11
 */
package com.ai4u.util.graph;

/**
 * @param <T> the type of the value in the vertex.
 * 
 * @author kreich
 */
public interface IVertex<T> {
	
	/**
	 * @return The value of the vertex.
	 */
	public T getValue();
	
	/**
	 * @return The {@link IEdge}s that lead from the current {@link IVertex}.
	 */
	public Iterable<IEdge<T>> getSuccessors();
	
}
