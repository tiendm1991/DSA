package tiendm.algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Kruskal {
	public static void main(String[] args) {
		int graph[][] = new int[][] {{0, 10, 6, 5},
						             {10, 0, 0, 15},
						             {6, 0, 0, 4},
						             {5, 15, 4, 0},
						            };
        krukalMST(graph);
	}

	static void krukalMST(int[][] graph) {
		int n = graph.length;
		Map<Integer, List<Integer>> edges = new TreeMap<>();
		for(int i = 0; i < n-1; i++){
			for(int j = i+1; j < n; j++){
				if(graph[i][j] == 0) continue;
				List<Integer> edge = edges.get(graph[i][j]);
				if( edge == null){
					edge = new ArrayList<>();
				}else {
					edge = edges.get(graph[i][j]);
				}
				edge.add(i);
				edge.add(j);
				edges.put(graph[i][j], edge);
			}
		}
		int count = 0;
		List<Integer> result = new ArrayList<>();
		for(Entry<Integer, List<Integer>> entry : edges.entrySet()){
			if(count == n-1) break;
			List<Integer> lsEdges = entry.getValue();
			for(int i = 0; i < lsEdges.size()-1; i+=2){
				int s = lsEdges.get(i);
				int d = lsEdges.get(i+1);
				if(result.contains(s) && result.contains(d)) continue;
				result.add(s);
				result.add(d);
				count++;
			}
		}
		printSlolution(graph,result);
	}

	static void printSlolution(int[][] graph, List<Integer> result) {
		for(int i = 0; i < result.size()-1; i+=2){
			System.out.println(result.get(i) + "-" + result.get(i+1) + " : "+graph[result.get(i)][result.get(i+1)]);
		}
		
	}
}
