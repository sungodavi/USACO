/*
ID: sungoda1
LANG: JAVA
TASK: buylow
*/
import java.util.*;
import java.io.*;
import java.math.BigInteger;

class buylow 
{
	static int[] a;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("buylow.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("buylow.out")));
//		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;
		int size = Integer.parseInt(f.readLine());
		a = new int[size];
		st = new StringTokenizer(f.readLine());
		for(int i = 0; i < size; i++)
		{
			if(!st.hasMoreTokens())
				st = new StringTokenizer(f.readLine());
			a[i] = Integer.parseInt(st.nextToken());
		}
		int[] len = new int[size];
		BigInteger[] counts = new BigInteger[size];
		len[0] = 1;
		int best = 1;
		for(int i = 1; i < size; i++)
		{
			int curr = 1;
			for(int j = 0; j < i; j++)
				if(a[j] > a[i])
					curr = Math.max(curr, len[j] + 1);
			len[i] = curr;
			if(best < len[i])
				best = len[i];
		}
		counts[0] = BigInteger.ONE;
		for(int i = 1; i < size; i++)
		{
			BigInteger curr = BigInteger.ZERO;
			HashSet<Integer> visited = new HashSet<Integer>();
			for(int j = i - 1; j >= 0; j--)
			{
				if(a[j] > a[i] && len[j] == len[i] - 1 && visited.add(a[j]))
					curr = curr.add(counts[j]);
			}
			if(curr.equals(BigInteger.ZERO))
				counts[i] = BigInteger.ONE;
			else
				counts[i] = curr;
		}
//		System.out.println(Arrays.toString(len));
//		System.out.println(Arrays.toString(counts));
		BigInteger result = BigInteger.ZERO;
		HashSet<Integer> visited = new HashSet<Integer>();
		for(int i = size - 1; i >= 0; i--)
			if(len[i] == best && visited.add(a[i]))
				result = result.add(counts[i]);
		
		out.printf("%d %s\n", best, result);
		out.close();
	}
}
