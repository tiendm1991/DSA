package tiendm.algorithm.sort;

import tiendm.util.Util;

public class QuickSort {
	public static void main(String[] args) {
		int[] arr = Util.generateArray(8,100);
		System.out.println("=== before sort");
		Util.printArray(arr);
		quickSort(arr,0,arr.length-1);
		System.out.println("=== after sort");
		Util.printArray(arr);
	}

	public static void quickSort(int[] arr, int left, int right) {
		int pivotIndex = partition(arr , left, right);
		if (left < pivotIndex) {
			quickSort(arr, left, pivotIndex);
		}
		if (pivotIndex < right){
			quickSort(arr, pivotIndex+1, right);
		}
	}

	public static int partition(int[] arr, int left, int right) {
		int pivot = arr[(left+right)/2];
		int i=left;
		int j=right;
		while (i <= j) {
			while (arr[i] < pivot) {
				i++;
			}
			while (arr[j] > pivot) {
				j--;
			}
			if(i<=j){
				Util.swap(arr, i, j);
				i++;
				j--;
			}
		}
		return i-1;
	}
}
