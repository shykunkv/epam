package queue;

import java.util.Arrays;
import java.util.Comparator;

public class MyPriorityQueue<E> {
	
	private static final int DEFAULT_CAPACITY = 11;
	
	private Object[] queue;
	private int size = 0;
	
	private final Comparator<? super E> comparator;
	
	public MyPriorityQueue() throws Exception {
		this(DEFAULT_CAPACITY, null);	
	}
	
	
	public MyPriorityQueue(int capacity) throws Exception {
		this(capacity, null);
	}
	
	
	public MyPriorityQueue(int capacity, Comparator<? super E> comparator) throws Exception {
		if (capacity < 1) throw new Exception("Invalid arguents");
		this.queue = new Object[capacity];
		this.comparator = comparator;
	}
	
	
	private void grow(int minCapacity) {
		int oldCapacity = queue.length;
		
		int newCapacity = ((oldCapacity < 64) ? ((oldCapacity + 1) * 2): ((oldCapacity / 2) * 3));
		if (newCapacity < 0) {
			newCapacity = Integer.MAX_VALUE;
		}
		if (newCapacity < minCapacity) {
			newCapacity = minCapacity;
		}
		
		queue = Arrays.copyOf(queue, newCapacity);
	}
	
	public boolean add(E e) {
		if (e == null) throw new NullPointerException();
		
		int i = size;
		if (i >= queue.length) grow(i + 1);
		
		size = i + 1;
		
		if (i == 0) queue[0] = e; 
		else siftUp(i, e);
		
		return true;
	}
	
	private void siftUp(int k, E x) {
		if (comparator != null) {
			siftUpUsingComparator(k, x);
		} else {
			siftUpComparable(k, x);
		}
	}
	
	
	private void siftUpUsingComparator(int k, E x) {
		while (k > 0) {
			int parent = (k - 1) >>> 1;
			Object e = queue[parent];
			if (comparator.compare(x, (E) e) >= 0) break;
			queue[k] = e;
			k = parent;
		}
		queue[k] = x;
	}

	private void siftUpComparable(int k, E x) {
		Comparable<? super E> key = (Comparable<? super E>) x;
		while (k > 0) {
			int parent = (k-1) >>> 1;
			Object e = queue[parent];
			
			if (key.compareTo((E) e) >= 0) break;
			
			queue[k] = e;
			k = parent;
		}
		queue[k] = key;
	}
	
	
	private int indexOf(Object o) {
		if (o != null) {
			for (int i = 0; i < size; i++) {
				if (o.equals(queue[i])) return i;
			}
		}
		return -1;
	}
	
	public boolean remove(Object o) {
		int i = indexOf(o);
		if (i == -1)
			return false;
		else {
			removeAt(i);
			return true;
		}
	}
	
	
	public E removeAt(int i) {
		assert i >= 0 && i < size;
		int s = --size;
		
		if (s == i) {
			queue[i] = null;
		} else {
			E moved = (E) queue[s];
			queue[s] = null;
			siftDown(i, moved);
			if (queue[i] == moved) {
				siftUp(i, moved);
				if (queue[i] != moved)
					return moved;
			}
		}
		return null;
	}
	
	
	private void siftDown(int k, E x) {
		if (comparator != null) 
			siftDownUsingComparator(k, x);
		else 
			siftDownCamparable(k, x);
	}


	private void siftDownCamparable(int k, E x) {
		Comparable<? super E> key = (Comparable<? super E>) x;
		int half = size >>> 1;
		
		while (k < half) {
			int child = (k << 1) + 1;
			Object c = queue[child];
			int right = child + 1;
			if (right < size &&  ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0) 
				c = queue[child = right];
			if (key.compareTo((E) c) <= 0) {
				break;
			}
			queue[k] = c;
			k = child;
		}
		queue[k] = key;
	}


	private void siftDownUsingComparator(int k, E x) {
		int half = size >>> 1;
		while (k < half) {
			int child = (k << 1) + 1;
			Object c = queue[child];
			int right = child + 1;
			if (right < size &&  comparator.compare((E) c, (E) queue[right]) > 0) 
				c = queue[child = right];
			if (comparator.compare(x, (E) c) <= 0) {
				break;
			}
			queue[k] = c;
			k = child;
		}
		queue[k] = x;
	}
	
	
	public E poll() {
		if (size == 0)
			return null;
		
		int s = --size;
		E result = (E) queue[0];
		E x = (E) queue[s];
		queue[s] = null;
		
		if (s != 0) {
			siftDown(0, x);
		}
		return result;
	}
}
