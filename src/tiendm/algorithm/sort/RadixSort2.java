package tiendm.algorithm.sort;

import java.util.Arrays;

public class RadixSort2 {
	public static void main(String[] args) {
		System.out.println(keyIdxCounting("dacffbdbfbea"));
		String[] inputs = {"dab","add","cab","fad","fee","bad","dad","bee","fed","bed","ebb","ace"};
		System.out.println(Arrays.toString(inputs));
		rdSort(inputs, 3);
		System.out.println(Arrays.toString(inputs));
	}
	
	private static char[] keyIdxCounting(String strInput){
		int R = 200;
		int N = strInput.length();
		char[] charInput = strInput.toCharArray();
		char[] output = new char[N];
		int[] count = new int[R+1];
		for(int i = 0; i < N; i++){
			count[charInput[i]+1]++;
		}
		for(int i = 0; i < R; i++){
			count[i+1] += count[i];
		}
		for(int i = 0; i < N; i++){
			output[count[charInput[i]]++] = charInput[i];
		}
		return output;
	}
	
	private static void rdSort(String[] inputs, int nbW){
		int R = 256;
		int N = inputs.length;
		String[] output = new String[N];
		System.out.println(Arrays.toString(inputs));
		for(int d = nbW-1; d >=0; d--){
			int[] count = new int[R+1];
			for(int i = 0; i < N; i++){
				count[inputs[i].charAt(d)+1]++;
			}
			for(int i = 0; i < R; i++){
				count[i+1] += count[i];
			}
			System.out.println(Arrays.toString(count));
			for(int i = 0; i < N; i++){
				output[count[inputs[i].charAt(d)]++] = inputs[i];
			}
			System.out.println(Arrays.toString(output));
			for(int i = 0; i < N; i++){
				inputs[i] = output[i];
			}
		}
	}
	
}
