import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Boresight
{
  public final int generateName;
  public String type;
  public Boresight enclosingScope;
  protected Map<String, BuildNode> symbolMap = new LinkedHashMap<String, BuildNode>();
  
  public Boresight(String type, int generateName, Boresight enclosingScope)
  {
    this.type = type;
    this.generateName = generateName;
    this.enclosingScope = enclosingScope;
  }
  
  public void define(String name, ArrayList<String> parameters)
  {
    String params = Strings.asString(parameters, true, ".");
    BuildNode symbol = new BuildNode(null, name + params, null);
    define(symbol);
  }
  
  public void define(Quantifier descriptor, String name, VariableType type)
  {
    BuildNode symbol = new BuildNode(descriptor, name, type);
    define(symbol);
  }
  
  private void define(BuildNode symbol)
  {
    symbol.setScope(this);
    if (this.symbolMap.containsKey(symbol.name)) {
      throw new IllegalArgumentException("DECLARATION ERROR " + symbol.name);
    }
    this.symbolMap.put(symbol.name, symbol);
  }
  
  private BuildNode resolve(String name)
  {
    BuildNode symbol = (BuildNode)this.symbolMap.get(name);
    if (symbol != null) {
      return symbol;
    }
    if (this.enclosingScope != null) {
      return this.enclosingScope.resolve(name);
    }
    return null;
  }
  
  public BuildNode resolve(String name, ArrayList<String> parameters)
  {
    String params = Strings.asString(parameters, true, ".");
    return resolve(name + params);
  }
  
  public Boresight enclosingScope()
  {
    return this.enclosingScope;
  }
  
  public String toString()
  {
    return this.symbolMap.values().toString();
  }
}
