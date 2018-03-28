package tiendm.algorithm.sort;

import tiendm.util.Util;

public class QuickSort2 {
	public static void main(String[] args) {
//		int[] arr = Util.generateArray(8,100);
		int[] arr = {78,	40,	89,	70,	4,	86,	1,	76	};
		System.out.println("=== before sort");
		Util.printArray(arr);
		quickSort(arr,0,arr.length-1);
		System.out.println("=== after sort");
		Util.printArray(arr);
	}

	public static void quickSort(int[] arr, int left, int right) {
		if(left < right){
			int pivot = partition(arr , left, right);
			quickSort(arr, left, pivot-1);
			quickSort(arr, pivot+1, right);
		}
	}

	public static int partition(int[] arr, int left, int right) {
		int pivot = arr[right];
		int i=left-1;
		for(int j = left; j < right; j++){
			if(arr[j] <= pivot){
				Util.swap(arr, ++i, j);
			}
		}
		Util.swap(arr, ++i, right);
		return i;
	}
}
