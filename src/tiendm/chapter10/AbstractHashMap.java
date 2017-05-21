package tiendm.chapter10;

import java.util.ArrayList;
import java.util.Random;

import tiendm.chapter9.Entry;

public abstract class AbstractHashMap<K,V> extends AbstractMap<K, V>{
	protected int n=0;
	protected int capacity ;
	protected int prime;
	private long shift,scale;
	
	protected abstract void createTable();
	protected abstract V bucketGet(int h, K k);
	protected abstract V bucketPut(int h, K k, V v);
	protected abstract V bucketRemove(int h, K k);
	
	public AbstractHashMap(int capacity, int prime) {
		super();
		this.capacity = capacity;
		this.prime = prime;
		Random rand = new Random();
		scale = rand.nextInt(prime-1)+1;
		shift = rand.nextInt(prime);
		createTable();
	}
	
	public AbstractHashMap(int capacity) {
		this(capacity, 109345121);
	}

	public AbstractHashMap() {
		this(17);
	}
	
	@Override
	public V get(K key) {
		return bucketGet(hashFuntion(key), key);
	}
	@Override
	public V put(K key, V value) {
		V answer =  bucketPut(hashFuntion(key), key, value);
		if(n>capacity/2){
			resize(2*capacity-1);
		}
		return answer;
	}
	
	@Override
	public V remove(K key) {
		return bucketRemove(hashFuntion(key), key);
	}
	
	private int hashFuntion(K k){
		return (int) ((Math.abs(k.hashCode()*scale + shift)%prime)%capacity);
	}
	
	private void resize(int newCap) {
		ArrayList<Entry<K, V>> buffer = new ArrayList<>(n);
		for (Entry<K, V> entry : entrySet()) {
			buffer.add(entry);
		}
		capacity = newCap;
		createTable();
		n=0;
		for (Entry<K, V> entry : buffer) {
			put(entry.getKey(), entry.getValue());
		}
	}
	
	public int size(){return this.n;}

	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getPrime() {
		return prime;
	}
	public void setPrime(int prime) {
		this.prime = prime;
	}
	public long getShift() {
		return shift;
	}
	public void setShift(long shift) {
		this.shift = shift;
	}
	public long getScale() {
		return scale;
	}
	public void setScale(long scale) {
		this.scale = scale;
	}
	
}
