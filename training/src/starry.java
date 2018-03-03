/*
ID: sungoda1
LANG: JAVA
TASK: starry
 */

import java.util.*;
import java.io.*;
import java.awt.Point;

public class starry
{
	static D[] dir;
	static char[][] a;
	public static void main(String[] args) throws IOException
	{
		load();
		BufferedReader f = new BufferedReader(new FileReader("starry.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("starry.out")));
		int cols = Integer.parseInt(f.readLine());
		int rows = Integer.parseInt(f.readLine());
		a = new char[rows][cols];
		for(int r = 0; r < rows; r++)
		{
			for(int c = 0; c < cols; c++)
				a[r][c] = (char)f.read();
			f.readLine();
		}
		ArrayList<Cluster> list = new ArrayList<Cluster>();
		for(int r = 0; r < rows; r++)
			for(int c = 0; c < cols; c++)
				if(a[r][c] == '1')
				{
					Cluster curr = new Cluster(r, c);
					int area = area(r, c, new boolean[rows][cols], curr);
					boolean found = false;
					outer:
					for(int i = 0; i < list.size(); i++)
					{
						Cluster star = list.get(i);
						for(int k = 0; k < dir.length; k++)
						{
							Point start = getCorner(curr, k);
							if(area == star.size() + 1 && star.check(dir[k], start))
							{
								fill(r, c, (char)(i + 'a'));
								found = true;
								break outer;
							}
						}
					}
					if(!found)
					{
						Cluster star = new Cluster(r, c);
						dfs(r, c, 0, star, (char)(list.size() + 'a'));
						list.add(star);
					}
				}
//		for(Cluster c : list)
//			System.out.println(c);
		display(out);
		out.close();
	}
	
	public static void fill(int r, int c, char letter)
	{
		a[r][c] = letter;
		D d = dir[0];
		for(int i = 0; i < d.length; i++)
		{
			int x = r + d.dx(i);
			int y = c + d.dy(i);
			if(isValid(x, y) && a[x][y] == '1')
				fill(x, y, letter);
		}
	}
	
	public static void display(PrintWriter out)
	{
		for(char[] row : a)
		{
			for(char c : row)
				out.print(c);
			out.println();
		}
	}
	
	public static Point getCorner(Cluster c, int index)
	{
		switch(index)
		{
		case 0: return c.tl;
		case 1: return c.rt;
		case 2: return c.br;
		case 3: return c.lb;
		case 4: return c.tr;
		case 5: return c.lt;
		case 6: return c.bl;
		case 7: return c.rb;
		}
		return null;
	}
	
	public static void dfs(int x, int y, int index, Cluster c, char letter)
	{
		a[x][y] = letter;
		c.update(x, y);
		D d = dir[0];
		for(int i = 0; i < d.length; i++)
		{
			int nX = x + d.dx(i);
			int nY = y + d.dy(i);
			if(isValid(nX, nY) && a[nX][nY] == '1')
			{
				c.add(index, i);
				dfs(nX, nY, c.size(), c, letter);
			}
		}
	}
	
	public static int area(int x, int y, boolean[][] visited, Cluster star)
	{
		int result = 1;
		visited[x][y] = true;
		D d = dir[0];
		star.update(x,  y);
		for(int i = 0; i < d.length; i++)
		{
			int r = x + d.dx(i);
			int c = y + d.dy(i);
			if(isValid(r, c) && !visited[r][c] && a[r][c] == '1')
				result += area(r, c, visited, star);
		}
		return result;
	}
	
	public static boolean isValid(int x, int y)
	{
		return x >= 0 && x < a.length && y >= 0 && y < a[0].length;
	}
	
	static void load()
	{
		dir = new D[8];
		dir[0] = new D(new int[] {0, 1, 2, 3, 4, 5, 6, 7});
		dir[1] = new D(new int[] {2, 3, 4, 5, 6, 7, 0, 1});
		dir[2] = new D(new int[] {4, 5, 6, 7, 0, 1, 2, 3});
		dir[3] = new D(new int[] {6, 7, 0, 1, 2, 3, 4, 5});
		dir[4] = new D(new int[] {0, 7, 6, 5, 4, 3, 2, 1});
		dir[5] = new D(new int[] {6, 5, 4, 3, 2, 1, 0, 7});
		dir[6] = new D(new int[] {4, 3, 2, 1, 0, 7, 6, 5});
		dir[7] = new D(new int[] {2, 1, 0, 7, 6, 5, 4, 3});
	}
	
	static class D
	{
		static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, 1, 1, 1, 0, -1, -1, -1};
		int[] map;
		int length;
		public D(int[] map)
		{
			this.map = map;
			length = map.length;
		}
		
		public int dx(int n)
		{
			return dx[map[n]];
		}
		
		public int dy(int n)
		{
			return dy[map[n]];
		}
	}
	
	static class Cluster
	{
		Point tl, tr, bl, br, lt, lb, rt, rb;
		ArrayList<Point> steps;
		public Cluster(int r, int c)
		{
			tl = tr = bl = br = lt = lb = rt = rb = new Point(r, c);
			steps = new ArrayList<Point>();
		}
		
		public boolean check(D d, Point start)
		{
			if(a[start.x][start.y] == '0')
				return false;
			if(steps.isEmpty())
				return true;
			Point[] loc = new Point[steps.size() + 1];
			loc[0] = start;
			int size = 1;
			for(Point p : steps)
			{
				int r = loc[p.x].x + d.dx(p.y);
				int c = loc[p.x].y + d.dy(p.y);
				if(!isValid(r, c) || a[r][c] == '0')
					return false;
				loc[size++] = new Point(r, c);
			}
			return true;
		}
		
		public void add(int index, int dir)
		{
			steps.add(new Point(index, dir));
		}
		
		public int size()
		{
			return steps.size();
		}
		
		public void update(int r, int c)
		{
			if(r < tl.x || r == tl.x && c < tl.y)
				tl = new Point(r, c);
			if(r < tr.x || r == tr.x && c > tr.y)
				tr = new Point(r, c);
			if(r > bl.x || r == bl.x && c < bl.y)
				bl = new Point(r, c);
			if(r > br.x || r == br.x && c > br.y)
				br = new Point(r, c);
			if(c < lt.y || c == lt.y && r < lt.x)
				lt = new Point(r, c);
			if(c > rt.y || c == rt.y && r < rt.x)
				rt = new Point(r, c);
			if(c < lb.y || c == lb.y && r > lb.x)
				lb = new Point(r, c);
			if(c > rb.y || c == rb.y && r > rb.x)
				rb = new Point(r, c);
		}
		
		public String toString()
		{
			return String.format("%s %s %s %s\n%s %s %s %s\n%s", tl, tr, bl, br, lt, rt, lb, rb, steps);
		}
	}
}