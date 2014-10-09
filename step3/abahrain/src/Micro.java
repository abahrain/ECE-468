import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;

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
      ANTLRErrorStrategy es = new BailErrorStrategy();
      parse.setErrorHandler(es);
	  
	  //The parser will either accept that the pattern sent in
	  //is of the correct format for a program. Or, if it is not
	  //the parser will throw an error that is then caught.
	  Boolean tester = true;
	  try
	  {
		ParseTree tree = parse.program();
	  }
	  catch (ParseCancellationException e) 
	  {
        tester = false;
        System.out.println("Not accepted");
      }
      if(tester)
      {
		parse.tree.printTree();
      }
  }
}