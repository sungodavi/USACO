/*
ID: sungoda1
LANG: JAVA
TASK: butter
 */

import java.util.*;
import java.io.*;
import java.awt.Point;

public class butter 
{
	public static int dijkstras(int[][] a, int source, HashMap<Integer, Integer> cows)
	{
		int cowsLeft = cows.size();
		PriorityQueue<Point> q = new PriorityQueue<Point>(new Comparator<Point>()
				{
			public int compare(Point x, Point y)
			{
				return x.y - y.y;
			}
				});
		int[] dist = new int[a.length];
		int sumDist = 0;
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[source] = 0;
		q.add(new Point(source, 0));
		boolean[] visited = new boolean[a.length];
		while(!q.isEmpty() && cowsLeft > 0)
		{
			Point p = q.remove();
			int r = p.x;
			if(visited[r])
				continue;
			visited[r] = true;
			if(cows.containsKey(r))
			{
				cowsLeft--;
				sumDist += cows.get(r) * dist[r];
			}
			for(int c = 0; c < a.length; c++)
			{
				if(a[r][c] > 0 && !visited[c])
				{
					int newDist = a[r][c] + dist[r];
					if(dist[c] > newDist)
						dist[c] = newDist;
					q.add(new Point(c, dist[c]));
				}
			}
		}
		return sumDist;		
	}
	
	public static int minPath(int[][] a, HashMap<Integer, Integer> cows)
	{
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		for(int r = 0; r < a.length; r++)
			q.add(dijkstras(a, r, cows));
		return q.remove();
	}
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("butter.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"butter.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int cows = Integer.parseInt(st.nextToken());
		int size = Integer.parseInt(st.nextToken());
		int[][] a = new int[size][size];
		int paths = Integer.parseInt(st.nextToken());
		
		HashMap<Integer, Integer> set = new HashMap<Integer, Integer>();
		
		for(int i = 0; i < cows; i++)
		{
			int num = Integer.parseInt(f.readLine()) - 1;
			if(set.containsKey(num))
				set.put(num, set.get(num) + 1);
			else
				set.put(num, 1);
		}
		
		//System.out.println(set);
		for(int i = 0; i < paths; i++)
		{
			st = new StringTokenizer(f.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			a[r][c] = a[c][r] = d;
		}
		out.println(minPath(a, set));
		out.close();
	}
}
