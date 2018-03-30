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
		int[] t = new int[sum+1];
		t[0] = 1;
		for(int i = 0; i < n; i++){
			for(int j = arr[i]; j <= sum; j++){
				t[j] += t[j-arr[i]];
			}
		}
		return t[sum];
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
