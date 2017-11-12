/*
ID: sungoda1
LANG: JAVA
TASK: comehome
 */

import java.util.*;
import java.io.*;

public class comehome 
{
	public static int minDistance(int[] dist, boolean[] visited)
	{
		int min = -1;
		for(int i = 0; i < dist.length; i++)
		{
			if(!visited[i])
			{
				if(min < 0)
					min = i;
				else if(dist[i] < dist[min])
					min = i;
			}
		}
		return min;
	}
	public static int[] dijkstra(int[][] map)
	{
		int[] dist = new int[map.length];
		boolean[] visited = new boolean[map.length];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[25] = 0;
		
		for(int i = 0; i < map.length - 1; i++)
		{
			int r = minDistance(dist, visited);
			visited[r] = true;
			for(int c = 0; c < map.length; c++)
			{
				if(map[r][c] > 0 && !visited[c] && dist[r] != Integer.MAX_VALUE && dist[c] > dist[r] + map[r][c])
				{
					dist[c] = dist[r] + map[r][c];
					if(dist[c] < 0)
						System.out.println("Error");
				}
			}
		}
		int min = 0;
		for(int i = 1; i < 25; i++)
			if(dist[i] < dist[min])
				min = i;
		System.out.println(Arrays.toString(map['R' - 'A']));
		return new int[] {min, dist[min]};
		
	}
	public static int convert(char c)
	{
		if(c >= 'a')
			return c - 'a' + 26;
		return c - 'A';
	}
	
	public static char convert(int num)
	{
		return (char)('A' + num);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("comehome.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"comehome.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int[][] a = new int[52][52];
		int times = Integer.parseInt(st.nextToken());
		for(; times > 0; times--)
		{
			String input = f.readLine();
			int r =convert(input.charAt(0));
			int c = convert(input.charAt(2));
			int d = Integer.parseInt(input.substring(4));
			if(a[r][c] > 0)
				a[r][c] = a[c][r] = Integer.min(a[r][c], d);
			else
				a[r][c] = a[c][r] = d;
			//System.out.println(input.charAt(0) + " " + convert(input.charAt(0)) + " " +
			//input.charAt(2) + " " + convert(input.charAt(2)));
		}
		int[] result = dijkstra(a);
		out.println(convert(result[0]) + " " + result[1]);
		out.close();
	}
}