/*
ID: sungoda1
LANG: JAVA
TASK: inflate
 */

import java.util.*;
import java.io.*;
import java.awt.Point;

public class inflate
{
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("inflate.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"inflate.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int time = Integer.parseInt(st.nextToken());
		Point[] a = new Point[Integer.parseInt(st.nextToken())];
		for(int i = 0; i < a.length; i++)
		{
			st = new StringTokenizer(f.readLine());
			a[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		int[] m = new int[time + 1];
		for(Point p : a)
		{
			for(int i = p.y; i < m.length; i++)
			{
				m[i] = Integer.max(m[i], m[i-p.y] + p.x);
			}
		}
		out.println(m[time]);
		out.close();
	}
}