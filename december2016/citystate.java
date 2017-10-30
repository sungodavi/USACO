/*
ID: sungoda1
LANG: JAVA
TASK: citystate
*/
import java.util.*;
import java.io.*;


class citystate
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("citystate.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("citystate.out")));

		HashMap<String, HashSet<String>> cities = new HashMap<String, HashSet<String>>();
		HashMap<String, HashSet<String>> states = new HashMap<String, HashSet<String>>();

		int lines = Integer.parseInt(f.readLine());
		int count = 0;
		for(; lines > 0; lines--)
		{
			String[] temp = f.readLine().split(" ");
			String city = temp[0].substring(0,2);
			String state = temp[1];
			
			if(cities.containsKey(city))
				cities.get(city).add(state);
			else
			{
				HashSet<String> set = new HashSet<String>();
				set.add(state);
				cities.put(city, set);
			}

			if(states.containsKey(state))
			{
				states.get(state).add(city);
			}
			else
			{
				HashSet<String> set = new HashSet<String>();
				set.add(city);
				states.put(state, set);
			}
		}

		Set<String> keys = new HashSet<String>(cities.keySet());
		keys.retainAll(states.keySet());
		for(String key : keys)
		{
			cities.get(key).retainAll(states.get(key));
			count += cities.get(key).size();
		}
		out.println(count / 2);
		out.close();
	}
}	
