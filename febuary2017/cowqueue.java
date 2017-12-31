/*
ID: sungoda1
LANG: JAVA
TASK: cowqueue
 */

import java.util.*;
import java.io.*;
import java.awt.Point;

public class cowqueue {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowqueue.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"cowqueue.out")));
		
		Point[] a = new Point[Integer.parseInt(f.readLine())];
		for(int i = 0; i < a.length; i++)
		{
			StringTokenizer st = new StringTokenizer(f.readLine());
			a[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(a, new Comparator<Point>() {
			public int compare(Point a, Point b)
			{
				if(a.x == b.x)
					return a.y - b.y;
				return a.x - b.x;
			}
		});
		
		int time = 0;
		for(Point p : a)
			time = Math.max(time, p.x) + p.y;
		out.println(time);
		out.close();
	}
}