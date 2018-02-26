import java.util.*;
import java.io.*;

public class standingout
{
	static int[] sa, ra, lcp, sizes;
	static StringBuilder t;
	static int offset;
	public static void main(String[] args) throws IOException //UNSOLVED
	{
//		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		BufferedReader f = new BufferedReader(new FileReader("input.in"));
//		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("standingout.out")));
		int size = Integer.parseInt(f.readLine());
		long counts[] = new long[size];
		offset = size + 1;
		StringBuilder s = new StringBuilder();
		sizes = new int[size + 1];
		for(int i = 0; i < size; i++)
		{
			String temp = f.readLine() + '$';
			s.append(temp);
			counts[i] = count(temp.length() - 1);
			sizes[i + 1] = sizes[i] + temp.length();
		}
		build(s);
		int prev = 0;
		boolean flag = true;
		for(int i = 0; i < lcp.length; i++)
		{
			if(lcp[i] > 0)
			{
				int i1 = search(sa[i]);
				int i2 = search(sa[i - 1]);
				counts[i1] -= lcp[i];
				if(i1 == i2)
					flag = false;
				else if(!flag || prev <= lcp[i])
				{
					counts[i2] -= lcp[i] - (flag ? prev : 0);
					flag = true;
				}
			}
			prev = lcp[i];
		}
		for(long num : counts)
			out.println(num);
		out.close();
	}
	
	public static long count(int size)
	{
		return size * (size + 1) >> 1;
	}
	
	public static int search(int val)
	{
		int low = 0;
		int high = sizes.length - 1;
		while(low <= high)
		{
			int mid = low + high >> 1;
			if(sizes[mid] == val)
				return mid;
			if(sizes[mid] < val)
				low = mid + 1;
			else
				high = mid - 1;
		}
		return high;
	}
	
	public static void lcp()
	{
		int n = sa.length;
		int[] temp = new int[n];
		lcp = new int[n];
		int[] phi = new int[n];
		phi[sa[0]] = -1;
		for(int i = 1; i < n; i++)
			phi[sa[i]] = sa[i - 1];
		int len = 0;
		for(int i = 0; i < n; i++, len = Math.max(0, len - 1))
		{
			if(phi[i] != -1)
			{
				while(len < n && t.charAt(len + i) == t.charAt(len + phi[i]) && t.charAt(len + i) != '$')
					len++;
				temp[i] = len;
			}
		}
		for(int i = 0; i < n; i++)
			lcp[i] = temp[sa[i]];
	}
	
	public static void build(StringBuilder s)
	{
		int n = s.length();
		t = s;
		sa = new int[n];
		ra = new int[n];
		int count = offset;
		for(int i = 0; i < n; i++)
		{
			sa[i] = i;
			if(s.charAt(i) == '$')
				ra[i] = count--;
			else
				ra[i] = s.charAt(i) + offset;
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
	
	public static void sort(int k) //add one to offset
	{
		int n = sa.length;
		int[] c = new int[Math.max(offset + 255, n)];
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
	
	public static void display()
	{
		for(int num : sa)
			System.out.println(t.substring(num));
	}
}