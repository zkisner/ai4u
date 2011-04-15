package com.ai4u.util.graph;

import com.ai4u.util.graph.impl.Vertex;

/**
 * @param <T> The type of elements in the vertices.
 *
 * @author kreich
 */
public interface IEdge<T> {

	/**
	 * @return The The origin of the edge.
	 */
	public Vertex<T> getFrom();

	/**
	 * @return The destination of the edge.
	 */
	public Vertex<T> getTo();

}