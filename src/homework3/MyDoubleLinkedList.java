package homework3;


public class MyDoubleLinkedList<E> {
	
	@SuppressWarnings("hiding")
	class ListNode<E> implements Comparable<E> {
	     
	    private E value;
	    private ListNode<E> next;
	    private ListNode<E> prev;
	     
	    public ListNode() {};
	    
	    
	    public E getValue() {
	    	return value;
	    }
	    
	    public ListNode(E value, ListNode<E> next, ListNode<E> prev) {
	    	this.value = value;
	    	this.next = next;
	    	this.prev = prev;
	    }
	    
	    @Override
	    public int compareTo(E arg) {
	        if(arg == this.value){
	            return 0;
	        } else {
	            return 1;
	        }
	    }
	}
	
	
    private ListNode<E> head;
    private int size;
    
    MyDoubleLinkedList() {
    	this.head = null;
    	this.size = 0;
    }
    
    
   public void addLast(int value) {
        if (size == 0) {
            head = new ListNode(value, null, null);
            head.next = head.prev = head;
        } else {
            ListNode<E> prevLast = head.prev;
            ListNode<E> newLast = new ListNode(value, prevLast, head);
            prevLast.next = newLast;
            head.prev = newLast;
        }
        size++;
    }

    public void addFirst(int value) {
        addLast(value);
        head = head.prev;
    }

    public E removeFirst() {
        if (size == 0) return null;

        E result = head.getValue();
        if (size == 1) {
            head = null;
        } else {
            head.prev.next = head.next;
            head.next.prev = head.prev;
            head = head.next;
        }
        size--;

        return result;
    }

    public E removeLast() {
        if (size == 0) return null;

        head = head.prev;
        return removeFirst();
    }

    public E remove(int index){
        if (index < 0 || index >= size) return null;

        if (index == 0) return removeFirst();
        if (index == size - 1) return removeLast();

        ListNode<E> cur = head;
        if (index <= (size >> 1)) {
            for (int i = 0; i < index; i++)
                cur = cur.next;
        } else {
            for (int i = size; i > index; i--)
                cur = cur.prev;
        }

        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        size--;

        return cur.getValue();
    }

    public E getFirst() {
        if (size == 0) return null;
        return head.getValue();
    }

    public E getLast(){
        if (size == 0) return null;
        return head.prev.getValue();
    }

    public int getSize() {
        return size;
    }

   
}
 

