/*
ID: sungoda1
LANG: JAVA
TASK: camelot
*/
import java.util.*;
import java.io.*;
import java.awt.Point;

class camelot 
{
	static Piece king;
	static ArrayList<Piece> knights;
	static boolean[][] visited;
	static int rows, columns;
	static int[][] dist;
	static int[] dx = {2, 2, -2, -2, 1, 1, -1, -1};
	static int[] dy = {1, -1, 1, -1, 2, -2, 2, -2};
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("camelot.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("camelot.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		rows = Integer.parseInt(st.nextToken());
		columns = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		king = convert(st.nextToken(), st.nextToken());
		knights = new ArrayList<Piece>();
		for(String input = f.readLine(); input != null; input = f.readLine())
		{
			st = new StringTokenizer(input);
			while(st.hasMoreTokens())
			{
				String col = st.nextToken();
				String row = st.nextToken();
				knights.add(convert(col, row));
			}
		}
		int best = Integer.MAX_VALUE;
		for(int r = 0; r < rows; r++)
		{
			outer:
			for(int c =0; c < columns; c++)
			{
				int[][] dist = bfs(r, c);
				int[][] distKing = bfsKing(r, c);				
				int sum = 0;
				int minKingD = dist(r, c, king);
				for(Piece p : knights)
				{
					if(dist[p.x][p.y] == 1e9)
						continue outer;
					sum += dist[p.x][p.y];
					minKingD = Math.min(minKingD, distKing[p.x][p.y] - dist[p.x][p.y]);
				}
				best = Math.min(best, sum + minKingD);
			}
		}
		out.println(best);
		out.close();
	}
	
	public static int[][] bfs(int r, int c)
	{
		int[][] dist = new int[rows][columns];
		for(int[] temp : dist)
			Arrays.fill(temp, (int)1e9);
		dist[r][c] = 0;
		Queue<Point> q = new PriorityQueue<Point>();
		q.add(new Point(r, c, 0));
		while(!q.isEmpty())
		{
			Point p = q.poll();
			for(int i =0; i < dx.length; i++)
			{
				int x = p.x + dx[i];
				int y = p.y + dy[i];
				if(isValid(x, y) && dist[p.x][p.y] + 1 < dist[x][y])
				{
					dist[x][y] = dist[p.x][p.y] + 1;
					q.add(new Point(x, y, dist[x][y]));
				}
			}
		}
		return dist;
	}
	public static int[][] bfsKing(int r, int c)
	{
		int[][] dist = new int[rows][columns];
		for(int[] temp : dist)
			Arrays.fill(temp, (int)1e9);
		dist[r][c] = dist(r, c, king);
		Queue<Point> q = new PriorityQueue<Point>();
		q.add(new Point(r, c, 0, dist(r, c, king)));
		dist[r][c] = dist(r, c, king);
		while(!q.isEmpty())
		{
			Point p = q.poll();
			for(int i =0; i < dx.length; i++)
			{
				int x = p.x + dx[i];
				int y = p.y + dy[i];
				if(isValid(x, y))
				{
					int d = p.d + 1;
					int kingD = Math.min(p.kingD, dist(x, y, king));
					if(dist[x][y] > d + kingD)
					{
						dist[x][y] = d + kingD;
						q.add(new Point(x, y, d, kingD));
					}
				}
			}
		}
		return dist;
	}
	
	public static Piece convert(String col, String row)
	{
		int r = rows - Integer.parseInt(row);
		int c = col.charAt(0) - 'A';
		return new Piece(r, c);
	}
	
	
	public static int dist(int r, int c, Piece end)
	{
		return Math.max(Math.abs(r - end.x), Math.abs(c - end.y));
	}
	
	public static boolean isValid(int r, int c)
	{
		return r >= 0 && c >= 0 && r < rows && c < columns;
	}
	
	static class Point implements Comparable<Point>
	{
		int x, y, d, kingD;
		public Point(int x, int y, int d)
		{
			this.x = x;
			this.y = y;
			this.d = d;
		}
		public Point(int x, int y, int d, int kingD)
		{
			this.x = x;
			this.y = y;
			this.d = d;
			this.kingD = kingD;
		}
		
		public int compareTo(Point p)
		{
			return d + kingD - p.d - p.kingD;
		}
	}
	
	static class Piece
	{
		int x, y;
		public Piece(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
		public String toString()
		{
			return x + " " + y;
		}
	}
}
