/*
ID: sungoda1
LANG: JAVA
TASK: lgame
*/
import java.util.*;
import java.io.*;

class lgame
{
	static int[] vals = {2, 5, 4, 4, 1, 6, 5, 5, 1, 7, 6, 3, 5, 2, 3, 5, 7, 2, 1, 2, 4, 6, 6, 7, 5, 7};
	static HashSet<String> dict = new HashSet<String>();
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("lgame.in"));
		BufferedReader scan = new BufferedReader(new FileReader("lgame.dict"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lgame.out")));
		
		for(String input = scan.readLine(); !input.equals("."); input = scan.readLine())
			dict.add(input);
		scan.close();
		
		char[] a = f.readLine().toCharArray();
		TreeSet<Pair> pairs = new TreeSet<Pair>();
		int best = 0;
		Arrays.sort(a);
		
		int size = fact(a.length);
		for(int i = 0; i < size; i++, next(a))
		{
			String s = new String(a);
			for(int j = 3; j <= a.length; j++)
			{
				String s1 = s.substring(0, j);
				if(dict.contains(s1))
				{
					for(int k = j + 3; k <= a.length; k++)
					{
						String s2 = s.substring(j, k);
						if(dict.contains(s2))
						{
							int score = getScore(s1) + getScore(s2);
							if(score >= best)
							{
								if(score > best)
								{
									best = score;
									pairs = new TreeSet<Pair>();
								}
								pairs.add(new Pair(s1, s2));
							}
						}
					}
					int score = getScore(s1);
					if(score >= best)
					{
						if(score > best)
						{
							best = score;
							pairs = new TreeSet<Pair>();
						}
						pairs.add(new Pair(s1, ""));
					}
				}
			}
		}
		out.println(best);
		for(Pair p : pairs)
			out.println(p);
		out.close();
	}
	
	public static void next(char[] a)
	{
		int j, l;
		j = a.length - 2;
		while(j > 0 && a[j] >= a[j + 1])
			j--;
		l = a.length - 1;
		while(l > 0 && a[j] >= a[l])
			l--;
		swap(a, j, l);
		j++;
		l = a.length - 1;
		while(j < l)
			swap(a, j++, l--);
	}
	
	public static void swap(char[] a, int x, int y)
	{
		char temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	public static int fact(int n)
	{
		int result = 1;
		for(int i = 2; i <= n; i++)
			result *= i;
		return result;
	}
	
	public static int getScore(String s)
	{
		int score = 0;
		for(int i =0; i < s.length(); i++)
			score += vals[s.charAt(i) - 'a'];
		return score;
	}
	
	static class Pair implements Comparable<Pair>
	{
		String a, b;
		public Pair(String a, String b)
		{
			if(a.compareTo(b) > 0)
			{
				String temp = a;
				a = b;
				b = temp;
			}
			this.a = a;
			this.b = b;
		}
		
		public int hashCode()
		{
			return (a + b).hashCode();
		}
		
		public int compareTo(Pair p)
		{
			return (a + b).compareTo(p.a + p.b);
		}
		
		public String toString()
		{
			if(a.isEmpty())
				return b;
			return a + " " + b;
		}
	}
}
