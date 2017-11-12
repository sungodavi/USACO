/*
ID: sungoda1
LANG: JAVA
TASK: humble
 */

import java.util.*;
import java.io.*;

public class humble 
{
	public static long search(long[] a, int end, long target)
	{
		int low = 0;
		int high = end;
		while(low <= high)
		{
			int middle = (low + high) / 2;
			if(a[middle] == target)
				return target;
			
			if(a[middle] < target)
				low = middle + 1;
			else
				high = middle - 1;
		}
		return a[Integer.min(low, end)];
	}
	
	public static long solve(int num, long[] primes)
	{
		long[] a = new long[num + 1];
		a[0] = 1;
		for(int i = 1; i <= num; i++)
		{
			long min = Long.MAX_VALUE;
			for(long p : primes)
			{
				long target = a[i-1] / p + 1;
				long n = search(a, i - 1, target) * p;
				if(n < min)
					min = n;
				
				if(min == a[i-1] + 1)
					break;
			}
			a[i] = min;
		}
		//System.out.println(Arrays.toString(a));
		return a[num];
	}
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("humble.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"humble.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		long[] primes = new long[Integer.parseInt(st.nextToken())];
		int n = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(f.readLine());
		for(int i = 0; i < primes.length; i++)
		{
			primes[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(primes);
		
		out.println(solve(n, primes));
		out.close();

	}
}