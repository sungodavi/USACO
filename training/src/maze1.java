/*
ID: sungoda1
LANG: JAVA
TASK: maze1
 */

import java.util.*;
import java.io.*;
class P
{
	int r, c, d;
	public P(int r, int c, int d)
	{
		this.r = r;
		this.c = c;
		this.d = d;
	}
}

class Maze
{
	char[][] a;
	int[][] dist;
	boolean[][] visited;
	public Maze(char[][] a)
	{
		this.a = a;
		dist = new int[a.length / 2][a[0].length / 2];
		visited = new boolean[dist.length][dist[0].length];
	}
	public boolean isValid(int r, int c)
	{
		return r >= 0 && r < a.length && c >= 0 && c < a[0].length && a[r][c] == ' ';
	}
	public void bfs(int r, int c)
	{
		visited = new boolean[dist.length][dist[0].length];
		Queue<P> q = new LinkedList<P>();
		q.add(new P(r, c, 1));
		while(!q.isEmpty())
		{
			P p = q.poll();
			int x = p.r / 2;
			int y = p.c / 2;
			if(!isValid(p.r, p.c) || visited[x][y])
				continue;
			visited[x][y] = true;
			if(dist[x][y] > 0)
				dist[x][y] = Integer.min(dist[x][y], p.d);
			else
				dist[x][y] = p.d; 
			if(a[p.r - 1][p.c] == ' ')
				q.add(new P(p.r - 2, p.c, p.d + 1));
			if(a[p.r + 1][p.c] == ' ')
				q.add(new P(p.r + 2, p.c, p.d + 1));
			if(a[p.r][p.c + 1] == ' ')
				q.add(new P(p.r, p.c + 2, p.d + 1));
			if(a[p.r][p.c - 1] == ' ')
				q.add(new P(p.r, p.c - 2, p.d + 1));
		}
	}
	
	public int solve()
	{
		for(int r = 0; r < a.length; r++)
		{
			if(a[r][0] == ' ')
			{
				bfs(r, 1);
			}
			if(a[r][a[0].length - 1] == ' ')
			{
				bfs(r, a[0].length - 2);
			}
		}
		
		for(int c = 0; c < a[0].length; c++)
		{
			if(a[0][c] == ' ')
			{
				bfs(1, c);
			}
			if(a[a.length - 1][c] == ' ')
			{
				bfs(a.length - 2, c);
			}
		}
		
		int max = 0;
		for(int[] temp: dist)
			for(int num : temp)
				if(num > max)
					max = num;
		return max;
	}
}
public class maze1 
{
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("maze1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"maze1.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int c = 2 * Integer.parseInt(st.nextToken()) + 1;
		int r = 2 * Integer.parseInt(st.nextToken()) + 1;
		char[][] a = new char[r][c];
		for(int i = 0; i < a.length; i++)
			a[i] = f.readLine().toCharArray();
		Maze m = new Maze(a);
		out.println(m.solve());
		out.close();		

	}
}