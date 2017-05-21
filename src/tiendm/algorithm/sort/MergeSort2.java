package tiendm.algorithm.sort;

import tiendm.util.Util;

public class MergeSort2 {
	public static void main(String[] args) {
		int[] arr = Util.generateArray(10);
		System.out.println("=== before sort");
		Util.printArray(arr);
		arr = mergesort(arr);
		System.out.println("=== after sort");
		Util.printArray(arr);
	}
	
	private static int[] mergesort(int[] arr) {
		int[] L = Util.copArr(arr, 0, arr.length/2-1);
		int[] R = Util.copArr(arr, arr.length/2,arr.length-1);
		if(L.length>1 || R.length>1){
			L = mergesort(L);
			R = mergesort(R);
		}
		return merge(L,R);
	}

	private static int[] merge(int[] L, int[] R) {
		int i=0;
		int j=0;
		int c = 0;
		int n1 = L.length;
		int n2 = R.length;
		int[] newArr = new int[L.length+R.length];
		while(i<n1 && j<n2){
			if(L[i]<R[j]){
				newArr[c] = L[i];
				i++;
			}else{
				newArr[c] = R[j];
				j++;
			}
			c++;
		}
		for(int k = i;k<L.length;k++){
			newArr[c++] = L[k];
		}
		for(int k = j;k<R.length;k++){
			newArr[c++] = R[k];
		}
		return newArr;
	}
}
