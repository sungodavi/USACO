import java.util.*;
import java.io.*;

public class nocross 
{
	public static void main(String[] args) throws IOException 
	{
//		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		BufferedReader f = new BufferedReader(new FileReader("nocross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocross.out")));
		
		int size = Integer.parseInt(f.readLine());
		int[] left = new int[size];
		ArrayList<Integer>[] left_pos = new ArrayList[size];
		int[] right = new int[size];
		ArrayList<Integer>[] right_pos = new ArrayList[size];
		for(int i = 0; i < size; i++)
		{
			left_pos[i] = new ArrayList<Integer>();
			right_pos[i] = new ArrayList<Integer>();
		}
		for(int i = 0; i < size; i++)
			left[i] = Integer.parseInt(f.readLine());
		for(int i = 0; i < size; i++)
			right[i] = Integer.parseInt(f.readLine());
		
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				if(Math.abs(left[i] - right[j]) <= 4)
				{
					left_pos[i].add(j);
					right_pos[j].add(i);
				}
			}
		}
		int[][] dp = new int[size + 1][size + 1];
		for(int r = 1; r <= size; r++)
		{
			for(int c = 1; c <= size; c++)
			{
				int best = Math.max(dp[r - 1][c], dp[r][c - 1]);
				for(int k : left_pos[r - 1])
				{
					if(k < c)
					{
						best = Math.max(best, dp[r - 1][k] + 1);
					}
				}
				for(int k : right_pos[c - 1])
				{
					if(k < r)
					{
						best = Math.max(best, dp[k][c - 1] + 1);
					}
				}
				dp[r][c] = best;
			}
		}
		out.println(dp[size][size]);
		out.close();
	}
}