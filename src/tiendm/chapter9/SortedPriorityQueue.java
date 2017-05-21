package tiendm.chapter9;

import java.util.Comparator;

import tiendm.chapter7.LinkedPositionalList;
import tiendm.chapter7.Position;

public class SortedPriorityQueue<K,V> extends AbstractPriorityQueue<K, V> {
	private LinkedPositionalList<Entry<K, V>> list = new LinkedPositionalList<>();
	
	public SortedPriorityQueue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SortedPriorityQueue(Comparator<K> comparator) {
		super(comparator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Entry<K, V> min() {
		if(list.isEmpty()) return null;
		return list.first().getElement();
	}

	@Override
	public Entry<K, V> removeMin() {
		if(list.isEmpty()) return null;
		return list.remove(list.first());
	}

	@Override
	public Entry<K, V> insert(K key, V value) {
//		Position<Entry<K, V>> current = list.last();
//		Entry<K, V> newest = new PQEntry<>(key, value);
//		while (current!=null && compare(current.getElement(), newest) >=0) {
//			current = list.after(current);
//		}
//		if(current == null) list.addFirst(newest);
//		else list.addAfter(current, newest);
//		return newest;
		Entry<K, V> newest = new PQEntry<>(key, value);
		for(Position<Entry<K, V>> p : list.positions()){
			if(compare(newest, p.getElement()) < 0)
				list.addAfter(p, newest);
				return newest;
		}
		list.addLast(newest);
		return newest;
	}

}
