/*
ID: sungoda1
LANG: JAVA
TASK: telecow
*/

import java.util.*;
import java.io.*;

class telecow 
{
	static int[][] a;
	static final int INF = (int)1e9;
	static int[] prev;
	static int src, sink;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("telecow.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("telecow.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int size = Integer.parseInt(st.nextToken());
		int roads = Integer.parseInt(st.nextToken());
		src = Integer.parseInt(st.nextToken()) - 1;
		sink = Integer.parseInt(st.nextToken()) - 1;
		int[][] matrix = new int[size * 2][size * 2];
		for(int r = 0; r < size; r++)
			matrix[in(r)][out(r)] = 1;
		matrix[in(src)][out(src)] = matrix[in(sink)][out(sink)] = INF;
		for(int i = 0; i < roads; i++)
		{
			st = new StringTokenizer(f.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			matrix[out(u)][in(v)] = matrix[out(v)][in(u)] = INF;
		}
		src = in(src);
		sink = out(sink);
		a = new int[matrix.length][matrix.length];
		prev = new int[a.length];
		int result = maxFlow(matrix);
		out.println(result);
		ArrayList<Integer> list = new ArrayList<Integer>(result);
		for(int i = 0; i < a.length && list.size() < result; i += 2)
		{
			if(i == src || i + 1 == sink)
				continue;
			matrix[i][i + 1] = 0;
			int flow = maxFlow(matrix);
			if(flow != result - list.size())
			{
				list.add(i / 2 + 1);
			}
			else
				matrix[i][i + 1] = 1;
		}
		out.print(list.get(0));
		for(int i = 1; i < list.size(); i++)
			out.print(" " + list.get(i));
		out.println();
		out.close();
		System.exit(0);
	}
	
	public static int maxFlow(int[][] matrix)
	{
		copy(matrix);
		int mf = 0;
		while(true)
		{
			Arrays.fill(prev, -1);
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(src);
			while(!q.isEmpty())
			{
				int r = q.poll();
				if(r == sink)
					break;
				for(int c = 0; c < a.length; c++)
				{
					if(a[r][c] > 0 && prev[c] == -1)
					{
						prev[c] = r;
						q.add(c);
					}
				}
			}
			if(prev[sink] == -1)
				break;
			int f = augment(sink, INF);
			if(f == 0)
				break;
			mf += f;
		}
		return mf;
	}
	
	public static int augment(int v, int min)
	{
		if(v == src)
			return min;
		int u = prev[v];
		int f = augment(u, Math.min(min, a[u][v]));
		a[u][v] -= f;
		a[v][u] += f;
		return f;
	}
	
	public static void copy(int[][] matrix)
	{
		for(int r = 0; r < matrix.length; r++)
			System.arraycopy(matrix[r], 0, a[r], 0, a[0].length);
	}
	
	public static int in(int n)
	{
		return 2 * n;
	}
	
	public static int out(int n)
	{
		return 2 * n + 1;
	}
}