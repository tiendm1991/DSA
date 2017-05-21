package tiendm.chapter14;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import tiendm.chapter14.interfaces.Edge;
import tiendm.chapter14.interfaces.Vertex;
import tiendm.chapter7.LinkedPositionalList;
import tiendm.chapter7.PositionalList;

public class TopologialSort<V,E> {
	public PositionalList<Vertex<V>> topologicalSort(AdjacencyGraph<V, E> g){
		PositionalList<Vertex<V>> topo = new LinkedPositionalList<>();
		Stack<Vertex<V>> ready = new Stack<>();
		Map<Vertex<V>, Integer> inCount = new HashMap<>();
		
		for(Vertex<V> u : g.vertices()){
			inCount.put(u, g.inDegree(u));
			if(g.inDegree(u) == 0){
				ready.push(u);
			}
		}
		while (ready.size() != 0) {
			Vertex<V> u = ready.pop();
			topo.addLast(u);
			for(Edge<E> e : g.outgoingEdges(u)){
				Vertex<V> v = g.opposite(u, e);
				inCount.put(v, inCount.get(v)-1);
				if(inCount.get(v) == 0){
					ready.push(v);
				}
			}
		}
		return topo;
 	}
}
