import java.util.*;
import java.io.*;

public class div7
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("div7.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));
//		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int[] a = new int[Integer.parseInt(f.readLine())];
		for(int i = 0; i < a.length; i++)
			a[i] = Integer.parseInt(f.readLine());
		int[] sums = new int[a.length + 1];
		for(int i = 1; i <= a.length; i++)
			sums[i] = (sums[i - 1] + a[i - 1]) % 7;
		//System.out.println(Arrays.toString(sums));
		int[] indexes = new int[7];
		Arrays.fill(indexes, -1);
		int max = 0;
		for(int i = 1; i <= a.length; i++)
		{
			if(indexes[sums[i]] != -1)
				max = Math.max(max, i - indexes[sums[i]]);
			else
				indexes[sums[i]] = i;
			//System.out.println(Arrays.toString(indexes) + " " + max);
		}
		out.println(max);
		out.close();
	}
}