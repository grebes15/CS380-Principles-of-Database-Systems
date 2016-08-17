import java.util.*;
import java.io.*;
import org.xml.sax.*;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.*;
import org.xml.sax.helpers.DefaultHandler;

public class SAXEcho extends HandlerBase {

	static boolean noDebuggingOutput = false;

	public static void main (String argv [] ) {

		String filename = null;

		long startTime = System.nanoTime();

		for(int i = 0; i < argv.length; i++){

			if (argv[i].startsWith("-no")){
				noDebuggingOutput = true;
			}
			else if (i == argv.length - 1){
				filename = argv[i];

			} else if (i != argv.length -1){
				System.out.println("Usage: cmd filename");
			}
		}

		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {		
			InputStream xmlInput  = new FileInputStream(filename);
			SAXParser saxParser = factory.newSAXParser();

			if(!noDebuggingOutput){

				DefaultHandler handler   = new SaxHandler();
				saxParser.parse(xmlInput, handler);

			}

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



