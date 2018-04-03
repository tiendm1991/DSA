package tiendm.algorithm.recursive;

public class TowerHN {
	public static void tower(int n, char from, char to, char tmp){
		if(n == 1){
			System.out.println("Move disc 1 from " + from + " to " + to);
			return;
		}
		tower(n-1, from, tmp, to);
		System.out.println("Move disc "+n+" from " + from + " to " + to);
		tower(n-1, tmp, to, from);
	}
	
	public static void main(String[] args) {
		tower(4, 'A', 'C', 'B');
	}
}
