package tiendm.algorithm.devideAndconquer;

import java.util.Arrays;

import tiendm.algorithm.sort.QuickSort;
import tiendm.util.Util;

public class MinAbsSum {
	public static void main(String[] args) {
//		int[] arr = Util.generateArray(5,9);
		int[] arr = {1, 60, -10, 70, -80, 85};
		MinAbsSum mas = new MinAbsSum();
		System.out.println("=== array ===");
		Util.printArray(arr);
		mas.brute(arr);
		mas.sortAlg(arr);
	}
	
	private void brute(int[] arr){
		System.out.println("****Brute***");
		int minsum = Integer.MAX_VALUE, n1 = 0, n2 = 0;
		for(int i = 0; i < arr.length-1; i++){
			for(int j = i+1; j < arr.length; j++){
				if(Math.abs(arr[i] + arr[j]) < minsum){
					minsum = Math.abs(arr[i] + arr[j]) ;
					n1 = arr[i];
					n2 = arr[j];
				}
			}
		}
		System.out.println("min sum = " + minsum + " , num1 = " + n1 + " , num2 = " + n2);
	}
	
	private void sortAlg(int[] arr){
		System.out.println("****SortAlg***");
		int i = 0, j = arr.length-1;
		int minsum = Integer.MAX_VALUE;
		int sum = 0, id1 = i, id2 = j;
		QuickSort.quickSort(arr, 0, arr.length-1);
		while (i < j) {
			sum = Math.abs(arr[i] + arr[j]);
			if(sum < minsum){
				minsum = sum;
				id1 = i;
				id2 = j;
			}
			if(minsum >=0 ) {
				i++;
			}else {
				j--;
			}
		}
		System.out.println("min sum = " + minsum + " , num1 = " + arr[id1] + " , num2 = " + arr[id2]);
	}
}
