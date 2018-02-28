import java.util.*;
import java.io.*;

public class moocast
{
	public static void main(String[] args) throws IOException
	{
//		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		BufferedReader f = new BufferedReader(new FileReader("moocast.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
		StringTokenizer st;
		
		int size = Integer.parseInt(f.readLine());
		Point[] a = new Point[size];
		Edge[] edges = new Edge[size * (size - 1) >> 1];
		for(int i = 0; i < size; i++)
		{
			st = new StringTokenizer(f.readLine());
			a[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		int index = 0;
		for(int i = 0; i < size - 1; i++)
			for(int j = i + 1; j < size; j++)
				edges[index++] = new Edge(i, j, dist(a[i], a[j]));
		UnionFind uf = new UnionFind(size);
		Arrays.sort(edges);
		long best = 0;
		for(int i = 0; i < edges.length && uf.size > 1; i++)
		{
			Edge e = edges[i];
			if(uf.union(e.u, e.v))
				best = e.w;
		}
		out.println(best);
		out.close();
	}
	
	static long dist(Point a, Point b)
	{
		long dx = a.x - b.x;
		long dy = a.y - b.y;
		return dx * dx + dy * dy;
	}
	
	static class UnionFind
	{
		int[] parent;
		int[] rank;
		int size;
		public UnionFind(int size)
		{
			this.size = size;
			parent = new int[size];
			rank = new int[size];
			for(int i = 1; i < size; i++)
				parent[i] = i;
		}
		
		public int root(int p)
		{
			while(p != parent[p])
				p = parent[p] = parent[parent[p]];
			return p;
		}
		
		public boolean union(int a, int b)
		{
			int p = root(a);
			int q = root(b);
			if(p == q)
				return false;
			size--;
			if(rank[p] <= rank[q])
			{
				if(rank[p] == rank[q])
					rank[q]++;
				parent[p] = q;
			}
			else
				parent[q] = p;
			return true;
		}
	}
	
	static class Edge implements Comparable<Edge>
	{
		int u, v;
		long w;
		public Edge(int u, int v, long w)
		{
			this.u = u;
			this.v = v;
			this.w = w;
		}
		
		public int v(int n)
		{
			return n == u ? v : u;
		}
		
		public int compareTo(Edge e)
		{
			return Long.compare(w, e.w);
		}
	}
	static class Point
	{
		int x, y;
		public Point(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}
}