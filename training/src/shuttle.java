/*
ID: sungoda1
LANG: JAVA
TASK: shuttle
*/

import java.util.*;
import java.io.*;

class shuttle 
{
	static char[] a;
	static HashMap<String, Integer> visited;
	static String target;
	static int lim, nlim;
	static final int INF = (int)1e9;
	static ArrayList<Integer> result;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("shuttle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shuttle.out")));
		int size = Integer.parseInt(f.readLine());
		result = new ArrayList<Integer>();
		target = " ";
		for(int i = 0; i < size; i++)
			target = 'B' + target + 'W';
		a = new char[target.length()];
		for(int i = 0; i < target.length(); i++)
			a[i] = target.charAt(target.length() - i - 1);
		visited = new HashMap<String, Integer>();
		lim = size * (size + 2);
		if(dfs(0))
		{
			int count, k;
			for(k = result.size() - 1, count = 1; k >= 0; k--, count++)
			{
				out.print(result.get(k));
				if(count % 20 == 0)
					out.println();
				else if(k != 0)
					out.print(' ');
			}
			if(--count % 20 != 0)
				out.println();
		}
		out.close();
	}
	
	public static int h(char[] a)
	{
		int count = 0;
		for(int i = 0; i < a.length >> 1; i++)
			if(a[i] != 'B')
				count++;
		for(int i = a.length / 2 + 1; i < a.length; i++)
			if(a[i] != 'W')
				count++;
		return count;
	}
	
	public static boolean dfs(int depth)
	{
		if(depth > lim)
		{
			return false;
		}
		String temp = new String(a);
		if(visited.containsKey(temp) && visited.get(temp) <= depth)
			return false;
		visited.put(temp, depth);
//		System.out.println(new String(a) + " " + depth + " ");
		if(new String(a).equals(target))
			return true;
		int index = space();
//		if(index >= 2 && a[index - 1] != a[index - 2])
//			System.out.print(1);
//		if(index >= 1)
//			System.out.print(2);
//		if(index < a.length - 1)
//			System.out.print(3);
//		if(index < a.length - 2 && a[index + 1] != a[index + 2])
//			System.out.print(4);
//		System.out.println();
		
		if(index >= 2 && a[index - 1] != a[index - 2] && a[index - 2] == 'W')
		{
			if(move(index, index - 2, depth))
			{
				return true;
			}
		}
		
		if(index >= 1 && a[index - 1] == 'W')
		{
			if(move(index, index - 1, depth))
			{
				return true;
			}
		}
		
		if(index < a.length - 1 && a[index + 1] == 'B')
		{
			if(move(index, index + 1, depth))
			{
				return true;
			}
		}
		
		if(index < a.length - 2 && a[index + 1] != a[index + 2] && a[index + 2] == 'B')
		{
			if(move(index, index + 2, depth))
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean move(int i1, int i2, int depth)
	{
		char temp = a[i2];
		a[i2] = ' ';
		a[i1] = temp;
		if(dfs(depth + 1))
		{
			result.add(i2 + 1);
			return true;
		}
		a[i1] = ' ';
		a[i2] = temp;
		return false;
	}
	
	public static int space()
	{
		for(int i = 0; i < a.length; i++)
			if(a[i] == ' ')
				return i;
		return -1;
	}
}