import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class Micro 
{
  public static void main(String[] args) throws Exception
  {
	try
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
		ParseTree tree = parse.program();
		ParseTreeWalker walking = new ParseTreeWalker();
		MyListener listen = new MyListener(parse);
		walking.walk(listen, tree);
		MyVisitor visiting = new MyVisitor(listen.stack,listen.function);
		visiting.visit(tree);
		ConvertIR convert = new ConvertIR(visiting.output,visiting.table);
		System.out.println(convert);
		//System.out.println("BOOM!");
	}
	catch (ParseCancellationException e) 
	{
		System.out.println(e);
    }
    /*if(tester)
    {
		parse.tree.printTree();
    }*/
  }
}