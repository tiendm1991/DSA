package tiendm.chapter3;

import javax.lang.model.element.Element;

public class SingleLinkedList<E> {
	private static class Node<E>{
		private E element;
		private Node<E> next;
		public Node(E element, Node<E> next) {
			super();
			this.element = element;
			this.next = next;
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
	}
	
	private int size = 0;
	private Node<E> head = null;
	private Node<E> tail = null;
	
	public SingleLinkedList() {
		
	}
	
	public boolean isEmpty(){
		return this.size == 0;
	}
	
	public E first() {
		if (isEmpty()) {
			return null;
		}
		return head.getElement();
	}
	
	public E last() {
		if (isEmpty()) {
			return null;
		}
		return tail.getElement();
	}
	
	public void addFirst(E newNode){
		head = new Node<>(newNode, head);
		if(size==0) tail = head;
		size++;
	}
	
	public void addLast(E element){
		Node<E> newNode = new Node<>(element, null); 
		if(isEmpty()) head = newNode;
		else tail.next = newNode;
		tail = newNode;
		size++;
	}
	
	public E removeFirst(){
		if(isEmpty()) return null;
		E result = head.getElement();
		head = head.getNext();
		size--;
		if(size==0) tail = null;
		return result;
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
	public Node<E> getTail() {
		return tail;
	}
	public void setTail(Node<E> tail) {
		this.tail = tail;
	}
	
	

	@Override
	public String toString() {
		if(isEmpty()) return "null cmnr";
		String result = "";
		Node<E> current = head;
		do{
			result += current.getElement() + "\n";
			current = current.getNext();
		}while(current !=null);
		return result;
	}

	public static void main(String[] args) {
		SingleLinkedList<Student> singleLinkedList = new SingleLinkedList<>();
		System.out.println("1: \n"+singleLinkedList);
		singleLinkedList.addFirst(new Student("1", "1"));
		System.out.println("2: \n"+singleLinkedList);
		singleLinkedList.addFirst(new Student("2", "2"));
		System.out.println("3: \n"+singleLinkedList);
		singleLinkedList.addLast(new Student("3", "3"));
		System.out.println("4: \n"+singleLinkedList);
		singleLinkedList.removeFirst();
		System.out.println("5: \n"+singleLinkedList);
	}
}
