package tiendm.algorithm.graph;

import java.util.Stack;

public class Dijsktra {
	private static final int V = 9;
	public static void main(String[] args) {
		int graph[][] = createGraph();
		dijkstra(graph, 0);
	}
	
	public static int[][] createGraph(){
		int[][] graph = {	{0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
               };
		return graph;
	}
	
	private static void dijkstra(int[][] graph, int source) {
		int[] backtrack = new int[V];
		int[] arrDis = new int[V];
		boolean [] sptSet = new boolean[V];
		for(int i=0;i<V;i++){
			arrDis[i] = Integer.MAX_VALUE;
			sptSet[i] = false;
		}
		arrDis[source] =0;
		
		for(int i=0;i<V-1;i++){
			int u = minDistance(arrDis, sptSet);
			sptSet[u] = true;
			for(int v = 0; v<V ; v++){
				if(!sptSet[v] && graph[u][v] !=0 && arrDis[u]!=Integer.MAX_VALUE && arrDis[u]+graph[u][v]<arrDis[v]){
					arrDis[v] = arrDis[u] + graph[u][v];
					backtrack[v] = u;
				}
			}
		}
		printSolution(arrDis,backtrack,0);
	}
	
	private static int minDistance(int[] arrDis , boolean[] sptSet){
		int min = Integer.MAX_VALUE;
		int min_index = 0;
		for(int i=0; i < arrDis.length;i++){
			if(!sptSet[i] && arrDis[i] < min){
				min = arrDis[i];
				min_index = i;	
			}
		}
		return min_index;
	}
	
	private static void  printSolution(int dist[], int[] backtrack, int source){
        System.out.println("Vertex\t\tDistance\tPath");
        for (int i = 0; i < V; i++)
            System.out.println(i+"\t\t "+dist[i] + "\t\t" + getShortestPath(backtrack, source, i));
    }

	private static String getShortestPath(int[] backtrack, int source, int dest) {
		StringBuilder path = new StringBuilder();
		Stack<Integer> stack = new Stack<>();
		int current = dest;
		while(current!=source){
			stack.push(current);
			current = backtrack[current];
		}
		path.append(source)  ;
		while (!stack.isEmpty()) {
			path.append(" -> "+stack.pop());
		}
		return path.toString();
	}
	
}
