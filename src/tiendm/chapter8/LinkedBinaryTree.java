package tiendm.chapter8;

import java.security.KeyStore.Entry;
import java.util.Iterator;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
	protected static class Node<E> implements Position<E>{
		private E element;
		private Node<E> parent;
		private Node<E> left;
		private Node<E> right;
		public Node(E element, Node<E> parent, Node<E> left, Node<E> right) {
			super();
			this.element = element;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}
		@Override
		public E getElement() throws IllegalStateException {
			return element;
		}
		public Node<E> getParent() {
			return parent;
		}
		public void setParent(Node<E> parent) {
			this.parent = parent;
		}
		public Node<E> getLeft() {
			return left;
		}
		public void setLeft(Node<E> left) {
			this.left = left;
		}
		public Node<E> getRight() {
			return right;
		}
		public void setRight(Node<E> right) {
			this.right = right;
		}
		public void setElement(E element) {
			this.element = element;
		}
	}
	protected Node<E> root = null;
	protected int size = 0;
	
	protected Node<E> createNode(E element, Node<E> parent, Node<E> left, Node<E> right){
		return new Node<E>(element, parent, left, right);
	}
	
	public LinkedBinaryTree() {
		
	}
	
	protected Node<E> validateNode(Position<E> p) throws IllegalArgumentException{
		if(!(p instanceof Node))
			throw new IllegalArgumentException("Poisition argument is not the node!");
		Node<E> node = (Node<E>) p;
		if(node.getParent() == node) throw new IllegalArgumentException("Poisition argument is invalid!");
		return node;
	}
	
	@Override
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validateNode(p);
		return node.getLeft();
	}

	@Override
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validateNode(p);
		return node.getRight();
	}

	@Override
	public Position<E> root() {
		return root;
	}

	@Override
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validateNode(p);
		return node.getParent();
	}

	@Override
	public int size() {
		return size;
	}
	
	public Position<E> addRoot(E e) throws Exception{
		if(!isEmpty()) throw new Exception("Tree is not empty!");
		root = createNode(e, null, null, null);
		size=1;
		return root;
	}
	
	public Position<E> addLeft(Position<E> p, E e) throws Exception {
		Node<E> parent = validateNode(p);
		if(parent.getLeft()!=null) throw new Exception("Node had left!");
		Node<E> left = createNode(e, parent, null, null);
		parent.setLeft(left);
		size++;
		return left;
	}
	
	public Position<E> addRight(Position<E> p, E e) throws Exception {
		Node<E> parent = validateNode(p);
		if(parent.getRight()!=null) throw new Exception("Node had left!");
		Node<E> right = createNode(e, parent, null, null);
		parent.setRight(right);
		size++;
		return right;
	}
	
	public E set(Position<E> p, E e) throws Exception {
		Node<E> node = validateNode(p);
		E old = p.getElement();
		node.setElement(e);
		return old;
	}
	
	public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws Exception{
		Node<E> node = validateNode(p);
		if(isInternal(p)) throw new Exception("Node is internal Node");
		size+= t1.size() + t2.size();
		if(!t1.isEmpty()){
			t1.root.setParent(node);
			node.setLeft(t1.root);
			t1.root = null;
			t1.size =0;
		}
		if(!t2.isEmpty()){
			t2.root.setParent(node);
			node.setRight(t2.root);
			t2.root = null;
			t2.size =0;
		}
	}
	
	public E remove(Position<E> p) throws Exception{
		if(p == null) throw new Exception("Node is null!");
		Node<E> node = validateNode(p);
		if(numChildrens(p) == 2) throw new Exception("Node has 2 childrens");
		Node<E> child = node.getLeft() != null ? node.getLeft() : node.getRight();
		if(child!=null){
			child.setParent(node.getParent());
		}
		if(node == root){
			root = child;
		}else{
			Node<E> parent = node.getParent();
			if(node == parent.getLeft()){
				parent.setLeft(child);
			}else{
				parent.setRight(child);
			}
		}
		size --;
		E remove = node.getElement();
		node.setElement(null);
		node.setParent(null);
		node.setLeft(null);
		node.setRight(null);
		return remove;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}

}
