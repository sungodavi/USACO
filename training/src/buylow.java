/*
ID: sungoda1
LANG: JAVA
TASK: buylow
*/
import java.util.*;
import java.io.*;

class buylow 
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("buylow.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("buylow.out")));
		int size = Integer.parseInt(f.readLine());
		StringTokenizer st = new StringTokenizer(f.readLine());
		int[] a = new int[size];
		for(int i =0; i < size; i++)
		{
			if(!st.hasMoreTokens())
				st = new StringTokenizer(f.readLine());
			a[i] = Integer.parseInt(st.nextToken());
		}
		LinkedList<Integer> indices = new LinkedList<Integer>();
		indices.add(0);
		int[] dp = new int[size];
		int[] prev = new int[size];
		dp[0] = 1;
		int length = 1;
		for(int i = 1; i < size; i++)
		{
			int best = -1;
			for(int j = 0; j < i; j++)
			{
				if(a[j] > a[i] && (best < 0 || dp[j] > dp[best]))
					best = j;
			}
			dp[i] = best < 0 ? 1 : dp[best] + 1;
			prev[i] = best;
			if(dp[i] > length)
			{
				indices = new LinkedList<Integer>();
				indices.add(i);
				length = dp[i];
			}
			else if(best == length)
			{
				indices.add(i);
			}
		}
		for(int i = 0; i < a.length; i++)
		{
			System.out.println(Arrays.toString(recreate(a, prev, i, dp[i])));
		}
	}
	
	static int[] recreate(int[] a, int[] prev, int i, int size)
	{
		int[] result = new int[size];
		for(int k = size - 1; k >= 0; k--)
		{
			result[k] = a[i];
			i = prev[i];
		}
		return result;
	}
}
