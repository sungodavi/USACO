/*
ID: sungoda1
LANG: JAVA
TASK: nocows
 */

import java.util.*;
import java.io.*;

public class nocows 
{
	public static long solve(int n, int k)
	{
		long[][] a = new long[k + 1][n + 1];
		a[1][1] = 1;
		for(int r = 2; r <= k; r++)
		{
			for(int c = 2 * r - 1; c <= n; c += 2)
			{
				for(int part = 1; part <= c / 2; part += 2)
				{
					int s1 = 0;
					int s2 = 0;
					for(int i = 1; i < r - 1; i++)
					{
						s1 += a[i][part] % 9901;
						s2 += a[i][c - 1 - part] % 9901;
						if(s1 < 0 || s2 < 0)
							System.out.println("1. Error");
					}
					long total = (s1 * a[r - 1][c - 1 - part] % 9901 + s2 * a[r-1][part] % 9901 + a[r-1][part] * a[r-1][c-1-part] % 9901) % 9901;
					if(total < 0)
						System.out.println("2. Error");
					a[r][c] += (part == (c - 1) / 2 ? 1 : 2) * total;
					if(a[r][c] < 0)
						System.out.println("3. Error");
					a[r][c] %= 9901;
				}
			}
		}		
		return a[k][n];
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("nocows.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"nocows.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		out.println(solve(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		out.close();

	}
}