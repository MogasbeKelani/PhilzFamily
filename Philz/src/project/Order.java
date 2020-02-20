package project;

public class Order implements Comparable<Order>
{
	
	private int shippingDate;
	private String coffee_name;
	private double cost;
	private boolean shipped = false;
   
	
	public Order(String coffee_name, int shippingDate, double cost)
	{
		this.shippingDate = shippingDate;
		this.coffee_name = coffee_name;
		this.cost = cost;
	}

	@Override
	public int compareTo(Order o)
	{
		if(o.getShippingDate() < shippingDate)
			return 14;
		else if (o.getShippingDate()> shippingDate)
		   return -14;
		else
		   return 0;
	}
	@SuppressWarnings("unused")
	public boolean getShipped() {
		   return shipped;
	   }
	public int getShippingDate()
	{
		return shippingDate;
	}

	public void setShippingDate(int shippingDate)
	{
		this.shippingDate = shippingDate;
	}

	public String getCoffeeName()
	{
		return coffee_name;
	}

	public void setShipped() {
		shipped = true;
	}
	public void setCoffeeName(String coffee_name)
	{
		this.coffee_name = coffee_name;
	}

	public double getCost()
	{
		return cost;
	}

	public void setCost(double cost)
	{
		this.cost = cost;
	}

   
   public String toString() {
      return (coffee_name + "\n" + shippingDate + "\n");
   }
}