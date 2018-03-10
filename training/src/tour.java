/*
ID: sungoda1
LANG: JAVA
TASK: tour
 */

import java.util.*;
import java.io.*;

public class tour
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("tour.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("tour.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int size = Integer.parseInt(st.nextToken());
		int roads = Integer.parseInt(st.nextToken());
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(int i = 0; i < size; i++)
			map.put(f.readLine().trim(), i);
		boolean[][] a = new boolean[size][size];
		for(int i = 0; i < roads; i++)
		{
			st = new StringTokenizer(f.readLine());
			int u = map.get(st.nextToken());
			int v = map.get(st.nextToken());
			a[u][v] = a[v][u] = true;
		}
		int[][] dp = new int[size][size];
		for(int[] temp : dp)
			Arrays.fill(temp, -9);
		dp[0][0] = 1;
		for(int r = 0; r < size; r++)
			for(int c = r + 1; c < size; c++)
			{
				for(int k = 0; k < c; k++)
				{
					if(a[k][c] && dp[r][k] > 0)
						dp[r][c] = Math.max(dp[r][c], dp[r][k] + 1);
				}
				dp[c][r] = dp[r][c];
			}
//		for(int[] temp : dp)
//			System.out.println(Arrays.toString(temp));
		int result = 0;
		for(int r = 0; r < size - 1; r++)
			if(a[r][size - 1])
				result = Math.max(result, dp[r][size - 1]);
		if(result > 0)
			out.println(result);
		else
			out.println(1);
		out.close();
		System.exit(0);
	}
	
	public static int max(int[] a)
	{
		int max = a[0];
		for(int num : a)
			max = Math.max(max, num);
		return max;
	}
}