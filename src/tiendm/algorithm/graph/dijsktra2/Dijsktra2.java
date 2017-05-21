package tiendm.algorithm.graph.dijsktra2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

public class Dijsktra2 {
	public static void main(String[] args) {
		try {
			GraphUtil util = new GraphUtil();
			Graph graph = util.readGraph("file\\graph1.txt");
			Dijsktra2 dijsktra = new Dijsktra2();
			dijsktra.dijsktra(graph, graph.getVertexs().get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dijsktra(Graph graph, Vertex source){
		List<Vertex> vertexs = graph.getVertexs();
		List<Edge> edges = graph.getEdges();
		Set<Vertex> visited = new HashSet<>();
		Map<Vertex, Integer> distance = new HashMap<>();
		Map<Vertex, Vertex> backtrack = new HashMap<>();
		for(Vertex v : vertexs){
			if(v.equals(source)){
				distance.put(source, 0);
			}else{
				distance.put(v, Integer.MAX_VALUE);
			}
		}
		
		for(int i=0;i<vertexs.size()-1;i++){
			Vertex u = minDistances(distance, visited);
			visited.add(u);
			for(Vertex v : vertexs){
				if(!visited.contains(v) && getWeight(u, v, edges)!=0
						&& distance.get(u) != Integer.MAX_VALUE
						 && distance.get(u) + getWeight(u, v, edges) < distance.get(v) ){
						distance.replace(v, distance.get(u) + getWeight(u, v, edges));
						backtrack.put(v, u);
				}
			}
		}
		printSolution(distance,backtrack,source);
	}
	
	private int getWeight(Vertex s, Vertex d, List<Edge> edges){
		for(Edge e : edges){
			if(e.getSource().equals(s) && e.getDest().equals(d)) return e.getWeight();
		}
		return 0;
	}
	
	public Vertex minDistances(Map<Vertex, Integer> distance, Set<Vertex> visited){
		int min = Integer.MAX_VALUE;
		Vertex minVertex = null;
		for (Entry<Vertex, Integer> entry : distance.entrySet()) {
			if(!visited.contains(entry.getKey()) && entry.getValue() < min){
				min = entry.getValue();
				minVertex = entry.getKey();
			}
		}
		return minVertex;
	}
	
	private  void  printSolution(Map<Vertex, Integer> distance, Map<Vertex, Vertex> backtrack, Vertex source){
        System.out.println("Vertex\t\tDistance\tPath");
        for (Entry<Vertex, Integer> entry : distance.entrySet())
            System.out.println(entry.getKey().getName()+"\t"+entry.getValue() + "\t" + getShortestPath(backtrack,source,entry.getKey()));
    }

	private String getShortestPath(Map<Vertex, Vertex> backtrack, Vertex source, Vertex dest) {
		StringBuilder path = new StringBuilder();
		Stack<Vertex> stack = new Stack<>();
		Vertex current = dest;
		while(current!=null && !current.equals(source)){
			stack.push(current);
			current = backtrack.get(current);
		}
		path.append(source.getId())  ;
		while (!stack.isEmpty()) {
			path.append(" -> "+stack.pop().getId());
		}
		return path.toString();
	}
}
