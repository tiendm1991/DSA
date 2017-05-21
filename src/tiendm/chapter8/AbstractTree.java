package tiendm.chapter8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tiendm.chapter6.LinkedQueue;
import tiendm.chapter6.Queue;

public abstract class AbstractTree<E> implements Tree<E> {
	@Override
	public boolean isInternal(Position<E> p){
		return numChildrens(p) > 0;
	}
	
	@Override
	public boolean isExternal(Position<E> p){
		return numChildrens(p) == 0;
	}
	
	@Override
	public boolean isRoot(Position<E> p){
		return p == root();
	}
	
	@Override
	public boolean isEmpty(){
		return size() ==0;
	}
	
	public int dept(Position<E> p){
		if(isRoot(p))
			return 0;
		else
			return 1+dept(parent(p));
	}
	
	protected int heightBad(){
		int h = 0 ;
		for(Position<E> p : positions()){
			if(isExternal(p)) {
				int dept = dept(p);
				if(dept > h) h= dept;
			}
		}
		return h;
	}
	
	protected int height(Position<E> p ){
		if(isExternal(p)) return 0;
		int h=0;
		int heightLocal = 0;
		for(Position<E> child : childrens(p)){
			heightLocal = 1+height(child);
			if(heightLocal>h) h = heightLocal;
		}
		return h;
	}
	
	private class ElementIterator implements Iterator<E>{
		Iterator<Position<E>> posIterator = positions().iterator();
		@Override
		public boolean hasNext() {
			return posIterator.hasNext();
		}

		@Override
		public E next() {
			return posIterator.next().getElement();
		}
		
		public void remove(){
			posIterator.remove();
		}
	}
	
	public Iterator<E> iterator(){
		return new ElementIterator();
	}
	
	public Iterable<Position<E>> positions(){
		return preorder();
	}
	
	private void preorderSubtree(Position<E> p, List<Position<E>> snapshot){
		snapshot.add(p);
		for(Position<E> c : childrens(p)){
			preorderSubtree(c, snapshot);
		}
	}

	private Iterable<Position<E>> preorder() {
		List<Position<E>> snapshot = new ArrayList<>(); 
		if(!isEmpty()){
			preorderSubtree(root(), snapshot);
		}
		return snapshot;
	}
	
	private void postorderSubtree(Position<E> p, List<Position<E>> snapshot){
		for(Position<E> c : childrens(p)){
			postorderSubtree(c, snapshot);
		}
		snapshot.add(p);
	}

	private Iterable<Position<E>> postorder() {
		List<Position<E>> snapshot = new ArrayList<>(); 
		if(!isEmpty()){
			postorderSubtree(root(), snapshot);
		}
		return snapshot;
	}
	
	public Iterable<Position<E>> breadthFirst(){
		List<Position<E>> snapshot = new ArrayList<>();
		if(!isEmpty()){
			Queue<Position<E>> fringe = new LinkedQueue<>();
			fringe.enqueue(root());
			while (!fringe.isEmpty()) {
				Position<E> p = fringe.dequeue();
				snapshot.add(p);
				for(Position<E> c : childrens(p)){
					fringe.enqueue(p);
				}
			}
		}
		return snapshot;
	}
	
	public void printPreOrderIndent(Tree<E> T, Position<E> p, int d){
		System.out.println(spaces(2*d) + p.getElement());
		for(Position<E> c : childrens(p)){
			printPreOrderIndent(T, c, d+1);
		}
	}
	
	public void printPreOrderLabel(Tree<E> T, Position<E> p , ArrayList<Integer> path){
		int d= path.size();
		System.out.println(spaces(2*d));
		for(int i=0;i<d;i++) System.out.print(path.get(i) + (i==d-1 ? " " : "."));
		System.out.print(p.getElement());
		path.add(1);
		for(Position<E> c : childrens(p)){
			printPreOrderLabel(T, c, path);
			path.set(d, 1+path.get(d));
		}
		path.remove(d);
	}

	private String spaces(int n) {
		StringBuilder s = new StringBuilder("");
		for(int i=0;i<n;i++){
			s.append(" ");
		}
		return s.toString();
	}
	
	public int dispaces(Tree<Integer> T, Position<Integer> p){
		int totalSpace = p.getElement();
		for(Position<Integer> c : T.childrens(p)){
			totalSpace += dispaces(T, c);
		}
		return totalSpace;
	}
	
	public static <E> void parenthesize(Tree<E> T, Position<E> p){
		System.out.println(p.getElement());
		if(T.isInternal(p)){
			boolean first = true;
			for(Position<E> c : T.childrens(p)){
				System.out.println(first ? " (" : ", ");
				first = false;
				parenthesize(T, c);
			}
			System.out.println(")");
		}
	}
	
}
