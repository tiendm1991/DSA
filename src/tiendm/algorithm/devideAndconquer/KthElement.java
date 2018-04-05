package tiendm.algorithm.devideAndconquer;

import tiendm.algorithm.sort.QuickSort;
import tiendm.util.Util;

public class KthElement {

	public static void main(String[] args) {
		testCorrect();
		testTiming();
	}
	
	private static void testCorrect() {
		int count = 0, total = 1000;
		for(int i = 0; i < total; i++){
			if(test()) count++;
			else {
				break;
			}
		}
		System.out.println("Correct/Total = " + count + "/" + total + " = " + (count*100/total) + "%");
	}
	
	private static boolean test() {
		int n = 50, m = 50;
		int[] arr1 = Util.generateArray(n, m);
		int[] arr2 = Util.generateArray(n, m);
		int k = Util.generateNumInt(2*n)+1;
//		int[] arr1 = {0, 3, 7, 8, 11, 14};
//		int[] arr2 = {2, 5, 11, 13, 17, 19};
//		int k = 11;
		QuickSort.quickSort(arr1, 0, arr1.length-1);
		QuickSort.quickSort(arr2, 0, arr2.length-1);
		KthElement cc = new KthElement();
		System.out.println("=== array ===");
		Util.printArray(arr1);
		Util.printArray(arr2);
		System.out.println("k = " + k);
		int linear = cc.linear(arr1,arr2, k);
		int devide = cc.devideAlg(arr1,arr2, k);
		return linear == devide;
	}
	
	private static void testTiming() {
		System.out.println("****************TEST TIMMING***************");
		int DATA = 100000;
		int n = 50, m = 50;
		double t0 = System.currentTimeMillis();
		KthElement cc = new KthElement();
		for(int i = 0; i < DATA; i++){
			int[] arr1 = Util.generateArray(n, m);
			int[] arr2 = Util.generateArray(n, m);
			int k = Util.generateNumInt(2*n)+1;
//			int[] arr1 = {0, 3, 7, 8, 11, 14};
//			int[] arr2 = {2, 5, 11, 13, 17, 19};
//			int k = 11;
			QuickSort.quickSort(arr1, 0, arr1.length-1);
			QuickSort.quickSort(arr2, 0, arr2.length-1);
			System.out.println("=== array ===");
			Util.printArray(arr1);
			Util.printArray(arr2);
			System.out.println("k = " + k);
			cc.linear(arr1,arr2, k);
		}
		double t1 = System.currentTimeMillis();
		for(int i = 0; i < DATA; i++){
			int[] arr1 = Util.generateArray(n, m);
			int[] arr2 = Util.generateArray(n, m);
			int k = Util.generateNumInt(2*n)+1;
//			int[] arr1 = {0, 3, 7, 8, 11, 14};
//			int[] arr2 = {2, 5, 11, 13, 17, 19};
//			int k = 11;
			QuickSort.quickSort(arr1, 0, arr1.length-1);
			QuickSort.quickSort(arr2, 0, arr2.length-1);
			System.out.println("=== array ===");
			Util.printArray(arr1);
			Util.printArray(arr2);
			System.out.println("k = " + k);
			cc.devideAlg(arr1,arr2, k);
		}
		double t2 = System.currentTimeMillis();
		System.out.println("Timing linear = " + (t1-t0)/1000 + " , Timing devide = " + (t2-t1)/1000);
	}

	private int linear(int[] arr1, int[] arr2, int k) {
		int i = 0, j = 0, count = 0;
		int[] tmp = new int[arr1.length + arr2.length];
		while (i < arr1.length && j < arr2.length ) {
			if(arr1[i] < arr2[j]) {
				tmp[count++] = arr1[i++];
			}else {
				tmp[count++] = arr2[j++];
			}
		}
		while (i < arr1.length && count <= k) {
			tmp[count++] = arr1[i++];
		}
		while (j < arr2.length && count <= k) {
			tmp[count++] = arr2[j++];
		}
		System.out.println("Brute num = " + tmp[k-1]);
		return tmp[k-1];
	}

	private int devideAlg(int[] arr1, int[] arr2, int k) {
		if(k < 0 || k > arr1.length+arr2.length) return -1;
		if(k == arr1.length + arr2.length) return Math.max(arr1[arr1.length-1], arr2[arr2.length-1]);
		return devide(arr1, arr2, 0, arr1.length-1, 0, arr2.length-1, k);
	}

	private int devide(int[] arr1, int[] arr2, int start1, int end1, int start2, int end2, int k) {
		int mid1 = (start1 + end1)/2;
		int mid2 = (start2 + end2)/2;
		int n1 = mid1 - start1 + 1;
		int n2 = mid2 - start2 + 1;
		int result = 0;
		if(k == 1){
			System.out.println("Devide num = " + Math.min(arr1[start1], arr2[start2]));
			return Math.min(arr1[start1], arr2[start2]);
		}
		if(start1 == end1 ) {
			if(arr1[start1] > arr2[start2+k-1]){
				result = arr2[start2+k-1];
			}else {
				result = Math.max(arr1[start1], arr2[start2+k-2]);
			}
			System.out.println("Devide num = " + result );
			return result;
		}
		if(start2 == end2 ) {
			if(arr2[start2] > arr1[start1+k-1]){
				result = arr1[start1+k-1];
			}else {
				result = Math.max(arr2[start2], arr1[start1+k-2]);
			}
			System.out.println("Devide num = " + result );
			return result;
		}
		if(n1 + n2 == k){
			if(arr1[mid1] < arr2[mid2]){
				return devide(arr1, arr2, mid1+1, end1, start2, mid2, k-n1);
			}
			else {
				return devide(arr1, arr2, start1, mid1, mid2+1, end2, k-n2);
			}
		}else if(n1 + n2 < k){
			if(arr1[mid1] < arr2[mid2]){
				return devide(arr1, arr2, mid1+1, end1, start2, end2, k-n1);
			}
			else {
				return devide(arr1, arr2, start1, end1, mid2+1, end2, k-n2);
			}
		}else {
			if(arr1[mid1] < arr2[mid2]){
				return devide(arr1, arr2, start1, end1, start2, mid2, k);
			}
			else {
				return devide(arr1, arr2, start1, mid1, start2, end2, k);
			}
		}
	}
}
