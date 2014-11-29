import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CFGNode
{
  int type = 0;
  int index = 0;
  ArrayList<String> define = null;
  ArrayList<String> use = null;
  Set<String> livenessVar = new HashSet();
  
  public CFGNode(ArrayList<String> define, ArrayList<String> use, int type)
  {
    this.type = type;
    this.use = use;
    this.define = define;
  }
  
  public String liveness(CFGNode in)
  {
    return this.livenessVar.toString();
  }
  
  public String toString()
  {
    return "use: " + this.use + "\ndefine: " + this.define + "\nliveness: " + this.livenessVar;
  }
  
  public String testPre1(CFGNode in)
  {
    if (in != null) {
      return "true";
    }
    return "false";
  }
}