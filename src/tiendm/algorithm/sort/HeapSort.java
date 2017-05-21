package tiendm.algorithm.sort;

import tiendm.util.Util;

public class HeapSort {
	public static void main(String[] args) {
		int[] arr = Util.generateArray(10);
		System.out.println("=== before sort");
		Util.printArray(arr);
		heapSort(arr,arr.length-1);
		System.out.println("=== after sort");
		Util.printArray(arr);
		Util.checkArrSort(arr,true);
	}

	private static void heapSort(int[] arr, int last) {
		int n = last/2 ;
		for(int i= n ; i>=0; i--){
			heapify(arr,last, i);
		}
		Util.swap(arr, 0,last);
		while (last>0) {
			last--;
			heapify(arr, last, 0);
			Util.swap(arr, 0,last);
		}
	}

	private static void heapify(int[] arr,int n, int i) {
		int max = arr[i];
		int indexMax = i;
		int left = 2*i+1;
		int right = 2*i+2;
		if(left <= n && arr[left] > max){
			max = arr[left];
			indexMax = left;
		} 
		if(right <= n && arr[right] > max){
			max = arr[right] ;
			indexMax = right;
		}
		if(indexMax != i){
			Util.swap(arr, i, indexMax);
			heapify(arr,n, indexMax);
		}
	}
}
