/*
ID: sungoda1
LANG: JAVA
TASK: theme
*/

import java.util.*;
import java.io.*;

class theme 
{
	static int[] sa, ra, lcp, a;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("theme.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("theme.out")));
		StringTokenizer st = null;
		int size = Integer.parseInt(f.readLine());
		int[] notes = new int[size];
		for(int i = 0; i < size; i++)
		{
			if(i % 20 == 0)
				st = new StringTokenizer(f.readLine());
			notes[i] = Integer.parseInt(st.nextToken());
		}
		a = new int[size];
		for(int i = 0; i < size - 1; i++)
			a[i] = notes[i + 1] - notes[i] + 100;
		build();
		int[] temp = new int[size];
		for(int i =0; i < size; i++)
			temp[i] = i;
		display();
	}
	
	public static void display()
	{
		for(int num : sa)
		{
			int[] temp = new int[sa.length - num];
			System.arraycopy(a, num, temp, 0, temp.length);
			System.out.println(Arrays.toString(temp));
		}
	}
	
	public static void lcp()
	{
		int[] phi = new int[sa.length];
		lcp = new int[sa.length];
		int[] temp = new int[sa.length];
		phi[sa[0]] = -1;
		for(int i = 1; i < sa.length; i++)
			phi[sa[i]] = sa[i - 1];
		int len = 0;
		for(int i = 0; i < sa.length; i++, len = Math.max(0 , len - 1))
		{
			if(phi[i] != -1)
			{
				while(i + len < sa.length && phi[i] + len < sa.length && a[i + len] == a[phi[i] + len])
					len++;
				temp[i] = len;
			}
		}
		for(int i = 0; i < sa.length; i++)
			lcp[i] = temp[sa[i]];
	}
	
	public static void build()
	{
		int n = a.length;
		sa = new int[n];
		ra = new int[n];
		for(int i =0; i < n; i++)
		{
			sa[i] = i;
			ra[i] = a[i];
		}
		
		for(int k = 1; k < n; k <<= 1)
		{
			sort(k);
			sort(0);
			int[] temp = new int[n];
			int r = 0;
			for(int i = 1; i < n; i++)
			{
				if(ra[sa[i]] == ra[sa[i - 1]] && ra[sa[i] + k] == ra[sa[i - 1] + k])
					temp[sa[i]] = r;
				else
					temp[sa[i]] = ++r;
			}
			System.arraycopy(temp, 0, ra, 0, n);
		}
		lcp();
	}
	
	public static void sort(int k)
	{
		int[] c = new int[5001];
		int n = a.length;
		for(int i = 0; i < n; i++)
		{
			int num = i + k;
			c[num < n ? ra[num] : 0]++;
		}
		for(int i = 1; i < c.length; i++)
			c[i] += c[i - 1];
		int[] temp = new int[n];
		for(int i = n - 1; i >= 0; i--)
		{
			int num = sa[i] + k;
			temp[--c[num < n ? ra[num] : 0]] = sa[i];
		}
		System.arraycopy(temp, 0, sa, 0, n);
	}
}