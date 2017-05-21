package tiendm.algorithm.knapsack;

import java.util.ArrayList;
import java.util.List;

public class KnapSackDP {
	public static void main(String[] args) {
		List<Item> lsItem = KnapSackUtil.initRandomListItem(5,10);
//		List<Item> lsItem = KnapSackUtil.initListItem("knapsack_data\\data1.dat");
		final int W = 15;
		KnapSackUtil.printListItem(lsItem);
		KnapSackDP knapsackDP = new KnapSackDP();
		int result = knapsackDP.solveKnapsack(lsItem,W);
		System.out.println("sum price= " + result);
	}
	private int solveKnapsack(List<Item> lsItem, int W) {
		int n = lsItem.size();
		int[][] knapsack = new int[n+1][W+1];
		int[][] trace = new int[n+1][W+1];
		for(int i=0;i<=W;i++){
			System.out.print(i + "\t");
		}
		System.out.println();
		for(int i=0;i<=n;i++){
			Item item = new Item("", 0, 0);
			if(i>0){
				item = lsItem.get(i-1);
			}
			for(int j=0;j<=W;j++){
				if(i==0) {
					System.out.print(knapsack[i][j] + "\t");
					continue;
				}
				if(j<item.getWeight()){
					knapsack[i][j] = knapsack[i-1][j];
					trace[i][j] = 0;
					System.out.print(knapsack[i][j] + "\t");
					continue;
				}
				knapsack[i][j] = Math.max(item.getPrice() + knapsack[i-1][j-item.getWeight()], knapsack[i-1][j]);
				if(item.getPrice() + knapsack[i-1][j-item.getWeight()] > knapsack[i-1][j]) trace[i][j] = 1;
				System.out.print(knapsack[i][j] + "\t");
			}
			System.out.println();
		}
		printTraceTable(W, n, trace);
		List<Integer> lsItemSelect = new ArrayList<>();
		traceItem(lsItem,lsItemSelect,trace,W);
		System.out.println(lsItemSelect);
		return knapsack[n][W];
	}

	private void traceItem(List<Item> lsItem, List<Integer> lsItemSelected, int[][] trace, int K) {
		int n = lsItem.size();
		if(K<=0) return;
		int select = 0;
		for(int i=n;i>=1;i--){
			if(trace[i][K]==1 && !lsItemSelected.contains(i)){
				select = i;
				break;
			} 
		}
		if(select > 0){
			lsItemSelected.add(select);
			traceItem(lsItem, lsItemSelected, trace, K-lsItem.get(select-1).getWeight());
		}
	}
	
	private void printTraceTable(int W, int n, int[][] trace) {
		System.out.println("------------trace-----------");
		for(int i=0;i<=W;i++){
			System.out.print(i + "\t");
		}
		System.out.println();
		for(int i=0;i<=n;i++){
			for(int j=0;j<=W;j++){
				System.out.print(trace[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
