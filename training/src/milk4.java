/*
ID: sungoda1
LANG: JAVA
TASK: milk4
*/

import java.util.*;
import java.io.*;
import java.awt.Point;

class milk4 
{
	static int[][] dp, first;
	static Point[][] next;
	static int[] a;
	static int INF = 101;
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("milk4.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk4.out")));
		int num = Integer.parseInt(f.readLine());
		int size = Integer.parseInt(f.readLine()) + 1;
		a = new int[size];
		for(int i = 1; i < size; i++)
			a[i] = Integer.parseInt(f.readLine());
		Arrays.sort(a);
//		System.out.println(Arrays.toString(a));
		dp = new int[num + 1][size];
		first = new int[num + 1][size];
		next = new Point[num + 1][size];
		for(int[] temp : dp)
			Arrays.fill(temp, -1);
		first = new int[num + 1][size];
		out.print(dfs(num, 0));
		display(out);
		System.out.println(System.currentTimeMillis() - start);
		out.close();
	}
	
	public static void display(PrintWriter out)
	{
		int coin = 0;
		int u = dp.length - 1;
		int curr = 0;
		while(u > 0)
		{
			if(curr != coin)
				out.print(" " + a[coin = curr]);
			Point p = next[u][curr];
			u = p.x;
			curr = p.y;
		}
		if(coin != curr)
			out.print(" " + a[curr]);
		out.println();
	}
	
	public static int dfs(int u, int curr)
	{
		if(u == 0)
			return 0;
		if(dp[u][curr] != -1)
			return dp[u][curr];
		int result = INF;
		Point best = null;
		for(int i = Math.max(1, curr); i < a.length; i++)
		{
			if(a[i] > u)
				break;
			int v = u - a[i];
			int p = dfs(v, i);
			if(i != curr)
			{
				p++;
				first[v][i] = i;
			}
			if(p < 101 && (best == null || p < result || p == result && first[v][i] < first[best.x][best.y]))
			{
				best = new Point(v, i);
				result = p;
				first[u][curr] = i;
			}
		}
//		System.out.println(u + " " + curr + " " + result + " " + first[u][curr] + " " + best);
		next[u][curr] = best;
		return dp[u][curr] = result;
	}
}