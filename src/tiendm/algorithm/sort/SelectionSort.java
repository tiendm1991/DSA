package tiendm.algorithm.sort;

import tiendm.util.Util;

public class SelectionSort {
	public static void main(String[] args) {
		int[] arr = Util.generateArray(10);
		System.out.println("=== before sort");
		Util.printArray(arr);
		selectionSort(arr);
		System.out.println("=== after sort");
		Util.printArray(arr);
	}
	
	private static void selectionSort(int[] arr) {
		int min = Integer.MAX_VALUE;
		int minIndex = 0;
		for(int i=0;i<arr.length-1;i++){
			minIndex = i;
			min = arr[i];
			for(int j=i+1;j<arr.length;j++){
				if(arr[j]<min){
					min = arr[j];
					minIndex = j;
				}
			}
			if(arr[i]>=min) Util.swap(arr, i, minIndex);
		}
	}
}
