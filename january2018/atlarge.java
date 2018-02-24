import java.util.*;
import java.io.*;

public class atlarge 
{
	static LinkedList<Integer>[] list;
	static int[] depth, minPath, children;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("atlarge.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("atlarge.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int size = Integer.parseInt(st.nextToken());
		int root = Integer.parseInt(st.nextToken());
		list = new LinkedList[size + 1];
		depth = new int[size + 1];
		minPath = new int[size + 1];
		children = new int[size + 1];
		visited = new boolean[size + 1];
		for(int i = 1; i <= size; i++)
			list[i] = new LinkedList<Integer>();
		for(int i =0; i < size - 1; i++)
		{
			st = new StringTokenizer(f.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			list[u].add(v);
			list[v].add(u);
		}
		if(list[root].size() == 1)
		{
			out.println(1);
			out.close();
			return;
		}
		dfs(root, 0);
//		System.out.println(Arrays.toString(depth));
//		System.out.println(Arrays.toString(minPath));		
		visited = new boolean[size + 1];
		int result = count(root);
		out.println(result);
		out.close();
	}
	
	public static int count(int u)
	{
		if(visited[u])
			return 0;
		visited[u] = true;
		if(list[u].size() == 1)
			return 1;
		if(list[u].size() > 2 && minPath[u] <= depth[u])
			return 1;
		int count = 0;
		for(int v : list[u])
			if(!visited[v])
				count += count(v);
		return count;
	}
	
	public static int dfs(int u, int d)
	{
		if(visited[u])
			return 0;
		depth[u] = d;
		visited[u] = true;
		if(list[u].size() == 1)
			return d;
		int best = Integer.MAX_VALUE;
		for(int v : list[u])
		{
			if(!visited[v])
				best = Math.min(best, dfs(v, d + 1));
		}
		minPath[u] = best - d;
		return best;
	}
}