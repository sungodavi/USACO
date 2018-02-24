import java.util.*;
import java.io.*;

public class fencedin 
{
	public static int[] dx = {1, -1, 0, 0};
	public static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("fencedin.in"));
		PrintWriter out = new PrintWriter(new FileWriter("fencedin.out"));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int rows = Integer.parseInt(st.nextToken());
		int cols = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		boolean[][] visited = new boolean[m + 1][n + 1];
		int[] vLines = new int[n + 2];
		int[] hLines = new int[m + 2];
		for(int i = 1; i <= n; i++)
			vLines[i] = Integer.parseInt(f.readLine());
		vLines[n + 1] = rows;
		for(int i = 1; i <= m; i++)
			hLines[i] = Integer.parseInt(f.readLine());
		hLines[m + 1] = cols;
		Arrays.sort(vLines); Arrays.sort(hLines);
		
		Queue<Point> q = new PriorityQueue<Point>();
		long result = 0;
		q.add(new Point(0, 0, 0));
		while(!q.isEmpty())
		{
			Point p = q.poll();
			if(visited[p.x][p.y])
				continue;
			//System.out.println(p);
			visited[p.x][p.y] = true;
			result += p.d;
			for(int i = 0; i < dx.length; i++)
			{
				int r = p.x + dx[i];
				int c = p.y + dy[i];
				if(r < 0 || c < 0 || r >= visited.length || c >= visited[0].length || visited[r][c])
					continue;
				//System.out.println(r + " " + c);
				int d;
				if(p.x == r)
				{
					d = hLines[r + 1] - hLines[r];
				}
				else
				{
					d = vLines[c + 1] - vLines[c];
				}
				//System.out.println(p + " " + r + " " + c + " " + d);
				q.add(new Point(r, c, d));
			}
		}
		out.println(result);
		out.close();
	}
	
	static class Point implements Comparable<Point>
	{
		int x, y, d;
		public Point(int x, int y, int d)
		{
			this.x = x;
			this.y = y;
			this.d = d;
		}
		
		public int compareTo(Point p)
		{
			return d - p.d;
		}
		
		public String toString()
		{
			return x + " " + y + " " + d;
		}
	}
}