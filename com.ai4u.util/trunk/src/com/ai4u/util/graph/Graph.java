package com.ai4u.util.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @param <T> The type of values in the graph.
 *
 * @author igalk
 */
public class Graph<T> {

	/** The vertices in the graph. */
	private final HashSet<Vertex<T>> vertices;
	/** The edges in the graph. */
	private final HashMap<Vertex<T>, Edge<T>> edges;

	/**
	 * Constructor.
	 */
	public Graph() {
		this.vertices = new HashSet<Vertex<T>>();
		this.edges = new HashMap<Vertex<T>, Edge<T>>();
	}
	
	/**
	 * @param value The value for the new vertex.
	 * @return The created vertex. 
	 */
	public Vertex<T> add(T value) {
		Vertex<T> v = new Vertex<T>(value);
		vertices.add(v);
		return v;
	}
	
	/**
	 * @param from
	 * @param to
	 */
	public void connect(Vertex<T> from, Vertex<T> to) {
		edges.put(from, new Edge<T>(from, to));
	}
	
}
