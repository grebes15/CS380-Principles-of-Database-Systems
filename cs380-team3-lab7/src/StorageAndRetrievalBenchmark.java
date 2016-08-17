import java.util.Iterator;

import com.thoughtworks.xstream.XStream;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.io.PrintStream;

import java.io.FileWriter;

import java.io.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;


public class StorageAndRetrievalBenchmark
{

    public static String preOrderPrint(Tree T, Position v) 
    {
        String s = v.element().toString(); // elements must implement toString
        Iterator children = T.children(v);
        while (children.hasNext())
            s += " " + preOrderPrint(T, (Position) children.next());
        return s;
    }

    
    public static String parentheticRepresentation(Tree T, Position v) 
    {
        String s = v.element().toString(); // elements must implement toString

        if (T.isInternal(v)) 
        {

            Iterator children = T.children(v);
            // open parenthesis and recursively process the first subtree
            s += " ( " + 
                parentheticRepresentation(T, (Position) children.next());

            while (children.hasNext())
                // recursively process the remaining subtrees
                s += ", " + 
                    parentheticRepresentation(T, 
                            (Position) children.next());
            s += " )"; // close parenthesis
        }

        return s;

    }

    public static void main(String[] args)
    {

        Tree randomTree = RandomTreeBuilder.randomTree(10);

        System.out.println("The random tree: \n\n" + 
                preOrderPrint(randomTree,
                    randomTree.getRoot()));

        System.out.println();

        System.out.println("Parenthetic representation: \n\n" + 
                parentheticRepresentation(randomTree,
                    randomTree.getRoot()));

		//Store the tree and time how long this takes.

		//Placing the parenthetic representation into a string.
        String parentheticTree = parentheticRepresentation(randomTree,randomTree.getRoot());
        
        XStream xstream = new XStream();
        
        //For timing the conversion and storing of the tree.
        long startTime = 0;
        long endTime = 0;
        long elapsedTime = 0;

        startTime = System.currentTimeMillis();

        //Converting the tree into XML text.
        String xml = xstream.toXML(randomTree);

        //Writing the XML string to a .xml file.
        try {

			//A file to write to and a writer to do the writing.
          	File file = new File("example.xml");
          	BufferedWriter output = new BufferedWriter(new FileWriter(file));

			//Write the XML tree to the file.
          	output.write(xml);

			//Get the execution time of the conversion and the storage.
			endTime = System.currentTimeMillis();
			elapsedTime = endTime - startTime; //Finding the amount of time taken by the_
											   //conversion and storage of the tree in XML.
			//Close the writer.
          	output.close();

        } catch ( IOException e ) {

           e.printStackTrace();

        }
   

		//Confirming a successful write.
		System.out.println("Done");
		System.out.println("Elapsed Time To Convert to and Store Tree in " +
				"XML: " + elapsedTime + "ms");

		//Start timing the storage of the tree using serialization.
		startTime = System.currentTimeMillis();
        
	    try {

			//Writing the object to a text file.
	     	FileOutputStream fileOut = new FileOutputStream("serializer.txt");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);

			//The tree is serialized and written to the text file.
	        out.writeObject(parentheticTree);

			//Get the execution time of this action.
	        endTime = System.currentTimeMillis();
	        elapsedTime = endTime - startTime;

			//Close the two output streams.
	        out.close();
	        fileOut.close();

			//Confirming a successful write and giving the execution time.
	        System.out.printf("Serialized data is saved in serializer.txt");
	        System.out.print("\n");
	        System.out.println("Elapsed Time to Write Tree to a Text File: " + elapsedTime + "ms");

		} catch(IOException i) {

	    	i.printStackTrace();

	    }


        //Retrieve the tree and time how long this takes.
        //
        //Retrieving the XML from the .xml file.
    	System.out.print("\n");

		//A string to rebuild the object and a string to check for the file's end.
    	String treeFromXML = new String();
    	String checkForEOF = new String();

		try {
			
			//Start the timing of the retrieval of the tree through XML.
			startTime = System.currentTimeMillis();

			
			//Opening a reader into the file.
			BufferedReader input = new BufferedReader(new FileReader("example.xml"));

			//Starting the check for the end of the file.
			checkForEOF = input.readLine();

			//Reading in all the lines of the XML file.
			while (checkForEOF != null) {

				treeFromXML = treeFromXML.concat(checkForEOF);
				checkForEOF = input.readLine(); //Checking for another line.

			}
			
			//Print out the conversion of the XML back into a Tree object.
			System.out.println((Tree)xstream.fromXML(treeFromXML));

			//Get the elapsed time.
			endTime = System.currentTimeMillis();
			elapsedTime = endTime - startTime;

			//Print out the timed result.
			System.out.println("Elapsed Time to Retrieve the Tree Object From the XML " +
					"File: " + elapsedTime + "ms");

			//Close the reader.
			input.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException i) {

			i.printStackTrace();

		}

		try {

			//Timing the execution time of the retrieval of a serialied_
			//tree from a text file.
			startTime = System.currentTimeMillis();

			//Setting up an input channel from the text file.
			FileInputStream fileIn = new FileInputStream("serializer.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);

			//Retrieve and display the object originally serialized.
			System.out.println(in.readObject());

			//Getting the elapsed time.
			endTime = System.currentTimeMillis();
			elapsedTime = endTime - startTime;

			//Printing the timed result.
			System.out.println("Elapsed Time to Retrieve the Serialized Tree From" +
					" a Text File: " + elapsedTime + "ms");

			//Closing the InputStreams.
			in.close();
			fileIn.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException i) {

			i.printStackTrace();
	
		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}

    }

}
