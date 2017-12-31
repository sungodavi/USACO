/*
ID: sungoda1
LANG: JAVA
TASK: ditch
 */

import java.util.*;
import java.io.*;

public class ditch
{
	static final int INF = Integer.MAX_VALUE;
	static int end;
	static int[][] a;
	static int[] prev;
	static int f;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("ditch.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ditch.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int edges = Integer.parseInt(st.nextToken());
		int nodes = Integer.parseInt(st.nextToken());
		end = nodes - 1;
		int[][] res = new int[nodes][nodes];
		prev = new int[nodes];
		for(int i = 0; i < edges; i++)
		{
			st = new StringTokenizer(f.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			res[r][c] += w;
		}
		a = res;
		out.println(maxFlow());
		out.close();
		f.close();
	}
	
	public static int maxFlow()
	{
		int mf = 0;
		int[] dist = new int[a.length];
		while(true)
		{
			f = 0;
			Arrays.fill(dist, INF);
			dist[0] = 0;
			Arrays.fill(prev, -1);
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(0);
			while(!q.isEmpty())
			{
				int r = q.poll();
				if(r == end)
					break;
				for(int c = 0; c < a.length; c++)
				{
					if(a[r][c] > 0 && dist[c] == INF)
					{
						dist[c] = dist[r] + 1;
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
			if(u != -1)
			{
				augment(u, Math.min(minEdge, a[u][v]));
				a[u][v] -= f;
				a[v][u] += f;
			}
		}
	}
}