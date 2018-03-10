/*
ID: sungoda1
LANG: JAVA
TASK: hidden
 */
import java.util.*;
import java.io.*;

public class hidden
{
	static StringBuilder t;
	static int[] sa, ra, lcp;
	static int n;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("hidden.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hidden.out")));
		int size = io(f);
		build();
		int index = -1;
		for(int i = 0; i < n; i++)
		{
			if(sa[i] < size)
			{
				index = i;
				break;
			}
		}
		for(int i = index + 1; i < n && lcp[i] >= size; i++)
			if(sa[i] < sa[index])
				index = i;
		out.println(sa[index]);
		out.close();
		System.exit(0);
	}
	
	public static int io(BufferedReader f) throws IOException
	{
		int size = Integer.parseInt(f.readLine());
		StringBuilder s = new StringBuilder();
		while(s.length() < size)
		{
			s.append(f.readLine());
		}
		s.append(s);
		s.append('$');
		n = s.length();
		t = s;
		f.close();
		return size;
	}
	
	public static void display()
	{
		for(int n : sa)
			System.out.println(t.substring(n));
		System.out.println(Arrays.toString(sa));
		System.out.println(Arrays.toString(lcp));
	}
	
	public static void lcp()
	{
		lcp = new int[n];
		int[] phi = new int[n];
		phi[sa[0]] = -1;
		for(int i = 1; i < n; i++)
			phi[sa[i]] = sa[i - 1];
		int len = 0;
		int[] temp = new int[n];
		for(int i = 0; i < n; i++)
		{
			if(phi[i] == -1)
				continue;
			while(len < n && t.charAt(i + len) == t.charAt(phi[i] + len))
				len++;
			temp[i] = len;
			len = Math.max(len - 1, 0);
		}
		for(int i = 0; i < n; i++)
			lcp[i] = temp[sa[i]];
	}
	
	public static void build()
	{
		sa = new int[n];
		ra = new int[n];
		for(int i = 0; i < n; i++)
		{
			sa[i] = i;
			ra[i] = t.charAt(i);
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
		int[] c = new int[Math.max(255, n)];
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