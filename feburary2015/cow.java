/*
ID: sungoda1
LANG: JAVA
TASK: cow
*/
import java.util.*;
import java.io.*;

class cow {
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("cow.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cow.out")));
		String cow = "COW";
		f.readLine();
		String s = f.readLine();
		long[][] a = new long[3][s.length()];
		if(s.charAt(0) == 'C')
			a[0][0] = 1;
		for(int c = 1; c < s.length(); c++)
			a[0][c] = a[0][c-1] + (s.charAt(c) == 'C' ? 1 : 0);
		for(int r = 1; r < a.length; r++)
			for(int c = 1; c < s.length(); c++)
				a[r][c] = a[r][c-1] + (s.charAt(c) == cow.charAt(r) ? a[r - 1][c - 1] : 0);
		out.println(a[2][s.length() - 1]);
		out.close();
	}
}
