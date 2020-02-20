package project;

import java.util.NoSuchElementException;

public class List<T extends Comparable<T>> {
	private class Node 
	{
		private T data;
		private Node next;
		private Node prev;

		public Node(T data) 
		{
			this.data = data;
			this.next = null;
			this.prev = null;
		}
	}
    
    private int length;
    private Node iterator;
    private Node first;
    private Node last;
    
    /****CONSTRUCTORS****/
    
    /**
     * Instantiates a new List with default values
     * @postcondition A new list is created
     */
    public List() {
    	first = null;
    	last = null;
    	length = 0;
    }
    
    /**
     * Instantiates a new List by copying another List
     * @param original the List to make a copy of
     * @postcondition a new List object, which is an identical
     * but separate copy of the List original
     */
    public List(List<T> original) 
    {
        if (original.length == 0) 
        {
            length = 0;
            first = null;
            last = null;
            iterator = null;
        }
        else 
        {
            Node temp = original.first;
            while (temp != null) 
            {
                addLast(temp.data); //inserts into this
                temp = temp.next;
            }
            iterator = null;
        }
       
    }
    
/****ACCESSORS****/

    /**
     * Returns the index of the iterator
     * from 1 to n. Note that there is 
     * no index 0.
     * @precondition iterator != null
     * @return the index of the iterator
     * @throws NullPointerException when
     * the precondition is violated
     */
    public int getIndex() throws NullPointerException{
    	if(iterator == null)
    		throw new NullPointerException("getIndex(): Iterator is null!");
    	if(iterator.prev == null)
    		return 1;
    	else if(iterator.next == null)
    		return length;
    	else
    	{
    		Node temp = iterator;
    		int i;
    		for(i = 1; temp.prev != null; i++)
    			temp = temp.prev;
    		return i;
    	}
    }

    /**
     * Determines whether a List is sorted
     * by calling the recursive helper method
     * isSorted
     * Note: A List that is empty can be
     * considered to be (trivially) sorted
     * @return whether this List is sorted
     */
    public boolean isSorted() 
    {
        return isSorted(first);
    }
    
    /**
     * Recursively determines whether 
     * a List is sorted in ascending order
     * @return whether this List is sorted
     */
    private boolean isSorted(Node n) 
    {
        if(n.next == null && n.data.compareTo(n.prev.data) >= 0)
        	return true;
        else if(n.prev == null)
        	return isSorted(n.next);
        else if(n.data.compareTo(n.prev.data) >= 0)
        	return isSorted(n.next);
        else
        	return false;
    }
    
    /**
     * Searches the List for the specified
     * value using the iterative linear
     * search algorithm
     * @param value the value to search for
     * @return the location of value in the
     * List or -1 to indicate not found
     * Note that if the List is empty we will
     * consider the element to be not found
     * @postcondition: position of the iterator remains
     * unchanged!
     */
    public int linearSearch(T value) {
        
       Node temp = first;
        for (int i = 1; temp != null ; i++)
        {
        	if (temp.data == value)
        		return i;
        	else
        		temp = temp.next;
        }
    	return -1;
    }
    
    /**
     * Returns the index from 1 to length
     * where value is located in the List
     * by calling the private helper method
     * binarySearch
     * @param value the value to search for
     * @return the index where value is 
     * stored from 1 to length, or -1 to
     * indicate not found
     * @precondition isSorted()
     * @postcondition the position of the
     * iterator must remain unchanged! 
     * @throws IllegalStateException when the
     * precondition is violated.
     */
    public int binarySearch(T value) throws IllegalStateException 
    {
        if (!(isSorted()))
        	throw new IllegalStateException("binarySearch(): List is not sorted!");
        return binarySearch(0, length, value);
    }
    
    /**
     * Searches for the specified value in
     * the List by implementing the recursive
     * binarySearch algorithm
     * @param low the lowest bounds of the search
     * @param high the highest bounds of the search
     * @param value the value to search for
     * @return the index at which value is located
     * or -1 to indicate not found
     * @postcondition the location of the iterator
     * must remain unchanged
     */
    private int binarySearch(int low, int high, T value)
    {
        if (high < low)
        	return -1;
        
        int mid = (low + high) / 2;
        
        Node temp = first;
        		
        for(int i = 0; i < mid; i++)
        	temp = temp.next;

        if(temp.data == value)
        	return mid;
        else if(value.compareTo(temp.data) == -1)
	        return binarySearch(low, mid - 1, value);
        
        else
        	return binarySearch(mid + 1, high, value);
        	
    }
    
    /**
     * Returns the value stored in the first node
     * @precondition length != 0 
     * @return the integer value stored at node first
     * @throws NoSuchElementException when precondition is violated
     */
    public T getFirst() throws NoSuchElementException{
    	if(first == null)
    		throw new NoSuchElementException("getFirst(): Cannot access first element: no such element");
    	else
    		return first.data;
    }
    
    /**
     * Returns the value stored in the last node
     * @precondition length != 0
     * @return the integer value stored in the node last
     * @throws NoSuchElementException when precondition is violated
     */
    public T getLast() throws NoSuchElementException{
    	if(last == null)
    		throw new NoSuchElementException("getLast(): Cannot access last element: no such element");
    	else
    		return last.data;
    }
    
    /**
     * Returns the current length of the list
     * @return the length of the list from 0 to n
     */
    public int getLength() {
        return length;
    }
    
    /**
     * Returns whether the list is currently empty
     * @return whether the list is empty
     */
    public boolean isEmpty() {
        	return length == 0;
        
        
    }
    
    /**
     * Gets the element currently pointed at by the iterator
     * @throws NullPointerException if iterator points to null
     * @return The element currently pointed at by the iterator
     */
    public T getIterator() throws NullPointerException
    {
    	if (iterator == null)
    		throw new NullPointerException("getIterator(): Iterator currently points to null.");
    	
    	return iterator.data;
    }
    
    /**
     * Returns whether the iterator is off the end of the list
     * @return iterator is off the end of the list
     */
    public boolean offEnd()
    {
    	return iterator == null;
    }
    
    /**
     * prints the contents of the linked list to the screen in the format #. <element> followed by a newline
     */
    public void printNumberedList()
    {
    	Node temp = first;
    	for(int i = 0; i < length; i++)
    	{
    		System.out.println(i + 1 + ". " + temp.data);
    		temp = temp.next;
    	}
    }
    
    /**
     * Determines whether two Lists have the same data
     * in the same order
     * @param L the List to compare to this List
     * @return whether the two Lists are equal
     */
	@SuppressWarnings("unchecked")
	@Override
    public boolean equals(Object o)
    {
    	if(o == this)
    		return true;
    	else if(!(o instanceof List))
    	{
    		return false;
    	}
    	else
    	{
    		List<T> L = (List<T>)o;
    		if(this.length != L.length)
    			return false;
    		else
    		{
    			Node temp1 = first;
    			Node temp2 = L.first;
    			while(temp1 != null)
    			{
    				if(temp1.data != temp2.data)
						return false;
					temp1 = temp1.next;
					temp2 = temp2.next;
    			}
    			return true;
    		}
    	}
    }
    
    /****MUTATORS****/
	
	/**
     * Points the iterator at first
     * and then iteratively advances 
     * it to the specified index
     * @param index the index where
     * the iterator should be placed
     * @precondition 1 <= index <= length
     * @throws IndexOutOfBoundsException
     * when precondition is violated
     */
    public void moveToIndex(int index) throws IndexOutOfBoundsException{
        if(index > length || index < 1)
        	throw new IndexOutOfBoundsException("moveToIndex(): Index out of bounds!");
        pointIterator();
        for(int i = 1; i < index; i++)
        	advanceIterator();
    }
    
    /**
     * Creates a new first element
     * @param data the data to insert at the 
     * front of the list
     * @postcondition New node is inserted at front of list 
     */
    public void addFirst(T data) {
    	if (first == null) {
            first = last = new Node(data);
        } else {
            Node N = new Node(data);
            N.next = first;
            first.prev = N;
            first = N;
        }
        length++;
    }
    
    /**
     * Creates a new last element
     * @param data the data to insert at the 
     * end of the list
     * @postcondition A new node is inserted at the end of list 
     */
    public void addLast(T data) {
        if (last == null)
        	first = last = new Node(data);   	
    	else {
    		Node N = new Node(data);
    		N.prev = last;
    		last.next = N;
    		last = N;
    	}
        length++;
    }
    
    /**
    * removes the element at the front of the list
    * @precondition List is not empty
    * @postcondition Length of list is reduced by one
    * @throws NoSuchElementException when precondition is violated
    */
    public void removeFirst() throws NoSuchElementException{
        if (first == null) {
        	throw new NoSuchElementException("removeFirst(): List is currently empty");
        }
        else if (length == 1) {
        	first = null;
        	last = null;
        }
        else {
        	first = first.next;
        	first.prev = null;
        }
    	length--;
    	
    }
    
    /**
     * removes the element at the end of the list
     * @precondition List cannot be empty
     * @postcondition Last element of list is removed
     * @throws NoSuchElementException when precondition is violated
     */
    public void removeLast() throws NoSuchElementException{
    	if (length == 0) 
    	{
    		throw new NoSuchElementException("removeLast(): List is empty");
    	}
        else if (length == 1) 
        {
            first = null;
            last = null;
        } 
        else
            last = last.prev;
        length--;
    }
    
    /**
     * Points iterator to the head
     * @postcondition iterator = first
     */
    public void pointIterator()
    {
    	iterator = first;
    }
    
    /**
     * inserts an element after the node currently pointed to by the iterator
     * @precondition iterator != null
     * @postcondition New node is inserted. Iterator remains pointing to the same element it was originally
     * @param data The data to insert
     * @throws NullPointerException when precondition is violated
     */
    public void addIterator(T data) throws NullPointerException
    {
    	if(iterator == null)
    		throw new NullPointerException("addIterator(): iterator is null!");
    	else
    	{
	    	Node N = new Node(data);
	    	if(iterator.next != null)
	    	{
		    	N.next = iterator.next;
		    	iterator.next.prev = N;
	    	}
	    	else
	    		last = N;
	    	iterator.next = N;
	    	N.prev = iterator;
	    	length++;
    	}
    }
    
    /**
     * removes the node currently pointed to by the iterator
     * @precondition iterator != null
     * @postcondition iterator = null
     * @param data The data to insert
     * @throws NullPointerException when precondition is violated
     */
    public void removeIterator() throws NullPointerException
    {
    	if(iterator == null)
    		throw new NullPointerException("addIterator(): iterator is null!");
    	else
    	{
    		if(iterator.next == null && iterator.prev == null)
    			first = last = null;
    		else if(iterator.prev == null)
    		{
    			iterator.next.prev = null;
    			first = iterator.next;
    		}
    		else if(iterator.next == null)
    		{
    			iterator.prev.next = null;
    			last = iterator.prev;
    		}
    		else
    		{
    			iterator.prev.next = iterator.next;
    			iterator.next.prev = iterator.prev;
    		}
    		iterator = null;
    		length--;
    	}
    }
    
    /**
     * Moves the iterator up by one node
     * @postcondition iterator = iterator.next
     */
    public void advanceIterator() throws NoSuchElementException
    {
    	if(iterator == null)
    		throw new NoSuchElementException("advanceIterator(): iterator is null!");
    	else
    		iterator = iterator.next;
    }
    
    /**
     * Moves the iterator down by one node
     * @precondition iterator != null
     * @postcondition iterator = iterator.next
     * @throws NoSuchElementException when precondition is violated
     */
    public void reverseIterator() throws NoSuchElementException
    {
    	if(iterator == null)
    		throw new NoSuchElementException("reverseIterator(): iterator is null!");
    	else
    		iterator = iterator.prev;
    }
    
    /****ADDITIONAL OPERATIONS****/
    
    /**
     * Prints a linked list to the console
     * in reverse by calling the private 
     * recursive helper method printReverse
     */
    public void printReverse() {
       printReverse(last);
    }
    
    /**
     * Prints a linked list to the console
     * recursively (no loop)
     * in reverse order from last to first
     * Each element separated by a space
     * Should print a new line after all
     * elements have been displayed
     */    
    private void printReverse(Node n) {
        System.out.print(n.data + " ");
        if(n.prev != null)
        	printReverse(n.prev);
        else
        	System.out.println();
    }  
    
    /**
     * List with each value separated by a blank space
     * At the end of the List a new line
     * @return the List as a String for display
     */
    @Override public String toString() {
       String result = "";
       Node temp = first;
       while (temp != null) {
    	   result += temp.data + " ";
    	   temp = temp.next;
       }
       result += "\n";
       return result;
    }
}