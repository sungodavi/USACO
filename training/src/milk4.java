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
	static BigInteger[][] dp;
	static boolean[][] visited;
	static int[] a;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("milk4.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk4.out")));
		int num = Integer.parseInt(f.readLine());
		int size = Integer.parseInt(f.readLine()) + 1;
		a = new int[size];
		for(int i = 1; i < size; i++)
			a[i] = Integer.parseInt(f.readLine());
//		System.out.println(Arrays.toString(a));
		dp = new BigInteger[num + 1][size];
		visited = new boolean[num + 1][size];
		BigInteger result = dfs(num, 0);
		display(result, out);
		out.close();
	}
	
	public static BigInteger dfs(int u, int curr)
	{
		if(u == 0)
			return BigInteger.ZERO;
		if(visited[u][curr])
			return dp[u][curr];

		BigInteger result = null;
		for(int i = Math.max(1, curr); i < a.length; i++)
		{
			if(a[i] > u)
				break;
			BigInteger p = dfs(u - a[i], i);
			if(p != null)
			{
				if(curr != i)
					p = p.setBit(i);
				if(result == null || comp(p, result))
					result = p;
			}
		}
//		System.out.println(u + " " + curr + (result == null ? "" : " " + result.toString(2)));
		visited[u][curr] = true;
		return dp[u][curr] = result;
	}
	
	public static void display(BigInteger result, PrintWriter out)
	{
		out.print(result.bitCount());
		while(result.compareTo(BigInteger.ZERO) > 0)
		{
			int p = result.getLowestSetBit();
			out.print(" " + a[p]);
			result = result.clearBit(p);
		}
		out.println();
	}
	
	public static boolean comp(BigInteger p, BigInteger curr)
	{
		int c1 = p.bitCount();
		int c2 = curr.bitCount();
		return c1 < c2 || (c1 == c2 && p.getLowestSetBit() < curr.getLowestSetBit());
	}
}