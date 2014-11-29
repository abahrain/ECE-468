 import java.io.IOException;
 import java.io.PrintStream;
 import org.antlr.v4.runtime.ANTLRFileStream;
 import org.antlr.v4.runtime.CommonTokenStream;
 import org.antlr.v4.runtime.TokenStream;
 import org.antlr.v4.runtime.tree.ParseTree;
 import org.antlr.v4.runtime.tree.ParseTreeWalker;
 
 public class Micro
 {
   public static void main(String[] args)
   {
     try
     {
       ANTLRFileStream reader = new ANTLRFileStream(args[0]);
       MicroLexer lexer = new MicroLexer(reader);
       TokenStream tokens = new CommonTokenStream(lexer);
       MicroParser parser = new MicroParser(tokens);
       parser.setErrorHandler(new MyErrorStrategy());
       ParseTree tree = parser.program();
       
       ParseTreeWalker walker = new ParseTreeWalker();
       ExtractMicroBaseListener extractor = new ExtractMicroBaseListener(parser);
       walker.walk(extractor, tree);
       EvalMicroBaseVisitor visitor = new EvalMicroBaseVisitor(extractor.table, extractor.functionMap);
       visitor.visit(tree);
       Liveness CFG = new Liveness(visitor.outputList, visitor.tableMap, extractor.table);
       
       IRToTiny translator = new IRToTiny(visitor.outputList, extractor.table, visitor.tableMap, visitor.tempMap, CFG.CFG, 4, visitor.finalTempIndex);
       System.out.println(translator);
     }
     catch (IOException e)
     {
       System.out.println("file not found");
     }
     catch (ArrayIndexOutOfBoundsException e)
     {
       System.out.println("You didn't include the argument");
     }
     catch (IllegalMonitorStateException e)
     {
       System.out.println("Not accepted");
     }
     catch (IllegalArgumentException e)
     {
       System.out.println(e.getMessage());
     }
   }
 }