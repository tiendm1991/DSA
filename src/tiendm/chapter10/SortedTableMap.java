package tiendm.chapter10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import tiendm.chapter9.Entry;

public class SortedTableMap<K,V> extends AbstractSortedMap<K, V>{
	private List<Entry<K, V>> table = new ArrayList<>();
	
	public SortedTableMap() {
		super();
	}
	public SortedTableMap(Comparator<K> comp) {
		super(comp);
	}

	@Override
	public V get(K key) {
		int keySearch = findIndex(key);
		if(keySearch >= size() || compare(key, table.get(keySearch).getKey()) !=0) return null;
		return table.get(findIndex(key)).getValue();
	}

	@Override
	public V put(K key, V value) {
		int keySearch = findIndex(key);
		Entry<K, V> newEntry = new MapEntry<>(key, value);
		if(keySearch<size() && compare(key, table.get(keySearch).getKey()) ==0) 
			return table.get(keySearch).getValue();
		else 
			table.add(keySearch, newEntry);
		return null;
	}

	@Override
	public V remove(K key) {
		int keySearch = findIndex(key);
		if(keySearch >= size() || compare(key, table.get(keySearch).getKey()) !=0) return null;
		return table.remove(keySearch).getValue();
	}
	
	private int findIndex(K key) {
		return findIndex(key,0,size()-1);
	}
	
	private int findIndex(K key, int low, int high){
		if(high <low) return high+1;
		int mid = (low + high/2);
		K keyMid = table.get(mid).getKey();
		if(key.equals(keyMid)) return mid;
		if(compare(key,keyMid) < 0) return findIndex(key, low, mid);
		else return findIndex(key, mid+1, high);
	}
	
	private Entry<K, V> safeEntry(int j){
		if(j <0 || j>=size()) return null;
		return table.get(j);
	}
	
	@Override
	public int getSize() {
		return table.size();
	}

	@Override
	protected Entry<K, V> firstEntry() {
		return table.get(0);
	}

	@Override
	protected Entry<K, V> lastEntry() {
		// TODO Auto-generated method stub
		return table.get(size()-1);
	}

	@Override
	protected Entry<K, V> floorEntry(K k) {
		int keySearch = findIndex(k);
		if(keySearch > size()) return null;
		if(compare(k, table.get(keySearch).getKey()) == 0) return  table.get(keySearch);
		return safeEntry(keySearch-1);
	}

	@Override
	protected Entry<K, V> ceilingEntry(K k) {
		return  safeEntry(findIndex(k));
	}

	@Override
	protected Entry<K, V> lowerEntry(K k) {
		return safeEntry(findIndex(k)-1);
	}

	@Override
	protected Entry<K, V> higherEntry(K k) {
		int keySearch = findIndex(k);
		if(keySearch < size() && k.equals(table.get(keySearch))) keySearch++;
		return safeEntry(keySearch);
	}

	@Override
	protected Iterable<Entry<K, V>> subMap(K k1, K k2) {
		ArrayList<Entry<K, V>> buffer = new ArrayList<>();
		int low = findIndex(k1);
		int high = findIndex(k2);
		for(int i=low; i<high;i++){
			buffer.add(table.get(i));
		}
		return buffer;
	}
	
	@Override
	public Iterable<Entry<K, V>> entrySet(){
		return subMap(table.get(0).getKey(), table.get(size()-1).getKey());
	}

}
