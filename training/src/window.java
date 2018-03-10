/*
ID: sungoda1
LANG: JAVA
TASK: window
 */

import java.util.*;
import java.io.*;
import java.awt.Point;

public class window
{
	static int top, bottom;
	static HashMap<Character, W> map;
	
	public static void main(String[] args) throws IOException
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("window.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("window.out")));
		StringTokenizer st;
		map = new HashMap<Character, W>();
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			char type = input.charAt(0);
			st = new StringTokenizer(input.substring(2, input.length() - 1), ",");
			char id = st.nextToken().charAt(0);
//			System.out.println(id);
			switch(type)
			{
			case 'w':
				int x1 = Integer.parseInt(st.nextToken());
				int y1 = Integer.parseInt(st.nextToken());
				int x2 = Integer.parseInt(st.nextToken());
				int y2 = Integer.parseInt(st.nextToken());
				w(id, x1, y1, x2, y2);
				break;
			case 'd':
				d(id);
				break;
			case 't':
				t(id);
				break;
			case 'b':
				b(id);
				break;
			case 's':
				out.printf("%.3f\n", s(id));
				break;
			}
//			System.out.println(map);
		}
//		System.out.println(System.currentTimeMillis() - start);
		out.close();
		System.exit(0);
	}
	
	public static double s(char type)
	{
		W w = map.get(type);
		return 100.0 * recurse(w.p1.x, w.p1.y, w.p2.x, w.p2.y, w.index) / w.area();
	}
	
	public static void t(char type)
	{
		map.get(type).index = --top;
	}
	
	public static void b(char type)
	{
		map.get(type).index = ++bottom;
	}
	
	public static void d(char type)
	{
		map.remove(type);
	}
	
	public static void w(char type, int x1, int y1, int x2, int y2)
	{
		map.put(type, new W(type, x1, y1, x2, y2));
	}
	public static int recurse(int x1, int y1, int x2, int y2, int index)
	{
//		System.out.printf("%d %d %d %d\n", x1, y1, x2, y2);
		if(x1 >= x2 || y1 >= y2)
			return 0;
		for(W w : map.values())
		{
			if(w.index < index && w.intersects(x1, y1, x2, y2))
			{
//				System.out.println("Intersects: " + w);
				int wx1 = Math.max(x1, w.p1.x);
				int wx2 = Math.min(x2, w.p2.x);
				int wy1 = Math.max(y1, w.p1.y);
				int wy2 = Math.min(y2, w.p2.y);
				int p1 = recurse(x1, wy1, wx1, wy2, index);
				int p2 = recurse(x1, wy2, x2, y2, index);
				int p3 = recurse(wx2, wy1, x2, wy2, index);
				int p4 = recurse(x1, y1, x2, wy1, index);
				return p1 + p2 + p3 + p4;
			}
		}
		return (y2 - y1) * (x2 - x1);
	}
	static class W
	{
		Point p1, p2;
		char type;
		int index;
		public W(char type, int x1, int y1, int x2, int y2)
		{
			if(x1 > x2)
			{
				int temp = x1;
				x1 = x2;
				x2 = temp;
			}
			
			if(y1 > y2)
			{
				int temp = y1;
				y1 = y2;
				y2 = temp;
			}
			p1 = new Point(x1, y1);
			p2 = new Point(x2, y2);
			this.type = type;
			this.index = --top;
		}
		
		public boolean intersects(int x1, int y1, int x2, int y2)
		{
			return x1 < p2.x && x2 > p1.x && y1 < p2.y && y2 > p1.y;
		}
		
		public int area()
		{
			return (p2.y - p1.y) * (p2.x - p1.x);
		}
		
		public String toString()
		{
			return p1 + " " + p2 + " " + type + " " + index;
		}
	}
}