package tiendm.chapter10;

import java.util.Iterator;

import tiendm.chapter9.Entry;

public abstract class AbstractMap<K, V> implements Map<K, V> {
	private int size;
	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
	
	protected static class MapEntry<K, V> implements Entry<K, V>{
		private K key;
		private V value;
		
		public MapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return this.key;
		}

		@Override
		public V getValue() {
			return this.value;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V setValue(V value) {
			V old = value;
			this.value = value;
			return old;
		}
	}
	
	private class KeyIterator implements Iterator<K>{
		private Iterator<Entry<K, V>> entries = entrySet().iterator();
		@Override
		public boolean hasNext() {
			return entries.hasNext();
		}

		@Override
		public K next() {
			return entries.next().getKey();
		}
		
		public void remove(){
			throw new UnsupportedOperationException("Cannot remove key");
		}
	}
	
	private class KeyIterable implements Iterable<K>{
		@Override
		public Iterator<K> iterator() {
			return new KeyIterator();
		}
	}
	
	public Iterable<K> keySet(){
		return new KeyIterable();
	}
	
	private class ValueIterator implements Iterator<V>{
		private Iterator<Entry<K, V>> entries = entrySet().iterator();
		@Override
		public boolean hasNext() {
			return entries.hasNext();
		}

		@Override
		public V next() {
			return entries.next().getValue();
		}
		
		public void remove(){
			throw new UnsupportedOperationException("Cannot remove key");
		}
	}
	
	private class ValueIterable implements Iterable<V>{
		@Override
		public Iterator<V> iterator() {
			return new ValueIterator();
		}
	}
	
	public Iterable<V> values(){
		return new ValueIterable();
	}

	
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
