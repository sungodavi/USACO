/*
ID: sungoda1
LANG: JAVA
TASK: range
*/
import java.util.*;
import java.io.*;

class range 
{
	public static int min(int a, int b, int c)
	{
		return Integer.min(Integer.min(a, b), c);
	}
	public static int[] count(int[][] a)
	{
		int[][] squares = new int[a.length][a.length];
		int[] counts = new int[251];
		for(int i = 0; i < a.length; i++)
		{
			squares[i][0] = a[i][0];
			squares[0][i] = a[0][i];
		}
		
		for(int r = 1; r < a.length; r++)
		{
			for(int c = 1; c < a.length; c++)
			{
				if(a[r][c] == 1)
				{
					int num = min(squares[r-1][c], squares[r][c-1], squares[r-1][c-1]) + 1;
					squares[r][c] = num;
					for(int i = 2; i <= num; i++)
					{
						counts[i]++;
					}
				}
			}
		}
		
		return counts;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("range.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("range.out")));
		int size = Integer.parseInt(f.readLine());
		int[][] a = new int[size][size];
		for(int r = 0; r < a.length; r++)
		{
			String s = f.readLine();
			for(int c = 0; c < a.length; c++)
			{
				a[r][c] = s.charAt(c) - '0';
			}
		}
		
		
		int[] counts = count(a);
		int index = 2;
		while(index < counts.length && counts[index] > 0)
		{
			out.println(index + " " + counts[index++]);
		}
		out.close();
	}
}
