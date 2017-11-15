/*
ID: sungoda1
LANG: JAVA
TASK: spin
 */

import java.util.*;
import java.io.*;
import java.awt.Point;

public class spin 
{
	static class Wheel implements Comparable<Wheel>
	{
		int speed;
		Point[] wedges;
		public Wheel(int speed, int[] data)
		{
			this.speed = speed;
			wedges = new Point[data.length / 2];
			for(int i = 0; i < data.length - 1; i += 2)
			{
				int start = data[i];
				int end = start + data[i + 1];
				end %= 360;
				if(end < start)
					end += 360;
				wedges[i / 2] = new Point(start, end);
			}
		}
		
		public void update()
		{
			for(Point p : wedges)
			{
				p.x = (p.x + speed) % 360;
				p.y = (p.y + speed) % 360;
				if(p.y < p.x)
					p.y += 360;
			}
		}
		
		public int compareTo(Wheel w)
		{
			return speed - w.speed;
		}
		
		public String toString()
		{
			return speed + " " + Arrays.toString(wedges);
		}

	}
	
	public static boolean backtrack(Wheel[] wheels, int index, int start, int end)
	{
		if(start > end)
			return false;
		if(index == wheels.length)
			return true;
		Wheel w = wheels[index++];
		for(Point wedge : w.wedges)
		{
			int newStart, newEnd;
			if(wedge.y < start && end >= 360)
			{
				newEnd = Integer.min(end - 360, wedge.y);
				newStart = wedge.x;
			}
			else if(end < wedge.x && wedge.y > 360)
			{
				newEnd = Integer.min(wedge.y - 360, end);
				newStart = start;
			}
			else
			{
				newStart = Integer.max(start, wedge.x);
				newEnd = Integer.min(end, wedge.y);
			}
			if(backtrack(wheels, index, newStart, newEnd))
				return true;
		}
		return false;
	}
	
	public static int solve(Wheel[] wheels)
	{
		for(int i = 0; i <= 2000; i++)
		{
			if(backtrack(wheels, 0 , 0, 720))
				return i;
				
			for(Wheel w : wheels)
				w.update();
		}
		return -1;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("spin.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"spin.out")));
		
		Wheel[] w = new Wheel[5];
		for(int k = 0; k < w.length; k++)
		{
			StringTokenizer st = new StringTokenizer(f.readLine());
			int speed = Integer.parseInt(st.nextToken());
			int[] data = new int[Integer.parseInt(st.nextToken()) * 2];
			for(int i = 0; i < data.length; i++)
				data[i] = Integer.parseInt(st.nextToken());
			//System.out.println(Arrays.toString(data));
			w[k] = new Wheel(speed, data);
		}
		//System.out.println(Arrays.toString(w));
		int result = solve(w);
		if(result < 0)
			out.println("none");
		else
			out.println(result);
		out.close();

	}
}