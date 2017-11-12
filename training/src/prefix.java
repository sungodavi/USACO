/*
ID: sungoda1
LANG: JAVA
TASK: prefix
 */

import java.util.*;
import java.io.*;

public class prefix 
{
	public static boolean equals(String s, String prefix, int index)
	{
		//System.out.println(s + " " + prefix + " " + index);
		int temp = 0;
		for(int i = index - prefix.length() + 1; i <= index; i++)
		{
			if(i < 0 || s.charAt(i) != prefix.charAt(temp++))
				return false;
		}
		return true;
	}
	public static int solve(String s, ArrayList<String> prefixes)
	{
		boolean[] a = new boolean[s.length() + 1];
		a[0] = true;
		int max = 0;
		for(int i = 1; i < a.length; i++)
		{
			for(String prefix : prefixes)
			{
				if(prefix.length() > i)
					continue;
				//System.out.println(s);
				//System.out.println(prefix + " " + (i-1) + " " + equals(s, prefix, i - 1));
				if(equals(s, prefix, i - 1) && a[i - prefix.length()])
				{
					a[i] = true;
					break;
				}
			}
			if(a[i])
				max = i;
		}
		//System.out.println(Arrays.toString(a));
		return max;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("prefix.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"prefix.out")));
		String input = f.readLine();
		ArrayList<String> list = new ArrayList<String>();
		while(!input.equals("."))
		{
			for(String s : input.split(" "))
				list.add(s);
			input = f.readLine();
		}
		
		StringBuilder s = new StringBuilder();
		input = f.readLine();
		while(input != null)
		{
			s.append(input);
			input = f.readLine();
		}
		out.println(solve(s.toString(), list));	
		out.close();

	}
}