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
		int machineA = Integer.parseInt(st.nextToken());
		int machineB = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		a = new int[machineA];
		b = new int[machineB];
		for(int i = 0; i < a.length; i++)
			a[i] = Integer.parseInt(st.nextToken());
		for(int i = 0; i < b.length; i++)
			b[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(a);
		Arrays.sort(b);
	}
	
	public static boolean check(int time)
	{
		int[] bufferA = new int[a.length];
		int[] bufferB = new int[b.length];
		int count = 0;
		for(int t = 0; t < time; t++)
		{
			
		}
	}
}