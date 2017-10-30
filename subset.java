/*
ID: sungoda1
LANG: JAVA
TASK: subset
*/
import java.util.*;
import java.io.*;


class subset
{
	public static long count(int num)
	{
		if(num * (num + 1) % 4 != 0)
			return 0;
		int size = (num * (num + 1) / 4);
		long[] a = new long[size + 1];
		a[0] = 1;
		for(int coin = 1; coin <= num; coin++)
		{
			for(int k = size; k >= coin; k--)
			{
				a[k] += a[k - coin];
			}
		}
		return a[size] / 2;
	}
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("subset.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		out.println(count(Integer.parseInt(st.nextToken())));
		out.close();
	}
}	
