package tiendm.chapter9;

import java.util.Comparator;

import tiendm.chapter7.LinkedPositionalList;
import tiendm.chapter7.Position;

public class UnsortedPriorityQueue<K,V> extends AbstractPriorityQueue<K, V> {
	private LinkedPositionalList<Entry<K, V>> list = new LinkedPositionalList<>();
	
	public UnsortedPriorityQueue(Comparator<K> comparator) {
		super(comparator);
	}
	
	private Position<Entry<K, V>> findMin(){
		Position<Entry<K, V>> result = list.first();
		for(Position<Entry<K, V>> p : list.positions()){
			if(compare(p.getElement(), result.getElement()) < 0){
				result = p;
			}
		}
		return result;
	}
	
	@Override
	public int size() {
		return list.size();
	}

	@Override
	public Entry<K, V> min() {
		if(list.isEmpty()) return null;
		return findMin().getElement();
	}

	@Override
	public Entry<K, V> removeMin() {
		if(list.isEmpty()) return null;
		return list.remove(findMin());
	}

	@Override
	public Entry<K,V> insert(K key, V value) throws IllegalArgumentException {
		checkKey(key);
		Entry<K, V> newest = new PQEntry<>(key, value);
		list.addLast(newest);
		return newest;
	}

}
