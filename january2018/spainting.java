import java.util.*;
import java.io.*;

public class spainting {
	static final int MOD = (int)1e9 + 7;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("spainting.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spainting.out")));
//		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		out.println(solve(n, m, k));
		out.close();
	}
	
	public static long solve(int n, int m, int k)
	{
		long[] dp = new long[n + 1];
		long sum = -1;
		for(int i = 0; i < k; i++)
		{
			dp[i] = modPow(m, i);
			sum = (sum + dp[i]) % MOD;
		}
		for(int i = k; i <= n; i++)
		{
			dp[i] = sum * (m - 1) % MOD;
			sum = (sum + dp[i] - dp[i - k + 1]) % MOD;
		}
		return Math.floorMod(modPow(m, n) - dp[n], MOD);
	}
	
	public static long modPow(int b, int e)
	{
		if(b == 1 || e == 0)
			return 1;
		if(e == 1)
			return b;
		long result = modPow(b, e >> 1);
		if(e % 2 == 0)
			return result * result % MOD;
		else
			return result * result % MOD * b % MOD;
	}
}