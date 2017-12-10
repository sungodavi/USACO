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
	static int[] curr, prices, map, amounts;
	static int[][] memoTable;
	static final int SIZE = 7777;
	static class Bag
	{
		int[] curr;
		public Bag(int[] a)
		{
			curr = a;
		}
		
		public int hashCode()
		{
			return Arrays.hashCode(curr);
		}
	}
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
	public static int convert(int[] a)
	{
		int code = 0;
		int e = 1;
		for(int i = 0; i < a.length; i++)
		{
			code += a[i] * e;
			e *= 6;
		}
		return code;
	}
	public static boolean canPurchase(Deal d)
	{
		for(Point product : d.products)
		{
			int index = map[product.x];
			if(curr[index] + product.y > amounts[index])
				return false;
		}
		return true;
	}
	public static void purchase(Deal d)
	{
		for(Point product : d.products)
		{
			int index = map[product.x];
			curr[index] += product.y;
		}
	}
	public static void revert(Deal d, int count)
	{
		for(Point product : d.products)
		{
			int index = map[product.x];
			curr[index] -= count * product.y;
		}
	}
	public static int findCost(int index)
	{
		if(index == deals.length)
		{
			int cost = 0;
			for(int i = 0; i < curr.length; i++)
			{
				cost += (amounts[i] - curr[i]) * prices[i];
			}
			return cost;
		}
		int code = convert(curr);
		if(memoTable[index][code] >= 0)
			return memoTable[index][code];
		int cost = Integer.MAX_VALUE;
		Deal d = deals[index];
		int count = 0;
		while(canPurchase(d))
		{
			purchase(d);
			count++;
			cost = Math.min(cost, findCost(index + 1) + count * d.price);
		}
		if(count > 0)
			revert(d, count);
		return memoTable[index][code] = Math.min(cost, findCost(index + 1));
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
		int items = Integer.parseInt(f.readLine());
		curr = new int[items];
		amounts = new int[items];
		prices = new int[items];
		map = new int[1000];
		memoTable = new int[deals.length][SIZE];
		for(int[] temp : memoTable)
			Arrays.fill(temp, -1);
		for(int i = 0; i < items; i++)
		{
			StringTokenizer st = new StringTokenizer(f.readLine());
			int key = Integer.parseInt(st.nextToken());
			int amount = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			amounts[i] = amount;
			prices[i] = price;
			map[key] = i;
		}
		int cost = findCost(0);
		out.println(cost);
		out.close();
	}
}
