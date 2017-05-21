package tiendm.algorithm.sort;

import tiendm.util.Util;

public class BubbleSort {
	public static void main(String[] args) {
		int[] arr = Util.generateArray(15);
		System.out.println("=== before sort");
		Util.printArray(arr);
		bubbleSort(arr);
		System.out.println("=== after sort");
		Util.printArray(arr);
	}
	
	public static void bubbleSort(int[] arr){
		for(int i=0;i<arr.length;i++){
			for(int j=i;j<arr.length;j++){
				if(arr[i]>arr[j]) Util.swap(arr, i, j);
			}
		}
	}
}
