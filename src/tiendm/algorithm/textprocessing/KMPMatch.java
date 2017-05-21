package tiendm.algorithm.textprocessing;

public class KMPMatch {
	
	static int[] preComputing(char[] pattern){
		int m = pattern.length;
		int j = 1, k =0;
		int [] fail = new int[m];
		while (j < m) {
			if(pattern[j] == pattern[k]){
				fail[j] = k+1;
				j++;
				k++;
			}else if (k > 0) {
				k = fail[k-1];
			}else {
				j++;
			}
		}
		return fail;
	}
	
	static int kmpMatched(String strText, String strPattern){
		char[] text = strText.toCharArray();
		char[] pattern = strPattern.toCharArray();
		int n = text.length, m = pattern.length;
		int j = 0, k = 0;
		int[] fail = preComputing(pattern);
		while (j < n) {
			if(text[j] == pattern[k]){
				if(k == m-1) return j-m+1;
				j++;
				k++;
			}else if (k > 0) {
				k = fail[k-1];
			}else {
				j++;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		char[] pattern = {'a','m','a','l','g','a','m','a','m','i','o','n'};
		System.out.println(preComputing(pattern));
	}	
}
