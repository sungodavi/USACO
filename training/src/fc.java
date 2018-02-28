/*
ID: sungoda1
LANG: JAVA
TASK: fc
*/

import java.util.*;
import java.io.*;

class fc 
{
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("fc.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fc.out")));
		StringTokenizer st;
		int size = Integer.parseInt(f.readLine());
		Point[] a = new Point[size];
		for(int i = 0; i < size; i++)
		{
			st = new StringTokenizer(f.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			a[i] = new Point(x, y);
		}
		out.printf("%.2f\n", perimeter(scan(a)));
		out.close();
	}
	
	public static ArrayList<Point> scan(Point[] a)
	{
		Point pivot = a[0];
		for(int i = 1; i < a.length; i++)
			if(a[i].y < pivot.y || (a[i].y == pivot.y && a[i].x < pivot.x))
				pivot = a[i];
		Arrays.sort(a, new C(pivot));
		ArrayList<Point> list = new ArrayList<Point>();
		list.add(a[a.length - 1]);
		list.add(a[0]);
		list.add(a[1]);
		for(int i = 2; i < a.length; i++)
		{
			while(list.size() >= 2 && !ccw(a[i], list.get(list.size() - 2), list.get(list.size() - 1)))
					list.remove(list.size() - 1);
			list.add(a[i]);
		}
		return list;
	}
	
	public static double perimeter(ArrayList<Point> list)
	{
		double result = 0;
		for(int i = 0; i < list.size() - 1; i++)
			result += dist(list.get(i), list.get(i + 1));
		return result;
	}
	
	static class C implements Comparator<Point>
	{
		Point pivot;
		public C(Point pivot)
		{
			this.pivot = pivot;
		}
		
		public int compare(Point a, Point b)
		{
			double cross = cross(pivot, a, b);
			if(cross == 0)
				return Double.compare(dist(pivot, a), dist(pivot, b));
			return cross > 0 ? -1 : 1;
		}
	}
	
	public static double dist(Point a, Point b)
	{
		double dx = a.x - b.x;
		double dy = a.y - b.y;
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	public static double cross(Point a, Point b)
	{
		return a.x * b.y - b.x * a.y;
	}
	
	public static double cross(Point p, Point q, Point r)
	{
		return cross(toVec(p, q), toVec(p, r));
	}
	
	public static boolean ccw(Point p, Point q, Point r)
	{
		return cross(p, q, r) > 0;
	}
	
	public static Point toVec(Point a, Point b)
	{
		return new Point(b.x - a.x, b.y - a.y);
	}
	
	static class Point
	{
		double x, y;
		public Point(double x, double y)
		{
			this.x = x;
			this.y = y;
		}
		
		public String toString()
		{
			return String.format("(%.2f,%.2f)", x, y);
		}
	}
}