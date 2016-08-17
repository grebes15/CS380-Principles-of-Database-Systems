import org.josql.*;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.*;

public class FileFinder {
    public static void main(String[] args) {
        try {

            // construct a list and populate it with some files
            // in your home directory (I used the lab directory)
            List<File> fileList = new ArrayList<File>();
            //fillPath("/Users/ColtonMcCurdy/Computer_Science/cs210F2014/cs210F2014-mccurdyc/labs/lab1/tex", "", fileList);
	    fillPath("/home/b/ballingerj/cs290", "", fileList);

            // Create a new Query.
            Query q = new Query ();

            // Parse the SQL you are going to use.
            q.parse ("SELECT * FROM java.io.File WHERE name LIKE '%t%'");

            // Execute the query.
            QueryResults qr = q.execute(fileList);

            // Cycle over the query results
            List res = qr.getResults();
            System.out.println("Number of Results: " + res.size());
            System.out.println("Names of the Matching Files:");

            // print out all of the matching files
            for (int i = 0; i < res.size (); i++) {
                // print out the matching file
                File f = (File) res.get (i);
                System.out.println("\t" + f.getName());
            }
        }

        catch(org.josql.QueryParseException e) {
            System.out.println("Error");
        }

        catch(org.josql.QueryExecutionException e) {
            System.out.println("Error");
        }
    }

    public static void fillPath(String path, String name, List<File> current) {
        File f = new File(path);
        if (f.isFile()) {
            current.add(f);
        } 

        else if (f.isDirectory()) {
            if (!path.endsWith(File.separator)) {
                path += File.separator;
            }

            String[] list = f.list();
            // Process all files recursivly
            for (int i = 0; (list != null) && (i < list.length); i++) {
                fillPath(path + list[i], list[i], current);
            }
        }
    }
}
