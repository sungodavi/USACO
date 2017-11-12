/*
ID: sungoda1
LANG: JAVA
TASK: fracdec
 */

import java.util.*;
import java.io.*;

public class fracdec 
{
	public static String solve(int n, int d)
	{
		int gcd = gcd(n, d);
		n /= gcd;
		d /= gcd;
		StringBuilder result = new StringBuilder(n / d + ".");
		int r = n % d;
		HashMap<Integer, Integer> set = new HashMap<Integer, Integer>();
		int index = 0;
		while(!set.containsKey(r))
		{
			if(r == 0)
			{
				if(result.charAt(result.length() - 1) == '.')
					return result + "0";
				return result.toString();
			}
			set.put(r, index++);
			result.append(r * 10 / d);
			r = (r * 10) % d;
		}
		//System.out.println(list);
		int i = set.get(r) + 1 + result.indexOf(".");
		result.insert(i, "(");
		result.append(")");
		return result.toString();
	}
	
	public static int gcd(int x, int y)
	{
		while(y > 0)
		{
			int temp = y;
			y = x % y;
			x = temp;
		}
		return x;
	}
	
	public static void print(String s, PrintWriter out)
	{
		for(int i = 0; i < s.length(); i += 76)
		{
			out.println(s.substring(i, Integer.min(i + 76, s.length())));
		}
	}
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("fracdec.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"fracdec.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		print(solve(n, d), out);
		out.close();
	}
}