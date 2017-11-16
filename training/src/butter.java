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
	public static int dijkstras(HashMap<Integer, ArrayList<Point>> map, int size, int source, int[] cows)
	{
		PriorityQueue<Point> q = new PriorityQueue<Point>(new Comparator<Point>()
				{
			public int compare(Point x, Point y)
			{
				return x.y - y.y;
			}
				});
		int[] dist = new int[size + 1];
		boolean[] visited = new boolean[size + 1];
		int sumDist = 0;
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[source] = 0;
		q.add(new Point(source, 0));
		//System.out.println(cows);
		while(!q.isEmpty())
		{
			Point p = q.remove();
			int r = p.x;
			if(visited[r])
				continue;
			visited[r] = true;
			//System.out.println(p);
			for(Point edge : map.get(r))
			{
				if(!visited[edge.x])
				{
					int newDist = edge.y + dist[r];
					if(dist[edge.x] > newDist)
						dist[edge.x] = newDist;
					q.add(new Point(edge.x, dist[edge.x]));
				}
			}
		}
		for(int cow : cows)
			sumDist += dist[cow];
		return sumDist;		
	}
	
	public static int minPath(HashMap<Integer, ArrayList<Point>> map, int size, int[] cows)
	{
		int max = Integer.MAX_VALUE;
		for(int r = 1; r <= size; r++)
		{
			//System.out.println(dijkstras(map, size, r, cows));
			max = Integer.min(max, dijkstras(map, size, r, cows));
		}
		return max;
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
		int[] cows = new int[Integer.parseInt(st.nextToken())];
		int size = Integer.parseInt(st.nextToken());
		int[][] a = new int[size][size];
		HashMap<Integer, ArrayList<Point>> map = new HashMap<Integer, ArrayList<Point>>();
		
		int paths = Integer.parseInt(st.nextToken());
		
		HashMap<Integer, Integer> set = new HashMap<Integer, Integer>();
		
		for(int i = 0; i < cows.length; i++)
		{
			cows[i] = Integer.parseInt(f.readLine());
		}
		
		//System.out.println(set);
		for(int i = 0; i < paths; i++)
		{
			st = new StringTokenizer(f.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			if(!map.containsKey(r))
				map.put(r, new ArrayList<Point>());
			if(!map.containsKey(c))
				map.put(c, new ArrayList<Point>());
			map.get(r).add(new Point(c, d));
			map.get(c).add(new Point(r, d));
		}
		//System.out.println(map);
		out.println(minPath(map, size, cows));
		out.close();
	}
}
