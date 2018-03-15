import java.util.*;
import java.io.*;
import java.awt.Point;

class marathon 
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("marathon.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("marathon.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int size = Integer.parseInt(st.nextToken());
		int queries = Integer.parseInt(st.nextToken());
		Point[] a = new Point[size];
		for(int i = 0; i < size; i++)
		{
			st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			a[i] = new Point(x, y);
		}
		int[] skips = new int[size - 2];
		int[] temp = new int[size - 1];
		for(int i = 0; i < skips.length; i++)
		{
			skips[i] = save(a[i], a[i + 1], a[i + 2]);
			temp[i] += dist(a[i], a[i + 1]);
		}
		temp[temp.length - 1] += dist(a[a.length - 2], a[a.length - 1]);
		Tree skip = new MinTree(skips), dist = new SumTree(temp);
		while(queries-- > 0)
		{
			char type = (char)f.read();
			st = new StringTokenizer(f.readLine());
			if(type == 'Q')
			{
				int s = Integer.parseInt(st.nextToken()) - 1;
				int e = Integer.parseInt(st.nextToken()) - 1;
				int result = dist.query(s, e - 1) + Math.min(0, skip.query(s, e - 2));
				out.println(result);
			}
			else
			{
				int index = Integer.parseInt(st.nextToken()) - 1;
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				a[index] = new Point(x, y);
				if(index < a.length - 1)
					dist.update(index, dist(a[index], a[index + 1]));
				if(index > 0)
					dist.update(index - 1, dist(a[index - 1], a[index]));
				if(index < a.length - 2)
					skip.update(index, save(a[index], a[index + 1], a[index + 2]));
				if(index > 0 && index < a.length - 1)
					skip.update(index - 1,  save(a[index - 1], a[index], a[index + 1]));
				if(index > 1)
					skip.update(index - 2, save(a[index - 2], a[index - 1], a[index]));
			}
		}
		out.close();
	}
	
	static interface Tree
	{
		public void update(int index, int val);
		public int query(int s, int e);
	}
	
	static class SumTree implements Tree
	{
		int[] a;
		int n;
		public SumTree(int[] in)
		{
			n = in.length;
			a = new int[2 * n];
			for(int i = n; i < 2 * n; i++)
				a[i] = in[i - n];
			for(int i = n - 1; i > 0; i--)
				a[i] = a[2 * i] + a[2 * i + 1];
		}
		
		public void update(int index, int val)
		{
			a[index += n] = val;
			for(index >>= 1; index > 0; index >>= 1)
				a[index] = a[2 * index] + a[2 * index + 1];
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
		
		public String toString()
		{
			return Arrays.toString(a);
		}
	}
	
	static class MinTree implements Tree
	{
		int[] a;
		int n;
		public MinTree(int[] in)
		{
			n = in.length;
			a = new int[2 * n];
			for(int i = n; i < 2 * n; i++)
				a[i] = in[i - n];
			for(int i = n - 1; i > 0; i--)
				a[i] = Math.min(a[2 * i], a[2 * i + 1]);
		}
		
		public void update(int index, int val)
		{
			a[index += n] = val;
			for(index >>= 1; index > 0; index >>= 1)
				a[index] = Math.min(a[2 * index], a[2 * index + 1]);
		}
		
		public int query(int s, int e)
		{
			int result = Integer.MAX_VALUE;
			for(s += n, e += n + 1; s < e; s >>= 1, e >>= 1)
			{
				if(s % 2 != 0)
					result = Math.min(result, a[s++]);
				if(e % 2 != 0)
					result = Math.min(result, a[--e]);
			}
			return result;
		}
		
		public String toString()
		{
			return Arrays.toString(a);
		}
	}
	
	public static int dist(Point a, Point b)
	{
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}
	
	public static int save(Point a, Point b, Point c)
	{
		return dist(a, c) - dist(a, b) - dist(b, c);
	}
}