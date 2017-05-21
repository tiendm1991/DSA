package tiendm.algorithm.textprocessing;

import java.util.HashMap;
import java.util.Map;

public class BoyerMoorePattern {
	int matchPatternBoyerMoore(String text, String pattern){
		char[] textChar = text.toCharArray();
		char[] patternChar = pattern.toCharArray();
		int n = textChar.length;
		int m = patternChar.length;
		Map<Character, Integer> lastCharacter = new HashMap<>();
		for(Character c : textChar){
			lastCharacter.put(c, -1);
		}
		for(int i = 0; i < patternChar.length; i++){
			lastCharacter.put(patternChar[i], i);
		}
		int i = m-1;
		int k = m-1;
		while (i < n) {
			if(textChar[i] == patternChar[k]){
				if(k == 0) return i;
				i--;
				k--;
			}else {
				i += m - Math.min(k, 1+lastCharacter.get(textChar[i]));
				k = m-1;
			}
		}
		return -1;
	}
}	
