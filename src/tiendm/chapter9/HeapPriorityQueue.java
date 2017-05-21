package tiendm.chapter9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import tiendm.chapter7.PositionalList;

public class HeapPriorityQueue<K,V> extends AbstractPriorityQueue<K, V> {
	List<Entry<K, V>> heap ;
	
	public HeapPriorityQueue() {
		super();
		heap = new ArrayList<>();
	}

	public HeapPriorityQueue(Comparator<K> comparator) {
		super(comparator);
		heap = new ArrayList<>();
	}
	
	public HeapPriorityQueue(K[] keys, V[] values) {
		super();
		for(int i=0; i<Math.min(keys.length, values.length);i++){
			heap.add(new PQEntry<>(keys[i], values[i]));
			heapify();
		}
	}

	protected void heapify() {
		int startIndex = parent(size()-1);
		for(int i=startIndex; i>=0;i--){
			downHeap(i);
		}
	}
	
	public static <E> void pqSort(PositionalList<E> P, PriorityQueue<E, ?> Q){
		int size = P.size();
		for(int i=0;i<size;i++){
			E element = P.remove(P.first());
			Q.insert(element, null);
		}
		for(int i=0;i<size;i++){
			E min = Q.removeMin().getKey();
			P.addLast(min);
		}
	}
	
	protected int parent(int j){
		return (j-1)/2;
	}
	
	protected int left(int j){
		return 2*j+1;
	}
	
	protected int right(int j){
		return 2*j+2;
	}
	
	protected boolean hasLeft(int j){
		return left(j) < heap.size();
	}
	
	protected boolean hasRight(int j){
		return right(j) < heap.size();
	}
	
	protected void swap(int x, int y){
		Entry<K, V> temp = heap.get(x);
		heap.set(x, heap.get(y));
		heap.set(y, temp);
	}
	
	protected void upHeap(int j){
		if(j==0) return;
		if(compare(heap.get(j), heap.get(parent(j))) <0 ) {
			swap(j, parent(j));
			upHeap(parent(j));
		}else {
			return ;
		}
	}
	
	protected void downHeap(int j) {
		if(j>=size()) return;
		int leftIndex =0;
		int minIndex =0;
		if(hasLeft(j)){
			leftIndex = left(j);
			minIndex = leftIndex;
		}
		if (hasRight(j)) {
			minIndex = compare(heap.get(left(j)), heap.get(right(j))) < 0 ? leftIndex : right(j);;
		}
		if(compare(heap.get(j), heap.get(minIndex))>0){
			swap(j, minIndex);
			downHeap(minIndex);
		}else {
			return;
		}
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return heap.size();
	}

	@Override
	public Entry<K, V> min() {
		if(isEmpty()) return null;
		return heap.get(0);
	}

	@Override
	public Entry<K, V> removeMin() {
		if(isEmpty()) return null;
		Entry<K, V> min = heap.get(0);
		Entry<K, V> last = heap.get(size()-1);
		heap.set(0, last);
		heap.remove(size()-1);
		downHeap(0);
		return min;
		
	}

	@Override
	public Entry<K, V> insert(K key, V value) {
		if(isEmpty()) return null;
		Entry<K, V> itemNew = new PQEntry<>(key, value);
		heap.add(itemNew);
		upHeap(size()-1);
		return itemNew;
	}

}
