package tiendm.algorithm.dynamicPrograming;

import java.util.Stack;

import tiendm.util.Util;

public class SnakeSequence {
	private int[][] grid;
	private int[][] T;

	public SnakeSequence(int[][] grid) {
		super();
		this.grid = grid;
	}

	private void solveSnake() {
		int N = grid.length;
		int M = grid[0].length;
		T = new int[grid.length][grid[0].length];
		int max = 0;
		Pair posMax = null;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (j > 0 && Math.abs(grid[i][j] - grid[i][j - 1]) == 1) {
					T[i][j] = Math.max(T[i][j], T[i][j - 1] + 1);
				}
				if (i > 0 && Math.abs(grid[i][j] - grid[i-1][j]) == 1) {
					T[i][j] = Math.max(T[i][j], T[i-1][j] + 1);
				}
				if(T[i][j] > max){
					max = T[i][j];
					posMax = new Pair(i, j);
				}
			}
		}
		Util.print2DArray(T);
		System.out.println("Max Snake*******");
		int x = posMax.x, y = posMax.y;
		Stack<Pair> track = new Stack<>();
		track.push(posMax);
		while ((x > 0 || y > 0) && max > 0) {
			if(x > 0 && T[x-1][y] == max-1){
				track.push(new Pair(--x, y));
				max--;
				continue;
			}
			if(y > 0 && T[x][y-1] == max-1){
				track.push(new Pair(x, --y));
				max--;
				continue;
			}
		}
		while (!track.isEmpty()) {
			System.out.print(track.pop() + "->");
		}
		System.out.print("end");
	}

	public int[][] getGrid() {
		return grid;
	}

	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

	public static void main(String[] args) {
		int mat[][] = { { 9, 6, 5, 2 }, { 8, 7, 6, 5 }, { 7, 3, 1, 6 }, { 1, 1, 1, 7 } };
		SnakeSequence snake = new SnakeSequence(mat);
		Util.print2DArray(snake.getGrid());
		System.out.println("--------Solve-------");
		snake.solveSnake();
		
	}
	
	private static class Pair{
		int x,y;
		
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		@Override
		public String toString() {
			return "(" + x + "," + y + ")";
		}
		
	}
}
