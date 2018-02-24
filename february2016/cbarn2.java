import java.util.*;
import java.io.*;

public class cbarn2 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int size = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] a = new int[size];
		for(int i = 0; i < size; i++)
			a[i] = Integer.parseInt(f.readLine());
		
	}
	
	public static int countBits(int num)
	{
		int count =0;
		while(num > 0)
		{
			count++;
			num -= num & -num;
		}
		return count;
	}
}