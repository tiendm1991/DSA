package tiendm.algorithm.sort;

import tiendm.util.Util;

public class FindKthMax2 {
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
		int pivot = partition(arr , left, right);
		if(pivot == idx){
			return arr[pivot];
		}else if(pivot > idx) {
			return kthMax(arr, left, pivot-1, idx);
		}else {
			return kthMax(arr, pivot+1, right, idx);
		}
	}
	
	public static int partition(int[] arr, int left, int right) {
		int pivot = arr[right];
		int i=left-1;
		for(int j = left; j < right; j++){
			if(arr[j] <= pivot){
				i++;
				Util.swap(arr, i, j);
			}
		}
		Util.swap(arr, ++i, right);
		return i;
	}
}
