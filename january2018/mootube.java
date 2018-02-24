import java.util.*;
import java.io.*;

public class mootube {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("mootube.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int size = Integer.parseInt(st.nextToken());
		int queries = Integer.parseInt(st.nextToken());
		
		UnionFind uf = new UnionFind(size);
		Edge[] edges = new Edge[size - 1];
		Q[] q = new Q[queries];
		int[] result = new int[queries];
		for(int i = 0; i < size - 1; i++)
		{
			st = new StringTokenizer(f.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(u, v, w);
		}
		for(int i = 0; i < queries; i++)
		{
			st = new StringTokenizer(f.readLine());
			int k = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			q[i] = new Q(k, u, i);
		}
		
		Arrays.sort(q);
		Arrays.sort(edges);
//		System.out.println(Arrays.toString(q));
//		System.out.println(Arrays.toString(edges));
		
		int index = 0;
		for(Q query : q)
		{
			while(index < edges.length && edges[index].w >= query.k)
			{
				uf.union(edges[index].u, edges[index].v);
//				System.out.println(Arrays.toString(uf.parent));
//				System.out.println(Arrays.toString(uf.rank));
//				System.out.println(Arrays.toString(uf.size));
				index++;
			}
			result[query.index] = uf.size(query.u);
		}
		
		for(int n : result)
			out.println(n);
		out.close();
	}
	
	static class Q implements Comparable<Q>
	{
		int k, u, index;
		public Q(int k, int u, int index)
		{
			this.k = k;
			this.u = u;
			this.index = index;
		}
		
		public int compareTo(Q q)
		{
			return q.k - k;
		}
		
		public String toString()
		{
			return k + " " + u;
		}
	}
	
	static class Edge implements Comparable<Edge>
	{
		int u, v, w;
		public Edge(int u, int v, int w)
		{
			this.u = u;
			this.v = v;
			this.w = w;
		}
		
		public int compareTo(Edge e)
		{
			return Integer.compare(e.w, w);
		}
		
		public String toString()
		{
			return u + " " + v + " " + w;
		}
	}
	
	static class UnionFind
	{
		int[] parent;
		int[] size;
		int[] rank;
		public UnionFind(int size)
		{
			parent = new int[size + 1];
			this.size = new int[size + 1];
			rank = new int[size + 1];
			Arrays.fill(this.size, 1);
			for(int i = 1; i <= size; i++)
				parent[i] = i;
		}
		
		public int root(int p)
		{
			while(p != parent[p])
			{
				parent[p] = parent[parent[p]];
				p = parent[p];
			}
			return p;
		}
		
		public int size(int p)
		{
			return size[root(p)] - 1;
		}
		
		public void union(int a, int b)
		{
			int p = root(a);
			int q = root(b);
			if(p == q)
				return;
			if(rank[p] <= rank[q])
			{
				if(rank[p] == rank[q])
					rank[q]++;
				parent[p] = q;
				size[q] += size[p];
			}
			else
			{
				parent[q] = p;
				size[p] += size[q];
			}
		}
	}
}