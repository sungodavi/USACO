/*
ID: sungoda1
LANG: JAVA
TASK: visitfj
 */

import java.util.*;
import java.io.*;

public class visitfj //WRONG
{
	static int[][] a;
	static int[][][] dist;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("visitfj.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"visitfj.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int size = Integer.parseInt(st.nextToken());
		int cost = Integer.parseInt(st.nextToken());
		a = new int[size][size];
		dist = new int[size][size][3];
		for(int[][] temp : dist)
			for(int[] temp2 : temp)
			Arrays.fill(temp2, (int)1e9);
		for(int r = 0; r < a.length; r++)
		{
			st = new StringTokenizer(f.readLine());
			for(int c = 0; c < a.length; c++)
				a[r][c] = Integer.parseInt(st.nextToken());
		}

		
		Queue<Point> q = new PriorityQueue<Point>();
		q.add(new Point(0,0,0,0));
		dist[0][0][0] = 0;
		while(!q.isEmpty())
		{
			Point p = q.poll();
			if(!p.isValid())
				continue;
			if(p.x == a.length - 1 && p.y == a.length - 1)
			{
				out.println(p.t);
				out.close();
				return;
			}
			for(int i = 0; i < dx.length; i++)
			{
				int x = p.x + dx[i];
				int y = p.y + dy[i];
				int d = (p.d + 1) % 3;
				if(isValid(x, y) && dist[x][y][d] == 1e9)
				{
					if(p.d > 0 && p.d % 3 == 2)
					{
						if(dist[x][y][d] > dist[p.x][p.y][p.d] + cost + a[x][y])
						{
							dist[x][y][d] = dist[p.x][p.y][p.d] + cost + a[x][y];
							q.add(new Point(x, y, d, dist[x][y][d]));
						}
					}
					else if(dist[x][y][d] > dist[p.x][p.y][p.d] + cost)
					{
						dist[x][y][d] = dist[p.x][p.y][p.d] + cost;
						q.add(new Point(x, y, d, dist[x][y][d]));
					}
				}
			}
		}
		for(int[][] temp : dist)
		{
			for(int[] temp2: temp)
				System.out.println(Arrays.toString(temp2));
			System.out.println();
		}
	}
	
	static boolean isValid(int x, int y)
	{
		return x >= 0 && y >= 0 && x < a.length && y < a.length;
	}
	static class Point implements Comparable<Point>
	{
		int x, y, d, t;
		public Point(int x, int y, int d, int t)
		{
			this.x = x;
			this.y = y;
			this.d = d;
			this.t = t;
		}
		
		public boolean isValid()
		{
			return x >= 0 && y >= 0 && x < a.length && y < a.length;
		}
		
		public int compareTo(Point p)
		{
			return t - p.t;
		}
	}
}