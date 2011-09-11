package com.ai4u.util.graph;

/**
 * @param <T> The type of elements in the vertices.
 *
 * @author kreich
 */
public interface IEdge<T> {

	/**
	 * @return The The origin of the edge.
	 */
	public IVertex<T> getFrom();

	/**
	 * @return The destination of the edge.
	 */
	public IVertex<T> getTo();

}