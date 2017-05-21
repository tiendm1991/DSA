package tiendm.chapter3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Student {
	private String id;
	private String name;
	public Student(String id, String name) {
		this.id = id;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}
	public static void main(String[] args) {
		List<String> lsString = new ArrayList<>();
		lsString.add("0");
		lsString.add("1");
		lsString.add("2");
		lsString.add("3");
		lsString.add("4");
		lsString.add("5");
		int j=1;
		System.out.println("element["+j+"] = "+lsString.get(j));
		System.out.println("element["+(j++)+"] = "+lsString.get(j++));
		System.out.println("element["+j+"] = "+lsString.get(j));
		
		List<String> ls2 = new ArrayList<>();
		ls2.add("xxx");
		Collections.copy(lsString, ls2);
		System.out.println(lsString);
		
	}
}
