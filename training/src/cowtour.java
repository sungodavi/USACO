/*
ID: sungoda1
LANG: JAVA
TASK: cowtour
*/

import java.util.*;
import java.io.*;
import java.awt.Point;

class CowTourHelper
{
	double[][] a;
	double[][] dist;
	double[] farthest;
	double[] diameters;
	Point[] points;
	
	public CowTourHelper(double[][] a, Point[] points)
	{
		this.a = a;
		this.points = points;
		diameters = new double[a.length];
	}
	
	public double loadDiameter(int start)
	{
		if(diameters[start] > 0)
			return diameters[start];
		boolean[] visited = new boolean[a.length];
		ArrayList<Integer> list = new ArrayList<Integer>();
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		double d = 0;
		while(!q.isEmpty())
		{
			int r = q.poll();
			if(visited[r])
				continue;
			visited[r] = true;
			list.add(r);
			d = Double.max(farthest[r], d);
			for(int c =0; c < a.length; c++)
				if(a[r][c] > 0)
					q.add(c);
		}
		for(int r : list)
			diameters[r] = d;
		return d;
	}
	public double diameter()
	{
		dist = new double[a.length][a.length];
		for(int r = 0; r < a.length; r++)
		{
			for(int c = 0; c < a.length; c++)
			{
				if(r == c)
					continue;
				if(a[r][c] > 0)
					dist[r][c] = a[r][c];
				else
					dist[r][c] = Double.MAX_VALUE;
			}
		}
		double max = 0;
		for(int k = 0; k < a.length; k++)
		{
			for(int r = 0; r < a.length; r++)
			{
				for(int c = 0; c < a.length; c++)
				{
					if(dist[r][k] != Double.MAX_VALUE && dist[k][c] != Double.MAX_VALUE 
							&& dist[r][c] > dist[r][k] + dist[k][c])
						dist[r][c] = dist[r][k] + dist[k][c];
					if(dist[r][c] != Double.MAX_VALUE && dist[r][c] > max)
						max = dist[r][c];
				}
			}
		}
		return max;
	}
	
	public double solve()
	{
		diameter();
		farthest = new double[a.length];
		for(int r = 0; r < a.length; r++)
		{
			double max = 0;
			for(int i = 0; i < a.length; i++)
				if(dist[r][i] != Double.MAX_VALUE && dist[r][i] > max)
					max = dist[r][i];
			farthest[r] = max;
		}
		
		double min = Double.MAX_VALUE;
		for(int r = 0; r < a.length; r++)
			for(int c = 0; c < a[0].length; c++)
			{
				if(dist[r][c] == Double.MAX_VALUE)
				{
					double newD = farthest[r] + farthest[c] + distance(points[r], points[c]);
					double minD = Double.max(loadDiameter(r), loadDiameter(c));
					min = Double.min(min, Double.max(newD, minD));
				}
			}
		return min;
	}
	
	public static double distance(Point a, Point b)
	{
		int dx = a.x - b.x;
		int dy = a.y - b.y;
		return Math.sqrt(dx * dx + dy * dy);
	}
}
public class cowtour 
{
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowtour.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"cowtour.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int size = Integer.parseInt(st.nextToken());
		double[][] a = new double[size][size];
		Point[] points = new Point[size];
		for(int r = 0; r < points.length; r++)
		{
			st = new StringTokenizer(f.readLine());
			points[r] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		for(int r = 0; r < a.length; r++)
		{
			String s = f.readLine();
			for(int c = 0; c < s.length(); c++)
				if(s.charAt(c) == '1')
					a[r][c] = CowTourHelper.distance(points[r], points[c]);
		}
		
		CowTourHelper help = new CowTourHelper(a, points);
		out.printf("%.6f\n", help.solve());
		out.close();
	}
}