package project;

import java.util.ArrayList;

public class Hash<T extends Comparable<T>> {

	private int numElements;
	private ArrayList<List<T>> Table;
	
	/**
	* Constructor for the Hash.java
	* class. Initializes the Table to
	* be sized according to value passed
	* in as a parameter
	* Inserts size empty Lists into the
	* table. Sets numElements to 0
	* @param size the table size
	*/
	public Hash(int size) {
		Table = new ArrayList<List<T>>();
		for(int i = 0; i < size; i++)
			Table.add(new List<T>());
	}
	
	/**Accessors*/
	
	/**
	* Returns the hash value in the Table
	* for a given key by taking modulus
	* of the hashCode value for that key
	* and the size of the table
	* @param t the key
	* @return the index in the Table
	*/
	private int hash(T t) {
	    return t.hashCode() % Table.size();
	}
	
	/**
	* Counts the number of keys at this index
	* @param index the index in the Table
	* @precondition 0 <= index < Table.length
	* @return the count of keys at this index
	* @throws IndexOutOfBoundsException
	*/
	public int countBucket(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index > Table.size())
			throw new IndexOutOfBoundsException("countBucket: Index out of bounds!");
	    return Table.get(index).getLength();
	}
	
	/**
	* Returns total number of keys in the Table
	* @return total number of keys
	*/
	public int getNumElements() {
	    return numElements;
	}
	
	public T get_object(T t)
	{
		Table.get(this.search(t)).pointIterator();
		for(int i = 0; i < Table.get(this.search(t)).getLength(); i++)
		{
    		if(Table.get(this.search(t)).getIterator().compareTo(t) == 0)
    			return Table.get(this.search(t)).getIterator();
    		Table.get(this.search(t)).advanceIterator();	
		}
		return null;
	}
	/**
	* Searches for a specified key in the Table
	* @param t the key to search for
	* @return the index in the Table (0 to Table.length - 1)
	* or -1 if t is not in the Table
	*/
	public int search(T t) {
	    if(Table.get(hash(t)) != null)
	    {
	    	List<T> l = Table.get(hash(t));
	    	if(l.getLength() == 0)
	    		return -1;
    		l.pointIterator();
    		for(int i = 0; i < l.getLength(); i++)
    		{
	    		if(l.getIterator().compareTo(t) == 0)
	    			return hash(t);
	    		l.advanceIterator();	
    		}
	    }
	    return -1;
	}
	
	
	/**Manipulation Procedures*/
	
	/**
	* Inserts a new key in the Table
	* calls the hash method to determine placement
	* @param t the key to insert
	*/
	public void insert(T t) {
		int loc = hash(t);
		if(Table.get(loc) == null)
		{
			Table.set(loc, new List<T>());
			Table.get(loc).addFirst(t);
		}
		else
			Table.get(loc).addLast(t);
		numElements++;
	} 
	
	
	/**
	* removes the key t from the Table
	* calls the hash method on the key to
	* determine correct placement
	* has no effect if t is not in
	* the Table
	* @param t the key to remove
	*/
	public void remove(T t) {
		if(Table.get(hash(t)) != null)
		{
			List<T> l = Table.get(hash(t));
	    	if(l.getLength() != 0)
	    	{
		    	l.pointIterator();
	    		for(int i = 0; i < l.getLength(); i++)
	    		{
		    		if(l.getIterator() == t)
		    		{
		    			l.removeIterator();
		    			numElements--;
		    			return;
		    		}
		    		l.advanceIterator();	
	    		}
    		}
		}
	}
	
	/**Additional Methods*/
	
	/**
	* Prints all the keys at a specified
	* bucket in the Table. Each key displayed
	* on its own line, with a blank line 
	* separating each key
	* Above the keys, prints the message
	* "Printing bucket #<bucket>:"
	* Note that there is no <> in the output
	* @param bucket the index in the Table
	*/
	public void printBucket(int bucket) {
		System.out.println("Printing bucket #" + bucket + ":");
		List<T> l = Table.get(bucket);
		if(l.getLength() != 0)
		{
			l.pointIterator();
			for(int i = 0; i < l.getLength(); i++)
			{
				System.out.println(l.getIterator());
	    		l.advanceIterator();	
			}
		}
	}
	
	/**
	* Prints the first key at each bucket
	* along with a count of the total keys
	* with the message "+ <count> -1 more 
	* at this bucket." Each bucket separated
	* with to blank lines. When the bucket is 
	* empty, prints the message "This bucket
	* is empty." followed by two blank lines
	*/
	public void printTable() {
		for(int i = 0; i < Table.size(); i++)
		{
			if(Table.get(i) != null)
			{
				List<T> l = Table.get(i);
				if(l.getLength() == 0)
					System.out.println("This bucket is empty.");
				else
					System.out.println(l.getFirst() + " " + (l.getLength() - 1) + " more at this bucket.\n\n");
			}
		}
	}
}