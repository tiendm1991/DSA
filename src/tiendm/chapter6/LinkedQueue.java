package tiendm.chapter6;

import tiendm.chapter3.SingleLinkedList;

public class LinkedQueue<E> implements Queue<E> {
	private SingleLinkedList<E> list = new SingleLinkedList<>();
	
	public LinkedQueue() {
	}

	@Override
	public int size() {
		return list.getSize();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void enqueue(E e) {
		list.addLast(e);
	}

	@Override
	public E first() {
		return list.first();
	}

	@Override
	public E dequeue() {
		return list.removeFirst();
	}

}
