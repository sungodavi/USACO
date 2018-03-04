/*
ID: sungoda1
LANG: JAVA
TASK: milk4
*/

import java.util.*;
import java.io.*;
import java.awt.Point;
import java.math.BigInteger;

class milk4 
{
	static List<Edge>[] list;
	static int[][] dp;
	static final int INF = (int)1e9;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("milk4.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk4.out")));
		int num = Integer.parseInt(f.readLine());
		int size = Integer.parseInt(f.readLine()) + 1;
		int[] a = new int[size];
		for(int i = 1; i < size; i++)
			a[i] = Integer.parseInt(f.readLine());
		Arrays.sort(a);
		list = new ArrayList[num + 1];
		for(int i = 0; i < list.length; i++)
			list[i] = new ArrayList<Edge>();
		for(int k = 1; k <= num; k++)
		{
			for(int index = 0; index < size; index++)
			{
				int c = a[index];
				if(c > k)
					break;
				if(k == c || !list[k - c].isEmpty())
				{
					list[k].add(new Edge(k - c, index));
				}
			}
		}
		System.out.println(Arrays.toString(list));
		dp = new int[num + 1][size];
		for(int[] temp : dp)
			Arrays.fill(temp, -1);
		int result = dfs(num, 0);
		ArrayList<Integer> pans = new ArrayList<Integer>();
		rebuild(num, 0, pans);
		out.print(pans.size());
		for(int pan : pans)
			out.print(" " + a[pan]);
		out.println();
		out.close();
	}
	
	public static void rebuild(int u, int curr, ArrayList<Integer> pans)
	{
		System.out.println(u + " " + curr);
		if(u == 0)
			return;
		int index = search(list[u], curr);
		if(index != -1)
			for(int i = index; i < list[u].size(); i++)
			{
				Edge e = list[u].get(i);
				if(dfs(e.v, e.w) + (e.w == curr ? 0 : 1) == dp[u][curr])
				{
					if(e.w != curr)
						pans.add(e.w);
					rebuild(e.v, e.w, pans);
					return;
				}
			}
	}
	
	public static int dfs(int u, int curr)
	{
		if(u == 0)
			return 0;
		if(dp[u][curr] != -1)
			return dp[u][curr];
		int result = INF;
		int index = search(list[u], curr);
		System.out.println(list[u] + " " + curr + " " + index);
		if(index != -1)
			for(int i = index; i < list[u].size(); i++)
			{
				Edge e = list[u].get(i);
				int cost = dfs(e.v, e.w) + (e.w == curr ? 0 : 1);
				result = Math.min(result, cost);
			}
		return dp[u][curr] = result;
	}
	
	public static int search(List<Edge> list, int val)
	{
		int low = 0;
		int high = list.size() - 1;
		while(low <= high)
		{
			int mid = low + high >> 1;
			if(list.get(mid).w >= val && (mid == 0 || list.get(mid - 1).w < val))
				return mid;
			else if(list.get(mid).w < val)
				low = mid + 1;
			else
				high = mid - 1;
		}
		return -1;
	}
	
	static class Edge
	{
		int v, w;
		public Edge(int v, int w)
		{
			this.v = v;
			this.w = w;
		}
		
		public String toString()
		{
			return v + " " + w;
		}
	}
	
	static class Pair
	{
		int size;
		BigInteger mask;
	}
}