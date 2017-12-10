/*
ID: sungoda1
LANG: JAVA
TASK: fence9
 */

import java.util.*;
import java.io.*;


public class fence9 
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("fence9.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"fence9.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(st.nextToken()); //x
		int m = Integer.parseInt(st.nextToken()); //y
		int p = Integer.parseInt(st.nextToken());
		
		int sum = calc(n, m, p);
		out.println(sum);
		out.close();
	}
	
	static int calc(int n, int m , int p)
	{
		
		Line l1 = new Line(0, 0, n, m);
		Line l2 = new Line(p, 0, n, m);
		int sum = 0;
		for(int i = 1; i < m; i++)
		{
			System.out.println(l2.getRight(i));
			System.out.println(l1.getLeft(i));
			sum += l2.getRight(i) - l1.getLeft(i) + 1;
		}
		return sum;
	}
	
	static class Line
	{
		int rise, run, x1, y1;
		public Line(int x1, int y1, int x2, int y2)
		{
			rise = y2 - y1;
			run = x2 - x1;
			this.x1 = x1;
			this.y1 = y1;
		}
		
		public int getLeft(int y)
		{
			if(rise == 0)
				return 0;
			int n = (y - y1) * run;
			int d = rise;
			System.out.println(rise + " " + run + " " + n + " " + d);
			//if(n % d == 0)
				//return n / d + x1 + 1;
			return n / d + x1 + 1;
		}
		
		public int getRight(int y)
		{
			if(rise == 0)
				return 0;
			int n = (y - y1) * run;
			int d = rise;
			System.out.println(rise + " " + run + " " + n + " " + d);
//			if(n % d == 0)
//				return n / d + x1 - 1;
			return n / d + x1 - 1;
		}
	}
}