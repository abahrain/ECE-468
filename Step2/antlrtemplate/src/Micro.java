import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Micro 
{
  public static void main(String[] args) throws Exception
  {
      //ANTLR will read in the file
      ANTLRFileStream stream = new ANTLRFileStream(args[0]);
	  //Pass the information to the lexer
      MicroLexer lex = new MicroLexer(stream);
	  CommonTokenStream cts = new CommonTokenStream(lex);
	  //Pass the information to the parser
	  MicroParser parse = new MicroParser(cts);
	  parse.setErrorHandler(new BailErrorStrategy());
	  
	  //The parser will either accept that the pattern sent in
	  //is of the correct format for a program. Or, if it is not
	  //the parser will throw an error that is then caught.
	  try
	  {
		ParseTree tree = parse.program();
	  }
	  catch (ParseCancellationException e) 
	  {
        System.out.print("Not Accepted");
      }
	  System.out.print("Accepted");
  }
}