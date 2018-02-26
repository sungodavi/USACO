/*
ID: sungoda1
LANG: JAVA
TASK: race3
*/
import java.util.*;
import java.io.*;

class race3 
{
	static int size, counter;
	static int[] low, num, parent;
	static boolean[] ap;
	static LinkedList<Integer>[] list = new LinkedList[51];
	static LinkedList<Integer>[] undirected = new LinkedList[51];
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("race3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("race3.out")));
		StringTokenizer st;
		for(int i = 0; i < undirected.length; i++)
			undirected[i] = new LinkedList<Integer>();
		for(String input = f.readLine(); !input.equals("-1"); input = f.readLine(), size++)
		{
			st = new StringTokenizer(input);
			list[size] = new LinkedList<Integer>();
			for(int num = Integer.parseInt(st.nextToken()); num != -2; num = Integer.parseInt(st.nextToken()))
			{
				list[size].add(num);
				undirected[size].add(num);
				undirected[num].add(size);
			}
		}
		LinkedList<Integer> result1 = new LinkedList<Integer>();
		for(int i = 1; i < size - 1; i++)
		{
			boolean[] visited = new boolean[size];
			visited[i] = true;
			if(!isConnected(0, visited))
				result1.add(i);
		}
		LinkedList<Integer> result2 = new LinkedList<Integer>();
		low = new int[size];
		num = new int[size];
		parent = new int[size];
		Arrays.fill(low, -1);
		ap = new boolean[size];
		counter = 0;
		dfs(0);
		for(int i = 1; i < size - 1; i++)
		{
			if(ap[i])
			{
				boolean[] course = new boolean[size];
				checkSplit(0, i, course, new boolean[size]);
				//System.out.println(Arrays.toString(course));
				if(checkSplit(i, size - 1, course, new boolean[size]))
					result2.add(i);
			}
		}
		out.print(result1.size());
		for(int num : result1)
			out.print(" " + num);
		out.println();
		out.print(result2.size());
		for(int num : result2)
			out.print(" " + num);
		out.println();
		out.close();
	}
	
	public static void dfs(int u)
	{
		low[u] = num[u] = counter++;
		for(int v : undirected[u])
		{
			if(low[v] == -1)
			{
				parent[v] = u;
				dfs(v);
				low[u] = Math.min(low[u], low[v]);
				if(low[v] >= num[u])
					ap[u] = true;
			}
			else if(v != parent[u])
				low[u] = Math.min(low[u], num[v]);
		}
	}
	
	public static boolean isConnected(int u, boolean[] visited)
	{
		if(visited[u])
			return false;
		visited[u] = true;
		if(u == size - 1)
			return true;
		for(int v : list[u])
			if(!visited[v] && isConnected(v, visited))
				return true;
		return false;
	}
	
	public static boolean checkSplit(int u, int end, boolean[] course, boolean[] visited)
	{
		if(u == end)
			return true;
		if(course[u])
			return false;
		visited[u] = true;
		for(int v : list[u])
			if(!visited[v] && !checkSplit(v, end, course, visited))
				return false;
		
		course[u] = true;
		return true;
	}
}