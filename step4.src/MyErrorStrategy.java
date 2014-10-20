/*  1:   */ import org.antlr.v4.runtime.DefaultErrorStrategy;
/*  2:   */ import org.antlr.v4.runtime.InputMismatchException;
/*  3:   */ import org.antlr.v4.runtime.NoViableAltException;
/*  4:   */ import org.antlr.v4.runtime.Parser;
/*  5:   */ import org.antlr.v4.runtime.RecognitionException;
/*  6:   */ import org.antlr.v4.runtime.Token;
/*  7:   */ 
/*  8:   */ public class MyErrorStrategy
/*  9:   */   extends DefaultErrorStrategy
/* 10:   */ {
/* 11:   */   public void recover(Parser recognizer, RecognitionException e)
/* 12:   */   {
/* 13:22 */     throw new IllegalArgumentException(e);
/* 14:   */   }
/* 15:   */   
/* 16:   */   public void reportInputMismatch(Parser parser, InputMismatchException e)
/* 17:   */   {
/* 18:27 */     throw new IllegalArgumentException(e);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public Token recoverInline(Parser recognizer)
/* 22:   */   {
/* 23:33 */     throw new IllegalArgumentException();
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void reportNoViableAlternative(Parser parser, NoViableAltException e)
/* 27:   */   {
/* 28:38 */     throw new IllegalArgumentException(e);
/* 29:   */   }
/* 30:   */ }


/* Location:           C:\Users\Adam\Downloads\step4.jar
 * Qualified Name:     MyErrorStrategy
 * JD-Core Version:    0.7.0.1
 */