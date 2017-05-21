package tiendm.chapter14;

import tiendm.chapter14.interfaces.Vertex;

public class TransitiveClosure <V,E>{
	public void transitiveClosure(AdjacencyGraph<V, E> g){
		for(Vertex<V> k : g.vertices()){
			for(Vertex<V> i : g.vertices()){
				if(i != k && g.getEdge(i, k)!=null){
					for(Vertex<V> j : g.vertices()){
						if(i!=j && j != k && g.getEdge(j, k) != null && g.getEdge(i, j) == null){
							g.insertEdge(i, j, null);
						}
					}
				}
			}
		}
	}
}
