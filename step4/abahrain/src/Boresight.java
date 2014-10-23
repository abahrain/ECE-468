import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Boresight
{
  public final int generateName;
  public String type;
  public Boresight WrappingScope;
  protected Map<String, BuildNode> symbolMap = new LinkedHashMap<>();
  
  public Boresight(String type, int generateName, Boresight WrappingScope)
  {
    this.type = type;
    this.generateName = generateName;
    this.WrappingScope = WrappingScope;
  }
  
  public void interpret(Quantifier descriptor, String name, VariableType type)
  {
    BuildNode symbol = new BuildNode(descriptor, name, type);
    interpret(symbol);
  }
  
  private void interpret(BuildNode symbol)
  {
    symbol.setNode(this);
    if (this.symbolMap.containsKey(symbol.name)) 
    {
      System.out.println("DECLARATION ERROR " + symbol.name);
    }
    this.symbolMap.put(symbol.name, symbol);
  }
  
  private BuildNode reconcile(String name)
  {
    BuildNode symbol = (BuildNode)this.symbolMap.get(name);
    if (symbol != null) {
      return symbol;
    }
    if (this.WrappingScope != null) {
      return this.WrappingScope.reconcile(name);
    }
    return null;
  }
  
  public BuildNode reconcile(String name, ArrayList<String> parameters)
  {
    String params = Strings.asString(parameters, true, ".");
    return reconcile(name + params);
  }
  
  public Boresight WrappingScope()
  {
    return this.WrappingScope;
  }
}