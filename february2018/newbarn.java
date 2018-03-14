import java.util.*;
import java.io.*;
import java.awt.Point;

public class newbarn 
{
	static LinkedList<Integer>[] list;
	static int size, index;
	static int[] h, e, l, dist;
	static int[] prev;
	static boolean[] visited;
	static int[][] dp;
	static final int INF = (int)1e9;
	public static void main(String[] args) throws IOException 
	{
//		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		BufferedReader f = new BufferedReader(new FileReader("newbarn.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("newbarn.out")));
		StringTokenizer st;
		int times = Integer.parseInt(f.readLine());
		list = new LinkedList[times + 1];
		prev = new int[times + 1];
		LinkedList<Point> queries = new LinkedList<Point>();
		while(times-- > 0)
		{
			st = new StringTokenizer(f.readLine());
			char type = st.nextToken().charAt(0);
			int num = Integer.parseInt(st.nextToken());
			if(type == 'B')
			{
				list[++size] = new LinkedList<Integer>();
				if(num != -1)
				{
					list[size].add(num);
					list[num].add(size);
					prev[size] = num;
				}
			}
			else
			{
				queries.add(new Point(num, size));
			}
		}
		h = new int[size + 1];
		e = new int[2 * size - 1];
		l = new int[2 * size - 1];
		visited = new boolean[size + 1];
		dfs(1, 0);
		build();
		dist = new int[size + 1];
		Arrays.fill(dist, INF);
		dist[0] = dist[1] = 0;
		dfs(1);
		Point[] diameters = new Point[size + 1];
		int u = 1, v = 1;
		diameters[1] = new Point(u, v);
		for(int i = 2; i <= size; i++)
		{
			if(dist(i, v) > dist(u, v))
				u = i;
			else if(dist(u, i) > dist(u, v))
				v = i;
			diameters[i] = new Point(u, v);
		}
		for(Point p : queries)
		{
			Point diameter = diameters[p.y];
			out.println(Math.max(dist(p.x, diameter.x), dist(p.x, diameter.y)));
		}
		out.close();
	}
	
	public static int dist(int u, int v)
	{
		return dist[u] + dist[v] - 2 * dist[lca(u, v)];
	}
	
	public static int lg(int num)
	{
		return 31 - Integer.numberOfLeadingZeros(num);
	}
	
	public static int lca(int u, int v)
	{
		if(h[u] < h[v])
			return e[rmq(h[u], h[v])];
		return e[rmq(h[v], h[u])];
	}
	
	public static int rmq(int s, int e)
	{
		int k = lg(e - s + 1);
		if(l[dp[k][s]] < l[dp[k][e - (1 << k) + 1]])
			return dp[k][s];
		return dp[k][e - (1 << k) + 1];
	}
	
	public static void build()
	{
		int n = l.length;
		dp = new int[lg(n) + 1][n];
		for(int i = 0; i < n; i++)
			dp[0][i] = i;
		for(int i = 1; i < dp.length; i++)
		{
			int flag = 1 << i - 1;
			for(int j = 0; j + flag < n; j++)
			{
				if(l[dp[i - 1][j]] < l[dp[i - 1][j + flag]])
					dp[i][j] = dp[i - 1][j];
				else
					dp[i][j] = dp[i - 1][j + flag];
			}
		}
	}
	
	public static void dfs(int u)
	{
		for(int v : list[u])
		{
			if(dist[v] == INF)
			{
				dist[v] = dist[u] + 1;
				dfs(v);
			}
		}
	}
	
	public static void dfs(int u, int d)
	{
		visited[u] = true;
		h[u] = index;
		e[index] = u;
		l[index] = d;
		index++;
		for(int v : list[u])
		{
			if(!visited[v])
			{
				dfs(v, d + 1);
				e[index] = u;
				l[index] = d;
				index++;
			}
		}
	}
}