/*
ID: sungoda1
LANG: JAVA
TASK: maxcross
 */

import java.util.*;
import java.io.*;

public class maxcross {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("maxcross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"maxcross.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int[] a = new int[Integer.parseInt(st.nextToken()) + 1];
		boolean[] map = new boolean[a.length];
		int size = Integer.parseInt(st.nextToken());
		int times = Integer.parseInt(st.nextToken());
		for(int k = 1; k <= times; k++)
		{
			map[Integer.parseInt(f.readLine())] = true;
		}
		for(int i = 1; i < a.length; i++)
		{
			a[i] = a[i - 1] + (map[i] ? 1 : 0);
		}
		int best = size;
		for(int i = size + 1; i < a.length; i++)
			best = Math.min(best, a[i] - a[i - size]);
		out.println(best);
		out.close();
		
	}
}