import java.util.*;
import java.io.*;

public class angry
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("angry.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int size = Integer.parseInt(st.nextToken());
		int cows = Integer.parseInt(st.nextToken());
		int[] a = new int[size];
		for(int i = 0; i < a.length; i++)
			a[i] = Integer.parseInt(f.readLine());
		Arrays.sort(a);
		for(int i = 0; i <= 25; i++)
			System.out.println(i + " " + check(a, i));
		out.println(solve(a, cows));
		out.close();
	}
	
	public static int solve(int[] a, int cows)
	{
		int low = 0;
		int high = a[a.length - 1] / 2;
		while(low <= high)
		{
			int mid = low + high >> 1;
			int c = check(a, mid);
			if(c <= cows && (mid == 0 || check(a, mid - 1) > cows))
				return mid;
			if(c > cows)
				low = mid + 1;
			else
				high = mid - 1;
		}
		return -1;
	}
	
	public static int check(int[] a, int r)
	{
		if(r == 0)
			return a.length;
		int count = 0;
		for(int i = 0; i < a.length;)
		{
			count++;
			int end = a[i] + 2 * r;
			while(i < a.length && a[i] <= end)
				i++;
		}
		return count;
	}
}