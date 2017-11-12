/*
ID: sungoda1
LANG: JAVA
TASK: ariprog
*/
import java.util.*;
import java.io.*;


	
class ariprog
{
	static class Point implements Comparable<Point>
	{
		int x,y;
		public Point(int a, int b)
		{
			x = a; y = b;
		}
		
		public int compareTo(Point p)
		{
			return y - p.y;
		}
	}
	public static ArrayList<Point> list = new ArrayList<Point>();
	public static void load(boolean[] set, int depth, int max)
	{
		for(int a = 0; a < set.length; a++)
		{
			if(!set[a])
				continue;
			outer:
			for(int b = 1; b <= (max - a) / (depth - 1); b++)
			{
				for(int k = 1; k < depth; k++)
				{
					if(!set[a + b * k])
						continue outer;
				}
				list.add(new Point(a, b));
			}
		}
	}
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("ariprog.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(f.readLine());
		int m = Integer.parseInt(st.nextToken());
		
		boolean[] a = new boolean[2 * m * m + 1];
		for(int i = 0; i <= m; i++)
			for(int j = 0; j <= m; j++)
				a[i *i + j*j] = true;

		load(a, n, 2 * m * m);
		if(list.isEmpty())
			out.println("NONE");
		else
		{
			Collections.sort(list);
			for(Point p: list)
				out.println(p.x + " " + p.y);
		}
		out.close();
	}
}	
