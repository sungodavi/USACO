import java.util.*;
import java.io.*;

public class guard 
{
	//static TreeMap<State, Integer> dp = new TreeMap<State, Integer>();
	static Cow[] a;
	static int target;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("guard.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("guard.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int size = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());
		a = new Cow[size];
		for(int i = 0; i < a.length; i++)
		{
			st = new StringTokenizer(f.readLine());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			a[i] = new Cow(h, w, s);
		}
		int result = solve();
		out.println(result >= 0 ? result : "Mark is too tall");
		out.close();
	}
	
	public static int solve()
	{
		int size = 1 << a.length;
		int[] weight = new int[size];
		int[] height = new int[size];
		int count = 0;
		for(int i = 1; i < size; i++)
		{
			int temp = i;
			int p = 0;
			while(temp > 0)
			{
				p = temp & -temp;
				temp -= p;
			}
			//System.out.println(p + " " + i);
			if(p == i)
			{
				weight[i] = a[count].w;
				height[i] = a[count].h;
				count++;
			}
			else
			{
				weight[i] = weight[p] + weight[i - p];
				height[i] = height[p] + height[i - p];
			}
		}
		
		int[] dp = new int[size];
		int best = 0;
		dp[0] = Integer.MAX_VALUE;
		for(int i = 1; i < size; i++)
		{
			int temp = i;
			int index = 0;
			int max = Integer.MIN_VALUE;
			while(temp > 0)
			{
				if((temp & 1) > 0)
				{
					int flag = 1 << index;
					int s = Math.min(dp[i - flag], a[index].s - weight[i - flag]);
					max = Math.max(max, s);
				}
				temp >>= 1;
				index++;
			}
			dp[i] = max;
			if(height[i] >= target)
				best = Math.max(best, max);
		}
//		System.out.println(Arrays.toString(weight));
//		System.out.println(Arrays.toString(height));
//		System.out.println(Arrays.toString(dp));
		return best;
	}
	
	
	static class Cow
	{
		int h, w, s;
		public Cow(int h, int w, int s)
		{
			this.h = h;
			this.w = w;
			this.s = s;
		}
	}
}