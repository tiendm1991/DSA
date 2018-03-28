package tiendm.algorithm.sort;

import tiendm.util.Util;

public class FindKthMax {
	public static void main(String[] args) {
		int nbTest = 100000, nbCorrect = 0;
		for(int i = 0; i < nbTest; i++){
			if(test()) nbCorrect++;
		}
		System.out.println("***************");
		System.out.println("Number correct/Number Test = " + nbTest + "/" + nbCorrect);
		System.out.println("Ratio correct = " + (nbCorrect * 100 / nbTest) + "%");
	}
	
	private static boolean test(){
		System.out.println("---------------------");
		int k_th = 6;
		int[] arr = Util.generateArray(9,1000);
//		int[] arr = {29,	62,	45,	13,	26,	98,	13,	47,	20};
//		int[] arr = {74,	43,	19,	76,	2,	9,	35,	15,	8	};
		System.out.println("=== before sort");
		Util.printArray(arr);
		int x = kthMax(arr,0,arr.length-1, k_th);
		QuickSort.quickSort(arr, 0,arr.length-1);
		System.out.println("=== after sort");
		Util.printArray(arr);
		System.out.println("k-th max = " + x);
		return arr[k_th] == x;
	}

	private static int kthMax(int[] arr, int left, int right, int idx) {
		int pivotIndex = partition(arr , left, right);
		if(pivotIndex  == idx) {
			return maxRange(arr,left, pivotIndex);
		}
		if(pivotIndex > idx){
			return kthMax(arr, left, pivotIndex, idx);
		}else {
			return kthMax(arr, pivotIndex+1, right, idx);
		}
	}
	
	private static int maxRange(int[] arr, int left, int right){
		int max = arr[left];
		for(int i = left; i <= right; i++){
			if (arr[i] > max){
				max = arr[i];
			}
		}
		return max;
	}

	private static int partition(int[] arr, int left, int right) {
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
