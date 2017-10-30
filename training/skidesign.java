/*
ID: sungoda1
LANG: JAVA
TASK: skidesign
*/
import java.util.*;
import java.io.*;

class skidesign
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("skidesign.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int[] a = new int[Integer.parseInt(st.nextToken())];
		for(int i = 0; i < a.length; i++)
		{
			st = new StringTokenizer(f.readLine());
			a[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(a);
		int cost = Integer.MAX_VALUE;
		for(int i = 17; i <= a[a.length - 1]; i++)
		{
			int temp = 0;	
			for(int num : a)
			{
				if(num > i)
				{
					temp += (num - i) * (num - i);
				}
				else if(num < i - 17)
					temp += (i - 17 - num) * (i - 17 - num);
			}
			if(temp < cost)
				cost = temp;
		}
		out.println(cost);
		out.close();
	}
}	
