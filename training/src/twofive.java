/*
ID: sungoda1
LANG: JAVA
TASK: twofive
 */

import java.util.*;
import java.io.*;
import java.awt.Point;

public class twofive
{
	static HashMap<Integer, Integer> dp;
	static int size, OK, FULL;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("twofive.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("twofive.out")));
		char query = (char)f.read();
		f.readLine();
		init(5);
		recurse(1);
		if(query == 'N')
			out.println(rebuild(Integer.parseInt(f.readLine())));
		else
			out.println(count(f.readLine()));
		out.close();
	}
	
	public static int count(String s)
	{
		int mask = 1;
		int result = 1;
		Point[] a = new Point[25];
		for(int i = 1; i < s.length(); i++)
			a[s.charAt(i) - 'A'] = new Point(i / size, i % size);
		for(int i = 1; i < s.length(); i++)
		{
			Point target = a[i];
			int r = 0, c = 0;
			for(int temp = mask; temp > 0; temp >>= size, r++)
			{
				c = lg(FULL & temp) + 1;
				if(r == target.x && c == target.y)
					break;
				if(check(mask, r, c))
				{
					int flag = 1 << size * r + c;
					result += dp.get(mask | flag);
				}
			}
			mask |= 1 << size * target.x + target.y;
		}
		return result;
	}
	
	public static String rebuild(int count)
	{
		int mask = 1;
		char[] result = new char[size * size];
		result[0] = 'A';
		for(int i = 1; i < result.length; i++)
		{
			int r = 0, c = 0;
			for(int temp = mask; temp > 0; temp >>= size, r++)
			{
				c = lg(FULL & temp) + 1;
				int flag = 1 << size * r + c;
				if(check(mask, r, c))
				{
					System.out.println(r + " " + c + " " + dp.get(mask | flag) + " " + count);
					if(dp.get(mask | flag) >= count)
					{
						mask |= flag;
						break;
					}
					else
						count -= dp.get(mask | flag);					
				}
				c = 0;
			}
			if(r < size)
				mask |= 1 << size * r;
			result[size * r + c] = (char)('A' + i);
			System.out.println(r + " " + c + " " + Integer.toBinaryString(mask));
		}
		return new String(result);
	}
	
	public static void init(int n)
	{
		size = n;
		FULL = (1 << size) - 1;
		OK  = (1 << size * size) - 1;
		dp = new HashMap<Integer, Integer>();
		dp.put(OK, 1);
	}
	
	public static int recurse(int mask)
	{
		//System.out.println(Integer.toBinaryString(mask));
		if(dp.containsKey(mask))
			return dp.get(mask);
		int count = 0;
		int r = 0;
		for(int temp = mask; temp > 0; temp >>= size, r++)
		{
			int c = lg(FULL & temp) + 1;
			int flag = 1 << size * r + c;
			if(check(mask, r, c))
				count += recurse(mask | flag);
		}
		if(r < size)
			count += recurse(mask | 1 << size * r);
		dp.put(mask, count);
		return count;
	}
	
	public static boolean check(int mask, int r, int c)
	{
		return c < size && (r == 0 || (mask & 1 << size * (r - 1) + c) > 0);
	}
	
	public static int lg(int num)
	{
		return 31 - Integer.numberOfLeadingZeros(num);
	}
}