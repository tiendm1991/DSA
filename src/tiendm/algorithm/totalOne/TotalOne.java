package tiendm.algorithm.totalOne;

import java.util.ArrayList;
import java.util.List;

public class TotalOne {
	// long totalOnes(int k) {
	// if(k == 1) return 1;
	// int log = (int) (Math.log(k) / Math.log(2));
	// int sum = 0;
	// int[][] sumLevel = new int[log+1][(int) Math.pow(2, log)];
	// sumLevel[0][0] = 1;
	// for(int level = 1 ; level < log; level++){
	// int powLevel = (int) Math.pow(2, level);
	// int powLevelPre = (int) Math.pow(2, level-1);
	// for(int i = 0; i < powLevel; i++){
	// if(i < powLevelPre) {
	// sumLevel[level][i] = sumLevel[level-1][i];
	// } else {
	// sumLevel[level][i] = sumLevel[level][i-powLevelPre]+1;
	// }
	// }
	// }
	// int powLevelPre = (int) Math.pow(2, log-1);
	// if(k < Math.pow(2, log)) {
	// for(int i = 0; i < k; i++){
	// sumLevel[log][i] = sumLevel[log-1][i];
	// }
	// } else {
	// for(int i = 0; i <= k - Math.pow(2, log); i++){
	// if(i < powLevelPre) {
	// sumLevel[log][i] = sumLevel[log-1][i];
	// }else {
	// sumLevel[log][i] = sumLevel[log][i-powLevelPre]+1;
	// }
	// }
	// }
	//
	// return sum(sumLevel);
	// }
	//
	// long sum(int[][] sumLevel) {
	// long sum = 0;
	// for(int i = 0; i < sumLevel.length; i++){
	// for(int j = 0; j < sumLevel[i].length; j++){
	// if(sumLevel[i][j] == 0) break;
	// sum += sumLevel[i][j];
	// }
	// }
	// return sum;
	// }

	long totalOnes(int k) {
		if (k == 1)
			return 1;
		int log = (int) (Math.log(k) / Math.log(2));
		long sum = 0;
		long[] sumLevel = new long[log + 1];
		sumLevel[0] = 1;
		for (int level = 1; level < log; level++) {
			sumLevel[level] = (long) (sumLevel[level - 1] * 2 + Math.pow(2, level - 1));
		}

		int c = (int) ((k % Math.pow(2, log - 1)) + 1);
		int x = 0;
		List<Integer> trace = new ArrayList<>();
		while (c > 0) {
			if (c % 2 != 0) {
				trace.add(x);
			}
			x++;
			c = c / 2;
		}

		if (k < (int) (Math.pow(2, log + 1) - Math.pow(2, log - 1))) {
			for (int i = 0; i < trace.size(); i++) {
				sumLevel[log] += sumLevel[trace.get(i)] + (trace.size() - i - 1) * Math.pow(2, trace.get(i));
			}
		} else {
			for (int i = 0; i < trace.size(); i++) {
				sumLevel[log] += sumLevel[trace.get(i)] + (trace.size() - i) * Math.pow(2, trace.get(i));
			}
			sumLevel[log] += sumLevel[log - 1];
		}

		for (long i : sumLevel) {
			sum += i;
		}
		return sum;
	}

	// 0: 1 | 1
	// 1: 1 2 | 2 3
	// 2: 1 2 2 3 | 4 5 6 7
	// 3: 1 2 2 3 2 3 3 4 | 8 9 10 11 12 13 14 15
	// 4: 1 2 2 3 2 3 3 4 2 3 3 4 3 4 4 5
}
