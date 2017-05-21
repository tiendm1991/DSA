package tiendm.algorithm.sort;

import tiendm.util.Util;

public class RadixSort {
	public static void main(String[] args) {
		int[] arr = Util.generateArray(1000,10);
		System.out.println("=== before sort");
		Util.printArray(arr);
		radixSort(arr);
		System.out.println("=== after sort");
		Util.printArray(arr);
	}

	private static void radixSort(int[] arr) {
		int max = findMax(arr);
		int maxLoop = (int) Math.ceil(Math.log10(max));
		int exp = 1;
		for(int i=1;i<=maxLoop;i++){
			int digit = 0;
			int count[] = new int [10];
			int output[] = new int [arr.length];
			for(int j=0;j<arr.length;j++){
				digit = (arr[j]/exp)%10;
				count[digit]++;
			}
			for(int j=1;j<10;j++){
				count[j] += count[j-1];
			}
			for(int j=arr.length-1;j>=0;j--){
				digit = (arr[j]/exp)%10;
				output[count[digit]-1] = arr[j];
				count[digit]--;
			}
			for(int j=0;j<arr.length;j++){
				arr[j] = output[j];
			}
			System.out.println("===output");
			Util.printArray(output);
			exp *=10;
		}
	}

	private static int findMax(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]>max) max = arr[i];
		}
		return max;
	}
}
