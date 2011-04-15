/**
 * Created on 15/04/11
 */
package com.ai4u.util.graph.search;

import java.util.List;

import com.ai4u.util.Acceptor;
import com.ai4u.util.graph.IEdge;
import com.ai4u.util.graph.IVertex;
import com.ai4u.util.graph.impl.Edge;
import com.ai4u.util.graph.impl.Vertex;

/**
 * @param <T> The type of the elements in the vertices of the graph.
 *
 * @author kreich
 */
public interface ISearchAlgorithm<T> {

	/**
	 * Searches for a {@link IVertex} that matches the @{Acceptor}, from the
	 * starting point.
	 * 
	 * @param startPoint The @{IVertex} to start from.
	 * @param foundCondition The condition for the end of the search.
	 * @return The path in {@link IEdge}s from the starting point to the end.
	 */
	public List<Edge<T>> find(Vertex<T> startPoint,
			Acceptor<Vertex<T>> foundCondition);

}
