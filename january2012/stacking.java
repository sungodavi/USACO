/*
ID: sungoda1
LANG: JAVA
TASK: stacking
 */

import java.util.*;
import java.io.*;
import java.awt.Point;

public class stacking {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("stacking.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"stacking.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int[] diff = new int[Integer.parseInt(st.nextToken())];
		int val = 0;
		int[] a = new int[diff.length];
		Point[] queries = new Point[Integer.parseInt(st.nextToken())];
		for(int i = 0; i < queries.length; i++)
		{
			st = new StringTokenizer(f.readLine());
			queries[i] = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
		}
		for(Point p : queries)
		{
			diff[p.x]++;
			diff[p.y + 1]--;
		}
		for(int i = 0; i < a.length; i++)
		{
			val += diff[i];
			a[i] = val;
		}
		Arrays.sort(a);
		out.println(a[a.length >> 1]);
		out.close();
	}
}