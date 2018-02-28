/*
ID: sungoda1
LANG: JAVA
TASK: milk6
*/

import java.util.*;
import java.io.*;

class milk6 
{
	static long[][] a;
	static int[] prev;
	static final long INF = (long)1e18;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("milk6.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk6.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int size = Integer.parseInt(st.nextToken());
		int roads = Integer.parseInt(st.nextToken());
		long[][] adj = new long[size][size];
		a = new long[size][size];
		Edge[] edges = new Edge[roads];
		for(int i = 0; i < roads; i++)
		{
			st = new StringTokenizer(f.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			long w = Long.parseLong(st.nextToken()) * 1001 + 1;
			edges[i] = new Edge(u, v, w);
			adj[u][v] += w;
		}
		copy(adj);
		long temp = maxFlow();
		long mf = temp / 1001;
		long count = temp % 1001;
		out.printf("%d %d\n", mf, count);
		for(int i = 0; i < roads; i++)
		{
			Edge e = edges[i];
			adj[e.u][e.v] -= e.w;
			copy(adj);
			long nmf = maxFlow();
			if(nmf + e.w == temp)
			{
				out.println(i + 1);
				temp = nmf;
			}
			else
				adj[e.u][e.v] += e.w;
		}
		out.close();
	}
	
	public static void copy(long[][] adj)
	{
		for(int r = 0; r < a.length; r++)
			System.arraycopy(adj[r], 0, a[r], 0, a.length);
	}
	
	public static long maxFlow()
	{
		long mf = 0;
		while(true)
		{
			prev = new int[a.length];
			Arrays.fill(prev, -1);
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(0);
			while(!q.isEmpty())
			{
				int u = q.poll();
				if(u == a.length - 1)
					break;
				for(int v = 0; v < a.length; v++)
				{
					if(prev[v] == -1 && a[u][v] > 0)
					{
						prev[v] = u;
						q.add(v);
					}
				}
			}
			long f = augment(a.length - 1, INF);
			if(f == 0)
				break;
			mf += f;
		}
		return mf;
	}
	
	public static long augment(int v, long edge)
	{
		if(v == 0)
			return edge;
		int u = prev[v];
		if(u == -1)
			return 0;
		long f = augment(u, Math.min(edge, a[u][v]));
		a[u][v] -= f;
		a[v][u] += f;
		return f;
	}
	
	static class Edge
	{
		int u, v;
		long w;
		public Edge(int u, int v, long w)
		{
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}
}