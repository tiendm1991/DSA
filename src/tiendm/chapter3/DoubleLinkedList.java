package tiendm.chapter3;

public class DoubleLinkedList<E> {
	private static class Node<E>{
		private E element;
		private Node<E> next;
		private Node<E> pre;
		public Node(E element, Node<E> pre, Node<E> next) {
			super();
			this.element = element;
			this.next = next;
			this.pre = pre;
		}
		public E getElement() {
			return element;
		}
		public void setElement(E element) {
			this.element = element;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setNext(Node<E> next) {
			this.next = next;
		}
		public Node<E> getPre() {
			return pre;
		}
		public void setPre(Node<E> pre) {
			this.pre = pre;
		}
	}
	
	private int size = 0;
	private Node<E> head = null;
	private Node<E> trailer = null;
	public DoubleLinkedList() {
		head = new Node<>(null, null, null);
		trailer = new Node<>(null, head, null);
		head.next = trailer;
	}
	
	public boolean isEmpty(){
		return this.size == 0;
	}
	
	public E first(){
		if(isEmpty()) return null;
		return head.getNext().getElement();
	}
	
	public E last(){
		if(isEmpty()) return null;
		return trailer.getPre().getElement();
	}
	
	public void addBetween(E element, Node<E> preNode, Node<E> currentNode){
		Node<E> newNode = new Node<>(element, preNode, currentNode);
		preNode.next = newNode;
		currentNode.pre = newNode;
		size++;
	}
	
	public E remove(Node<E> currentNode){
		Node<E> preNode = currentNode.getPre();
		Node<E> nextNode = currentNode.getNext();
		preNode.setNext(nextNode);
		nextNode.setPre(preNode);
		size--;
		return currentNode.getElement();
	}
	
	public void addFirst(E element) {
		addBetween(element, head, head.getNext());
	}
	
	public void addLast(E element) {
		addBetween(element, trailer.getPre(), trailer);
	}
	
	public void removeFirst() {
		remove(head.getNext());
	}
	
	public void removeLast() {
		remove(trailer.getPre());
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Node<E> getHead() {
		return head;
	}
	public void setHead(Node<E> head) {
		this.head = head;
	}
	public Node<E> getTrailer() {
		return trailer;
	}
	public void setTrailer(Node<E> trailer) {
		this.trailer = trailer;
	}
	
	
}
