import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Micro 
{
  public static void main(String[] args) throws Exception
  {
      //ANTLR will read in the file
      ANTLRFileStream stream = new ANTLRFileStream(args[0]);
      Micro_scan lex = new Micro_scan(stream);
      Token t = lex.nextToken();
      
      while(t.getType() != Micro_scan.EOF)
      {
          System.out.println("Token Type: "+Micro_scan.tokenNames[t.getType()]);
          System.out.println("Value: "+t.getText());
	  t = lex.nextToken();
      }
  }
}