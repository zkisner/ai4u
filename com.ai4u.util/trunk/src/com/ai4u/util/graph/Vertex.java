package com.ai4u.util.graph;

/**
 * 
 * @param <T> the type of the value in the vertex.
 * 
 * @author kreich
 */
public class Vertex<T> {
	
	/** The value of the vertex */
	private final T value;

	/**
	 * Constructor.
	 * @param value The value of the vertex.
	 */
	public Vertex(T value) {
		this.value = value;
	}
	
	/**
	 * @return The value of the vertex.
	 */
	public T getValue() {
		return value;
	}
	
}
