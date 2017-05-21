package tiendm.chapter7;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedPositionalList<E> implements PositionalList<E> {
	
	private static class Node<E> implements Position<E>{
		private E element;
		private Node<E> prev;
		private Node<E> next;
		
		public Node(E element, Node<E> prev, Node<E> next) {
			this.element = element;
			this.prev = prev;
			this.next = next;
		}
		@Override
		public E getElement() throws IllegalStateException {
			return element;
		}
		public void setElement(E element) {
			this.element = element;
		}
		public Node<E> getPrev() {
			return prev;
		}
		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setNext(Node<E> next) {
			this.next = next;
		}
	}
	private int size;
	private Node<E> header;
	private Node<E> trailer;
	
	
	public LinkedPositionalList() {
		header = new Node<>(null,null,null);
		trailer = new Node<>(null, header, null);
		header.setNext(trailer);
	}

	public LinkedPositionalList(int size, Node<E> header, Node<E> trailer) {
		this.size = size;
		this.header = header;
		this.trailer = trailer;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	private Node<E> validate(Position<E> p) throws IllegalArgumentException{
		if(!(p instanceof Node)) throw new IllegalArgumentException("Invalid p");
		Node<E> node = (Node<E>) p;
		if(node.getNext() == null) throw new IllegalArgumentException("p is no longer than list");
		return node;
	}
	
	private Node<E> position(Node<E> node){
		if(node == header || node == trailer) return null;
		return node;
	}
	
	@Override
	public Position<E> first() {
		// TODO Auto-generated method stub
		return validate(header.getNext());
	}

	@Override
	public Position<E> last() {
		// TODO Auto-generated method stub
		return validate(trailer.getPrev());
	}

	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getPrev());
	}

	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getNext());
	}
	
	public Position<E> addBetween(E e, Node<E> prev, Node<E> next){
		Node<E> nodeNew = new Node<>(e, prev, next);
		prev.setNext(nodeNew);
		next.setPrev(nodeNew);
		size++;
		return nodeNew;
	}
	
	@Override
	public Position<E> addFirst(E e) {
		return addBetween(e, header, header.getNext());
	}

	@Override
	public Position<E> addLast(E e) {
		return addBetween(e, trailer.getPrev(), trailer);
	}

	@Override
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> nodeNew = validate(p);
		return addBetween(e, nodeNew.getPrev(), nodeNew);
	}

	@Override
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> nodeNew = validate(p);
		return addBetween(e, nodeNew, nodeNew.getNext());
	}

	@Override
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E old = node.getElement();
		node.setElement(e);
		return old;
	}

	@Override
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E value =  p.getElement();
		Node<E> prev = node.getPrev();
		Node<E> next = node.getNext();
		prev.setNext(next);
		next.setPrev(prev);
		size--;
		node.setElement(null);
		node.setPrev(null);
		node.setNext(null);
		return value;
	}
	
	private class PositionIterator implements Iterator<Position<E>>{
		private Position<E> cursor = first();
		private Position<E> recent = null;
		
		@Override
		public boolean hasNext() {
			return cursor != null;
		}

		@Override
		public Position<E> next() throws NoSuchElementException {
			if(cursor == null) throw new NoSuchElementException("NULL");
			recent = cursor;
			cursor = after(recent);
			return recent;
		}
		
		@Override
		public void remove() throws NoSuchElementException {
			if(cursor == null) throw new NoSuchElementException("NULL TO REMOVE");
			LinkedPositionalList.this.remove(recent);
		}
	}
	
	private class PositionIterable implements Iterable<Position<E>>{
		@Override
		public Iterator<Position<E>> iterator() {
			return new PositionIterator();
		}
	}
	
	public Iterable<Position<E>> positions(){
		return new PositionIterable();
	}
	
	private class ElementIterator implements Iterator<E>{
		Iterator<Position<E>> posIterator = new PositionIterator();
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
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Node<E> getHeader() {
		return header;
	}

	public void setHeader(Node<E> header) {
		this.header = header;
	}

	public Node<E> getTrailer() {
		return trailer;
	}

	public void setTrailer(Node<E> trailer) {
		this.trailer = trailer;
	}
}
