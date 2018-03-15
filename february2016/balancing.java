/*
ID: sungoda1
LANG: JAVA
TASK: balancing
*/

import java.util.*;
import java.io.*;

class balancing 
{
	static SegTree left, right;
	static final int LIM = (int)1e6;
	static ArrayList<Integer> y;
	static HashSet<Integer> set;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("balancing.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));
		StringTokenizer st;
		int size = Integer.parseInt(f.readLine());
		Point[] a = new Point[size];
		y = new ArrayList<Integer>();
		set = new HashSet<Integer>();
		for(int i = 0; i < size; i++)
		{
			st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			a[i] = new Point(x, y);
			if(set.add(y))
				balancing.y.add(y);
		}
		f.close();
		Arrays.sort(a);
		Collections.sort(y);
		left = new SegTree();
		right = new SegTree();
		for(Point p : a)
			right.update(p.y, 1);
		int result = size;
		for(int i = 0; i < size;)
		{
			int x = a[i].x;
			while(i < size && a[i].x == x)
			{
				left.update(a[i].y, 1);
				right.update(a[i].y, -1);
				i++;
			}
			result = Math.min(result, search());
		}
		out.println(result);
		out.close();
	}
	
	public static int search()
	{
		int low = 0;
		int high = y.size() - 1;
		int result = Integer.MAX_VALUE;
		while(low <= high)
		{
			int mid = low + high >> 1;
			int b = y.get(mid) - 1;
			int top = Math.max(left.query(0, b), right.query(0, b));
			int bot = Math.max(left.query(b, LIM), right.query(b, LIM));
			result = Math.min(result, Math.max(top, bot));
			if(top < bot)
				low = mid + 1;
			else if(top > bot)
				high = mid - 1;
			else
				break;
		}
		return result;
	}
	
	static class SegTree
	{
		int[] a;
		int n;
		
		public SegTree()
		{
			this(LIM);
		}
		public SegTree(int size)
		{
			n = size;
			a = new int[2 * size + 2];
		}
		
		public void update(int index, int val)
		{
			for(index += n; index > 0; index >>= 1)
				a[index] += val;
		}
		
		public int query(int s, int e)
		{
			int result = 0;
			for(s += n, e += n + 1; s < e; s >>= 1, e >>= 1)
			{
				if(s % 2 != 0)
					result += a[s++];
				if(e % 2 != 0)
					result += a[--e];
			}
			return result;
		}
	}
	
	static class Point implements Comparable<Point>
	{
		int x, y;
		public Point(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
		public int compareTo(Point p)
		{
			return x - p.x;
		}
	}
	
	public static int max(int a, int b, int c, int d)
	{
		if(a > b && a > c && a > d)
			return a;
		if(b > c && b > d)
			return b;
		if(c > d)
			return c;
		return d;
	}
}