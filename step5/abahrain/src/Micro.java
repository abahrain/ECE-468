import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
/*import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.antlr.v4.runtime.tree.gui.TreeViewer;
*/
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
		//System.out.println(tree.toStringTree(parse));
		MyVisitor visited = new MyVisitor(listen.table, listen.functionMap);
		visited.visit(tree);
		System.out.print(new TinyDancer(visited.outputList, listen.table));
		//System.out.println("BOOM!");
		/*JFrame frame = new JFrame("Antlr AST");
        JPanel panel = new JPanel();
        TreeViewer viewr = new TreeViewer(Arrays.asList(
                parse.getRuleNames()),tree);
        viewr.setScale(.73);//scale a little
        panel.add(viewr);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200,200);
        frame.setVisible(true);*/
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