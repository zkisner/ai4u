/**
 * Created on 08/11/10
 */
package com.ai4u.util.graph.impl;

import com.ai4u.util.graph.IEdge;

/**
 * @param <T> The type of values in the graph
 * 
 * @author kreich
 */
public class Edge<T> implements IEdge<T> {

	/** The origin of the edge. */
	private final Vertex<T> from;
	/** The destination of the edge. */
	private final Vertex<T> to;

	/**
	 * Constructor.
	 * @param from The origin of the edge.
	 * @param to The destination of the edge.
	 */
	public Edge(Vertex<T> from, Vertex<T> to) {
		this.from = from;
		this.to = to;
	}
	
	/* (non-Javadoc)
	 * @see com.ai4u.util.graph.IEdge#getFrom()
	 */
	@Override
	public Vertex<T> getFrom() {
		return from;
	}
	
	/* (non-Javadoc)
	 * @see com.ai4u.util.graph.IEdge#getTo()
	 */
	@Override
	public Vertex<T> getTo() {
		return to;
	}
	
}
