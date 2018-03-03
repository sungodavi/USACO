/*
ID: sungoda1
LANG: JAVA
TASK: snail
*/

import java.util.*;
import java.io.*;

class snail 
{
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] visited, blocked;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("snail.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snail.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int size = Integer.parseInt(st.nextToken());
		int blocks = Integer.parseInt(st.nextToken());
		blocked = new boolean[size][size];
		while(blocks-- > 0)
		{
			int c = f.read() - 'A';
			int r = Integer.parseInt(f.readLine()) - 1;
			blocked[r][c] = true;
		}
		visited = new boolean[size][size];
		int r1 = dfs(0, 0, 1);
		visited = new boolean[size][size];
		int r2 = dfs(0, 0, 2);
		out.println(Math.max(r1,  r2));
		out.close();
	}
	
	public static int dfs(int r, int c, int index)
	{
		if(!isValid(r, c) || visited[r][c])
			return 0;
		//System.out.println(r + " " + c + " " + index);
		int x = r + dx[index];
		int y = c + dy[index];
		visited[r][c] = true;
		int result;
		if(!isValid(x, y))
		{
			int i1 = (index + 1) % 4;
			int i2 = (index + 3) % 4;
			result =  1 + Math.max(dfs(r + dx[i1], c + dy[i1], i1), dfs(r + dx[i2], c + dy[i2], i2));
		}
		else
			result =  1 + dfs(x, y, index);
		visited[r][c] = false;
		return result;
	}
	
	public static boolean isValid(int r, int c)
	{
		return r >= 0 && r < blocked.length && c >= 0 && c < blocked[0].length && !blocked[r][c];
	}
}