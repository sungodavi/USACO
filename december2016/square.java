/*
ID: sungoda1
LANG: JAVA
TASK: square
*/
import java.util.*;
import java.io.*;

class Pasture
{
	int x1, x2, y1, y2;
	public Pasture(int a, int b, int c, int d)
	{
		x1 = a;
		y1 =b;
		x2 = c;
		y2 = d;
	}
}
class square
{
	public static int max(int a, int b)
	{
		if(a > b)
			return a;
		return b;
	}
	public static int min(int a, int b)
	{
		if(a < b)
			return a;
		return b;
	}
	public static int calc(Pasture p1, Pasture p2)
	{
		int side1 = max(p1.x2, p2.x2) - min(p1.x1, p2.x1);
		int side2 = max(p1.y2, p2.y2) - min(p1.y1, p2.y1);
		int side = max(side1, side2);
		return side * side;
	}
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("square.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("square.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		Pasture p1 = new Pasture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		st = new StringTokenizer(f.readLine());
		Pasture p2 = new Pasture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		out.println(calc(p1, p2));
		out.close();
	}
}	
