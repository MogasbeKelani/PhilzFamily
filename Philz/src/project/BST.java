package project;
/*
 * BST.java
 * @author Mohammed Mogasbe
 * @author Miles Wong
 * CIS 22C Lab 6
 */

import java.util.NoSuchElementException;

public class BST<T extends Comparable<T>> {
	private class coffee {
		private String primary;
		private String secondary;
		private double price;
		private coffee left;
		private coffee right;

		public coffee(String n,String a,double p) {
			primary = n;
			secondary= a;
			price= p;
			left = null;
			right = null;
		}
	}

	private coffee root;

	/*** CONSTRUCTORS ***/

	/**
	 * Default constructor for BST sets root to null
	 */
	public BST() {
		root = null;

	}


	/*** ACCESSORS ***/

	/**
	 * Returns the data stored in the root
	 * 
	 * @precondition !isEmpty()
	 * @return the data stored in the root
	 * @throws NoSuchElementException when preconditon is violated
	 */
	public String getRoot() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("getRoot: BST is Empty");
		}
		return root.primary;
	}

	/**
	 * Determines whether the tree is empty
	 * @return whether the tree is empty
	 */
	public boolean isEmpty() {
		if (root == null) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the current size of the tree (number of nodes)
	 * @return the size of the tree
	 */
	public int getSize() {
		if (isEmpty()) {
			return 0;
		} else {
			return getSize(root);
		}
	}

	/**
	 * Helper method for the getSize method
	 * @param node the current node to count
	 * @return the size of the tree
	 */
	private int getSize(coffee node) {
		if (node == null) {
			return 0;
		} else {
			return (getSize(node.left) + 1 + getSize(node.right));
		}
	}


	/**
	 * Returns the smallest value in the tree
	 * @precondition !isEmpty()
	 * @return the smallest value in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public String findMin() throws NoSuchElementException {
		if (this.isEmpty()) {
			throw new NoSuchElementException("findMin: Tree is Empty!");
		} else {
			return findMin(root);
		}
	}
	/**
	 * Searches for a specified value in the tree
	 * **Warning this function is restricted for the secondary BST**
	 * @param name of the item 
	 * @return whether the value is stored in the tree
	 */
	public double searchPrice(String data) {
		if (this.isEmpty()) {
			return 0;
		} else {
			return searchPrice(data, root);
		}
	}
	
	private double searchPrice(String data, coffee node) {
		if (data.compareTo(node.primary) == 0) {
			return node.price;
		} else if (data.compareTo(node.primary) < 0) {
			if (node.left == null) {
				return 0;
			} else {
				return searchPrice(data, node.left);
			}
		} else {
			if (node.right == null) {
				return 0;
			} else {
				return searchPrice(data, node.right);
			}
		}
	}

	/**
	 * Helper method to findMin method
	 * @param node the current node to check if it is the smallest
	 * @return the smallest value in the tree
	 */
	private String findMin(coffee node) {
		if (node.left == null) {
			return node.primary;
		} else {
			return findMin(node.left);
		}
	}



	/**
	 * Searches for a specified value in the tree
	 * **Warning this function is restricted for the primary BST**
	 * @param data the value to search for
	 * @return whether the value is stored in the tree
	 */
	public boolean searchName(String data) {
		if (this.isEmpty()) {
			return false;
		} else {
			return searchName(data, root);
		}
	}
	
	/**
	 * Searches for a specified value in the tree
	 * **Warning this function is restricted for the secondary BST**
	 * @param data the value to search for
	 * @return whether the value is stored in the tree
	 */
	public boolean searchAroma(String data) {
		if (this.isEmpty()) {
			return false;
		} else {
			return searchAroma(data, root);
		}
	}

	/**
	 * Helper method for the search method
	 * **Warning this function is restricted for the Primary BST**
	 * @param data the data to search for
	 * @param node the current node to check
	 * @return whether the data is stored in the tree
	 */
	private boolean searchName(String data, coffee node) {
		if (data.compareTo(node.primary) == 0) {
			return true;
		} else if (data.compareTo(node.primary) < 0) {
			if (node.left == null) {
				return false;
			} else {
				return searchName(data, node.left);
			}
		} else {
			if (node.right == null) {
				return false;
			} else {
				return searchName(data, node.right);
			}
		}
	}
	/**
	 * Helper method for the search method
	 * **Warning this function is restricted for the Secondary BST**
	 * @param data the data to search for
	 * @param node the current node to check
	 * @return whether the data is stored in the tree
	 */
	private boolean searchAroma(String data, coffee node) {
		if (data.compareTo(node.primary) == 0) {
			return true;
		} else if (data.compareTo(node.primary) < 0) {
			if (node.left == null) {
				return false;
			} else {
				return searchAroma(data, node.left);
			}
		} else {
			if (node.right == null) {
				return false;
			} else {
				return searchAroma(data, node.right);
			}
		}
	}

	/*** MUTATORS ***/

	/**
	 * Inserts a new node in the tree
	 * 
	 * @param data the data to insert
	 */
	public void insert(String m, String s,double p) {
		if (root != null) {
			insert(m,s,p, root);
		} else {
			root = new coffee(m,s,p);
		}

	}

	/**
	 * Helper method to insert Inserts a new value in the tree
	 * 
	 * @param data the data to insert
	 * @param node the current node in the search for the correct location in which
	 *             to insert
	 */
	private void insert(String s, String n,double p, coffee node) {

		if (s.compareTo(node.primary) <= 0) {
			if (node.left == null) {
				node.left = new coffee(s,n,p);
			} else {
				insert(s,n,p, node.left);
			}
		} else {
			if (node.right == null) {
				node.right = new coffee(s,n,p);
			} else {
				insert(s,n,p,
						node.right);
			}
		}

	}

	/**
	 * Removes a value from the BST
	 * 
	 * @param data the value to remove
	 * @precondition !isEmpty()
	 * @precondition the data is located in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public void remove(String data) throws NoSuchElementException {

		if (isEmpty()) {
			throw new NoSuchElementException("remove: Tree is empty!");
		} else if (this.searchName(data) == false && this.searchAroma(data)) {
			throw new NoSuchElementException("remove: Data is not in this BST!");
		} else {
			root = remove(data, root);
		}

	}

	/**
	 * Helper method to the remove method
	 * 
	 * @param data the data to remove
	 * @param node the current node
	 * @return an updated reference variable
	 */
	private coffee remove(String data, coffee node) {
		if (node == null)
			return node;
		else if (data.compareTo(node.primary) < 0)
			node.left = remove(data, node.left);
		else if (data.compareTo(node.primary) > 0)
			node.right = remove(data, node.right);
		else {
			if (node.left == null && node.right == null)
				node = null;
			else if (node.left != null && node.right == null)
				node = node.left;
			else if (node.right != null && node.left == null)
				node = node.right;
			else {
				node.primary = findMin(node.right);
				node.right = remove(node.primary, node.right);
			}
			
		}
		return node; 
	}

	/*** ADDITIONAL OPERATIONS ***/

	/**
	 * Prints the name menu using Primary BST
	 */
	public void namePrint() {
		
		System.out.printf("Name Search Menu:\n");
		System.out.printf("------------\n");
		inOrderPrintP(root);
		System.out.printf("------------\n");
	}
	/**
	 * Prints the aroma menu using Secondary BST
	 */
	public void aromaPrint() {
		System.out.printf("Aroma Search Menu:\n");
		System.out.printf("------------\n");
		inOrderPrintS(root);
		System.out.printf("------------\n");
	}
	/**
	 * Prints the offical menu using Primary BST
	 */
	public void menuPrint() {
		System.out.printf("Menu:\n");
		System.out.printf("------------\n");
		this.menuPrint(root);
		System.out.printf("------------\n");
	}
	/**
	 * Helper for Name menu
	 */
	private void inOrderPrintP(coffee node) {
		if (node != null) {
			inOrderPrintP(node.left);
			System.out.println(node.primary+"\n");
			inOrderPrintP(node.right);
			
		}

	}
	/**
	 * Helper for Aroma menu
	 */
	private void inOrderPrintS(coffee node) {

		if (node != null) {
			inOrderPrintS(node.left);
			System.out.println(node.primary+"\n");
			inOrderPrintS(node.right);
		}

	}
	/**
	 * Helper for offical Menu
	 */
	private void menuPrint(coffee node) {

		if (node != null) {
			menuPrint(node.left);
			System.out.printf("Blend: " + node.primary+"\nAroma: " + node.secondary+"\nCost:  " + node.price+"\n");
			menuPrint(node.right);
		}

	}
	/**
	 * Prints Item using Primary BST
	 * @param Name
	 */
	public void printItemPrimary(String key){
		if(this.searchName(key)==false)
		{
			System.out.println(key+" is not on the menu!");
			return;
		}
		else
		{
			System.out.println(this.Name(key, root));
		}
			
		
	}
	/**
	 * Prints Item using Secondary BST
	 * @param Aroma
	 */
	public void printItemSecondary(String key){
		if(this.searchAroma(key)==false )
		{
			System.out.println(key+" is not on the menu!");
			return;
		}
		else
		{
			System.out.println(this.Aroma(key, root));
		}
	}
	/**
	 * Helper for Print Item Primary
	 */
	private String Name(String data,coffee node)
	{
		if (data.compareTo(node.primary) == 0) {
			return "\nName: "+node.primary+"\nAroma: "+node.secondary+"\nPrice: "+node.price+"\n";
		}
		else if (data.compareTo(node.primary) < 0) 
		{
			
		return Name(data, node.left);
		}
		 
		else 
		{
		return Name(data, node.right);
			
		}
	}
	/**
	 * Helper for Print Item Secondary
	 */
	private String Aroma(String data,coffee node)
	{
		if (data.compareTo(node.primary) == 0) {
			return "\nAroma: "+node.primary+"\nName: "+node.secondary+"\nPrice: "+node.price+"\n";
		}
		else if (data.compareTo(node.primary) < 0) 
		{
			
		return Aroma(data, node.left);
		}
		 
		else 
		{
		return Aroma(data, node.right);
			
		}
	}


	
	
}

