package tiendm.chapter10;

import java.util.ArrayList;
import java.util.Iterator;

import tiendm.chapter9.Entry;

public class UnsortedMap<K,V> extends AbstractMap<K, V> {
	ArrayList<Entry<K, V>> list = new ArrayList<>();
	
	@Override
	public V get(K key) {
		return list.get(findIndex(key)).getValue();
	}

	@Override
	public V put(K key, V value) {
		boolean isExist = findIndex(key) > -1;
		if(!isExist){
			Entry<K, V> elementNew = new MapEntry<>(key, value);
			list.add(elementNew);
			return value;
		}
		return null;
	}

	@Override
	public V remove(K key) {
		int i = findIndex(key);
		if(i>-1){
			return list.remove(i).getValue();
		}
		return null;
	}
	
	private int findIndex(K key){
		for(int i=0;i<getSize();i++){
			if(list.get(i).getKey().equals(key)) return i;
		}
		return -1;
	}
	
	private class EntryIterator implements Iterator<Entry<K, V>>{
		private int j=0;
		
		@Override
		public boolean hasNext() {
			return j<=getSize();
		}

		@Override
		public Entry<K, V> next() {
			Entry<K, V> e = list.get(j);
			j++;
			return e;
		}
		
		public void remove() { throw new UnsupportedOperationException(); }
	}
	
	private class EntryIterable implements Iterable<Entry<K, V>>{

		@Override
		public Iterator<Entry<K, V>> iterator() {
			return new EntryIterator();
		}
	}
	
	public Iterable<Entry<K, V>> entrySet(){
		return new EntryIterable();
	}

	@Override
	public int getSize() {
		return list.size();
	}
	
	

}
