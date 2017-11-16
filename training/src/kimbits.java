/*
ID: sungoda1
LANG: JAVA
TASK: kimbits
 */

import java.util.*;
import java.io.*;

public class kimbits 
{
	public static long index;
	public static int size, bitCount;
	static long[][] a;
	
	public static void recurse(int n, int l, long i, PrintWriter out)
	{
		if(l > n)
			l = n;
		if(a[n][l] == i)
		{
			for(int k = 1; k <= l; k++)
				out.print("1");
			for(int k = 1; k <= n - l; k++)
				out.print("0");
		}
		else if(i > a[n - 1][l])
		{
			out.print("1");
			recurse(n - 1, l - 1, i - a[n - 1][l], out);
		}
		else
		{
			out.print("0");
			recurse(n - 1, l, i, out);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("kimbits.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"kimbits.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		size = Integer.parseInt(st.nextToken());
		bitCount = Integer.parseInt(st.nextToken());
		index = Long.parseLong(st.nextToken());
		a = new long[size + 1][bitCount + 1];
		Arrays.fill(a[0], 1);
		for(int r = 1; r < a.length; r++)
			a[r][0] = 1;
		
		for(int r = 1; r < a.length; r++)
			for(int c = 1; c < a[0].length; c++)
			{
				if(c > r)
					a[r][c] = a[r][r];
				else
					a[r][c] = a[r - 1][c - 1] + a[r-1][c];
			}
		
		recurse(size, bitCount, index, out);
		out.println();
		out.close();
	}
}