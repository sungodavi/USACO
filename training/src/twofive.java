/*
ID: sungoda1
LANG: JAVA
TASK: twofive
 */

import java.util.*;
import java.io.*;
import java.awt.Point;

public class twofive
{
	static int[][][][][] dp;
	static int size;
	static int[] maxR, maxC;
	static boolean[] used;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("twofive.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("twofive.out")));
		char query = (char)f.read();
		f.readLine();
		size = 5;
		maxR = new int[size];
		maxC = new int[size];
		used = new boolean[25];
		Arrays.fill(maxR, -1);
		Arrays.fill(maxC, -1);
		if(query == 'N')
			out.println(n(Integer.parseInt(f.readLine())));
		else
			out.println(w(f.readLine()));
		out.close();
		System.exit(0);
	}
	
	public static int w(String s)
	{
		int result = 1;
		int[] input = new int[5];
		for(int i = 0; i < s.length(); i++)
		{
			input[i / size]++;
			int letter;
			int index = s.charAt(i) - 'A';
			for(letter = 0; letter < index; letter++)
			{
				if(!used[letter] && letter > maxR[i / size] && letter > maxC[i % size])
				{
					int t = calc(i / size, i % size, letter, input);
					result += t;
					used[letter] = false;
				}
			}
			used[index] = true;
			maxR[i / 5] = maxC[i % 5] = index;
		}
		return result;
	}
	
	public static String n(int count)
	{
		char[] result = new char[25];
		int[] input = new int[5];
		for(int i = 0; i < result.length; i++)
		{
			input[i / size]++;
			int letter;
			for(letter = 0; letter < result.length; letter++)
			{
				if(!used[letter] && letter > maxR[i / size] && letter > maxC[i % size])
				{
					int t = calc(i / size, i % size, letter, input);
					if(t < count)
						count -= t;
					else
						break;
					used[letter] = false;
				}
			}
			System.out.println(letter + " " + used[letter]);
			result[i] = (char)('A' + letter);
		}
		return new String(result);
	}
	
	public static int calc(int r, int c, int letter, int[] input)
	{
		dp = new int[6][6][6][6][6];
		dp[5][5][5][5][5] = 1;
		maxR[r] = maxC[c] = letter;
		used[letter] = true;
		int result = recurse(input[0], input[1], input[2], input[3], input[4], 0);
		return result;
	}
	public static int recurse(int a, int b, int c, int d, int e, int letter)
	{
		if(dp[a][b][c][d][e] > 0)
			return dp[a][b][c][d][e];
		if(used[letter])
			return dp[a][b][c][d][e] = recurse(a, b, c, d, e, letter + 1);
		int result = 0;
		if(a < size && letter > maxR[0] && letter > maxC[a])
			result += recurse(a + 1, b, c, d, e, letter + 1);
		if(b < a && letter > maxR[1] && letter > maxC[b])
			result += recurse(a, b + 1, c, d, e, letter + 1);
		if(c < b && letter > maxR[2] && letter > maxC[c])
			result += recurse(a, b, c + 1, d, e, letter + 1);
		if(d < c && letter > maxR[3] && letter > maxC[d])
			result += recurse(a, b, c, d + 1, e, letter + 1);
		if(e < d && letter > maxR[4] && letter > maxC[e])
			result += recurse(a, b, c, d, e + 1, letter + 1);
		return dp[a][b][c][d][e] = result;
	}
}