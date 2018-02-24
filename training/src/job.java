/*
ID: sungoda1
LANG: JAVA
TASK: job
 */

import java.util.*;
import java.io.*;

public class job
{
	static int[] a;
	static int[] b;
	static int jobs;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("job.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("job.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		jobs = Integer.parseInt(st.nextToken());
		a = new int[Integer.parseInt(st.nextToken())];
		b = new int[Integer.parseInt(st.nextToken())];
		int maxA = 1;
		int maxB = 1;
		
		st = new StringTokenizer(f.readLine());
		for(int i = 0; i < a.length; i++)
		{
			if(!st.hasMoreTokens())
				st = new StringTokenizer(f.readLine());
			maxA = Math.max(maxA, a[i] = Integer.parseInt(st.nextToken()));
		}
		for(int i = 0;  i < b.length; i++)
		{
			if(!st.hasMoreTokens())
				st = new StringTokenizer(f.readLine());
			maxB = Math.max(maxB, b[i] = Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(b);
		
		int[] ta = getTimes(a);
		out.print(ta[ta.length - 1] + " ");
		int[] tb = getTimes(b);
		int max = 0;
		for(int i = 0; i < jobs; i++)
			max = Math.max(max, ta[i] + tb[jobs - i - 1]);
		out.println(max);
		out.close();
	}
<<<<<<< HEAD
=======
	
	public static int[] getTimes(int[] a)
	{
		int[] result = new int[jobs];
		int[] times = new int[a.length];
		for(int i = 0; i < jobs; i++)
		{
			int min = 0;
			int bestTime = times[0] + a[0];
			for(int j = 1; j < times.length; j++)
			{
				if(times[j] + a[j] < bestTime)
				{
					min = j;
					bestTime = times[j] + a[j];
				}
			}
			times[min] += a[min];
			result[i] = times[min];
		}
		return result;
	}
>>>>>>> 33727e7d472d74b9413d92830916bb0f5827867f
}