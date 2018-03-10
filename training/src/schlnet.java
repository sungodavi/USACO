/*
ID: sungoda1
LANG: JAVA
TASK: schlnet
 */

import java.util.*;
import java.io.*;

public class schlnet
{
	static int[][] a;
	static boolean[] visited;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("schlnet.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("schlnet.out")));
		StringTokenizer st;
		int size = Integer.parseInt(f.readLine());
		a = new int[size][size];
		for(int r = 0; r < size; r++)
		{
			st = new StringTokenizer(f.readLine());
			for(int c = Integer.parseInt(st.nextToken()); c > 0; c = Integer.parseInt(st.nextToken()))
				a[r][c - 1] = 1;
		}
		int top = tops();
		int bottom = bottoms();
//		System.out.println(top + " " + bottom);
		out.println(top);
		if(isConnected())
			out.println(0);
		else
			out.println(Math.max(top, bottom));
		out.close();
		System.exit(0);
	}
	
	public static boolean isConnected()
	{
		for(int i = 0; i < a.length; i++)
		{
			boolean[] flags = new boolean[a.length];
			visited = new boolean[a.length];
			fill(i, flags);
			for(boolean b : visited)
				if(!b)
					return false;
		}
		return true;
	}
	
	public static int tops()
	{
		boolean[] top = new boolean[a.length];
		Arrays.fill(top, true);
		for(int i = 0; i < a.length; i++)
			if(top[i])
			{
				visited = new boolean[a.length];
				fill(i, top);
				top[i] = true;
			}
		return count(top);
	}
	
	public static int bottoms()
	{
		boolean[] bottom = new boolean[a.length];
		Arrays.fill(bottom, true);
		for(int i = 0; i < a.length; i++)
			if(bottom[i])
			{
				visited = new boolean[a.length];
				fillReverse(i, bottom);
				bottom[i] = true;
			}
		return count(bottom);
	}
	
	public static void fill(int r, boolean[] flags)
	{
		if(visited[r])
			return;
		visited[r] = true;
		flags[r] = false;
		for(int c = 0; c < a.length; c++)
		{
			if(a[r][c] > 0)
			{
				if(!visited[c])
					fill(c, flags);
			}
		}
	}
	
	public static void fillReverse(int c, boolean[] flags)
	{
		if(visited[c])
			return;
		visited[c] = true;
		flags[c] = false;
		for(int r = 0; r < a.length; r++)
			if(a[r][c] > 0)
			{
				if(!visited[r])
					fillReverse(r, flags);
			}
	}
	
	public static int count(boolean[] a)
	{
		int result = 0;
		for(boolean b : a)
			if(b)
				result++;
		return result;
	}
}