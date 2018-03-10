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
	static int[][] dp, prev, used;
	static int[] a;
	static int INF = (int)200;
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
		solve(num);
		rebuild(out);
		System.out.println(System.currentTimeMillis() - start);
		out.close();
	}
	
	public static void rebuild(PrintWriter out)
	{
		int curr = dp[0].length - 1;
		int r = dp.length - 1;
		int size = dp[r][curr];
		out.print(size);
		for(int i = 0; i < size; i++)
		{
			out.print(" " + prev[r][curr]);
			curr -= prev[r][curr] * used[r][curr];
		}
		out.println();
	}
	
	public static int solve(int num)
	{
		dp = new int[a.length][num + 1];
		used = new int[a.length][num + 1];
		prev = new int[a.length][num + 1];
		for(int c = 0; c < dp[0].length; c++)
		{
			dp[0][c] = INF;
			prev[0][c] = -1;
			used[0][c] = -1;
		}
		dp[0][0] = 0;
		//reverse a
		for(int i = 1; i <= a.length / 2; i++)
		{
			int temp = a[i];
			a[i] = a[a.length - i];
			a[a.length - i] = temp;
		}
		for(int r = 1; r < dp.length; r++)
		{
			System.arraycopy(dp[r - 1], 0, dp[r], 0, dp[0].length);
			System.arraycopy(prev[r - 1], 0, prev[r], 0, prev[0].length);
			System.arraycopy(used[r - 1], 0, used[r], 0, used[0].length);
			for(int c = a[r]; c < dp[0].length; c++)
			{
				int u = c - a[r];
				if(dp[r][u] < INF)
				{
					if(prev[r][u] == a[r])
					{
						dp[r][c] = dp[r][u];
						used[r][c] = used[r][u] + 1;
						prev[r][c] = a[r];
					}
					else
					{
						dp[r][c] = dp[r][u] + 1;
						used[r][c] = 1;
						prev[r][c] = a[r];
					}
					
					if(dp[r - 1][u] + 1 < dp[r][c] || (dp[r - 1][u] + 1 == dp[r][c] && check(r - 1, u, c - used[r][c] * a[r])))
					{
						dp[r][c] = dp[r - 1][u] + 1;
						used[r][c] = 1;
						prev[r][c] = a[r];
					}
				}
			}
			for(int c = 0; c < dp[0].length; c++)
			{
				if(dp[r][c] > dp[r - 1][c])
				{
					dp[r][c] = dp[r - 1][c];
					used[r][c] = used[r - 1][c];
					prev[r][c] = prev[r - 1][c];
				}
			}
		}
		return dp[a.length - 1][num];
	}
	
	public static boolean check(int r, int a, int b)
	{
		while(a > 0 && b > 0)
		{
			if(prev[r][a] < prev[r][b])
				return true;
			if(prev[r][b] < prev[r][a])
				return false;
			a -= used[r][a] * prev[r][a];
			b -= used[r][b] * prev[r][b];
		}
		return a == 0;
	}
}