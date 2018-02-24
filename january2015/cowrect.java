import java.util.*;
import java.io.*;

public class cowrect
{
	static final int INF = 1000;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowrect.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowrect.out")));
		StringTokenizer st;
		
		int[][] a = new int[1001][1001];
		int rows = 0;
		int columns = 0;
		
		int cows = Integer.parseInt(f.readLine());
		while(cows-- > 0)
		{
			st = new StringTokenizer(f.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			char type = st.nextToken().charAt(0);
			if(type == 'H')
			{
				a[r][c] = 1;
				rows = Math.max(rows, r);
				columns = Math.max(columns, c);
			}
			else
				a[r][c] = -INF;
		}
		
		int[][] dp = new int[rows + 1][columns + 1];
		for(int r = 0; r < dp.length; r++)
			for(int c = 0; c < dp[0].length; c++)
			{
				dp[r][c] = a[r][c] + (r > 0 ? dp[r - 1][c] : 0);
			}
		
		int max = 0;
		int area = 0;
		for(int size = 1; size <= dp.length; size++)
		{
			for(int r = size - 1; r < dp.length; r++)
			{
				int count = 0;
				int startC = 0;
				for(int c = 0; c < dp[0].length; c++)
				{
					count += dp[r][c] - (r >= size ? dp[r - size][c] : 0);

					if(count <= 0)
					{
						count = 0;
						startC = c + 1;
					}
					else
					{
						int tempArea = (c - startC) * (size - 1);
						if(count > max || (count == max && tempArea < area))
						{
							//System.out.println(size + " " + r + " " + c + " " + startC);
							//System.out.println(max + " " + area + " " + count + " " + tempArea);
							max = count;
							area = tempArea;
						}
					}
				}
			}
		}
		out.println(max);
		out.println(area);
		out.close();
	}
}