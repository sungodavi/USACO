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
	static HashSet<String> visited;
	static String target;
	static int limit;
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
		limit = 12;
		for(int i = 15; i <= 15; i++)
		{
			visited = new HashSet<String>();
			limit = i;
			if(dfs(0))
			{
				System.out.println(result);
				break;
			}
		}
		out.close();
	}
	
	public static boolean dfs(int depth)
	{
		if(depth > limit || visited.contains(new String(a)))
			return false;
		visited.add(new String(a));
		System.out.println(new String(a) + " " + depth + " ");
		if(new String(a).equals(target))
			return true;
		int index = space();
		if(index >= 2 && a[index - 1] != a[index - 2])
			System.out.print(1);
		if(index >= 1)
			System.out.print(2);
		if(index < a.length - 1)
			System.out.print(3);
		if(index < a.length - 2 && a[index + 1] != a[index + 2])
			System.out.print(4);
		System.out.println();
		
		if(index >= 2 && a[index - 1] != a[index - 2])
		{
			if(move(index, index - 2, depth))
			{
				return true;
			}
		}
		
		if(index >= 1)
		{
			if(move(index, index - 1, depth))
			{
				return true;
			}
		}
		
		if(index < a.length - 1)
		{
			if(move(index, index + 1, depth))
			{
				return true;
			}
		}
		
		if(index < a.length - 2 && a[index + 1] != a[index + 2])
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
			result.add(i2);
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