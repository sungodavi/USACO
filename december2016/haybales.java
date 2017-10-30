/*
ID: sungoda1
LANG: JAVA
TASK: haybales
*/
import java.util.*;
import java.io.*;


class haybales
{
	public static int search(int[] a, int num, boolean front)
	{
		int low = 0;
		int high = a.length - 1;
		while(low <= high)
		{
			int middle = (low + high) / 2;
			if(a[middle] == num)
				return middle;
			if(a[middle] < num)
				low = middle + 1;
			else
				high = middle - 1;
		}
		if(front)
			return low >= a.length ? -1 : low;
		return high;
	}


	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("haybales.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int[] a = new int[Integer.parseInt(st.nextToken())];
		int times = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(f.readLine());
		for(int i = 0; i < a.length; i++)
			a[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(a);

		for(; times > 0; times--)
		{
			st = new StringTokenizer(f.readLine());
			int start = search(a, Integer.parseInt(st.nextToken()), true);
			int end = search(a, Integer.parseInt(st.nextToken()), false);
			if(start < 0 || end < 0)
				out.println(0);
			else
				out.println(end - start + 1);
		}
		out.close();
	}
}	
