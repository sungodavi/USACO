/*
ID: sungoda1
LANG: JAVA
TASK: castle
*/
import java.util.*;
import java.io.*;
import java.awt.Point;

class castle
{
	static class Size
	{
		int size;
		public String toString()
		{
			return "" + size;
		}
	}

	static class C
	{
		int[][] a;
		boolean[][] visited;
		Size[][] sizes;
		public C(int[][] maze)
		{
			a = maze;
			visited = new boolean[maze.length][maze[0].length];
			sizes = new Size[maze.length][maze[0].length];
		}
		public int bfs(int r, int c)
		{
			Queue<Point> q = new LinkedList<Point>();
			q.add(new Point(r, c));
			int size = 0;
			Size s = new Size();
			while(!q.isEmpty())
			{
				Point p = q.poll();
				if(visited[p.x][p.y])
					continue;
				++size;
				int num = a[p.x][p.y];
				visited[p.x][p.y] = true;
				sizes[p.x][p.y] = s;
				if((num & 1) == 0)
					q.add(new Point(p.x, p.y-1));
				num >>= 1;
				if((num & 1) == 0)
					q.add(new Point(p.x - 1, p.y));
				num >>= 1;
				if((num & 1) == 0)
					q.add(new Point(p.x, p.y + 1));
				num >>= 1;
				if(num == 0)
					q.add(new Point(p.x + 1, p.y));
			}
			s.size = size;
			return size;
		}
		public int[] count()
		{
			int rooms = 0;
			int max = 0;
			for(int r = 0; r < a.length; r++)
			{
				for(int c = 0; c < a[0].length; c++)
				{
					if(!visited[r][c])
					{
						rooms++;
						int size = bfs(r,c);
						if(size > max)
							max = size;
					}
				}
			}
			return new int[]{rooms, max};
		}	
		public void findWall(PrintWriter out)
		{
			char direction = 'L';
			int max = 0;
			int row = -1;
			int col = -1;
			for(int c = 0; c < a[0].length - 1; c++)
			{
				for(int r = a.length - 1; r > 0; r--)
				{
					if(sizes[r][c] != sizes[r-1][c])
					{
						int combined = sizes[r][c].size + sizes[r-1][c].size;
						if(combined > max)
						{
							max = combined;
							direction = 'N';
							row = r;
							col = c;
						}
					}
					if(sizes[r][c] != sizes[r][c+1])
					{
						int combined = sizes[r][c].size + sizes[r][c+1].size;
						if(combined > max)
						{
							max = combined;
							direction = 'E';
							row = r;
							col = c;
						}
					}
				}
			}
			if(a[0].length > 1 && sizes[0][a[0].length - 2] != sizes[0][a[0].length - 1])
			{	
				int r = 0;
				int c = a[0].length - 2;
				int combined = sizes[r][c].size + sizes[r][c+1].size;
				if(combined > max)
				{
					max = combined;
					direction = 'E';
					row = r;
					col = c;
				}		
			}
			for(int r = a.length - 1; r > 0; r--)
			{
				int c = a[0].length - 1;
				if(sizes[r][c] != sizes[r-1][c])
				{
					int combined = sizes[r][c].size + sizes[r-1][c].size;
					if(combined > max)
					{
						max = combined;
						direction = 'N';
						row = r;
						col = c;
					}
				}
			}
			out.println(max);
			out.printf("%d %d %c\n", row + 1, col + 1, direction);
		}

	}
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("castle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));

		String[] temp = f.readLine().split(" ");
		int[][] a = new int[Integer.parseInt(temp[1])][Integer.parseInt(temp[0])];
		for(int r = 0; r < a.length; r++)
		{
			String[] row = f.readLine().split(" ");
			for(int c = 0; c < a[0].length; c++)
			{
				a[r][c] = Integer.parseInt(row[c]);
			}
		}

		C castle = new C(a);
		int[] result = castle.count();
		out.println(result[0]);
		out.println(result[1]);
		castle.findWall(out);
		out.close();
	}
}	
