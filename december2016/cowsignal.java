/*
ID: sungoda1
LANG: JAVA
TASK: cowsignal
*/
import java.util.*;
import java.io.*;


class cowsignal
{
	public static void display(char[][] a, PrintWriter out)
	{
		for(char[] temp : a)
		{
			for(char c: temp)
			{
				out.print(c);
			}
			out.println();
		}
	}
	public static char[][] enlarge(char[][] start, int size)
	{
		char[][] a = new char[start.length * size][start[0].length * size];
		for(int r = 0; r < start.length; r++)
		{
			for(int i = 0; i < size; i++)
			{
				for(int c = 0; c < start[0].length; c++)
				{
					for(int j = 0; j < size; j++)
					{
						a[size * r + i][size * c + j] = start[r][c]; 
					}
				}
			}
		}
		return a;
	}
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("cowsignal.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowsignal.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		char[][] a = new char[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
		for(int r = 0; r < a.length; r++)
			a[r] = f.readLine().toCharArray();
		int size = Integer.parseInt(st.nextToken());
		display(enlarge(a, size), out);
		out.close();
	}
}	
