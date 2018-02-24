import java.util.*;
import java.io.*;

public class dream 
{
	public static int[][] a;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
//		BufferedReader f = new BufferedReader(new FileReader("dream.in"));
//		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dream.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int rows = Integer.parseInt(st.nextToken());
		int cols = Integer.parseInt(st.nextToken());
		a = new int[rows][cols];
		for(int r = 0;  r < a.length; r++)
		{
			st = new StringTokenizer(f.readLine());
			for(int c = 0; c < a[0].length; c++)
			{
				a[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		out.println(bfs(rows, cols));
		out.close();
	}
	
	public static int bfs(int rows, int cols)
	{
		Queue<State> q = new LinkedList<State>();
		q.add(new State(0, 0, 0, 0, 0));
		boolean[][][][] visited = new boolean[rows][cols][4][2];
		while(!q.isEmpty()) 
		{
			State s = q.poll();
			if(!s.isValid(visited))
				continue;
			//System.out.println(s);
			int r = s.x;
			int c = s.y;
			int o = s.orange;
			int dir = s.dir;
			visited[r][c][dir][o] = true;
			if(r == rows - 1 && c == cols - 1)
				return s.d;
			if(a[r][c] == 1 || a[r][c] == 3)
			{
				for(int i = 0; i < dx.length; i++)
				{
					State newState = new State(r + dx[i], c + dy[i], i, o, s.d + 1);
					if(newState.isValid(visited))
						q.add(newState);
				}
			}
			else if(a[r][c] == 2)
			{
				for(int i = 0; i < dx.length; i++)
				{
					State newState = new State(r + dx[i], c + dy[i], i, 1, s.d + 1);
					if(newState.isValid(visited))
						q.add(newState);
				}
			}
			else
			{
				State newState = new State(r + dx[dir], c + dy[dir], dir, 0, s.d + 1);
				if(newState.isValid(visited))
					q.add(newState);
				else
				{
					for(int i = 0; i < dx.length; i++)
					{
						if(i == dir)
							continue;
						newState = new State(r + dx[i], c + dy[i], i, 0, s.d + 1);
						if(newState.isValid(visited))
							q.add(newState);
					}
				}
			}
		}
		return -1;
	}
	
	static class State
	{
		int x, y, dir, orange, d;
		public State(int x, int y, int dir, int orange, int d)
		{
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.orange = orange;
			this.d = d;
		}
		
		public boolean isValid(boolean[][][][] visited)
		{
			if(x < 0 || y < 0 || x >= visited.length || y >= visited[0].length || visited[x][y][dir][orange])
				return false;
			if(a[x][y] == 0 || a[x][y] == 3 && orange == 0)
				return false;
			return true;
		}
		
		public String toString()
		{
			return x + " " + y + " " + dir + " " + orange + " " + d;
		}
	}
}