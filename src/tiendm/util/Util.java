package tiendm.util;

import java.util.Random;

public class Util {
	
	public static int generateNumInt(int n){
		Random rand = new Random();
		return rand.nextInt(n);
	}
	
	public static int[] generateArray(int n, int m){
		int [] x = new int[n];
		Random rand = new Random();
		for(int i=0;i<n;i++){
			x[i] = rand.nextInt(m);
		}
		return x;
	}
	
	public static int[] generateArray(int n){
		return generateArray(n,n);
	}
	
	public static void printArray(int[] x){
		if (x.length == 0) return;
		for(int i=0;i<x.length;i++){
			System.out.print(x[i] + "\t");
		}
		System.out.println();
	}
	
	public static void print2DArray(int[][] x){
		if (x.length == 0) return;
		for(int i=0;i<x.length;i++){
			for(int j=0;j<x[0].length;j++){
				System.out.print(x[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	public static void swap(int[] arr, int i1, int i2){
		int tmp = arr[i1];
		arr[i1]= arr[i2];
		arr[i2] = tmp;
	}

	public static int[] copArr(int[]arr, int start, int finish){
		int[] arrCopy = new int[finish-start+1];
		int k=0;
		for(int i=start; i<=finish;i++){
			arrCopy[k] = arr[i];
			k++;
		}
		return arrCopy;
	}
	
	public static boolean checkArrSort(int[] arr, boolean print){
		for(int i=0;i<arr.length-1;i++){
			if(arr[i]>arr[i+1]){
				if(print){
					System.out.println("Result: Array is not sort!");
				}
				return false;
			} 
		}
		if(print){
			System.out.println("Result: Array is sort!");
		}
		return true;
	}
	
	public static int getFactorize(int n){
		if(n==1) return n;
		return n * getFactorize(n-1);
	}
	
	public static void main(String[] args) {
		System.out.println(getFactorize(5));
	}
}
