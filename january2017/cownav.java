import java.util.*;
import java.io.*;
import java.awt.Point;

class cownav 
{
	static char[][] board;
	static boolean[][][][][][] visited;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("cownav.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownav.out")));
//		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int size = Integer.parseInt(f.readLine());
		board = new char[size][];
		visited = new boolean[size][size][size][size][4][4];
		for(int r = 0; r < size; r++)
			board[r] = f.readLine().toCharArray();
		
		out.println(bfs());
		out.close();
	}
	
	public static int bfs()
	{
		Queue<State> q = new LinkedList<State>();
		q.add(new State(new Point(board.length - 1, 0), new Point(board.length - 1, 0), 1, 2, 0));
		while(!q.isEmpty())
		{
			State s = q.poll();
			if(s.end())
				return s.d;
			if(s.visited())
				continue;
			s.mark();
			int d1 = (s.d1 + 1) % 4;
			int d2 = (s.d2 + 1) % 4;
			q.add(new State(s.a, s.b, d1, d2, s.d + 1));
			d1 = (s.d1 + 3) % 4;
			d2 = (s.d2 + 3) % 4;
			q.add(new State(s.a, s.b, d1, d2, s.d + 1));
			
			int r = s.a.x + dx[s.d1];
			int c = s.a.y + dy[s.d1];
			Point a = isValid(r, c) && !end(s.a)? new Point(r, c) : s.a;
			
			r = s.b.x + dx[s.d2];
			c = s.b.y + dy[s.d2];
			Point b = isValid(r, c) && !end(s.b)? new Point(r, c) : s.b;
			
			q.add(new State(a, b, s.d1, s.d2, s.d + 1));
		}
		return -1;
	}
	
	public static boolean isValid(int r, int c)
	{
		return r >= 0 && r < board.length && c >= 0 && c < board[0].length && board[r][c] != 'H';
	}
	
	public static boolean end(Point p)
	{
		return p.x == 0 && p.y == board.length - 1;
	}
	
	static class State
	{
		Point a, b;
		int d1, d2, d;
		
		public State(Point a, Point b, int d1, int d2, int d)
		{
			this.a = a;
			this.b = b;
			this.d1 = d1;
			this.d2 = d2;
			this.d = d;
		}
		
		public boolean visited()
		{
			return visited[a.x][a.y][b.x][b.y][d1][d2];
		}
		
		public void mark()
		{
			visited[a.x][a.y][b.x][b.y][d1][d2] = true;
		}
		
		public boolean end()
		{
			return cownav.end(a) && cownav.end(b);
		}
		
		public String toString()
		{
			return a.toString() + " " + b.toString() + " " + d1 + " " + d2 + " " + d;
		}
	}
}