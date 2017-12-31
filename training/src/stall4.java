/*
ID: sungoda1
LANG: JAVA
TASK: stall4
 */

import java.util.*;
import java.io.*;

public class stall4
{
	static int[][] a;
	static int[] prev;
	static int end;
	static final int INF = Integer.MAX_VALUE;
	static int cows, stalls;
	static int f;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("stall4.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stall4.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		cows = Integer.parseInt(st.nextToken());
		stalls = Integer.parseInt(st.nextToken());
		int size = cows + stalls + 2;
		a = new int[size][size];
		for(int i = 1; i <= cows; i++)
			a[0][i] = a[cows + i][a.length - 1] = 1;
		end = size - 1;
		for(int cow = 1; cow <= cows; cow++)
		{
			st = new StringTokenizer(f.readLine());
			int edges = Integer.parseInt(st.nextToken());
			for(int i = 0; i < edges; i++)
			{
				int stall = Integer.parseInt(st.nextToken());
				a[cow][cows + stall] = 1;
			}
		}
		out.println(maxFlow());
		out.close();
	}
	
	public static int maxFlow()
	{
		int mf = 0;
		prev = new int[a.length];
		while(true)
		{
			Arrays.fill(prev, -1);
			f = 0;
			prev[0] = 0;
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(0);
			while(!q.isEmpty())
			{
				int r = q.poll();
				if(r == end)
					break;
//				int[] bounds = getBounds(r);
				for(int c = 0; c < a.length; c++)
				{
					if(a[r][c] > 0 && prev[c] == -1)
					{
						prev[c] = r;
						q.add(c);
					}
				}
			}
			augment(end, INF);
			if(f == 0)
				return mf;
			mf += f;
		}
	}
	
	public static void augment(int v, int minEdge)
	{
		if(v == 0)
			f = minEdge;
		else
		{
			int u = prev[v];
			if(u == -1)
				return;
			augment(u, Math.min(minEdge, a[u][v]));
			a[u][v] -= f;
			a[v][u] += f;
		}
	}
	
//	public static int[] getBounds(int r)
//	{
//		if(r == 0)
//			return new int[]{1, cows};
//		if(r <= cows)
//			return new int[]{cows + 1, cows + stalls};
//		return new int[]{a.length - 1, a.length - 1};
//	}
}