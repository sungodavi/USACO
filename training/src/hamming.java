/*
ID: sungoda1
LANG: JAVA
TASK: hamming
*/
import java.util.*;
import java.io.*;


class hamming
{
	public static int hamming(int a, int b)
	{
		int count = 0;
		while(a > 0 || b > 0)
		{
			count += (a & 1) ^ (b & 1);
			a >>= 1;
			b >>= 1;
		}
		return count;
	}
	
	public static void display(int[] a, PrintWriter out)
	{
		for(int i =0 ; i < a.length; i++)
		{
			if(i > 0 && i % 10 == 0)
				out.println();
			out.print(a[i] + (i == a.length - 1 || i % 10 == 9 ? "" : " "));
		}
		out.println();
	}

	public static int[] find(int size, int limit, int distance)
	{
		int[] a = new int[size];
		int index = 1;
		outer:
		for(int i = 1; i < Math.pow(2, limit); i++)
		{
			if(index == a.length)
				break;
			for(int j = 0; j < index; j++)
				if(hamming(i, a[j]) < distance)
					continue outer;
			a[index++] = i;
		}
		return a;
	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("hamming.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int[] a = find(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		display(a, out);
		out.close();
	}
}	
