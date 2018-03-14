import java.util.*;
import java.io.*;

class triangles {
	static Point[] a;
	public static void main(String[] args) throws IOException //UNSOLVED
	{
		BufferedReader f = new BufferedReader(new FileReader("triangles.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));
		StringTokenizer st;
		int size = Integer.parseInt(f.readLine());
		a = new Point[size];
		for(int i = 0; i < size; i++)
		{
			st = new StringTokenizer(f.readLine());
			a[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		int[][] under = new int[size][size];
		for(int r = 0; r < size; r++)
			for(int c = 0; c < size; c++)
			{
				if(a[r].x < a[c].x)
				{
					for(int k = 0; k < size; k++)
					{
						if(k == r || k == c)
							continue;
						if(under(k, r))
							under[r][c]++;
						if(under(k, c))
							under[r][c]++;
						if(check(r, c, k))
							under[r][c] += 2;
					}
					under[c][r] = -under[r][c];
				}
			}
		int[] result = new int[size - 2];
		for(int a = 0; a < size; a++)
		{
			for(int b = 0; b < a; b++)
			{
				for(int c = 0; c < b; c++)
				{
					int count = Math.abs(under[a][b] + under[b][c] + under[c][a]) >> 1;
					System.out.println(a + " " + b + " " +c + " " + count);
					if(check(a, b, c))
						count--;
					System.out.println(a + " " + b + " " +c + " " + count);
					if(check(b, c, a))
						count--;
					System.out.println(a + " " + b + " " +c + " " + count);
					if(check(c, a, b))
						count--;
					
					result[count]++;
				}
			}
		}
		for(int num : result)
			out.println(num);
		out.close();
		f.close();
		System.exit(0);
	}
	
	static boolean check(int r, int c, int k)
	{
		if(a[r].x > a[c].x)
		{
			int temp = r;
			r = c;
			c = temp;
		}
		return a[r].x < a[k].x && a[k].x < a[c].x && cw(a[r], a[c], a[k]);
	}
	
	static boolean under(int k, int r)
	{
		return a[r].x == a[k].x && a[k].y < a[r].y;
	}
	
	static boolean cw(Point p, Point q, Point r)
	{
		return cross(toVec(p, q), toVec(p, r)) < 0;
	}
	
	static  int cross(Point a, Point b)
	{
		return a.x * b.y - b.x * a.y;
	}
	
	static  Point toVec(Point p, Point q)
	{
		return new Point(q.x - p.x, q.y - p.y);
	}
	
	static class Point
	{
		int x, y;
		public Point(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}
}