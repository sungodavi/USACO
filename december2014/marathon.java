/*
ID: sungoda1
LANG: JAVA
TASK: marathon
*/
import java.util.*;
import java.io.*;
import java.awt.Point;

class marathon 
{
	static int[][] dp;
	static Point[] a;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("marathon.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("marathon.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		a = new Point[n];
		dp = new int[n][k + 1];
		for(int i = 0; i < a.length; i++)
		{
			st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			a[i] = new Point(x, y);
		}
		out.println(recurse(0, k));
//		for(int[] temp : dp)
//			System.out.println(Arrays.toString(temp));
		out.close();
		f.close();
	}
	
	public static int recurse(int curr, int skips)
	{
		if(curr == a.length - 1)
			return 0;
		if(dp[curr][skips] > 0)
			return dp[curr][skips];
		int result = Integer.MAX_VALUE;
		int temp = skips;
		for(int i = curr + 1; i < a.length && skips >= 0; i++, skips--)
			result = Math.min(result, recurse(i, skips) + dist(a[curr], a[i]));
		return dp[curr][temp] = result;
	}
	
	public static int dist(Point a, Point b)
	{
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}
}
