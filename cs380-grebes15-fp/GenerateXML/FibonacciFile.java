/*
This file wil output the xml files that is 1 gb large
*/

//import statements
import java.math.BigInteger;
import java.io.*;


public class FibonacciFile {

  public static void main(String[] args) {
   
      BigInteger low  = BigInteger.ONE;
      BigInteger high = BigInteger.ONE;      
      
      try {        
        OutputStream fout= new FileOutputStream("fibonacci.xml"); //output the xml file of fibonacci numbers
        OutputStream bout= new BufferedOutputStream(fout); 
        OutputStreamWriter out 
         = new OutputStreamWriter(bout, "8859_1");
      
        out.write("<?xml version=\"1.0\" "); //print out the output stream writer
        out.write("encoding=\"ISO-8859-1\"?>\r\n");  
        out.write("<Fibonacci_Numbers>\r\n");  
        for (int i = 1; i <= 100000; i++) { //print out the first 100,000 fibonacci numbers 
          out.write("  <fibonacci index=\"" + i + "\">"); //print out the output stream writer
          out.write(low.toString());
          out.write("</fibonacci>\r\n");
          BigInteger temp = high; //calculations to calculate the fibonacci numbers
          high = high.add(low);
          low = temp;
        }
        out.write("</Fibonacci_Numbers>\r\n"); 
        
        out.flush();  // Don't forget to flush!
        out.close();
      }
      catch (UnsupportedEncodingException e) {
        System.out.println(
         "This VM does not support the Latin-1 character set."
        );
      }
      catch (IOException e) {
        System.out.println(e.getMessage());        
      }

  }

}