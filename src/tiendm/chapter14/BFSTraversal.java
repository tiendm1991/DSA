package tiendm.chapter14;

import java.util.Map;
import java.util.Set;

import tiendm.chapter14.interfaces.Edge;
import tiendm.chapter14.interfaces.Vertex;
import tiendm.chapter7.LinkedPositionalList;
import tiendm.chapter7.PositionalList;

public class BFSTraversal <V,E>{
	public void BFS(AdjacencyGraph<V, E> graph, Vertex<V> s, Set<Vertex<V>> known, Map<Vertex<V>, Edge<E>> forest){
		PositionalList<Vertex<V>> level = new LinkedPositionalList<>();
		known.add(s);
		level.addLast(s);
		while (!level.isEmpty()) {
			PositionalList<Vertex<V>> nexLevel = new LinkedPositionalList<>();
			for(int i =0; i < level.size(); i++){
				Vertex<V> u = level.remove(level.first());
				for(Edge<E> e : graph.outgoingEdges(u)){
					Vertex<V> v = graph.opposite(u, e);
					if(!known.contains(v)){
						nexLevel.addLast(v);
						known.add(v);
						forest.put(v, e);
					}
				}
			}
			level = nexLevel;
		}
	}
}
