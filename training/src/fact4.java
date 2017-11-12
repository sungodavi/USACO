/*
ID: sungoda1
LANG: JAVA
TASK: fact4
 */

import java.util.*;
import java.io.*;

public class fact4 
{
	public static int count(int num, int factor)
	{
		int count = 0;
		for(int i = factor; i <= num; i *= factor)
		{
			count += num / i;
		}
		return count;
	}
	
	public static ArrayList<Integer> sieve(int num)
	{
		boolean[] a = new boolean[num + 1];
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 2; i * i <= num; i++)
		{
			if(!a[i])
			{
				for(int j = 2 * i; j <= num; j += i)
					a[j] = true;
			}
		}
		for(int i = 2; i < a.length; i++)
			if(!a[i])
				list.add(i);
		return list;
	}
	public static int modPow(int b, int e)
	{
		//System.out.println(b + " " + e);
		return (int)Math.pow(b % 10, (e % 4) + 4);
	}
	public static int solve(int num)
	{
		ArrayList<Integer> primes = sieve(num);
		//System.out.println(primes);
		int result = 1;
		for(int prime : primes)
		{
			//System.out.println(prime + " " + count(num, prime));
			if(prime == 2)
			{
				//System.out.println("modPow 2: " + modPow(2, count(num, 2) - count(num, 5)));
				result = (result * modPow(2, count(num, 2) - count(num, 5))) % 10;
			}
			else if(prime != 5)
			{
				//System.out.println("modPow " + prime + ": " + modPow(prime, count(num, prime)));
				result = (result * modPow(prime, count(num, prime))) % 10;
			}
		}
		return result;
	}
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("fact4.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"fact4.out")));
		out.println(solve(Integer.parseInt(f.readLine())));
		//for(int i = 1; i <= 100; i++)
			//System.out.println(i + " " + solve(i));
		//System.out.println(solve(12));
		out.close();		
	}
}