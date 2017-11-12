/*
ID: sungoda1
LANG: JAVA
TASK: agrinet
 */

import java.util.*;
import java.io.*;

public class agrinet 
{
	public static int minDistance(int[] dist, boolean[] inTree)
	{
		int index = -1;
		for(int i = 0; i < dist.length; i++)
		{
			if(!inTree[i])
			{
				if(index < 0)
					index = i;
				else if(dist[i] < dist[index])
					index =i;
			}
		}
		return index;
	}
	public static int prims(int[][] a)
	{
		boolean[] inTree = new boolean[a.length];
		int size = 0;
		int[] dist = new int[a.length];
		Arrays.fill(dist, Integer.MAX_VALUE);
		inTree[0] = true;
		for(int c = 0; c < a.length; c++)
		{
			if(a[0][c] > 0)
				dist[c] = a[0][c];
		}
		for(int i = 1; i < a.length; i++)
		{
			int r = minDistance(dist, inTree);
			//System.out.println(r);
			inTree[r] = true;
			if(dist[r] != Integer.MAX_VALUE)
				size += dist[r];
			for(int c = 0; c < a.length; c++)
			{
				if(a[r][c] > 0)
					dist[c] = Integer.min(dist[c], a[r][c]);
			}
		}
		return size;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("agrinet.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"agrinet.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int size = Integer.parseInt(st.nextToken());
		int[][] a = new int[size][size];
		int index = 0;
		String s = f.readLine();
		while(s != null)
		{
			st = new StringTokenizer(s);
			for(int i = st.countTokens(); i > 0 ; i--)
			{
				a[index / a.length][index % a.length] = Integer.parseInt(st.nextToken());
				index++;
			}
			s = f.readLine();
		}
		
		out.println(prims(a));
		out.close();
	}
}