package tiendm.algorithm.graph.dijsktra2;

import java.util.List;

public class Graph {
	private List<Vertex> vertexs;
	private List<Edge> edges;
	
	public Graph(List<Vertex> vertexs, List<Edge> edges) {
		this.vertexs = vertexs;
		this.edges = edges;
	}
	
	public List<Vertex> getVertexs() {
		return vertexs;
	}
	public void setVertexs(List<Vertex> vertexs) {
		this.vertexs = vertexs;
	}
	public List<Edge> getEdges() {
		return edges;
	}
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	
}
