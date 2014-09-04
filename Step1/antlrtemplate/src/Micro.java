import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Micro 
{
  public static void main(String[] args)
  {
      //ANTLR will read in the file
      ANTLRFileStream stream = new ANTLRFileStream(args[0]);
      MicroLexer parser = new MicroLexer(stream);
      Token t = parser.nextToken();
      System.out.println(t);
      
      while(t.getType() != -1)
      {
          System.out.println("Token Type: "+t.getType());
          System.out.println("Value: "+t.getText());
      }
  }
}