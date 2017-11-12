/*
ID: sungoda1
LANG: JAVA
TASK: butter
 */

import java.util.*;
import java.io.*;

public class butter 
{
	public static int[] floydWarshall(int[][] a, HashMap<Integer, Integer> cows)
	{
		int[][] dist = new int[a.length][a.length];
		int[] minPaths = new int[a.length];
		for(int r = 0; r < a.length; r++)
			for(int c = 0; c < a.length; c++)
			{
				if(a[r][c] > 0)
					dist[r][c] = a[r][c];
				else if(r != c)
					dist[r][c] = Integer.MAX_VALUE;
			}
		
				
		for(int k = 0; k < a.length; k++)
		{
			for(int r = 0; r < a.length - 1; r++)
			{
				for(int c = r + 1; c < a.length; c++)
				{
					if(dist[r][k] == Integer.MAX_VALUE || dist[k][c] == Integer.MAX_VALUE)
						continue;
					
					int newPath = dist[Integer.min(r, k)][Integer.max(r, k)] + 
							dist[Integer.min(c, k)][Integer.max(c, k)];
					
					if(newPath < dist[r][c])
						dist[c][r] = dist[r][c] = newPath;
					
					if(k == a.length - 1)
					{
						if(cows.containsKey(r))
							minPaths[c] += cows.get(r) * dist[r][c];
						if(cows.containsKey(c))
							minPaths[r] += cows.get(c) * dist[r][c];
					}
				}
			}
		}
		return minPaths;
	}
	public static int minPath(int[][] a, HashMap<Integer, Integer> cows)
	{
		int[] paths = floydWarshall(a, cows);
		//System.out.println(Arrays.toString(paths));
		int min = paths[0];
		for(int i = 0; i < a.length; i++)
			if(paths[i] < min)
				min = paths[i];
		return min;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("butter.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"butter.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int cows = Integer.parseInt(st.nextToken());
		int size = Integer.parseInt(st.nextToken());
		int[][] a = new int[size][size];
		int paths = Integer.parseInt(st.nextToken());
		
		HashMap<Integer, Integer> set = new HashMap<Integer, Integer>();
		
		for(int i = 0; i < cows; i++)
		{
			int num = Integer.parseInt(f.readLine()) - 1;
			if(set.containsKey(num))
				set.put(num, set.get(num) + 1);
			else
				set.put(num, 1);
		}
		
		//System.out.println(set);
		for(int i = 0; i < paths; i++)
		{
			st = new StringTokenizer(f.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			a[r][c] = a[c][r] = d;
		}
		out.println(minPath(a, set));
		out.close();
	}
}
