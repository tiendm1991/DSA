package tiendm.chapter10;

import java.util.HashSet;
import java.util.Set;

public class Test {
	private String s ;
	
	public Test(String s) {
		this.s = s;
	}
	public static void main(String[] args) {
		Test t1 = new Test("t");
		Test t2 = new Test("t");
		System.out.println(t1.equals(t2));
		Set<Test> set = new HashSet<>();
		set.add(t1);
		set.add(t2);
		System.out.println(set);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((s == null) ? 0 : s.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Test other = (Test) obj;
		if (s == null) {
			if (other.s != null)
				return false;
		} else if (!s.equals(other.s))
			return false;
		return true;
	}



	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}
	@Override
	public String toString() {
		return "Test [s=" + s + "]";
	}
	
}
