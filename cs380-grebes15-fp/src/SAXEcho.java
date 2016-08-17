/*
This source code will parse the XML using the SAX parsing method
*/

//import statements
import java.util.*;
import java.io.*;
import org.xml.sax.*;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.*;
import org.xml.sax.helpers.DefaultHandler;

public class SAXEcho extends HandlerBase {

	static boolean noDebuggingOutput = false; //property to make sureno debugging output is created.


	public static void main (String argv [] ) {

		String filename = null; //file name of the xml file as a commnand line argument

		long startTime = System.nanoTime(); //start to calculate the time to parse the xml file

		for(int i = 0; i < argv.length; i++){

			if (argv[i].startsWith("-no")){
				noDebuggingOutput = true; 
				//if the command line argument -no is given, then no debugging output will be displayed
			}
			else if (i == argv.length - 1){ 
				filename = argv[i];
				//checking if the filename is been written in as a command line argument 
			} else if (i != argv.length -1){
				System.out.println("Usage: cmd filename");
				//if the commnand line argument has not been filled in correctly, then print out the statement to explain
				//what the usage should look like
			}
		}

		SAXParserFactory factory = SAXParserFactory.newInstance(); //create a new instance of the SAX Parser Factory
		try {		
			InputStream xmlInput  = new FileInputStream(filename); 
			//the input stream is going to be xml file. This will be in the command line argument
			SAXParser saxParser = factory.newSAXParser();


				DefaultHandler handler = new SaxHandler(noDebuggingOutput);
				//calling the default handler

				saxParser.parse(xmlInput, handler);

				//parse the xml file with the input and the handler

		} catch (Throwable err) {
			err.printStackTrace ();
		}



		long estimatedTime = System.nanoTime() - startTime;
            //stop timing how long it takes after the parsing is over

		double estimatedTime2 = ((System.nanoTime() - startTime) / 1000000000.0);
            //convert the estimatedTime to a double to displayed in seconds
		System.out.println("Time in nano seconds : " + estimatedTime);
            //display how long it took to parse in nano seconds
		System.out.printf("Time in seconds is %.9f", estimatedTime2);
            //display how long it took to parse in seconds

	}
}



