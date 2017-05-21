package tiendm.chapter14;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import tiendm.chapter14.interfaces.Edge;
import tiendm.chapter14.interfaces.Vertex;
import tiendm.chapter7.LinkedPositionalList;
import tiendm.chapter7.PositionalList;

public class DFSTraversal<V,E> {
	public void DFS(AdjacencyGraph<V, E> grap, Vertex<V> u, Set<Vertex<V>> known, Map<Vertex<V>, Edge<E>> forest){
		known.add(u);
		for(Edge<E> e : grap.outgoingEdges(u)){
			Vertex<V> next = grap.opposite(u, e);
			if(!known.contains(next)){
				known.add(next);
				forest.put(next, e);
				DFS(grap, next, known, forest);
			}
		}
	}
	
	public PositionalList<Edge<E>> constructPath(AdjacencyGraph<V, E> graph, Vertex<V> u,
													Vertex<V> v, Map<Vertex<V>, Edge<E>> forest){
		PositionalList<Edge<E>> path = new LinkedPositionalList<>();
		if(forest.get(v) != null){
			Vertex<V> walk = v;
			while (walk != u) {
				Edge<E> edge = forest.get(walk);
				path.addFirst(edge);
				walk = graph.opposite(walk, edge);
			}
		}
		return path;
	}
	
	public Map<Vertex<V>, Edge<E>> DFSComplete(AdjacencyGraph<V, E> graph){
		Set<Vertex<V>> known = new HashSet<>();
		Map<Vertex<V>, Edge<E>> forest = new HashMap<>();
		for(Vertex<V> u : graph.vertices()){
			if(!known.contains(u)){
				DFS(graph, u, known, forest);
			}
		}
		return forest;
	} 
}
