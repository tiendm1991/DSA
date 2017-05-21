package tiendm.chapter10;

import java.util.Comparator;

import tiendm.chapter9.DefaultComparator;
import tiendm.chapter9.Entry;

public abstract class AbstractSortedMap <K,V> extends AbstractMap<K, V> {
	protected abstract Entry<K, V> firstEntry();
	protected abstract Entry<K, V> lastEntry();
	protected abstract Entry<K, V> floorEntry(K k);
	protected abstract Entry<K, V> ceilingEntry(K k);
	protected abstract Entry<K, V> lowerEntry(K k);
	protected abstract Entry<K, V> higherEntry(K k);
	protected abstract Iterable<Entry<K, V>> subMap(K k1, K k2);
	private Comparator<K> comparator;
	
	public AbstractSortedMap() {
		this(new DefaultComparator<>());
	}
	
	public AbstractSortedMap(Comparator<K> comparator) {
		super();
		this.comparator = comparator;
	}
	
	protected int compare(K a, K b){
		return comparator.compare(a, b);
	}
	public Comparator<K> getComparator() {
		return comparator;
	}
	public void setComparator(Comparator<K> comparator) {
		this.comparator = comparator;
	}
	
}
