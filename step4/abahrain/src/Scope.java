import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Scope
{
  public final int genId;
  public String type;
  public Scope enclosingScope;
  protected Map<String, Symbol> symbolMap = new LinkedHashMap();
  
  public Scope(String type, int genId, Scope enclosingScope)
  {
    this.type = type;
    this.genId = genId;
    this.enclosingScope = enclosingScope;
  }
  
  public void define(String name, ArrayList<String> parameters)
  {
    String params = Strings.asString(parameters, true, ".");
    Symbol symbol = new Symbol(null, name + params, null);
    define(symbol);
  }
  
  public void define(BaseDescriptor descriptor, String name, ValueType type)
  {
    Symbol symbol = new Symbol(descriptor, name, type);
    define(symbol);
  }
  
  private void define(Symbol symbol)
  {
    symbol.setScope(this);
    if (this.symbolMap.containsKey(symbol.name)) {
      throw new IllegalArgumentException("DECLARATION ERROR " + symbol.name);
    }
    this.symbolMap.put(symbol.name, symbol);
  }
  
  private Symbol resolve(String name)
  {
    Symbol symbol = (Symbol)this.symbolMap.get(name);
    if (symbol != null) {
      return symbol;
    }
    if (this.enclosingScope != null) {
      return this.enclosingScope.resolve(name);
    }
    return null;
  }
  
  public Symbol resolve(String name, ArrayList<String> parameters)
  {
    String params = Strings.asString(parameters, true, ".");
    return resolve(name + params);
  }
  
  public Scope enclosingScope()
  {
    return this.enclosingScope;
  }
  
  public String toString()
  {
    return this.symbolMap.values().toString();
  }
}