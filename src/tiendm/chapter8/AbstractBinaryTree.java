package tiendm.chapter8;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E>{

	@Override
	public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
		Position<E> parent = parent(p);
		if(parent == null) return null;
		if(p == left(parent)) 
			return right(parent);
		else 
			return left(parent);
	}

	@Override
	public int numChildrens(Position<E> p) throws IllegalArgumentException {
		int count =0;
		if(left(p)!=null) count++;
		if(right(p)!=null) count++;
		return count;
	}

	@Override
	public Iterable<Position<E>> childrens(Position<E> p) throws IllegalArgumentException {
		List<Position<E>> ls = new ArrayList<>(2);
		if(left(p)!=null) ls.add(left(p));
		if(right(p)!=null) ls.add(right(p));
		return ls;
	}

	@Override
	public Iterable<Position<E>> positions(){
		return inorder();
	}
	
	public void inorderSubTree(Position<E> p, List<Position<E>> snapshot){
		if(left(p)!=null) inorderSubTree(left(p), snapshot);
		snapshot.add(p);
		if(right(p)!=null) inorderSubTree(right(p), snapshot);
	}
	
	private Iterable<Position<E>> inorder() {
		List<Position<E>> snapshot = new ArrayList<>();
		if(!isEmpty()){
			inorderSubTree(root(), snapshot);
		}
		return snapshot;
	}
	
	public static <E> int layout(BinaryTree<E> T, Position<E> p, int d, int x){
		if(T.left(p)!=null){
			x= layout(T, T.left(p), d+1, x);
		}
//		p.getElement().setX(x++);
//		p.getElement().setY(d);
		if(T.right(p)!=null){
			x= layout(T, T.right(p), d+1, x);
		}
		return x;
	}
	
}
