/*  1:   */ import java.io.IOException;
/*  2:   */ import java.io.PrintStream;
/*  3:   */ import org.antlr.v4.runtime.ANTLRFileStream;
/*  4:   */ import org.antlr.v4.runtime.CommonTokenStream;
/*  5:   */ import org.antlr.v4.runtime.TokenStream;
/*  6:   */ import org.antlr.v4.runtime.tree.ParseTree;
/*  7:   */ import org.antlr.v4.runtime.tree.ParseTreeWalker;
/*  8:   */ 
/*  9:   */ public class Micro
/* 10:   */ {
/* 11:   */   public static void main(String[] args)
/* 12:   */   {
/* 13:   */     try
/* 14:   */     {
/* 15:25 */       ANTLRFileStream reader = new ANTLRFileStream(args[0]);
/* 16:26 */       MicroLexer lexer = new MicroLexer(reader);
/* 17:27 */       TokenStream tokens = new CommonTokenStream(lexer);
/* 18:   */       
/* 19:   */ 
/* 20:   */ 
/* 21:   */ 
/* 22:   */ 
/* 23:   */ 
/* 24:   */ 
/* 25:   */ 
/* 26:   */ 
/* 27:   */ 
/* 28:   */ 
/* 29:   */ 
/* 30:   */ 
/* 31:   */ 
/* 32:   */ 
/* 33:   */ 
/* 34:   */ 
/* 35:   */ 
/* 36:   */ 
/* 37:   */ 
/* 38:   */ 
/* 39:   */ 
/* 40:   */ 
/* 41:   */ 
/* 42:   */ 
/* 43:   */ 
/* 44:   */ 
/* 45:   */ 
/* 46:   */ 
/* 47:   */ 
/* 48:   */ 
/* 49:   */ 
/* 50:   */ 
/* 51:   */ 
/* 52:   */ 
/* 53:   */ 
/* 54:   */ 
/* 55:   */ 
/* 56:   */ 
/* 57:   */ 
/* 58:   */ 
/* 59:   */ 
/* 60:70 */       MicroParser parser = new MicroParser(tokens);
/* 61:71 */       parser.setErrorHandler(new MyErrorStrategy());
/* 62:72 */       ParseTree tree = parser.program();
/* 63:   */       
/* 64:74 */       ParseTreeWalker walker = new ParseTreeWalker();
/* 65:75 */       ExtractMicroBaseListener extractor = new ExtractMicroBaseListener(parser);
/* 66:76 */       walker.walk(extractor, tree);
/* 67:77 */       EvalMicroBaseVisitor visitor = new EvalMicroBaseVisitor(extractor.table, extractor.functionMap);
/* 68:78 */       visitor.visit(tree);
/* 69:   */       
/* 70:   */ 
/* 71:   */ 
/* 72:   */ 
/* 73:   */ 
/* 74:   */ 
/* 75:   */ 
/* 76:   */ 
/* 77:   */ 
/* 78:   */ 
/* 79:   */ 
/* 80:   */ 
/* 81:91 */       IRToTiny translator = new IRToTiny(visitor.outputList, extractor.table);
/* 82:92 */       System.out.println(translator);
/* 83:   */     }
/* 84:   */     catch (IOException e)
/* 85:   */     {
/* 86:95 */       System.out.println("file not found");
/* 87:   */     }
/* 88:   */     catch (ArrayIndexOutOfBoundsException e)
/* 89:   */     {
/* 90:97 */       System.out.println("You didn't include the argument");
/* 91:   */     }
/* 92:   */     catch (IllegalArgumentException e)
/* 93:   */     {
/* 94:99 */       System.out.println("Not accepted");
/* 95:   */     }
/* 96:   */   }
/* 97:   */ }


/* Location:           C:\Users\Adam\Downloads\step4.jar
 * Qualified Name:     Micro
 * JD-Core Version:    0.7.0.1
 */