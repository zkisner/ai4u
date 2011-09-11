/**
 * Created on 08/11/10
 */
package com.ai4u.util.graph.impl;

import com.ai4u.util.graph.IEdge;
import com.ai4u.util.graph.IVertex;

/**
 * @param <T> the type of the value in the vertex.
 * 
 * @author kreich
 */
public class Vertex<T> implements IVertex<T> {
	
	/** The value of the vertex */
	private final T value;
	private final Graph<T> graph;

	/**
	 * Constructor.
	 * @param graph The graph that contains this vertex.
	 * @param value The value of the vertex.
	 */
	public Vertex(Graph<T> graph, T value) {
		this.graph = graph;
		this.value = value;
	}
	
	/**
	 * @return The value of the vertex.
	 */
	public T getValue() {
		return value;
	}

	@Override
	public Iterable<IEdge<T>> getSuccessors() {
		return graph.getSuccessors(this);
	}
	
}
