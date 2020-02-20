package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class philzMenu {
	private BST<String> defaultMain;
	private BST<String> defaultSecondary;
	
	public philzMenu()
	{
		defaultMain= new BST<String>();
		defaultSecondary= new BST<String>();
	}
	
	/**
	 * Fills the empty Menu!
	 */
	public void fillMenu()
	{
		try{
			Scanner x = new Scanner(new File("philzMenu"));
			while(x.hasNext())
			{
				String name= x.nextLine();
				String aroma=x.nextLine();
				double price= Double.parseDouble(x.nextLine());
				defaultMain.insert(name,aroma,price);
				defaultSecondary.insert(aroma,name,price);
				if(!x.hasNext())
				{
					break;
				}
				
			}
			x.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Couldn't find the file.");
		}
	}
	/**
	 * Seaches for an Aroma
	 * @param Aroma you are searching for
	 * @category Secpndary Implementation
	 * @return True/False Whether or  not the Aroma is there
	 */
	public boolean searchAroma(String aroma)
	{
		return defaultSecondary.searchAroma(aroma);
	}
	/**
	 * Seaches for the Name
	 * @param Name you are searching for
	 * @category Primary Implementation
	 * @return True/False Whether or not the Name is there
	 */
	public boolean searchName(String name)
	{
		return defaultMain.searchName(name);
		
	}
	/**
	 * Inserts a new Item into both the 
	 * secondary and primary
	 * @param Name followed by Aroma followed by price
	 */
	public void insertItem(String name,String aroma,double price)
	{
		defaultMain.insert(name, aroma, price);
		defaultSecondary.insert(aroma,name,price);
	}
	/**
	 * Removes an Item from both the 
	 * secondary and primary
	 * @precondition search==true
	 * @param Name followed by Aroma 
	 */
	public void removeItem(String name,String aroma)
	{
		if(this.searchAroma(aroma))
		defaultMain.remove(name);
		defaultSecondary.remove(aroma);
	}
	/**
	 * Prints Menu
	 */
	public void printMenu()
	{
		defaultMain.menuPrint();
		
	}
	/**
	 * Prints all the Aromas on the Menu
	 */
	public void printAromaMenu()
	{
		defaultSecondary.aromaPrint();
	}
	/**
	 * Prints all the Names on the Menu
	 */
	public void printNameMenu()
	{
		defaultMain.namePrint();
	}
	/**
	 * Prints the selected Aromas Information
	 * @param Aroma
	 */
	public void printAroma(String aroma)
	{
		defaultSecondary.printItemSecondary(aroma);
	}
	/**
	 * Prints the selected Names Information
	 * @param Name
	 */
	public void printName(String name)
	{
		defaultMain.printItemPrimary(name);
	}
	/**
	 * gets the price by searching for it through
	 * the primary BST
	 * @param Name
	 */
	public double getPriceP(String name)
	{
		return defaultMain.searchPrice(name);
	}
	/**
	 * gets the price by searching for it through
	 * the secondary BST
	 * @param Aroma
	 */
	public double getPriceS(String aroma)
	{
		return defaultSecondary.searchPrice(aroma);
	}
	static public void main(String args[])
	{
		philzMenu j= new philzMenu();
		j.fillMenu();
		j.printMenu();
		System.out.println(j.getPriceS("Nuts, Toffee"));
		
	}

}
