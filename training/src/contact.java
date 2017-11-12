/*
ID: sungoda1
LANG: JAVA
TASK: contact
 */

import java.util.*;
import java.io.*;

class Sub implements Comparable<Sub>
{
	String s;
	int count;
	public Sub(String s, int count)
	{
		this.s = s;
		this.count = count;
	}
	public int compareTo(Sub sub)
	{
		if(sub.count == count)
		{
			if(sub.s.length() != s.length())
				return s.length() - sub.s.length();
			return s.compareTo(sub.s);
		}
		return sub.count - count;
	}
}
public class contact 
{
	public static void display(String s, int a, int b, int n, PrintWriter out)
	{
		HashMap<String, Sub> set = new HashMap<String, Sub>();
		for(int size = a; size <= b; size++)
		{
			for(int k = size; k <= s.length(); k++)
			{
				String sub = s.substring(k - size, k);
				if(set.containsKey(sub))
				{
					set.get(sub).count++;
				}
				else
					set.put(sub, new Sub(sub, 1));
			}
		}
		ArrayList<Sub> list = new ArrayList<Sub>();
		for(String key : set.keySet())
			list.add(set.get(key));
		Collections.sort(list);
		int curr = -1;
		int count = 1;
		for(Sub sub : list)
		{
			if(sub.count != curr)
			{
				if(n == 0)
					break;
				if(curr != -1)
					out.println();
				curr = sub.count;
				out.println(curr);
				out.print(sub.s);
				n--;
				count = 1;
			}
			else
			{
				if(count % 6 == 0)
				{
					out.println();
					out.print(sub.s);
				}
				else
					out.print(" " + sub.s);
				count++;
			}
			
		}
		
	}
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("contact.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"contact.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int min = Integer.parseInt(st.nextToken());
		int max = Integer.parseInt(st.nextToken());
		
		int n = Integer.parseInt(st.nextToken());
		StringBuilder input = new StringBuilder();
		String s = f.readLine();
		while(s != null)
		{
			input.append(s);
			s = f.readLine();
		}
		display(input.toString(), min, max, n, out);
		out.println();
		out.close();
	}
}