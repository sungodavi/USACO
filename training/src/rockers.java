/*
ID: sungoda1
LANG: JAVA
TASK: rockers
 */

import java.util.*;
import java.io.*;
import java.awt.Point;

public class rockers
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("rockers.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rockers.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numsongs = Integer.parseInt(st.nextToken());
		int cdlength = Integer.parseInt(st.nextToken());
		int numcds = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(f.readLine());
		int[] length = new int[25];
		for(int i = 1; i <= numsongs; i++)
			length[i] = Integer.parseInt(st.nextToken());
		
		int[][][] dp = new int[25][25][25];
		    int best = 0;
		    for (int a = 0; a < numcds; a++)/* current cd */
			for (int b = 0; b <= cdlength; b++)	/* number of minutes used */
			    for (int c = 0; c <= numsongs; c++) {	/* last song */
				for (int d = c + 1; d <= numsongs; d++) {	/* new song */
				    if (b + length[d] <= cdlength) {
					if (dp[a][b][c] + 1 > dp[a][b + length[d]][d])
					    dp[a][b + length[d]][d] = dp[a][b][c] + 1;
				    }
				    else {
					if (dp[a][b][c] + 1 > dp[a + 1][length[d]][d])
					    dp[a + 1][length[d]][d] = dp[a][b][c] + 1;
				    }
				}
				if (dp[a][b][c] > best)
				    best = dp[a][b][c];
			    }
		out.println(best);
		out.close();
	}
}