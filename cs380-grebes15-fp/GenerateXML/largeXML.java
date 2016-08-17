import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.*;
import java.io.*;
import java.math.BigInteger;

public class largeXML {


	public static void main(String [] argv){

		BigInteger l = BigInteger.ONE;

		try {        
			OutputStream fout= new FileOutputStream("large.xml");
			OutputStream bout= new BufferedOutputStream(fout);
			OutputStreamWriter out 
			= new OutputStreamWriter(bout, "8859_1");

			out.write("<?xml version=\"1.0\" ");
			out.write("encoding=\"ISO-8859-1\"?>\r\n");  
			out.write("<Greetings>\r\n");  
			for (int i = 1; i <= 12500000; i++) {

				int x = 0;

				out.write("  <This is large to be large hopefully\"" + x + "\">");
				out.write(l.toString());
				out.write("</Hopefully this is going to be large>\r\n");
				x++;
			}
			out.write("<Hopefully this is going to work>\r\n");

			out.flush();
			out.close();
		}
		catch(UnsupportedEncodingException e) {
			System.out.println(
				"This VM does not support the Latin-1 character set.");
		}
		catch (IOException e) {
			System.out.println(e.getMessage());        
		}
	}
}