/*
ID: sungoda1
LANG: JAVA
TASK: zerosum
 */

import java.util.*;
import java.io.*;

public class zerosum 
{
	public static int eval(char[] a)
	{
		int sum = a[0] == ' ' ? 0 : 1;
		int num = a.length + 1;
		int index = a.length - 1;
		while(index >= 0)
		{
			if(a[index] == '+')
				sum += num--;
			else if(a[index] == '-')
				sum -= num--;
			else
			{
				int temp = num--;
				int exp = 10;
				while(index >= 0 && a[index] == ' ')
				{
					temp += exp * num--;
					exp *= 10;
					index--;
				}
				if(index >= 0 && a[index] == '-')
					sum -= temp;
				else
					sum += temp;
			}
			index--;
		}
		return sum;
	}
	
	public static String make(char[] a)
	{
		int num = 1;
		String s = "";
		for(int i = 0; i < a.length; i++)
			s += num++ + "" + a[i];
		return s + (a.length + 1);
	}
	
	public static void solve(int num, int index, char[] current, PrintWriter out)
	{
		if(index == num - 1)
		{
			if(eval(current) == 0)
			{
				out.println(make(current));
			}
		}
		else
		{
			char[] operators = {' ', '+', '-'};
			for(char c: operators)
			{
				current[index] = c;
				solve(num, index + 1, current, out);
			}
			current[index] = 0;
		}
	}
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("zerosum.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"zerosum.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int num = Integer.parseInt(st.nextToken());
		solve(num, 0, new char[num - 1], out);
		out.close();		
	}
}