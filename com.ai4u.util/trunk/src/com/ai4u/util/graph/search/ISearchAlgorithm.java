/**
 * Created on 15/04/11
 */
package com.ai4u.util.graph.search;

import com.ai4u.util.graph.IEdge;
import com.ai4u.util.graph.IVertex;
import com.ai4u.util.tree.INode;

/**
 * @param <T> The type of the elements in the vertices of the graph.
 *
 * @author kreich
 */
public interface ISearchAlgorithm<T> {

	/**
	 * Searches the graph till all the graph is scanned.
	 * 
	 * @param startPoint The @{IVertex} to start from.
	 * @return The path in {@link IEdge}s from the starting point to the end.
	 */
	public INode<T> find(IVertex<T> startPoint);

}
