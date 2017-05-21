package tiendm.chapter14;

import java.util.HashMap;
import java.util.Map;

import tiendm.chapter14.interfaces.Edge;
import tiendm.chapter14.interfaces.Vertex;
import tiendm.chapter9.Entry;
import tiendm.chapter9.HeapPriorityQueue;
import tiendm.chapter9.PriorityQueue;

public class Dijkstra <V,E>{
	public Map<Vertex<V> , Integer> shortestPath(AdjacencyGraph<V, E> graph, Vertex<V> s){
		Map<Vertex<V>, Integer> d = new HashMap<>();
		Map<Vertex<V>, Integer> cloud = new HashMap<>();
		PriorityQueue<Integer, Vertex<V>> pq = new HeapPriorityQueue<>();
		Map<Vertex<V>, Entry<Integer, Vertex<V>>> pqToken = new HashMap<>();
		for(Vertex<V> v : graph.vertices()){
			if(v == s)
				d.put(v, 0);
			else {
				d.put(v, Integer.MAX_VALUE);
			}
			pqToken.put(v, pq.insert(d.get(v), v));
		}
		
		while (!pq.isEmpty()) {
			Entry<Integer, Vertex<V>> entry = pq.removeMin();
			int key = entry.getKey();
			Vertex<V> v = entry.getValue();
			cloud.put(v, key);
			pqToken.remove(v);
			for(Edge<E> e : graph.outgoingEdges(v)){
				InnerEdge e2 = (InnerEdge) e;
				Vertex<V> next = graph.opposite(v, e);
				if(cloud.get(next) == null){
					int w = (int) e2.getElement();
					if(d.get(v) + w < d.get(next)){
						d.put(next, d.get(v) + w);
//						pq.replaceKey(pqToken.get(v), d.get(v));
					}
				}
			}
		}
		return cloud;
	}
}
