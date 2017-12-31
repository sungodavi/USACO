/*
ID: sungoda1
LANG: JAVA
TASK: nuggets
 */

import java.util.*;
import java.io.*;
import java.awt.Point;

public class nuggets
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("nuggets.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nuggets.out")));
		
		int[] sizes = new int[Integer.parseInt(f.readLine())];
		int sum = 0;
		int gcd = 0;
		for(int i = 0; i < sizes.length; i++)
		{
			int n = Integer.parseInt(f.readLine());
			if(gcd == 0)
				gcd = n;
			else
				gcd = gcd(gcd, n);
			sizes[i] = n;
			sum += n;
		}
		Arrays.sort(sizes);
		if(gcd > 1 || sizes[0] == 1)
		{
			out.println("0");
			out.close();
			return;
		}
		out.println(dijkstras(sizes));
		out.close();
	}
	
	static int dijkstras(int[] a)
	{
		int size = a[0];
		int[] dist = new int[size];
		Arrays.fill(dist, (int)1e9);
		boolean[] visited = new boolean[size];
		dist[0] = 0;
		PriorityQueue<Point> pq = new PriorityQueue<Point>(new Comparator<Point>() {
			public int compare(Point a, Point b)
			{
				return a.y - b.y;
			}
		});
		pq.add(new Point(0, 0));
		while(!pq.isEmpty())
		{
			int r = pq.poll().x;
			if(visited[r])
				continue;
			visited[r] = true;
			for(int i = 1; i < a.length; i++)
			{
				int c = (r + a[i]) % size;
				if(!visited[c] && dist[c] > dist[r] + a[i])
				{
					dist[c] = dist[r] + a[i];
					pq.add(new Point(c, dist[c]));
				}
			}
		}
		int best = 0;
		for(int num : dist)
			best = Math.max(num, best);
		return best - size;
	}
	
	static int gcd(int x, int y)
	{
		while(y > 0)
		{
			int temp = y;
			y = x % y;
			x = temp;
		}
		return x;
	}
}