package tiendm.algorithm.sort;

import tiendm.util.Util;

public class MergeSort {
	public static void main(String[] args) {
		int[] arr = Util.generateArray(10);
		System.out.println("=== before sort");
		Util.printArray(arr);
		mergesort(arr,0,arr.length-1);
		System.out.println("=== after sort");
		Util.printArray(arr);
	}
	
	private static void mergesort(int[] arr, int l, int r) {
		int m = (l+r)/2;
		if(l<r){
			mergesort(arr, l, m);
			mergesort(arr, m+1, r);
			merge(arr, l, m, r);
		}
	}

	private static void merge(int[] arr, int l, int m, int r) {
		int i=0;
		int j=0;
		int c = l;
		int n1 = m-l+1;
		int n2 = r-m;
		int[] L = new int[n1];
		int[] R = new int[n2];
		L= Util.copArr(arr, l, m);
		R= Util.copArr(arr, m+1, r);
		while(i<n1 && j<n2){
			if(L[i]<R[j]){
				arr[c] = L[i];
				i++;
			}else{
				arr[c] = R[j];
				j++;
			}
			c++;
		}
		for(int k = i;k<L.length;k++){
			arr[c++] = L[k];
		}
		for(int k = j;k<R.length;k++){
			arr[c++] = R[k];
		}
	}
}
