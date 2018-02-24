import java.util.*;
import java.io.*;

public class fortmoo {
	public static void main(String[] args) throws IOException {
//		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		BufferedReader f = new BufferedReader(new FileReader("fortmoo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fortmoo.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		char[][] a = new char[n][m];
		int[][] counts = new int[n][m];
		for(int r = 0; r < n; r++)
		{
			int count = 0;
			for(int c = 0; c < m; c++)
			{
				char in = (char)f.read();
				a[r][c] = in;
				if(in == '.')
					counts[r][c] = ++count;
				else
					counts[r][c] = count = 0;
			}
			f.readLine();
		}
		int result = 0;
		for(int size = 1; size <= m; size++)
		{
			for(int c = size - 1; c < m; c++)
			{
				int count = 0;
				for(int r = 0; r < n; r++)
				{
					if((count == 0 && counts[r][c] >= size) || (count != 0 && a[r][c] == '.' && a[r][c - size + 1] == '.'))
						count++;
					else
						count = 0;
					if(counts[r][c] >= size)
					{
						result = Math.max(result, count * size);
					}
				}
			}
		}
		out.println(result);
		out.close();
	}
}
