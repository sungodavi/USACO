/*
ID: sungoda1
LANG: JAVA
TASK: frameup
 */

import java.util.*;
import java.io.*;
import java.awt.Point;

public class frameup
{
	static Set<Integer>[] list, reverse;
	static int[] parents;
	static char[] indexes, result;
	static PrintWriter out;
	static int OK, size;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("frameup.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("frameup.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int rows = Integer.parseInt(st.nextToken());
		int cols = Integer.parseInt(st.nextToken());
		char[][] a = new char[rows][cols];
		for(int r = 0; r < rows; r++)
		{
			for(int c = 0; c < cols; c++)
				a[r][c] = (char)f.read();
			f.readLine();
		}
		TreeMap<Character, Frame> frames = new TreeMap<Character, Frame>();
		for(int r = 0; r < rows; r++)
		{
			for(int c = 0; c < cols; c++)
			{
				if(a[r][c] != '.')
				{
					char type = a[r][c];
					if(frames.containsKey(type))
						frames.get(type).update(r, c);
					else
						frames.put(type, new Frame(r, c));
				}
			}
		}
		size = frames.size();
		list = new HashSet[size];
		reverse = new HashSet[size];
		indexes = new char[size];
		int[] map = new int[26];
		Iterator<Character> iter = frames.keySet().iterator();
		for(int i = 0; i < list.length; i++)
		{
			char c = iter.next();
			map[c - 'A'] = i;
			indexes[i] = c;
			list[i] = new HashSet<Integer>();
			reverse[i] = new HashSet<Integer>();
		}
		for(char c : frames.keySet())
		{
			Frame fr = frames.get(c);
			for(int r = fr.tl.x; r <= fr.br.x; r++)
			{
				if(a[r][fr.tl.y] != c)
				{
					int u = map[c - 'A'];
					int v = map[a[r][fr.tl.y] - 'A'];
					list[u].add(v);
					reverse[v].add(u);
				}
				if(a[r][fr.br.y] != c)
				{
					int u = map[c - 'A'];
					int v = map[a[r][fr.br.y] - 'A'];
					list[u].add(v);
					reverse[v].add(u);
				}
			}
			
			for(int k = fr.tl.y + 1; k < fr.br.y; k++)
			{
				if(a[fr.tl.x][k] != c)
				{
					int u = map[c - 'A'];
					int v = map[a[fr.tl.x][k] - 'A'];
					list[u].add(v);
					reverse[v].add(u);
				}
				if(a[fr.br.x][k] != c)
				{
					int u = map[c - 'A'];
					int v = map[a[fr.br.x][k] - 'A'];
					list[u].add(v);
					reverse[v].add(u);
				}
			}
		}
		parents = new int[size];
		Arrays.fill(parents, -1);
		for(int i = 0; i < size; i++)
			if(parents[i] == -1)
				dfs(i);
		OK = (1 << size) - 1;
		result = new char[size];
		recurse(0, 0);
		out.close();
		f.close();
	}
	
	public static int dfs(int u)
	{
		if(parents[u] != -1)
			return parents[u];
		int mask = 1 << u;
		for(int v : reverse[u])
			mask |= dfs(v);
		return parents[u] = mask;
	}
	
	public static int lg(int n)
	{
		return 31 - Integer.numberOfLeadingZeros(n);
	}
	
	public static void recurse(int mask, int length)
	{
		if(mask == OK)
		{
			if(length == size)
				out.println(new String(result));
			return;
		}
		int temp = OK & ~mask;
		while(temp > 0)
		{
			int p = temp & -temp;
			temp -= p;
			int index = lg(p);
			result[length] = indexes[index];
			recurse(mask | parents[index], length + 1);
		}
	}
	
	static class Frame
	{
		Point tl, br;
		public Frame(int r, int c)
		{
			tl = new Point(r, c);
			br = new Point(r, c);
		}
		
		public void update(int r, int c)
		{
			tl.x = Math.min(tl.x, r);
			tl.y = Math.min(tl.y, c);
			br.x = Math.max(br.x, r);
			br.y = Math.max(br.y, c);
		}
		
		public String toString()
		{
			return tl + " " + br;
		}
	}
}