package tiendm.chapter11;

import java.util.Map.Entry;

import tiendm.chapter8.LinkedBinaryTree;
import tiendm.chapter8.Position;

public class BalanceableBinaryTree<K,V> extends LinkedBinaryTree<Entry<K, V>> {
	protected static class BSTNote<E> extends Node<E>{
		int aux = 0;
		public BSTNote(E element, tiendm.chapter8.LinkedBinaryTree.Node<E> parent,
				tiendm.chapter8.LinkedBinaryTree.Node<E> left, tiendm.chapter8.LinkedBinaryTree.Node<E> right) {
			super(element, parent, left, right);
		}
		public int getAux() {
			return aux;
		}
		public void setAux(int aux) {
			this.aux = aux;
		}
	}
	public int getAux(Position<Entry<K, V>> p){
		return ((BSTNote<Entry<K, V>>) p).getAux();
	}
	public void getAux(Position<Entry<K, V>> p, int value){
		((BSTNote<Entry<K, V>>) p).setAux(value);
	}
	
	protected Node<Entry<K, V>> createNote(Entry<K, V> e, Node<Entry<K, V>> parent,
											Node<Entry<K, V>> left, Node<Entry<K, V>> right) {
		return new BSTNote(e, parent, left, right);
	}
	
	private void relink(Node<Entry<K, V>> parent, Node<Entry<K, V>> child, boolean makeLeftChild){
		child.setParent(parent);
		if(makeLeftChild){
			parent.setLeft(child);
		}else {
			parent.setRight(child);
		}
	}
	
	public void rotate(Position<Entry<K, V>> p){
		Node<Entry<K, V>> x = validateNode(p);
		Node<Entry<K, V>> y = x.getParent();
		Node<Entry<K, V>> z = y.getParent();
		if(z == null){
			root = x;
			x.setParent(null);
		}else {
			relink(z, x, y == z.getLeft());
			if(x == y.getLeft()){
				relink(y, x.getRight(), true);
				relink(x, y, false);
			}else {
				relink(y, x.getLeft(), false);
				relink(x, y, true);
			}
		}
	}
	
	public Position<Entry<K, V>> restructure(Position<Entry<K, V>> p){
		Node<Entry<K, V>> x = validateNode(p);
		Node<Entry<K, V>> y = x.getParent();
		Node<Entry<K, V>> z = y.getParent();
		if((x == right(y)) == (y==right(z))){
			rotate(y);
			return y;
		}else {
			rotate(x);
			rotate(x);
			return x;
		}
	}
}
