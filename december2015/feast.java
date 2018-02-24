import java.util.*;
import java.io.*;

public class feast 
{
	static int[][] dp;
	static int a, b, size;
	public static void main(String[] args) throws IOException {
//		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		BufferedReader f = new BufferedReader(new FileReader("feast.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("feast.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		size = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		dp = new int[2][size + 1];
		for(int[] temp : dp)
			Arrays.fill(temp, -1);
		out.println(recurse(0, 0));
		out.close();
	}
	
	public static int recurse(int fullness, int flag)
	{
		if(fullness > size)
			return 0;
		if(dp[flag][fullness] != -1)
			return dp[flag][fullness];
		int best = Math.max(fullness, Math.max(recurse(fullness + a, flag), recurse(fullness + b, flag)));
		if(flag == 0)
			best = Math.max(best, recurse(fullness >> 1, 1));
		return dp[flag][fullness] = best;
	}
}
