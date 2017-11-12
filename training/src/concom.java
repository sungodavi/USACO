/*
ID: sungoda1
LANG: JAVA
TASK: concom
 */

import java.util.*;
import java.io.*;

class Node implements Comparable<Node>
{
	int p, index;
	public Node(int index, int p)
	{
		this.p = p;
		this.index = index;
	}
	
	public int compareTo(Node n)
	{
		return index - n.index;
	}
	public String toString()
	{
		return index + ": " + p;
	}
}

class Companies
{
	int[][] a;
	int min; int max;
	PrintWriter out;
	public Companies(int[][] a, int min, int max, PrintWriter out)
	{
		this.a = a;
		this.max = max;
		this.min = min;
		this.out = out;
	}
	
	public void solve()
	{
		boolean v[][] = new boolean[101][101];
        for (int i = min; i <= max; i++){
            for (int j = min; j <= max; j++) {
                if (i != j && !v[i][j] && a[i][j] > 50) {//if i controls j && hasn't already visited
                    v[i][j] = true;
                    for (int k = 1; k <= max; k++) {
                        a[i][k] += a[j][k];
                        if(v[j][k]) v[i][k] = true;//if j owns k, then i owns k
                    }
                    j=0;
                }
            }
        }
		for(int r = min; r <= max; r++)
		{
			for(int c = min; c <= max; c++)
			{
				if(r != c && v[r][c])
					out.println(r + " " + c);
			}
		}
	}
	
}
public class concom 
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("concom.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"concom.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		
		int[][] a = new int[101][101];
		int min = 100;
		int max = 1;
		int times = Integer.parseInt(st.nextToken());
		for(; times > 0; times--)
		{
			st = new StringTokenizer(f.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			
			a[row][col] = p;
			min = Integer.min(min, Integer.min(row, col));
			max = Integer.max(max, Integer.max(row, col));
		}
		
		//System.out.println(map);
		new Companies(a, min, max, out).solve();
		System.out.println(a[1][30]);
		out.close();
		

	}
}