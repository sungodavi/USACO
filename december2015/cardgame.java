import java.util.*;
import java.io.*;

public class cardgame 
{
	static LinkedList<Edge>[] list;
	static Edge[] prev;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("cardgame.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cardgame.out")));
//		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int size = Integer.parseInt(f.readLine());
		boolean[] a = new boolean[1 + (size << 1)];
		int[] left = new int[size];
		int[] right = new int[size];
		for(int i =0 ; i < size; i++)
			a[right[i] = Integer.parseInt(f.readLine())] = true;
		int index =0;
		for(int i = 1; i <= size << 1; i++)
			if(!a[i])
				left[index++] = i;
		list = new LinkedList[2 * size + 2];
		prev = new Edge[list.length];
		for(int i = 0; i < list.length; i++)
			list[i] = new LinkedList<Edge>();
		for(int i = 1; i <= size; i++)
		{
			Edge e = new Edge(0, i);
			Edge e2 = new Edge(i + size, list.length - 1);
			list[0].add(e); list[i].add(e);
			list[i + size].add(e2); list[list.length - 1].add(e2);
		}
		for(int i = 0; i < left.length; i++)
		{
			for(int j = 0; j < right.length >> 1; j++)
			{
				if(left[i] > right[j])
				{
					Edge e = new Edge(i  + 1, j + size + 1);
					list[i + 1].add(e); list[j + size + 1].add(e);
				}
			}
			for(int j = right.length >> 1; j < right.length; j++)
			{
				if(left[i] < right[j])
				{
					Edge e = new Edge(i  + 1, j + size + 1);
					list[i + 1].add(e); list[j + size + 1].add(e);
				}
			}
		}
		
		out.println(maxFlow());
		out.close();
	}
	
	public static int maxFlow()
	{
		int flow = 0;
		while(true)
		{
			prev = new Edge[list.length];
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(0);
			while(!q.isEmpty())
			{
				int u = q.poll();
				if(u == list.length - 1)
					break;
				for(Edge e : list[u])
				{
					int v = e.other(u);
					if(e.canUse(u) && prev[v] == null)
					{
						prev[v] = e;
						q.add(v);
					}
				}
			}
			//System.out.println(Arrays.toString(prev));
			if(prev[list.length - 1] == null)
				return flow;
			augment(list.length - 1);
			flow++;
		}
	}
	
	public static void augment(int v)
	{
		while(v != 0)
		{
			Edge e = prev[v];
			e.back = !e.back;
			v = e.other(v);
		}
	}
	
	static class Edge
	{
		int u, v;
		boolean back;
		
		public Edge(int u, int v)
		{
			this.u = u;
			this.v = v;
		}
		
		public int other(int x)
		{
			if(x == u)
				return v;
			return u;
		}
		
		public boolean canUse(int x)
		{
			return x == u && !back || x == v && back;
		}
	}
}