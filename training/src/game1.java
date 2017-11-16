/*
ID: sungoda1
LANG: JAVA
TASK: game1
*/
import java.util.*;
import java.io.*;

class game1 
{
	static int[][] costs;
	static int[] board;
	public static void build()
	{
		costs = new int[board.length][board.length];
		costs[costs.length - 1] = board;
		for(int r = costs.length - 2; r >= 0; r--)
		{
			for(int c = 0; c <= r; c++)
			{
				costs[r][c] = Integer.max(board[c + board.length - 1 - r] - costs[r + 1][c], board[c] - costs[r+1][c+1]);
			}
		}
	}
	public static int[] play()
	{
		int r = 0, c = 0;
		int left = 0;
		int right = costs.length - 1;
		int[] players = new int[2];
		int count = 0;
		int debug = 0;
		while(r < costs.length - 1)
		{
			if(board[left] - costs[r+1][c+1] == costs[r][c])
			{
				c++;
				//System.out.println(board[left]);
				players[count % 2] += board[left++];
				if(count % 2 == 0)
					debug += board[left - 1];
				else
					debug -= board[left - 1];
			}
			else
			{
				//System.out.println(board[right]);
				players[count % 2] += board[right--];
				
				if(count % 2 == 0)
					debug += board[right + 1];
				else
					debug -= board[right + 1];
			}
			//System.out.println("debug: " + debug);
			r++;
			count++;
			//System.out.println(Arrays.toString(players));
		}
		//System.out.println(costs[costs.length - 1][c]);
		//System.out.println(Arrays.toString(players));
		//System.out.println(board[left] + " " + board[right]);
		//System.out.println(debug);
		players[count % 2] += board[left];
		return players;
		
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("game1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("game1.out")));
		int size = Integer.parseInt(f.readLine());
		board = new int[size];
		StringTokenizer st = null;
		for(int i = 0; i < size; i++)
		{
			if(st == null || !st.hasMoreTokens())
				st = new StringTokenizer(f.readLine());
			board[i] = Integer.parseInt(st.nextToken());
		}
		build();
		
		int[] result = play();
		out.println(result[0] + " " + result[1]);
		out.close();
	}
}
