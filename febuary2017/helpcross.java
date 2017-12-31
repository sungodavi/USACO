/*
ID: sungoda1
LANG: JAVA
TASK: helpcross
 */

import java.util.*;
import java.io.*;
import java.awt.Point;

public class helpcross 
{
	static boolean[] used;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("helpcross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"helpcross.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int chickenCount = Integer.parseInt(st.nextToken());
		int cowCount = Integer.parseInt(st.nextToken());
		int[] chickens = new int[chickenCount];
		Point[] cows = new Point[cowCount];
		for(int i = 0; i < chickens.length; i++)
			chickens[i] = Integer.parseInt(f.readLine());
		for(int i =0; i < cows.length; i++)
		{
			st = new StringTokenizer(f.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			cows[i] = new Point(start, end);
		}
		Arrays.sort(chickens);
		Arrays.sort(cows, new Comparator<Point>() {
			public int compare(Point a, Point b)
			{
				return a.y - b.y;
			}
		});
		used = new boolean[cows.length];
		int count =0;
		for(int chicken : chickens)
		{
			for(int i = 0; i < cows.length; i++)
			{
				if(check(chicken, cows[i], i))
				{
					++count;
					used[i] = true;
					break;
				}
			}
		}
		out.println(count);
		out.close();
	}
	
	public static boolean check(int a, Point b, int i)
	{
		return !used[i] && a >= b.x && a <= b.y;
	}
}