/*
ID: sungoda1
LANG: JAVA
TASK: stamps
 */

import java.util.*;
import java.io.*;

public class stamps 
{
	public static int solve(int size, int[] coins)
	{
		int[] a = new int[coins[coins.length - 1] * size + 1];
		Arrays.fill(a, Integer.MAX_VALUE);
		a[0] = 0;
		for(int i = 1; i < a.length; i++)
		{
			for(int c : coins)
			{
				if(c > i)
					break;
				a[i] = Integer.min(a[i], a[i-c] + 1);
			}
			if(a[i] > size)
			{
				//System.out.println(Arrays.toString(a));
				return i - 1;
			}
		}
		System.out.println(Arrays.toString(a));
		return a.length - 1;
	}
	
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("stamps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"stamps.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int max = Integer.parseInt(st.nextToken());
		int[] coins = new int[Integer.parseInt(st.nextToken())];
		
		for(int r = 0; r < coins.length; r++)
		{
			if(!st.hasMoreTokens())
				st = new StringTokenizer(f.readLine());
			coins[r] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(coins);
		out.println(solve(max, coins));
		out.close();
	}
}

