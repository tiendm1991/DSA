package tiendm.algorithm.devideAndconquer;

import tiendm.util.Util;

public class CountInversions {
	public static void main(String[] args) {
//		int[] arr = Util.generateArray(5,9);
		int[] arr = {8,	5,	6,	7,	0	};
		CountInversions ci = new CountInversions();
		System.out.println("=== array ===");
		Util.printArray(arr);
		System.out.println("--- brute = " + ci.brute(arr));
		System.out.println("--- merge = " + ci.mergesort(arr,0,arr.length-1));
		System.out.println("*** array ***");
		Util.printArray(arr);
	}
	
	private int brute(int[] arr){
		int count = 0;
		for(int i = 0; i < arr.length-1; i++){
			for(int j = i+1; j < arr.length; j++){
				if(arr[i] > arr[j]) count++;
			}
		}
		return count;
	}
	private int mergesort(int[] arr, int l, int r) {
		int count = 0;
		int m = (l+r)/2;
		if(l<r){
			count = mergesort(arr, l, m);
			count += mergesort(arr, m+1, r);
			count += merge(arr, l, m, r);
		}
		return count;
	}

	private int merge(int[] arr, int left, int mid, int right) {
		int i=left;
		int j=mid;
		int k = 0; 
		int count = 0;
		int[] tmp = new int[arr.length];
		while ((i < mid) && (j <= right)) {
			if(arr[i] < arr[j]){
				tmp[k++] = arr[i++];
			}else {
				tmp[k++] = arr[j++];
				count += (mid-i);
			}
		}
		
		while (i < mid) {
			tmp[k++] = arr[i++];
		}
		while (j <= right) {
			tmp[k++] = arr[j++];
		}
		
		for(int c = left; c <= right; c++){
			arr[c] = tmp[c];
		}
		return count;
	}
}
