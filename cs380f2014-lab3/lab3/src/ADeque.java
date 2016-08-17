import org.josql.*;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.JCommander;
import java.util.ArrayDeque;
import java.util.List;
import java.io.File;
import java.util.*;

public class ADeque {

	public static ArrayDeque<String> list = new ArrayDeque<String>(); // holds lines of algae

	public static void main(String[] args) {

	Arguments params = new Arguments();
        JCommander cmd = new JCommander(params, args);
        try {
            cmd.parse(args);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            cmd.usage();
        }
        
        // The debugging flag which will determine whether or not
        // we produce output
        boolean debugOutput = params.getOutput();

	// The pull flag will determine which data attritute to pull
	String pullOutput = params.getPull().toString();

		try{
			Scanner scan = new Scanner(new File("../data/algae.csv")); // scan algae

			/* Scan until there are no more lines, add them to an arrayList---list---
			 * and scan every line in list and search for lines that contain 'winter'
			 * and add them to winterList */
			while (scan.hasNextLine())
			{
				String next = scan.nextLine();
				list.add(next);
			}
			System.out.println("By hand:\n");
			findByHand(pullOutput);

			System.out.println("\nBy JoSQL:\n");			
			findByJo(pullOutput);

		} catch (java.io.FileNotFoundException FNF) {
			System.out.println("Couldn't Find File");
		}
	}

	public static void findByJo(String pullOutput)
	{

		try {

			// Create a new Query.
			Query q = new Query ();
			//String winter = "winter";
			String query = "SELECT * FROM java.lang.String WHERE toString LIKE '%" + pullOutput + "%'";

			long startTime = System.nanoTime();

			// Parse the SQL you are going to use.
			q.parse(query);

			// Execute the query.
			QueryResults qr = q.execute(list);

			// Cycle over the query results
			List<String> res =  qr.getResults();

			long endTime = System.nanoTime();
			long estimatedTime = endTime - startTime;

			for (String result : res)
			{
				System.out.println(result);
			}
				System.out.println("Time of findByJo() with data-structure ArrayDeque: " + estimatedTime + " nanoseconds");
			}

			catch(org.josql.QueryParseException e) {
				System.out.println("Error One");
			}

			catch(org.josql.QueryExecutionException e) {
				System.out.println("Error Two");
			}
		}

		/* Hand-coded version that finds occurences of winter in algae.csv
		 * Does it by scanning each line of algae adding them to an arrayList
		 * then in that arrayList searches each line for 'Winter,' then adds
		 * them to another arrayList */
		public static void findByHand(String pullOutput) {

			ArrayDeque<String> pullList = new ArrayDeque<String>(); // holds lines that contain the pull value

			long startTime = System.nanoTime();

			for (String str : list)
			{
				if (str.contains(pullOutput))
					pullList.add(str);
			}
			long endTime = System.nanoTime();
			long estimatedTime = endTime - startTime;
			System.out.println("Time of findByHand() with data-structure ArrayDeque: " + estimatedTime + " nanoseconds");

			for (String contains : pullList) 
			 { 
			 	System.out.println(contains); 
			 } 
		}
	}
