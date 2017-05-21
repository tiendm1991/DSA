package tiendm.chapter10;

import java.util.ArrayList;

import tiendm.chapter9.Entry;

public class ChainHashMap<K,V> extends AbstractHashMap<K, V> {
	private UnsortedMap<K,V>[] table;
	
	public ChainHashMap(int capacity, int prime) {
		super(capacity, prime);
	}

	public ChainHashMap(int capacity) {
		super(capacity);
	}
	
	public ChainHashMap() {
		super();
	}

	@Override
	protected void createTable() {
		table = new UnsortedMap[capacity];
	}

	@Override
	protected V bucketGet(int h, K k) {
		UnsortedMap<K, V> bucket = table[h];
		if(bucket == null) return null;
		return table[h].get(k);
	}

	@Override
	protected V bucketPut(int h, K k, V v) {
		UnsortedMap<K, V> bucket = table[h];
		if(bucket == null) bucket = table[h] = new UnsortedMap<>();
		int oldSize = bucket.size();
		V result = bucket.put(k, v);
		n+= (bucket.size() -oldSize);
		return result;
	}

	@Override
	protected V bucketRemove(int h, K k) {
		UnsortedMap<K, V> bucket = table[h];
		if(bucket == null) bucket = table[h] = new UnsortedMap<>();
		int oldSize = bucket.size();
		V result = bucket.remove(k);
		n+= (bucket.size() -oldSize);
		return result;
	}
	
	public Iterable<Entry<K, V>> rentrySet(){
		ArrayList<Entry<K, V>> buffer = new ArrayList<>();
		for(int i=0;i<capacity;i++){
			if(table[i]!=null){
				for(Entry<K, V> entry : table[i].entrySet()){
					buffer.add(entry);
				}
			}
		}
		return buffer;
	}

	public UnsortedMap<K, V>[] getTable() {
		return table;
	}

	public void setTable(UnsortedMap<K, V>[] table) {
		this.table = table;
	}
	
}
