/*
ID: sungoda1
LANG: JAVA
TASK: schlnet
 */

import java.util.*;
import java.io.*;

public class schlnet
{
	static int[] scc;
	static int count;
	static boolean[] visited, child;
	static Stack<Integer> st;
	static LinkedList<Integer>[] list, reverse;
	public static void main(String[] args) throws IOException
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("schlnet.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("schlnet.out")));
		StringTokenizer st;
		int size = Integer.parseInt(f.readLine());
		list = new LinkedList[size];
		reverse = new LinkedList[size];
		for(int i = 0; i < size; i++)
		{
			list[i] = new LinkedList<Integer>();
			reverse[i] = new LinkedList<Integer>();
		}
		for(int i = 0; i < size; i++)
		{
			st = new StringTokenizer(f.readLine());
			for(int j = Integer.parseInt(st.nextToken()); j != 0; j = Integer.parseInt(st.nextToken()))
			{
				j--;
				list[i].add(j);
				reverse[j].add(i);
			}
		}
		scc();
		System.out.println(Arrays.toString(scc));
		int top = count(reverse);
		int bottom = count(list);
		out.println(top);
		out.println(a - 2 + count);
		out.close();
		System.out.println(System.currentTimeMillis() - start);
		System.exit(0);
	}
	
	public static void scc()
	{
		visited = new boolean[list.length];
		scc = new int[list.length];
		st = new Stack<Integer>();
		count = 0;
		for(int i = 0; i < list.length; i++)
			if(!visited[i])
				dfs(i);
		Arrays.fill(scc, -1);
		visited = new boolean[list.length];
		while(!st.isEmpty())
		{
			int u = st.pop();
			if(!visited[u])
				assign(u, count++);
		}
	}
	
	public static int count(LinkedList<Integer>[] list)
	{
		int result = 0;
		visited = new boolean[list.length];
		child = new boolean[count];
		for(int i = 0; i < visited.length; i++)
			if(!visited[i])
				root(i, list);
		for(boolean b : child)
			if(!b)
				result++;
		return result;
	}
	
	public static void root(int u, LinkedList<Integer>[] list)
	{
		if(visited[u])
			return;
		visited[u] = true;
		for(int v : list[u])
		{
			if(scc[v] != scc[u])
				child[scc[u]] = true;
			dfs(v);
		}
	}
	
	public static void dfs(int u)
	{
		if(visited[u])
			return;
		visited[u] = true;
		for(int v : list[u])
			dfs(v);
		st.push(u);
	}
	
	public static void assign(int u, int root)
	{
		if(visited[u])
			return;
		visited[u] = true;
		scc[u] = root;
		for(int v : reverse[u])
			assign(v, root);
	}
}