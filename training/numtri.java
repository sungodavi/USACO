/*
ID: sungoda1
LANG: JAVA
TASK: numtri
*/
import java.util.*;
import java.io.*;


class numtri
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("numtri.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int size = Integer.parseInt(st.nextToken());
		int[][] a = new int[size][size];
		for(int r = 0; r < a.length; r++)
		{
			st = new StringTokenizer(f.readLine());
			for(int c = 0; c <= r; c++)
				a[r][c] = Integer.parseInt(st.nextToken());
		}

		for(int r = a.length - 1; r > 0; r--)
		{
			for(int c = 0; c < r; c++)
			{
				if(a[r][c] > a[r][c + 1])
					a[r-1][c] += a[r][c];
				else
					a[r-1][c] += a[r][c+1];
			}
		}
		out.println(a[0][0]);
		out.close();	
	}
}	
