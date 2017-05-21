package tiendm.chapter14;

import java.util.HashMap;
import java.util.Map;

import tiendm.chapter14.interfaces.Edge;
import tiendm.chapter14.interfaces.Vertex;
import tiendm.chapter7.Position;

public class InnerVertex<V,E> implements Vertex<V> {
	private V element;
	private Position<Vertex<V>> pos;
	private Map<Vertex<V>, Edge<E>> outging, incoming;
	
	public InnerVertex(V element, boolean bDirect) {
		this.element = element;
		outging = new HashMap<>();
		if(bDirect){
			incoming = new HashMap<>();
		}else {
			incoming = outging;
		}
	}
	public V getElement() {
		return element;
	}
	public void setElement(V element) {
		this.element = element;
	}
	public Position<Vertex<V>> getPos() {
		return pos;
	}
	public void setPos(Position<Vertex<V>> pos) {
		this.pos = pos;
	}
	public Map<Vertex<V>, Edge<E>> getOutging() {
		return outging;
	}
	public void setOutging(Map<Vertex<V>, Edge<E>> outging) {
		this.outging = outging;
	}
	public Map<Vertex<V>, Edge<E>> getIncoming() {
		return incoming;
	}
	public void setIncoming(Map<Vertex<V>, Edge<E>> incoming) {
		this.incoming = incoming;
	}
	
}
