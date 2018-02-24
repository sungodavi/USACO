import java.util.*;
import java.io.*;

<<<<<<< HEAD
public class nocross {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		//BufferedReader f = new BufferedReader(new FileReader("nocross.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocross.out")));
		int size = Integer.parseInt(f.readLine());
		int[] left = new int[size];
		int[] right = new int[size];
		int[] mapL = new int[size + 1];
		int[] mapR = new int[size + 1];
		for(int i = 0; i < size; i++)
		{
			int num = Integer.parseInt(f.readLine());
			left[i] = num;
			mapL[num] = i;
		}
		for(int i = 0; i < size; i++)
		{
			int num = Integer.parseInt(f.readLine());
			right[i] = num;
			mapR[num] = i;
		}
		int[] result = new int[size + 1];
		int[][] dp = new int[size + 1][2];
		for(int i = 0; i < size; i++)
		{
			int l = left[i];
			int best = 0;
			for(int k = max(1, l - 4); k <= Math.min(size, l + 4); k++)
			{
				int index = mapR[k];
				if(index < i)
					best = Math.max(best, max(dp[index + 1][0], result[index]) + 1);
			}
			dp[i + 1][0] = best;
			int r = right[i];
			best = 0;
			for(int k = max(1, r - 4); k <= Math.min(size, r + 4); k++)
			{
				int index = mapL[k];
				if(index < i)
					best = Math.max(best, max(dp[index + 1][1], result[index]) + 1);
			}
			dp[i + 1][1] = best;
			result[i + 1] = max(dp[i + 1][0], dp[i + 1][1], result[i] + (Math.abs(left[i] - right[i]) <= 4 ? 1 : 0));
		}
		System.out.println(Arrays.toString(left));
		System.out.println(Arrays.toString(right));
		System.out.println(Arrays.toString(result));
		for(int[] temp : dp)
			System.out.println(Arrays.toString(temp));
		out.println(result[size]);
		out.close();
	}
	
	public static int max(int x, int y)
	{
		return x > y ? x : y;
	}
	
	public static int max(int a, int b, int c)
	{
		return max(a, max(b, c));
	}
=======
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
>>>>>>> 33727e7d472d74b9413d92830916bb0f5827867f
}