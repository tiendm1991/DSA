package tiendm.chapter14;

import tiendm.chapter14.interfaces.Edge;
import tiendm.chapter14.interfaces.Vertex;
import tiendm.chapter7.Position;

public class InnerEdge<V,E> implements Edge<E> {
	private E element;
	private Position<Edge<E>> pos;
	private Vertex<V>[] endpoints;
	
	public InnerEdge(E element) {
		this.element = element;
	}
	
	public InnerEdge(Vertex<V>u, Vertex<V>v, E element) {
		this.element = element;
		endpoints = new Vertex[]{u,v};
	}
	
	public E getElement() {
		return element;
	}
	public void setElement(E element) {
		this.element = element;
	}
	public Position<Edge<E>> getPos() {
		return pos;
	}
	public void setPos(Position<Edge<E>> pos) {
		this.pos = pos;
	}
	public Vertex<V>[] getEndpoints() {
		return endpoints;
	}
	public void setEndpoints(Vertex<V>[] endpoints) {
		this.endpoints = endpoints;
	}
	
	
}
