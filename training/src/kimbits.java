/*
ID: sungoda1
LANG: JAVA
TASK: kimbits
 */

import java.util.*;
import java.io.*;

public class kimbits 
{
	public static String findN(int size, int num, long index)
	{
		int[] a = new int[num];
		for(int i = 0; i < a.length; i++)
			a[i] = -1;
		System.out.println(index);
		for(; index > 1; index--)
		{
			//System.out.println(index + " " + convert(a, size) + " " + Arrays.toString(a));
			boolean found = false;
			for(int j = 0; j < a.length - 1; j++)
			{
				if(a[j] + 1 < a[j+1])
				{
					a[j]++;
					found = true;
					break;
				}
				else
					a[j] = -1;
			}
			if(!found)
			{
				a[a.length - 1]++;
			}
			System.out.println(index + " " + Arrays.toString(a) + convert(a, size));
		}
		return convert(a, size);
	}
	
	public static String convert(long n, int size)
	{
		StringBuilder result = new StringBuilder(Long.toBinaryString(n));
		while(result.length() < size)
			result.insert(0, "0");
		return result.toString();
	}
	
	public static String convert(int[] a, int size)
	{
		char[] result = new char[size];
		Arrays.fill(result, '0');
		for(int i : a)
		{
			if(i >= 0)
				result[result.length - 1 - i] = '1';
		}
		return new String(result);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("kimbits.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"kimbits.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int size = Integer.parseInt(st.nextToken());
		int num = Integer.parseInt(st.nextToken());
		long index = Long.parseLong(st.nextToken());
		System.out.println(findN(size, num, index));
		out.close();
	}
}