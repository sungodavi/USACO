/*
ID: sungoda1
LANG: JAVA
TASK: frac1
*/
import java.util.*;
import java.io.*;
import java.awt.Point;

class C implements Comparator<Point>
{
	public int compare(Point p1, Point p2)
	{
		return p1.x* p2.y - p2.x * p1.y;
	}
}

class frac1
{
	public static int gcd(int x, int y)
	{
		while(y > 0)
		{
			int temp = x;
			x = y;
			y = temp % y;
		}
		return x;
	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("frac1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int num = Integer.parseInt(st.nextToken());

		PriorityQueue<Point> q = new PriorityQueue<Point>(new C());
		for(int n = 1; n <= num; n++)
		{
			for(int d = n; d <= num; d++)
			{
				if(gcd(n, d) > 1)
					continue;
				q.add(new Point(n, d));
			}
		}
		out.println("0/1");
		while(!q.isEmpty())
		{
			Point p = q.poll();
			out.printf("%d/%d\n", p.x, p.y);
		}
		out.close();
	}
}	
