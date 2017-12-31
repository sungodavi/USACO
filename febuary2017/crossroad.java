/*
ID: sungoda1
LANG: JAVA
TASK: crossroad
 */

import java.util.*;
import java.io.*;

public class crossroad {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("crossroad.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"crossroad.out")));
		int[] a = new int[11];
		Arrays.fill(a, -1);
		int times = Integer.parseInt(f.readLine());
		long count = 0;
		while(times-- > 0)
		{
			StringTokenizer st = new StringTokenizer(f.readLine());
			int index = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			if(a[index] >= 0)
				count += val ^ a[index];
			a[index] = val;
		}
		out.println(count);
		out.close();
	}
}