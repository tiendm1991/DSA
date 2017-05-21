package tiendm.algorithm.sort;

import tiendm.util.Util;

public class InsertionSort {
	public static void main(String[] args) {
		int[] arr = Util.generateArray(10);
		System.out.println("=== before sort");
		Util.printArray(arr);
		insertionSort(arr);
		System.out.println("=== after sort");
		Util.printArray(arr);
	}
	
	private static void insertionSort(int[] arr) {
		int count = 0;
		for(int i=0;i<arr.length;i++){
			if(i == 0) continue;
			count = i;
			while (count>0 && arr[count] < arr[count-1]){
				Util.swap(arr, count-1, count);
				count -- ;
			}
		}
	}
}
