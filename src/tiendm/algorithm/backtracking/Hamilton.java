package tiendm.algorithm.backtracking;

public class Hamilton {
	private int[][] graph;
	private int[] path;
	
	public Hamilton(int[][] graph) {
		this.graph = graph;
	}

	private boolean isSafe(int[] path, int pos, int v){
		if(graph[path[pos-1]][v] == 0) return false;
		for(int i = 0; i < pos; i++){
			if(path[i] == v) return false;
		}
		return true;
	}
	
	private boolean solve(int[] path, int pos){
		if(pos == graph.length){
			return (graph[path[pos-1]][path[0]] == 1);
		}
		for(int v = 0; v < graph.length; v++){
			if(isSafe(path, pos, v)){
				path[pos] = v;
				if(solve(path, pos+1)){
					return true;
				}
				path[pos] = 0;
			}
		}
		return false;
	}
	
	private void hamCycle(){
		path = new int[graph.length];
		path[0] = 0;
		if(!solve(path, 1)){
			System.out.println("Do not exist hamilton cycle");
		}else {
			printSolution();
		}
	}
	
	private void printGraph() {
		for (int row = 0; row < graph.length; row++) {
			for (int col = 0; col < graph.length; col++)
				System.out.printf("%2d", graph[row][col]);
			System.out.println();
		}
	}
	
	private void printSolution() {
		for (int i = 0; i < path.length; i++) {
			System.out.printf("%2d ->", path[i]);
		}
		System.out.print(path[0]);
	}
	
	public static void main(String[] args) {
		int graph[][] = {{0, 1, 0, 1, 0},
			            {1, 0, 1, 0, 0},
			            {0, 1, 0, 1, 1},
			            {1, 0, 1, 0, 1},
			            {0, 0, 1, 1, 0},
			        };
		Hamilton hamilton = new Hamilton(graph);
		hamilton.hamCycle();
		
	}
}
