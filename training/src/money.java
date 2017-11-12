/*
ID: sungoda1
LANG: JAVA
TASK: money
 */

import java.util.*;
import java.io.*;

public class money {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("money.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"money.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int[] coins = new int[Integer.parseInt(st.nextToken())];
		int index = 0;
		long[] a = new long[Integer.parseInt(st.nextToken()) + 1];
		String input = f.readLine();
		while(input != null)
		{
			for(String s: input.split(" "))
				coins[index++] = Integer.parseInt(s);
			input = f.readLine();
		}
		a[0] = 1;
		for(int coin : coins)
		{
			for(int k = coin; k < a.length; k++)
				a[k] += a[k-coin];
		}
		//System.out.println(Arrays.toString(a));
		out.println(a[a.length - 1]);
		out.close();
	}
}