package homework3;


public class MyLinkedList<E> {
	
	class ListNode<E> implements Comparable<E> {
	     
	    private E value;
	    private ListNode<E> next;
	     
	    ListNode() {}
	    
	    ListNode(E value) {
	    	this.value = value;
	    }
	    
	    public E getValue() {
	        return value;
	    }
	    
	    public void setValue(E value) {
	        this.value = value;
	    }
	    
	    public boolean hasNext() {
	    	if (this.next == null) return false;
	    	return true;
	    }
	    
	    public ListNode<E> next() {
	        return next;
	    }
	    
	    public void setNext(ListNode<E> next) {
	        this.next = next;
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
    private ListNode<E> tail;
    private int size;
    
    MyLinkedList() {
    	this.size = 0;
    }
      
    
    /**
     * Add new node to the end of list
     * 
     * @param	value	the value of new node  
     */
    public void add(E value){
         
        ListNode<E> newNode = new ListNode<E>(value);
        
        // if list is empty
        if(size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        
        size++;
    }
    
    
    /**
     * Add new node to the list on some position
     * 
     * @param	index 	position to add in list
     * @param	value 	the value of new node
     * */
    public void add(int index, E value) {
        //invalid index
    	if (index < 0 || index > size) return; 
        
        
        ListNode<E> temp = head;
        ListNode<E> refNode = null;
        
        while (index > 1) {
            temp = temp.next();
            index--;
        }
        
        ListNode<E> newNode = new ListNode<E>(value);
        newNode.setNext(temp.next());
       
        if (temp == tail) tail = newNode; // if we need modify tail
        temp.setNext(newNode);
      
        size++;
    }
   
    
    /**
     * Return the value at the specified position in this list
     * 
     * @param	index	index of element to return
     * 
     * @return	E	the value at the specified position in this list
     * */
    public E get(int index) {
    	// invalid index
    	if (index < 0 || index > size) return null;
    	
    	ListNode<E> temp = head;
    	
    	while (index > 0) {
    		temp = temp.next();
    		index--;
    	}
    	
    	return temp.getValue();
    }
    
    
    /**
     * Returns the number of elements in list
     * 
     * @return	int	 the number of elements in this list 	
     * */
    public int size() {
    	return this.size;
    }
    
    
    /**
     * Removes and returns the first element from this list
     * 
     * @return 	E	the first value from list
     * */
    public E removeFirst() {
    	if (size == 0) return null;
    	if (size == 1) {
    		E value = head.getValue();
    		head = null;
    		tail = null;
    		size--;
    		return value;
    	} else {
    		E value = head.getValue();
        	head = head.next();
        	size--;
        	return value;
        	
        }
    }

    /**
     * Removes and returns the last element from this list
     * 
     * @return 	E	the last value from list
     * */
    public E removeLast() {
    	if (size == 0) return null;
    	if (size == 1) {
    		E value = head.getValue();
    		head = null;
    		tail = null;
    		size--;
    		return value;
    	} else {
    		ListNode<E> temp = head;
    		while (temp.next() != tail) temp = temp.next();
    		E value = tail.getValue();
    		temp.setNext(null);
    		tail = temp;
    		size--;
    		return value;
    	}
    }
    
    
    /**
     * Removes the element at the specified position in this list
     * 
     * @param	index	position in the list to remove
     * 
     * @return	E	the value at the specified position int this list
     * */
    public E remove(int index) {
    	
    	//invalid index
    	if (index < 0 || index > size) return null;
    	
    	ListNode<E> temp = head;
    	
    	if (index == 0) return removeFirst();
    	if (index == size) return removeLast();
    	
    	while (index > 1) {
    		temp = temp.next();
    		index--;
    	}
    	
    	E value = temp.next().getValue();
    	temp.setNext(temp.next().next());
    	size--;
    	return value;
    }
    
    
    @Override
    public String toString(){
         
        ListNode<E> temp = head;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while(temp != null) {
        	if (temp.next() != null) sb.append(temp.getValue() + ", ");
        	else  sb.append(temp.getValue());
            temp = temp.next();
        }
        sb.append("]");
        
        return sb.toString();
    }
     
    
    /**
     * Return position of value in this list
     * 
     * @param	E	value to find
     * 
     * @return	int	position of value in this list or -1 if there isn't such value in list
     * */
    public int indexOf(E value) {
    	if (size == 0) return -1;
    	
    	ListNode<E> temp = head;
    	int index = 0;
    	while (temp != null) {
    		if (temp.getValue().equals(value)) return index;
    		temp = temp.next();
    		index++;
    	}
    	return -1;
    }

   
}
 

