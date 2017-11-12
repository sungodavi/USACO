/*
ID: sungoda1
LANG: JAVA
TASK: ratios
 */

import java.util.*;
import java.io.*;


public class ratios 
{
	public static boolean isValid(int[] curr, int[] goal)
	{
		for(int i = 0; i < curr.length; i++)
			if(curr[i] > goal[i])
				return false;
		return true;
	}
	
	public static boolean check(int[] curr, int[] goal)
	{
		for(int i = 0; i < curr.length; i++)
			if(curr[i] != goal[i])
				return false;
		return true;
	}
	
	public static int[] backtrack(int[] curr, int[] amounts, int[] goal, int[][] feeds, int index)
	{
		if(!isValid(curr, goal))
			return null;
		if(check(curr, goal))
			return amounts;
		if(amounts[0] >= 100 || amounts[1] >= 100 || amounts[2] >= 100)
			return new int[0];
		for(int r = index; r < feeds.length; r++)
		{
			for(int k = 0; k < curr.length; k++)
				curr[k] += feeds[r][k];
			amounts[r]++;
			int[] result = backtrack(curr, amounts, goal, feeds, r);
			if(result != null)
				return result;
			for(int k = 0; k < curr.length; k++)
				curr[k] -= feeds[r][k];
			amounts[r]--;
		}
		return null;
	}
	
	public static void solve(int[] goal, int[][] feeds, PrintWriter out)
	{
		int[] curr = new int[3];
		int[] amount = new int[3];
		int[] temp = goal.clone();
		int[] result = backtrack(curr, amount, goal, feeds, 0);
		int count = 1;
		while(result == null)
		{
			for(int i = 0; i < goal.length; i++)
				goal[i] += temp[i];
			count++;
			result = backtrack(curr, amount, goal, feeds, 0);
		}
		if(result.length == 0)
			out.println("NONE");
		else
			out.printf("%d %d %d %d\n", amount[0], amount[1], amount[2], count);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ratios.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"ratios.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int[] goal = new int[3];
		for(int r = 0; r < 3; r++)
			goal[r] = Integer.parseInt(st.nextToken());
		
		int[][] feeds = new int[3][3];
		for(int r = 0; r < feeds.length; r++)
		{
			st = new StringTokenizer(f.readLine());
			for(int k = 0; k < feeds[0].length; k++)
				feeds[r][k] = Integer.parseInt(st.nextToken());
		}
		solve(goal, feeds, out);
		out.close();
	}
}