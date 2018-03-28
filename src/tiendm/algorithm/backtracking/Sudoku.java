package tiendm.algorithm.backtracking;

public class Sudoku {

	private int[][] grid;

	public Sudoku(int[][] grid) {
		super();
		this.grid = grid;
	}

	public final int UNASSIGNED = 0;

	public boolean solve() {
		int unassign  =  findUnassignedLocation(grid);
		if(unassign == -1) return true;
		int row = unassign / grid.length;
		int col = unassign % grid.length;
		for (int num = 1; num <= 9; num++) {
			if (isSafe(grid, row, col, num)) {
				grid[row][col] = num;
				if (solve())
					return true;
				grid[row][col] = UNASSIGNED;
			}
		}
		return false;
	}

	private int findUnassignedLocation(int grid[][]) {
		for (int row = 0; row < grid.length; row++)
			for (int col = 0; col < grid.length; col++)
				if (grid[row][col] == UNASSIGNED)
					return row*grid.length+col;
		return -1;
	}

	private boolean isSafe(int[][] grid, int row, int col, int num) {
		return rowValid(grid, row, num) && colValid(grid, col, num)
				&& boxValid(grid, row - row % 3, col - col % 3, num);
	}

	private boolean rowValid(int[][] grid, int row, int num) {
		for (int i = 0; i < grid.length; i++) {
			if (grid[row][i] == num)
				return false;
		}
		return true;
	}

	private boolean colValid(int[][] grid, int col, int num) {
		for (int i = 0; i < grid.length; i++) {
			if (grid[i][col] == num)
				return false;
		}
		return true;
	}

	private boolean boxValid(int[][] grid, int startRow, int startCol, int num) {
		for (int i = startRow; i < startRow + 3; i++) {
			for (int j = startCol; j < startCol + 3; j++) {
				if (grid[i][j] == num)
					return false;
			}
			return true;
		}
		return true;
	}

	private void printGrid() {
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid.length; col++)
				System.out.printf("%2d", grid[row][col]);
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int grid[][] = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
				{ 0, 0, 3, 0, 1, 0, 0, 8, 0 }, { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
				{ 1, 3, 0, 0, 0, 0, 2, 5, 0 }, { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
		Sudoku sudoku = new Sudoku(grid);
		if(sudoku.solve()) sudoku.printGrid();
		String[] arr = {"Java","C"};
		String s = "Java";
		System.out.println(arr[0] == s);
	}
}
