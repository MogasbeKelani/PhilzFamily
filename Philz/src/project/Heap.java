package project;
import java.util.ArrayList;
import java.util.Collection;

public class Heap
{
   private ArrayList<Order> orders;

   public Heap()
   {
      orders = new ArrayList<Order>();
   }
   
   public Heap(int capacity)
   {
      orders = new ArrayList<Order>(capacity);
   }

   public Heap(Collection<Order> collection)
   {
      orders = new ArrayList<Order>(collection);
      
      for (int i = size() / 2; i >= 0; i--)
      {
         minHeapify(i);
      }
   }

   public Order getMin()
   {
      if (size() < 1)
      {
         throw new IndexOutOfBoundsException("No minimum value:  the heap is empty.");
      }
      return orders.get(0);
   }

   public Order extractMin()
   {
      Order value = getMin();
      swap(0, size() - 1);
      orders.remove(size() - 1);
      minHeapify(0);
      return value;
   }

   public void insert(Order newOrder)
   {
      orders.add(newOrder);
      int index = size() - 1;

      
      buildHeap(index);

   }

   private void buildHeap(int index)
   {
      if (index != 0)
      {
         int parent = parent(index);
         while (orders.get(parent).compareTo(orders.get(index)) > 0)
         {
            swap(parent, index);

            index = parent;
            parent = parent(index);

            if (index == 0)
               break;
         }
      }
   }

   
   private int parent(int index)
   {
      return (index - 1) / 2;
   }

   private int left(int index)
   {
      return 2 * index + 1;
   }

   private int right(int index)
   {
      return 2 * index + 2;
   }

   private int size()
   {
      return orders.size();
   }

   private void swap(int from, int to)
   {  
      Order val = orders.get(from);      
      orders.set(from, orders.get(to));
      orders.set(to, val);
   }

   private void minHeapify(int index)
   {
      int left = left(index);
      int right = right(index);
      int smallest = index;
      if (left < size() && orders.get(left).compareTo(orders.get(smallest)) < 0)
      {
         smallest = left;
      }
      if (right < size() && orders.get(right).compareTo(orders.get(smallest)) < 0)
      {
         smallest = right;
      }
      if (smallest != index)
      {
         swap(index, smallest);
         minHeapify(smallest);
      }
   }
   
   public String sort() {
      int size = orders.size();

      ArrayList<Order> officialOrders = new ArrayList<>();
      
      for (int i = 0; i< size; i++) {
         officialOrders.add(extractMin());
      }
      
      orders = officialOrders;
      
      
      String ret = "";
      int count = 1;
      for (int i = 0; i < size; i++) {
         ret +=("Order " + count++ + ": \n"+ orders.get(i) + "\n");
      }
      return ret;
   }
   public Order getOrder(int i) {
	   return orders.get(i);
   }
   
}
