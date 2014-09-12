import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Micro 
{
  public static void main(String[] args) throws Exception
  {
      //ANTLR will read in the file and pass the string to the lexer
      ANTLRFileStream stream = new ANTLRFileStream(args[0]);
      Micro_scan lex = new Micro_scan(stream);
	  //The lexer piece is then set equal to a token
      Token t = lex.nextToken();
      
	  //Runs until the end of the file
	  //prints the token type and value to the screen dump
	  //gets next token in the set
      while(t.getType() != Micro_scan.EOF)
      {
          System.out.println("Token Type: "+Micro_scan.tokenNames[t.getType()]);
          System.out.println("Value: "+t.getText());
		  t = lex.nextToken();
      }
  }
}