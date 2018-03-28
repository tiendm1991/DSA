package tiendm.algorithm.backtracking;

public class NQueen {
	private int queen[][];
	private int nbSolution;

	public NQueen(int N) {
		this.queen = new int[N][N];
	}
	
	private boolean checkDiagonal(int r, int c, int row, int col){
		if(r < 0 || c < 0 || r >= queen.length || c >= queen.length) return true;
		if(queen[r][c] == 1 && r != row && c != col) return false;
		return true;
	}

	private boolean isSafe(int row, int col) {
		for (int i = 0; i < queen.length; i++) {
			if (queen[i][col] == 1 && i != row)
				return false;
			if (queen[row][i] == 1 && i != col)
				return false;
			if(!checkDiagonal(row+i, col+i, row, col))
				return false;
			if(!checkDiagonal(row-i, col-i, row, col))
				return false;
			if(!checkDiagonal(row+i, col-i, row, col))
				return false;
			if(!checkDiagonal(row-i, col+i, row, col))
				return false;
		}
		return true;
	}

	private void solve(int row) {
		if (row >= queen.length) {
			printSolution(++nbSolution);
			return;
		}
		
		for(int i = 0; i < queen.length; i++){
			queen[row][i] = 1;
			if(isSafe(row, i)){
				solve(row+1) ;
			}
			queen[row][i] = 0;
		}
	}
	
	
	private void printSolution(int n) {
		System.out.println("-------- solution " + n + " --------");
		for(int i = 0; i < queen.length; i++){
			for(int j = 0; j < queen.length; j++){
				System.out.printf("%2d ", queen[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		NQueen nQueen = new NQueen(10);
		nQueen.solve(0);
	}
}
