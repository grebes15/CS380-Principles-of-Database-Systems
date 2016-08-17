import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.*;
import java.io.*;
import org.xml.sax.*;


public class DOMEcho2 {



	static Boolean noDebuggingOutput = false;

	public static void main (String [] argv){

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



		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {



			builder = builderFactory.newDocumentBuilder();

			Document document = builder.parse(new FileInputStream(filename));

			Element rootElement = document.getDocumentElement();


			NodeList nodes = rootElement.getChildNodes();

			for(int i=0; i<nodes.getLength(); i++){
				Node node = nodes.item(i);

				if(node instanceof Element){
    //a child element to process
					Element child = (Element) node;
					String attribute = child.getAttribute("width");
				}
			}

			String attrValue = rootElement.getAttribute("attrName");

			Attr attribute = rootElement.getAttributeNode("attrName");


			long estimatedTime = System.nanoTime() - startTime;
            //stop timing how long it takes after the parsing is over

			double estimatedTime2 = ((System.nanoTime() - startTime) / 1000000000.0);
            //convert the estimatedTime to a double to displayed in seconds
			System.out.println("Time in nano seconds : " + estimatedTime);
            //display how long it took to parse in nano seconds
			System.out.printf("Time in seconds is %.9f", estimatedTime2);
            //display how long it took to parse in seconds

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		catch (ParserConfigurationException e) {
			e.printStackTrace();  
		}
	}
}




