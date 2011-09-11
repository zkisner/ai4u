/**
 * Created on 08/11/10
 */
package com.ai4u.util.graph.impl;

import java.util.*;

import com.ai4u.util.graph.IEdge;

/**
 * @param <T> The type of values in the graph.
 *
 * @author kreich
 */
public class Graph<T> {

	/** The vertices in the graph. */
	private final Set<Vertex<T>> vertices;
	/** A mapping from a source vertex to all its outgoing edges. */
	private final Map<Vertex<T>, Set<Edge<T>>> edges;

	/**
	 * Constructor.
	 */
	public Graph() {
		this.vertices = new HashSet<Vertex<T>>();
		this.edges = new HashMap<Vertex<T>, Set<Edge<T>>>();
	}
	
	/**
	 * @param value The value for the new vertex.
	 * @return The created vertex. 
	 */
	public synchronized Vertex<T> add(T value) {
		Vertex<T> v = new Vertex<T>(this, value);
		vertices.add(v);
		edges.put(v, new HashSet<Edge<T>>());
		return v;
	}
	
	/**
	 * @param from The source vertex to connect
	 * @param to The destination vertex to connect.
	 */
	public synchronized void connect(Vertex<T> from, Vertex<T> to) {
		edges.get(from).add(new Edge<T>(from, to));
	}
	
	synchronized Iterable<IEdge<T>> getSuccessors(Vertex<T> vertex) {
		if (!vertices.contains(vertex)) {
			throw new IllegalArgumentException("vertex " + vertex + " is not in the graph");
		}
		return new HashSet<IEdge<T>>(edges.get(vertex));
	}
	
}
