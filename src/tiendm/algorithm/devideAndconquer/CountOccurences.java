package tiendm.algorithm.devideAndconquer;

import tiendm.algorithm.sort.QuickSort;
import tiendm.util.Util;

public class CountOccurences {

	public static void main(String[] args) {
//		int[] arr = Util.generateArray(10,6);
		int count = 0, total = 1000;
		for(int i = 0; i < total; i++){
			if(test()) count++;
		}
		System.out.println("Correct/Total = " + count + "/" + total + " = " + (count*100/total) + "%");
	}
	
	private static boolean test() {
		int n = 500, m = 200, k = Util.generateNumInt(m);
		int[] arr = Util.generateArray(n, m);
//		int[] arr = {0,	0,	0,	0,	1,	2,	2,	3,	};
		QuickSort.quickSort(arr, 0, arr.length-1);
		CountOccurences cc = new CountOccurences();
		System.out.println("=== array ===");
		Util.printArray(arr);
		int linear = cc.linear(arr, k);
		int devide = cc.devide(arr, k);
		return linear == devide;
	}

	private int linear(int[] arr, int num) {
		System.out.println("Brute");
		int count =0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == num) {
				count++;
			}
			if(arr[i] > num) break;
		}
		System.out.println("count number " + num + " = " + count);
		return count;
	}

	private int devide(int[] arr, int num) {
		System.out.println("Devide");
		int count = 0;
		int first = first(arr,num,0,arr.length-1);
		int last = last(arr,num,0,arr.length-1);
		if(first == -1 || last == -1){
			count = 0;
		}else {
			count = last - first + 1;
		}
		System.out.println("count number " + num + " = " + count);
		return count;
	}

	private int first(int[] arr, int num, int left, int right) {
		if(left == right){
			if(arr[left] == num) return left;
			else {
				return -1;
			}
		}
		int mid = (left + right) / 2;
		if(arr[mid] > num){
			return first(arr, num, left, Math.max(left, mid-1));
		}else if (arr[mid] < num) {
			return first(arr, num, Math.min(mid+1, right), right);
		}else {
			if(mid == left) return mid;
			else {
				if(arr[mid - 1] < num) return mid;
				else {
					return first(arr, num, left, mid-1);
				}
			}
		}
	}

	private int last(int[] arr, int num, int left, int right) {
		if(left == right){
			if(arr[left] == num) return right;
			else {
				return -1;
			}
		}
		int mid = (left + right) / 2;
		if(arr[mid] < num){
			return last(arr, num, Math.min(mid+1, right), right);
		}else if (arr[mid] > num) {
			return last(arr, num, left, Math.max(left, mid-1));
		}else {
			if(arr[mid + 1] > num) return mid;
			else {
				return last(arr, num, mid+1, right);
			}
		}
	}
}
