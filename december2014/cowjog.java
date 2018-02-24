import java.util.*;
import java.io.*;

class cowjog {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowjog.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowjog.out")));
//		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int cows = Integer.parseInt(st.nextToken());
		ArrayList<Long> list = new ArrayList<Long>(cows);
		int t = Integer.parseInt(st.nextToken());
		
		while(cows-- > 0)
		{
			st = new StringTokenizer(f.readLine());
			long b = Integer.parseInt(st.nextToken());
			long m = Integer.parseInt(st.nextToken());
			long val = m * t + b;
			int index = binarySearch(list, val);
			if(index < 0)
				list.add(val);
			else
				list.set(index, val);
		}
		out.println(list.size());
		out.close();
	}
	
	public static int binarySearch(ArrayList<Long> list, long val)
	{
		int low = 0;
		int high = list.size() - 1;
		while(low <= high)
		{
			int mid = low + high >> 1;
			if((mid == 0 && list.get(mid) < val) || (list.get(mid) < val && list.get(mid - 1) >= val))
				return mid;
			if(list.get(mid) >= val)
				low = mid + 1;
			else
				high = mid - 1;
		}
		return -1;
	}
}