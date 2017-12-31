/*
ID: sungoda1
LANG: JAVA
TASK: hps
 */

import java.util.*;
import java.io.*;
import java.awt.Point;

public class hps 
{
	static Point[] a;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("hps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"hps.out")));
		StringTokenizer st;
		int size = Integer.parseInt(f.readLine());
		a = new Point[size];
		for(int i =0; i < size; i++)
		{
			st = new StringTokenizer(f.readLine());
			a[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		int best = 0;
		best = Math.max(best, run(new int[]{1, 2, 3}));
		best = Math.max(best, run(new int[]{1, 3, 2}));
		best = Math.max(best, run(new int[]{2, 1, 3}));
		best = Math.max(best, run(new int[]{2, 3, 1}));
		best = Math.max(best, run(new int[]{3, 1, 2}));
		best = Math.max(best, run(new int[]{3, 2, 1}));
		out.println(best);
		out.close();
	}
	
	public static int run(int[] game)
	{
		int count = 0;
		for(Point p : a)
		{
			int x = p.x;
			int y = p.y;
			if(x != y)
			{
				for(int i = 0; i < a.length; i++)
				{
					if(game[i] == x)
					{
						if(game[(i + 1) % game.length] == y)
							count++;
						break;
					}
				}
			}
		}
		return count;
	}
}