import java.util.*;
import java.io.*;

public class team 
{
	static final int MOD = (int)1e9 + 9;
	static int[][][] dp;
	static int[] fb, fj;
	public static void main(String[] args) throws IOException 
	{
//		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		BufferedReader f = new BufferedReader(new FileReader("team.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("team.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		fj = new int[Integer.parseInt(st.nextToken())];
		fb = new int[Integer.parseInt(st.nextToken())];
		int k = Integer.parseInt(st.nextToken());
		load(f.readLine(), fj);
		load(f.readLine(), fb);
		out.println(solve(k));
		out.close();
		f.close();
		System.exit(0);
	}
	
	public static int solve(int size)
	{
		int N = fj.length;
		int M = fb.length;
		int[][] dpLast = new int[N + 1][M + 1];
		int[][] dp = new int[N + 1][M + 1];
		int[][] sumsLast = new int[N + 1][M + 1];
		int[][] sums = new int[N + 1][M + 1];
		for(int[] temp : dpLast)
			Arrays.fill(temp, 1);
		for(int r = 0; r <= N; r++)
			for(int c = 0; c <= M; c++)
				sumsLast[r][c] = c + 1;
		for(int k = 1; k <= size; k++)
		{
			for(int n = 1; n <= N; n++)
			{
				for(int m = 1; m <= M; m++)
				{
					dp[n][m] = dp[n - 1][m];
//					for(int i = 1; i <= m; i++)
//					{
//						if(fb[i - 1] >= fj[n - 1])
//						{
//							break;
//						}
//						dp[n][m] = (dp[n][m] + dpLast[n - 1][i - 1]) % MOD;
//					}
//					if(search(fj[n - 1]) != -1)
//						System.out.printf("%d %d %d %d %d %d\n", k, n, m, dp[n][m], dp[n - 1][m] + sumsLast[n - 1][Math.min(search(fj[n - 1]), m - 1)], search(fj[n - 1]));
//					else
//						System.out.printf("%d %d %d %d %d\n", k, n, m, dp[n][m], dp[n - 1][m]);
					int index = search(fj[n - 1]);
					if(index != -1)
						dp[n][m] = (dp[n][m] + sumsLast[n - 1][Math.min(index, m - 1)]) % MOD;
					sums[n][m] = (sums[n][m - 1] + dp[n][m]) % MOD;
				}
			}
			copy(dp, dpLast);
			copy(sums, sumsLast);
		}
		return dp[N][M];
	}
	
	public static void copy(int[][] a, int[][] b)
	{
		for(int r = 0; r < a.length; r++)
			System.arraycopy(a[r], 0, b[r], 0, a[r].length);
	}
	
	public static int search(int val)
	{
		int low = 0;
		int high = fb.length - 1;
		while(low <= high)
		{
			int mid = low + high >> 1;
			if(fb[mid] < val && (mid == fb.length - 1 || fb[mid + 1] >= val))
				return mid;
			if(fb[mid] >= val)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return -1;
	}
	
	public static int recurse(int n, int m, int k)
	{
		if(k == 0)
			return 1;
		if(n < 0 || m < 0)
			return 0;
		if(dp[n][m][k] != -1)
		{
//			System.out.println("DP: " + n + " " + m + " " + k);
			return dp[n][m][k];
		}
		long result = 0;
//		System.out.println(n + " " + m + " " + k);
		for(int i = k - 1; i <= m; i++)
		{
			if(fb[i] >= fj[n])
				break;
//			System.out.printf("%d %d %d calling %d %d %d\n", n, m, k, n - 1, i - 1, k - 1);
			result = (result + recurse(n - 1, i - 1, k - 1)) % MOD;
		}
		result = (result + recurse(n - 1, m, k)) % MOD;
		return dp[n][m][k] = (int)result;
	}
	
	public static void load(String s, int[] a)
	{
		StringTokenizer st = new StringTokenizer(s);
		for(int i = 0; i < a.length; i++)
			a[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(a);
	}
}