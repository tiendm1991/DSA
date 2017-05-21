package tiendm.algorithm.dynamicPrograming;

public class CheckSumSub {
	boolean checkSumSub(int[] arr, int k) {
//		return isSubSum(arr,k,n);
		int n = arr.length;
		boolean[][] T = new boolean[n+1][k+1];
		for(int j = 0; j < n; j++){
			T[j][0] = true;
		}
		for(int i = 1; i < n+1; i++){
			for(int sum = 1; sum < k+1; sum++){
				if(T[i-1][sum]){
					T[i][sum] = true;
				}else {
					if(sum < arr[i-1]){
						T[i][sum] = T[i-1][sum];
					}else{
						T[i][sum] = T[i-1][sum-arr[i-1]];
					}
				}
			}
		}
		return T[n][k];
	}
	boolean isSubSum(int[] arr, int sum, int n) {
		if(sum==0) return true;
		if(sum !=0 && n ==0) return false;
		if(sum == arr[n-1]) return true;
		else {
			return isSubSum(arr, sum, n-1) || isSubSum(arr, sum-arr[n-1], n-1);
		}
	}
	public static void main(String[] args) {
		int[] arr = {0,1,2,3,4,5};
		CheckSumSub test = new CheckSumSub();
		System.out.println(test.checkSumSub(arr, 12));
	}
}
