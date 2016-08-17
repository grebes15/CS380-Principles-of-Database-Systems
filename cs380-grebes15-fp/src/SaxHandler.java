/*
This is the source code that will print out the debugging output if the command line argument -no is not called
*/

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

public class SaxHandler extends DefaultHandler {

	boolean noDebuggingOutput = false;

	public SaxHandler(Boolean b) {
		
	noDebuggingOutput = b;
}


    public void startDocument() throws SAXException {

        if(!noDebuggingOutput){
        System.out.println("start document   : ");
    }
    }

    public void endDocument() throws SAXException {
        if(!noDebuggingOutput){
        System.out.println("end document     : ");
    }
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(!noDebuggingOutput){
        System.out.println("start element    : " + qName);
    }
    }

    public void endElement(String uri, String localName, String qName)
    throws SAXException {
        if(!noDebuggingOutput){
        System.out.println("end element      : " + qName);
    }
    }

    public void characters(char ch[], int start, int length) throws SAXException {
        if(!noDebuggingOutput){
        System.out.println("start characters : " + new String(ch, start, length));
    }
    }

    public void ignorableWhitespace(char ch[], int start, int length) throws SAXException {
    }
}
