import java.util.*;
import java.io.*;

public class maxflow 
{
	static LinkedList<Integer>[] list;
	static boolean[] visited;
	static int[] depth, first, seq, start, end;
	static int[][] lca;
	static int result, index;
	public static void main(String[] args) throws IOException 
	{
//		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		BufferedReader f = new BufferedReader(new FileReader("maxflow.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maxflow.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int size = Integer.parseInt(st.nextToken());
		int roads = Integer.parseInt(st.nextToken());
		list = new LinkedList[size + 1];
		for(int i = 1; i <= size; i++)
			list[i] = new LinkedList<Integer>();
		for(int i = 0; i < size - 1; i++)
		{
			st = new StringTokenizer(f.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			list[u].add(v);
			list[v].add(u);
		}
		depth = new int[2 * size - 1];
		seq = new int[2 * size - 1];
		first = new int[size + 1];
		visited = new boolean[size + 1];
		dfs(1, 0);
		build();
//		System.out.printf("%s\n%s\n%s\n\n", Arrays.toString(first), Arrays.toString(seq), Arrays.toString(depth));
		for(int[] temp : lca)
			System.out.println(Arrays.toString(temp));
		start = new int[size + 1];
		end = new int[size + 1];
		while(roads-- > 0)
		{
			st = new StringTokenizer(f.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			start[u]++;
			start[v]++;
//			System.out.println("lca" + u + " " + v + " " + lca(u, v));
			int lca = lca(u, v);
			end[lca]++;
			start[lca]--;
		}
//		System.out.println(Arrays.toString(start));
//		System.out.println(Arrays.toString(end));
		visited = new boolean[size + 1];
		dfs(1);
		out.println(result);
		out.close();
	}
	
	public static int lg(int num)
	{
		return 31 - Integer.numberOfLeadingZeros(num);
	}
	public static void build()
	{
		lca = new int[lg(depth.length) + 1][depth.length];
		for(int i = 0; i < depth.length; i++)
			lca[0][i] = i;
		for(int k = 1; k < lca.length; k++)
		{
			int flag = 1 << k - 1;
			for(int j = 0; j + flag < seq.length; j++)
			{
				if(depth[lca[k - 1][j]] < depth[lca[k - 1][j + flag]])
					lca[k][j] = lca[k - 1][j];
				else
					lca[k][j] = lca[k - 1][j + flag];
			}
		}
	}
	
	public static int lca(int u, int v)
	{
		int e = first[v];
		int s = first[u];
		if(s > e)
		{
			int temp = s;
			s = e;
			e = temp;
		}
		int k = lg(e - s + 1);
		if(depth[lca[k][s]] < depth[lca[k][e - (1 << k) + 1]])
			return seq[lca[k][s]];
		return seq[lca[k][e - (1 << k) + 1]];
	}
	
	public static int dfs(int u)
	{
		visited[u] = true;
		int count = start[u];
		for(int v : list[u])
			if(!visited[v])
				count += dfs(v);
		result = Math.max(result, count);
		return count - end[u];
	}
	
	public static void dfs(int u, int d)
	{
		if(visited[u])
			return;
		visited[u] = true;
		depth[index] = d;
		first[u] = index;
		seq[index] = u;
		index++;
		for(int v : list[u])
		{
			if(!visited[v])
			{
				dfs(v, d + 1);
				seq[index] = u;
				depth[index] = d;
				index++;
			}
		}
	}
}