package tiendm.algorithm.dynamicPrograming;

public class CoinChange {

	public CoinChange() {
	}
	
	private int count(int[] arr, int n, int sum) {
		if(sum == 0) return 1;
		if(sum < 0) return 0;
		if(n <= 0 && sum >= 1) return 0;
		return count(arr, n-1, sum) + count(arr, n, sum-arr[n-1]);
	}
	
	private int countDP(int[] arr, int n, int sum) {
		int[][] t = new int[n+1][sum+1];
		for(int i = 0; i <= n; i++){
			t[i][0] = 1;
		}
		for(int i = 1; i <= sum; i++){
			t[0][sum] = 0;
		}
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= sum; j++){
				t[i][j] = t[i-1][j];
				if(j-arr[i-1] >= 0) t[i][j] += t[i][j-arr[i-1]];
			}
		}
		return t[n][sum];
	}
	
	public static void main(String[] args) {
		int i, j;
        int arr[] = {1, 2, 3};
        int m = arr.length;
        CoinChange coin = new CoinChange();
        System.out.println( coin.count(arr, m, 4));
        System.out.println( coin.countDP(arr, m, 4));
	}

}
