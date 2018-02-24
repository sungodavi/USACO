import java.util.*;
import java.io.*;

class bphoto {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("bphoto.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bphoto.out")));
//		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		
		int size = Integer.parseInt(f.readLine());
		Cow[] a = new Cow[size];
		for(int i = 0; i < size; i++)
		{
			a[i] = new Cow(Integer.parseInt(f.readLine()), i);
		}
		Arrays.sort(a);
		BIT tree = new BIT(size);
		int seen = 0;
		int count = 0;
		for(Cow c : a)
		{
			int left = tree.get(c.index);
			int right = seen - left;
			if(Math.max(left, right) > (Math.min(left, right) << 1))
				count++;
			tree.update(c.index);
			seen++;
		}
		out.println(count);
		out.close();
	}
	
	static class BIT
	{
		int[] a;
		public BIT(int size)
		{
			a = new int[size + 1];
		}
		
		public void update(int index)
		{
			++index;
			while(index < a.length)
			{
				++a[index];
				index += index & -index;
			}
		}
		
		public int get(int index)
		{
			int sum = 0;
			for(; index > 0; index -= (index & -index))
				sum += a[index];
			return sum;
		}
	}
	
	static class Cow implements Comparable<Cow>
	{
		int h, index;
		public Cow(int h, int index)
		{
			this.h = h;
			this.index = index;
		}
		
		public int compareTo(Cow c)
		{
			return c.h - h;
		}
	}
}