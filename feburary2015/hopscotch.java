/*
ID: sungoda1
LANG: JAVA
TASK: hopscotch
*/
import java.util.*;
import java.io.*;

class hopscotch 
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("hopscotch.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hopscotch.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int rows = Integer.parseInt(st.nextToken());
		int cols = Integer.parseInt(st.nextToken());
		char[][] a = new char[rows][cols];
		int[][] dp = new int[rows][cols];
		dp[0][0] = 1;
		for(int r = 0; r < a.length; r++)
			a[r] = f.readLine().toCharArray();
		for(int r = 0; r < a.length; r++)
			for(int c = 0; c < a.length; c++)
			{
				for(int i = 0; i < r; i++)
					for(int j = 0; j < c; j++)
						if(a[i][j] != a[r][c])
							dp[r][c] += dp[i][j];
			}
		out.println(dp[rows - 1][cols - 1]);
		out.close();
	}
}
