package tiendm.chapter9;

import java.util.Comparator;

public abstract class AbstractPriorityQueue<K,V> implements PriorityQueue<K, V>{
	protected static class PQEntry<K,V> implements Entry<K, V>{
		private K key;
		private V value;
		public PQEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		@Override
		public K getKey() {
			return key;
		}
		@Override
		public V getValue() {
			return value;
		}
		public void setKey(K key) {
			this.key = key;
		}
		public void setValue(V value) {
			this.value = value;
		}
	}
	private Comparator<K> comparator;
	public AbstractPriorityQueue(Comparator<K> comparator) {
		this.comparator = comparator;
	}
	public AbstractPriorityQueue() {
		this(new DefaultComparator<K>());
	}
	protected int compare(Entry<K, V> a, Entry<K, V> b){
		return comparator.compare(a.getKey(), b.getKey());
	}
	protected boolean checkKey(K key){
		return comparator.compare(key,key) == 0;
	}
	
	@Override
	public boolean isEmpty() {
		return size() <= 0;
	}
	
}
