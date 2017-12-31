/*
ID: sungoda1
LANG: JAVA
TASK: countcross
 */

import java.util.*;
import java.io.*;
import java.awt.Point;

public class countcross 
{
	static int[][] grid;
	static boolean[][] visited;
	static int[] dx = {0, 0, 1, -1}; //Right Left Down Up
	static int[] dy = {1, -1, 0, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("countcross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"countcross.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int size = Integer.parseInt(st.nextToken());
		grid = new int[size][size];
		visited = new boolean[size][size];
		int cows = Integer.parseInt(st.nextToken());
		int walls = Integer.parseInt(st.nextToken());
		for(int k = 1; k <= walls; k++)
		{
			st = new StringTokenizer(f.readLine());
			int sr = Integer.parseInt(st.nextToken()) - 1;
			int sc = Integer.parseInt(st.nextToken()) - 1;
			int er = Integer.parseInt(st.nextToken()) - 1;
			int ec = Integer.parseInt(st.nextToken()) - 1;
			load(sr, sc, er, ec);
		}
		for(int k = 1; k <= cows; k++)
		{
			st = new StringTokenizer(f.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			grid[r][c] += 32;
		}
		for(int[] temp: grid)
			System.out.println(Arrays.toString(temp));
		long result = 0;
		ArrayList<Long> list = new ArrayList<Long>();
		for(int r = 0; r < grid.length; r++)
			for(int c = 0; c < grid[0].length; c++)
			{
				if(!visited[r][c])
				{
					list.add(bfs(r, c));
				}
			}
		for(int i =0; i < list.size(); i++)
			for(int j = i + 1; j < list.size(); j++)
				result += list.get(i) * list.get(j);
		out.println(result);
		out.close();
	}
	
	public static long bfs(int r, int c)
	{
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(r, c));
		long count = 0;
		while(!q.isEmpty())
		{
			Point p = q.poll();
			if(!isValid(p.x, p.y))
				continue;
			visited[p.x][p.y] = true;
			if(grid[p.x][p.y] >= 32)
				count++;
			for(int i = 0; i < dx.length; i++)
			{
				if((grid[p.x][p.y] & 1 << i) == 0)
					q.add(new Point(p.x + dx[i], p.y + dy[i]));
			}
		}
		return count;
	}
	
	public static boolean isValid(int r, int c)
	{
		return r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && !visited[r][c];
	}
	public static void load(int sr, int sc, int er, int ec)
	{
		if(sr == er)
		{
			if(sc > ec)
			{
				int temp = sc;
				sc = ec;
				ec = temp;
			}
			if((grid[sr][sc] & 1) == 0)
				grid[sr][sc] += 1;
			if((grid[er][ec] >> 1 & 1) == 0)
				grid[er][ec] += 2;
		}
		else
		{
			if(sr > er)
			{
				int temp = sr;
				sr = er;
				er = temp;
			}
			if((grid[sr][sc] >> 2 & 1) == 0)
				grid[sr][sc] += 4;
			if((grid[er][ec] >> 3 & 1) == 0)
				grid[er][ec] += 8;
		}
	}
}