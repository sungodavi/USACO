/*
ID: sungoda1
LANG: JAVA
TASK: shopping
*/
import java.util.*;
import java.io.*;
import java.awt.Point;

class shopping 
{
	static Deal[] deals;
	static HashMap<Integer, Integer> prices;
	static class Deal
	{
		public int price;
		public Point[] products;
		public Deal(Point[] products, int price)
		{
			this.price = price;
			this.products = products;
		}
		
		public String toString()
		{
			return price + " " + Arrays.toString(products);
		}
	}
	
	public static boolean isValid(Deal d, HashMap<Integer, Integer> items)
	{
		for(Point product : d.products)
		{
			if(items.get(product.x) < product.y)
				return false;
		}
		return true;
	}
	public static void purchase(Deal d, HashMap<Integer, Integer> items)
	{
		for(Point product : d.products)
		{
			items.put(product.x, items.get(product.x) - product.y);
		}
	}
	
	public static void revert(Deal d, HashMap<Integer, Integer> items, int count)
	{
		for(Point product : d.products)
		{
			items.put(product.x, items.get(product.x) + count * product.y);
		}
	}
	public static int findCost(HashMap<Integer, Integer> itemsLeft, int index)
	{
		//System.out.println(index + " " + itemsLeft);
		if(index == deals.length)
		{
			
			int cost = 0;
			for(int item : itemsLeft.keySet())
			{
				cost += prices.get(item) * itemsLeft.get(item);
			}
			//System.out.println(itemsLeft + " " + cost);
			return cost;
		}
		int cost = Integer.MAX_VALUE;
		Deal d = deals[index];
		int count = 0;
		while(isValid(d, itemsLeft))
		{
			count++;
			purchase(d, itemsLeft);
			cost = Integer.min(cost, d.price * count + findCost(itemsLeft, index + 1));
		}
		if(count > 0)
			revert(d, itemsLeft, count);
		return Integer.min(cost, findCost(itemsLeft, index + 1));
	}
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("shopping.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shopping.out")));
		deals = new Deal[Integer.parseInt(f.readLine())];
		for(int i = 0; i < deals.length; i++)
		{
			StringTokenizer st = new StringTokenizer(f.readLine());
			Point[] a = new Point[Integer.parseInt(st.nextToken())];
			for(int k = 0; k < a.length; k++)
				a[k] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			deals[i] = new Deal(a, Integer.parseInt(st.nextToken()));
		}
		prices = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> items = new HashMap<Integer, Integer>();
		for(int times = Integer.parseInt(f.readLine()); times > 0; times--)
		{
			StringTokenizer st = new StringTokenizer(f.readLine());
			int id = Integer.parseInt(st.nextToken());
			int amount = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			prices.put(id, price);
			items.put(id, amount);
		}
		//System.out.println(prices);
		//System.out.println(items);
		//System.out.println(Arrays.toString(deals));
		int cost = findCost(items, 0);
		out.println(cost);
		out.close();
	}
}
