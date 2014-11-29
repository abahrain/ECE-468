import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;

public class MyErrorStrategy
  extends DefaultErrorStrategy
{
  public void recover(Parser recognizer, RecognitionException e)
  {
    throw new IllegalMonitorStateException();
  }
  
  public void reportInputMismatch(Parser parser, InputMismatchException e)
  {
    throw new IllegalMonitorStateException();
  }
  
  public Token recoverInline(Parser recognizer)
  {
    throw new IllegalMonitorStateException();
  }
  
  public void reportNoViableAlternative(Parser parser, NoViableAltException e)
  {
    throw new IllegalMonitorStateException();
  }
}
