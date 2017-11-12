/*
ID: sungoda1
LANG: JAVA
TASK: msquare
 */

import java.util.*;
import java.io.*;

public class msquare 
{
	static class State
	{
		String sequence;
		int square;
		public State(int square, String sequence)
		{
			this.sequence = sequence;
			this.square = square;
		}
	}
	public static final int x = 10000;
	public static int A(int num)
	{
		int top = num / x;
		int bottom = num % x;
		return bottom * x + top;
	}
	
	public static int B(int num)
	{
		int top = num / x;
		int bottom = num % x;
		
		top = top / 10 + top % 10 * 1000;
		bottom = bottom / 10 + bottom % 10  * 1000;
		return top * x + bottom;
	}
	public static int[] toArray(int num)
	{
		int[] a = new int[4];
		for(int i = a.length - 1; i >= 0; i--)
		{
			a[i] = num % 10;
			num /= 10;
		}
		return a;
	}
	public static int toInt(int[] a)
	{
		int result = 0;
		int exp = 1;
		for(int i = a.length - 1; i >= 0; i--)
		{
			result += a[i] * exp;
			exp *= 10;
		}
		return result;
	}
	public static int C(int num)
	{
		int[] top = toArray(num / x);
		int[] bottom = toArray(num % x);
		int temp = top[1];
		top[1] = bottom[1];
		bottom[1] = bottom[2];
		bottom[2] = top[2];
		top[2] = temp;
		return toInt(top) * x + toInt(bottom);
		
	}
	
	public static String backTrack(int target)
	{
		HashSet<Integer> used = new HashSet<Integer>();
		Queue<State> q = new LinkedList<State>();
		q.add(new State(12348765, ""));
		while(!q.isEmpty())
		{
			State s = q.poll();
			if(s.square == target)
				return s.sequence;
			if(used.contains(s.square))
				continue;
			used.add(s.square);
			q.add(new State(A(s.square), s.sequence + "A"));
			q.add(new State(B(s.square), s.sequence + "B"));
			q.add(new State(C(s.square), s.sequence + "C"));
		}
		return "None";
	}
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("msquare.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"msquare.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int top = 0;
		int bottom = 0;
		for(int i = 0; i < 4; i++)
			top = top * 10 + Integer.parseInt(st.nextToken());
		int exp = 1;
		for(int i = 0; i < 4; i++)
		{
			bottom += Integer.parseInt(st.nextToken()) * exp;
			exp *= 10;
		}
		String s = backTrack(top * x + bottom);
		out.println(s.length());
		if(s.length() == 0)
			out.println();
		else
			for(int i = 0; i < s.length(); i += 60)
			{
				out.println(s.substring(i, Integer.min(s.length(), i + 60)));
			}
		out.close();
			
	}
}