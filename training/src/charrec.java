/*
ID: sungoda1
LANG: JAVA
TASK: charrec
*/

import java.util.*;
import java.io.*;

class charrec 
{
	static C[] key;
	static int[] a;
	static final int INF = (int)1e9;
	public static void main(String[] args) throws IOException 
	{
		load();
		BufferedReader f = new BufferedReader(new FileReader("charrec.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("charrec.out")));
		int size = Integer.parseInt(f.readLine());
		a = new int[size];
		for(int i = 0; i < size; i++)
			a[i] = Integer.parseInt(f.readLine(), 2);
		f.close();
		StringBuilder s = new StringBuilder();
		int[] dp = new int[size + 1];
		int[] prev = new int[size + 1];
		char[] used = new char[size + 1];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		for(int i = 19; i < dp.length; i++)
		{
			int[] c;
			if(i >= 19 && dp[i - 19] != INF)
			{
				c = copy(i - 19);
				int error = INF;
				int p = -1;
				char u = 0;
				for(int j = 0; j < key.length; j++)
				{
					int e = key[j].checkDelete(c);
					if(e < error)
					{
						error = e;
						p = i - 19;
						u = convert(j);
					}
				}
				if(dp[i - 19] + error < dp[i])
				{
					dp[i] = dp[i - 19] + error;
					prev[i] = p;
					used[i] = u;
				}
			}
			if(i >= 20 && dp[i - 20] != INF)
			{
				c = copy(i - 20);
				int error = INF;
				int p = -1;
				char u = 0;
				for(int j = 0; j < key.length; j++)
				{
					int e = key[j].checkNorm(c);
					if(e < error)
					{
						error = e;
						p = i - 20;
						u = convert(j);
					}
				}
				if(dp[i - 20] + error < dp[i])
				{
					dp[i] = dp[i - 20] + error;
					prev[i] = p;
					used[i] = u;
				}
			}
			if(i >= 21 && dp[i - 21] != INF)
			{
				c = copy(i - 21);
				int error = INF;
				int p = -1;
				char u = 0;
				for(int j = 0; j < key.length; j++)
				{
					int e = key[j].checkAdd(c);
					if(e < error)
					{
						error = e;
						p = i - 21;
						u = convert(j);
					}
				}
				if(dp[i - 21] + error < dp[i])
				{
					dp[i] = dp[i - 21] + error;
					prev[i] = p;
					used[i] = u;
				}
			}
		}
		for(int i = size; i > 0; i = prev[i])
			s.append(used[i]);
		out.println(s.reverse());
		out.close();
		System.exit(0);
	}
	
	public static int[] copy(int index)
	{
		int[] result = new int[Math.min(21, a.length - index)];
		System.arraycopy(a, index, result, 0, result.length);
		return result;
	}
	
	public static void load() throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("font.in"));
		f.readLine();
		key = new C[27];
		for(int i = 0; i < key.length; i++)
		{
			int[] a = new int[20];
			for(int k = 0; k < a.length; k++)
				a[k] = Integer.parseInt(f.readLine(), 2);
			key[i] = new C(a);
		}
		f.close();
	}
	
	static int count(int num)
	{
		int count = 0;
		while(num > 0)
		{
			++count;
			num -= num & -num;
		}
		return count;
	}
	
	static char convert(int index)
	{
		if(index == 0)
			return ' ';
		return (char)(index + 'a' - 1);
	}
	
	static class C
	{
		int[] a;
		public C(int[] a)
		{
			this.a = a;
		}
		
		public int checkNorm(int[] c)
		{
			int error = 0;
			for(int i = 0; i < a.length; i++)
				error += count(a[i] ^ c[i]);
			return error;
		}
		
		public int checkDelete(int[] c)
		{
			int result = Integer.MAX_VALUE;
			int offset = 0;
			int norm = 0;
			for(int i = 1; i < a.length; i++)
				offset += count(a[i] ^ c[i - 1]);
			for(int k = 0; k < a.length; k++)
			{
				int error = norm + offset;
				result = Math.min(result, error);
				if(k < a.length - 1)
				{
					norm += count(a[k] ^ c[k]);
					offset -= count(a[k + 1] ^ c[k]);
				}
			}
			return result;
		}
		
		public int checkAdd(int[] c)
		{
			int result = Integer.MAX_VALUE;
			int offset = 0;
			int norm = 0;
			for(int i = 1; i < a.length; i++)
				offset += count(a[i] ^ c[i + 1]);
			for(int k = 0; k < a.length; k++)
			{
				int error = norm + Math.min(count(a[k] ^ c[k]), count(a[k] ^ c[k + 1])) + offset;
				result = Math.min(result, error);
				if(k < a.length - 1)
				{
					norm += count(a[k] ^ c[k]);
					offset -= count(a[k + 1] ^ c[k + 2]);
				}
			}
			return result;
		}
		
		public String toString()
		{
			String s = "";
			for(int num : a)
				s += Integer.toBinaryString(num) + "\n";
			return s;
		}
	}
}