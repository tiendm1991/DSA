package tiendm.algorithm.graph;

public class PrimMST {
	public static void main(String[] args) {
		int graph[][] = new int[][] {{0, 2, 0, 6, 0},
            {2, 0, 3, 8, 5},
            {0, 3, 0, 0, 7},
            {6, 8, 0, 0, 9},
            {0, 5, 7, 9, 0},
           };
        primMST(graph);
	}
	
	public static int minEdges(int[] egdes, boolean[] visited){
		int min = egdes[0], index = 0;
		for(int i = 0; i < egdes.length ; i++){
			if(!visited[i] && egdes[i] < min){
				min = egdes[i];
				index = i;
			}
		}
		return index;
	}
	
	public static void primMST(int [][] graph){
		int n = graph.length;
		int[] edges = new int[n];
		int[] parent = new int[n];
		for(int i = 0; i < n; i++){
			edges[i] = Integer.MAX_VALUE;
		}
		boolean[] visited = new boolean[n];
		int current = 0;
		for(int i = 0; i < n-1; i++){
			current = minEdges(edges, visited);
			visited[current] = true;
			for(int j = 0; j < n; j++){
				if(!visited[j] && graph[current][j] != 0
							&& graph[current][j] < edges[j]){
					edges[j] = graph[current][j];
					parent[j] = current;
				}
			}
		}
		printSolution(graph,parent);
	}

	static void printSolution(int[][] graph, int[] parent) {
		for(int i = 1; i < parent.length; i++){
			System.out.println(parent[i] + "-" + i + " : "+graph[i][parent[i]]);
		}
		
	} 
}
