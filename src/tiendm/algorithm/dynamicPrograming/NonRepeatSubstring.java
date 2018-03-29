package tiendm.algorithm.dynamicPrograming;

public class NonRepeatSubstring {
	private String str;

	public NonRepeatSubstring(String str) {
		super();
		this.str = str;
	}
	
	private int longestSubstringUnique(){
		int max_length = 0;
		int NUM_CHAR = 256;
		int[] visited = new int[NUM_CHAR];
		for(int i = 0; i < NUM_CHAR; i++){
			visited[i] = -1;
		}
		visited[str.charAt(0)] = 0;
		int cur_length = 1;
		for(int i = 1; i < str.length(); i++){
			int pre_idx = visited[str.charAt(i)];
			if(pre_idx == -1 || i - cur_length > pre_idx){
				cur_length++;
			}else {
				if(cur_length > max_length){
					max_length = cur_length;
				}
				cur_length = i - pre_idx;
			}
			visited[str.charAt(i)] = i;
		}
		if(cur_length > max_length) max_length = cur_length;
		return max_length;
	}
	
	public static void main(String[] args) {
		NonRepeatSubstring ncrs = new NonRepeatSubstring("ABDEFGABEF");
		System.out.println(ncrs.longestSubstringUnique());
	}
}
