/*
ID: sungoda1
LANG: JAVA
TASK: cowdance
 */

import java.util.*;
import java.io.*;
import java.awt.Point;

public class cowdance 
{
	static int[] a;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowdance.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"cowdance.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int size = Integer.parseInt(st.nextToken());
		int max = Integer.parseInt(st.nextToken());
		a = new int[size];
		for(int i = 0; i < a.length; i++)
			a[i] = Integer.parseInt(f.readLine());
		out.println(solve(max));
		out.close();
	}
	
	public static int solve(int max)
	{
		for(int i = 1; i < max; i++)
			if(check(i, max))
				return i;
		return max;
	}
	
	public static boolean check(int size, int max)
	{
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i = 0; i < size; i++)
			pq.add(a[i]);
		for(int i = size; i < a.length; i++)
		{
			//System.out.println(pq + " " + pq.peek() + " " + " " + a[i]);
			int best = pq.remove();
			if(best + a[i] > max)
				return false;
			pq.add(best + a[i]);
		}
		return true;
	}
}