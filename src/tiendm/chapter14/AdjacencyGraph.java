package tiendm.chapter14;

import tiendm.chapter14.interfaces.Edge;
import tiendm.chapter14.interfaces.Graph;
import tiendm.chapter14.interfaces.Vertex;
import tiendm.chapter7.LinkedPositionalList;
import tiendm.chapter7.PositionalList;

public class AdjacencyGraph<V,E> implements Graph<V, E> {
	private boolean bDirect;
	private PositionalList<Vertex<V>> vertices = new  LinkedPositionalList<>();
	private PositionalList<Edge<E>> edges = new LinkedPositionalList<>();
	public AdjacencyGraph(boolean bDirect) {
		super();
		this.bDirect = bDirect;
	}
	
	public int numVertices(){
		return vertices.size();
	}
	public int numEdges(){
		return edges.size();
	}
	public Iterable<Vertex<V>> vertices(){
		return (Iterable<Vertex<V>>) vertices;
	}
	public Iterable<Edge<E>> edges(){
		return (Iterable<Edge<E>>) edges;
	}
	public int outDegree(Vertex<V> v){
		InnerVertex<V,E> vert = new InnerVertex(v, isbDirect());
		return vert.getOutging().size();
	}
	public int inDegree(Vertex<V> v){
		InnerVertex<V,E> vert = new InnerVertex(v, isbDirect());
		return vert.getIncoming().size();
	}
	public Iterable<Edge<E>> incommingEdges(Vertex<V> v){
		InnerVertex<V,E> vert = new InnerVertex(v, isbDirect());
		return (Iterable<Edge<E>>) vert.getIncoming();
	}
	public Iterable<Edge<E>>outgoingEdges(Vertex<V> v){
		InnerVertex<V,E> vert = new InnerVertex(v, isbDirect());
		return (Iterable<Edge<E>>) vert.getOutging();
	}
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v){
		InnerVertex<V,E> vert = new InnerVertex(u, isbDirect());
		return vert.getOutging().get(v);
	}
	public Vertex<V>[] endpoints(Edge<E> e){
		InnerEdge<V, E> edge = new InnerEdge(e);
		return edge.getEndpoints();
	}
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e){
		InnerVertex<V,E> vert = new InnerVertex(v, isbDirect());
		Vertex[] endpoint = endpoints(e);
		if(endpoint[0].equals(v)){
			return endpoint[1];
			
		}else if(endpoint[1].equals(v)){
			return endpoint[0];
		}
		return null;
	}
	public Vertex<V> insertVertex(V element){
		InnerVertex<V,E> vert = new InnerVertex(element, isbDirect());
		vert.setPos(vertices.addLast(vert));
		return vert;
	}
	
	public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, Edge<E> e){
		if(getEdge(u, v) == null){
			InnerEdge<V, E> edge = new InnerEdge(u, v, e);
			edge.setPos(edges.addLast(edge));
			InnerVertex<V,E> uVert = new InnerVertex(u, isbDirect());
			InnerVertex<V,E> vVert = new InnerVertex(v, isbDirect());
			uVert.getOutging().put(v, e);
			vVert.getIncoming().put(u, e);
			return edge;
		}
		return getEdge(u, v);
	}
	
	public boolean isbDirect() {
		return bDirect;
	}
	public void setbDirect(boolean bDirect) {
		this.bDirect = bDirect;
	}
	public PositionalList<Edge<E>> getEdges() {
		return edges;
	}
	public void setEdges(PositionalList<Edge<E>> edges) {
		this.edges = edges;
	}

	public PositionalList<Vertex<V>> getVertices() {
		return vertices;
	}

	public void setVertices(PositionalList<Vertex<V>> vertices) {
		this.vertices = vertices;
	}
}
