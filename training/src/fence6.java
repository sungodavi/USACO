/*
ID: sungoda1
LANG: JAVA
TASK: fence6
 */

import java.util.*;
import java.io.*;
import java.awt.Point;

public class fence6
{
	static int[][] a;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("fence6.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence6.out")));
		StringTokenizer st;
		int size = Integer.parseInt(f.readLine());
		Fence[] edges = new Fence[size];
		for(int i = 0; i < size; i++)
		{
			st = new StringTokenizer(f.readLine());
			int index = Integer.parseInt(st.nextToken()) - 1;
			int length = Integer.parseInt(st.nextToken());
			int[] left = new int[Integer.parseInt(st.nextToken())];
			int[] right = new int[Integer.parseInt(st.nextToken())];
			st = new StringTokenizer(f.readLine());
			for(int k = 0; k < left.length; k++)
				left[k] = Integer.parseInt(st.nextToken()) - 1;
			st = new StringTokenizer(f.readLine());
			for(int k = 0; k < right.length; k++)
				right[k] = Integer.parseInt(st.nextToken()) - 1;
			edges[i] = new Fence(index, length, left, right);
		}
		
		Arrays.sort(edges);
		
		a = makeList(edges);
		out.println(shortestCycle());
		out.close();
	}
	
	static int shortestCycle()
	{
		int best = Integer.MAX_VALUE;
		for(int r = 0; r < a.length; r++)
			for(int c = 0; c < a.length; c++)
			{
				if(a[r][c] > 0)
				{
					int temp = a[r][c];
					a[r][c] = 0;
					best = Math.min(best, shortestPath(r, c) + temp);
					a[r][c] = temp;
				}
			}
		return best;
	}
	
	static int shortestPath(int u, int v)
	{
		PriorityQueue<Point> pq = new PriorityQueue<Point>(new Comparator<Point>(){
			public int compare(Point a, Point b)
			{
				return a.y - b.y;
			}
		});
		pq.add(new Point(u, 0));
		int[] dist = new int[a.length];
		Arrays.fill(dist, (int)1e9);
		dist[u] = 0;
		boolean[] visited = new boolean[a.length];
		while(!pq.isEmpty())
		{
			int r = pq.poll().x;
			if(visited[r])
				continue;
			if(r == v)
				return dist[r];
			for(int c = 0; c < a.length; c++)
			{
				if(a[r][c] > 0 && dist[c] > dist[r] + a[r][c])
				{
					dist[c] = dist[r] + a[r][c];
					pq.add(new Point(c, dist[c]));
				}
			}
		}
		return (int)1e9;
	}
	
//	static void dfs(int node, int d)
//	{
//		System.out.println(node + " " + d);
//		temp.push(node);
//		if(dist[node] >= 0)
//		{
//			best = Math.min(best, d - dist[node]);
//			System.out.println("Best: " + best);
//			System.out.println(temp);
//		}
//		else
//		{
//			int prev = dist[node];
//			dist[node] = d;
//			for(int c = 0; c < a.length; c++)
//			{
//				if(c != parent[node] && a[node][c] > 0)
//				{
//					int prevParent = parent[c];
//					parent[c] = node;
//					dfs(c, d + a[node][c]);
//					parent[c] = prevParent;
//				}
//			}
//			dist[node] = prev;
//		}
//		temp.pop();
//		
//	}
//	
	static int[][] makeList(Fence[] edges)
	{
		int[][] nodes = new int[edges.length][2];
		int count = 0;
		for(int r = 0; r < nodes.length; r++)
			for(int c = 0; c < nodes[0].length; c++)
				nodes[r][c] = ++count;
		
		for(Fence e : edges)
		{
			for(int n : e.left)
			{
				int index = edges[n].getSide(e.index);
				if(nodes[n][index] != nodes[e.index][0])
				{
					nodes[n][index] = nodes[e.index][0];
					count--;
				}
				
			}
			for(int n : e.right)
			{
				int index = edges[n].getSide(e.index);
				if(nodes[n][index] != nodes[e.index][1])
				{
					nodes[n][index] = nodes[e.index][1];
					count--;
				}
			}
		}
		
		int[] map = new int[2 * edges.length + 1];
		
		int index = 0;
		Arrays.fill(map, -1);
		for(int[] row : nodes)
			for(int n : row)
				if(map[n] < 0)
					map[n] = index++;
		
		int[][] a = new int[count][count];
		for(int i = 0; i < edges.length; i++)
		{
			int r = map[nodes[i][0]];
			int c = map[nodes[i][1]];
			int d = edges[i].length;
			a[r][c] = a[c][r] = d;
		}
		return a;
	}
	
	static class Fence implements Comparable<Fence>
	{
		int index;
		int length;
		int[] left, right;
		public Fence(int index, int length, int[] left, int[] right)
		{
			this.index = index;
			this.length = length;
			this.left = left;
			this.right = right;
		}
		
		public int compareTo(Fence f)
		{
			return index - f.index;
		}
		
		int getSide(int num)
		{
			if(contains(left, num))
				return 0;
			return 1;
		}
		
		static boolean contains(int[] a, int num)
		{
			for(int n : a)
				if(n == num)
					return true;
			return false;
		}
	}
}