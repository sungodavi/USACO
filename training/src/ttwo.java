/*
ID: sungoda1
LANG: JAVA
TASK: ttwo
 */

import java.util.*;
import java.io.*;
class Point
{
	int r;
	int c;
	int direction;
	public Point(int a, int b, int x)
	{
		r = a;
		c = b;
		direction = x;
	}
	
	public boolean equals(Point p)
	{
		return r == p.r && c == p.c;
	}
}
class TTwoHelper
{
	char[][] a;
	Point farmer;
	Point cow;
	public TTwoHelper(char[][] a)
	{
		this.a = a;
		locate();
	}
	
	public void locate()
	{
		for(int r = 0; r < a.length; r++)
		{
			for(int c = 0; c < a.length; c++)
			{
				if(a[r][c] == 'F')
					farmer = new Point(r, c, 0);
				else if(a[r][c] == 'C')
					cow = new Point(r, c, 0);
			}
		}
	}
	public boolean isValid(int r, int c)
	{
		return r >= 0 && r < a.length && c >= 0 && c < a[0].length && a[r][c] != '*';
	}
	public void move(Point p)
	{
		int newR = p.r;
		int newC = p.c;
		
		switch(p.direction)
		{
		case 0: newR--; break;
		case 1: newC++; break;
		case 2: newR++; break;
		case 3: newC--; break;
		}
		if(isValid(newR, newC))
		{
			p.r = newR;
			p.c = newC;
		}
		else
			p.direction = (p.direction + 1) % 4;
		
	}
	
	public void move()
	{
		move(farmer);
		move(cow);
	}
	
	public int solve()
	{
		int count = 0;
		while(!farmer.equals(cow) && count <= 400)
		{
			move();
			count++;
		}
		if(farmer.equals(cow))
			return count;
		return 0;
	}
}
public class ttwo 
{
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ttwo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"ttwo.out")));
		char[][] a = new char[10][];
		for(int r = 0; r < a.length; r++)
			a[r] = f.readLine().toCharArray();
		TTwoHelper t = new TTwoHelper(a);
		out.println(t.solve());
		out.close();

	}
}