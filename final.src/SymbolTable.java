import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class SymbolTable
{
  protected Stack<Scope> scopeStack;
  protected ArrayList<Scope> allScopes;
  protected int genId;
  
  public SymbolTable()
  {
    init();
  }
  
  private void init()
  {
    this.scopeStack = new Stack();
    this.allScopes = new ArrayList();
    this.genId = 0;
    
    Scope globals = new Scope("GLOBAL", nextGenId(), null);
    this.scopeStack.push(globals);
    this.allScopes.add(globals);
  }
  
  public Scope pushScope(String type)
  {
    Scope enclosingScope = (Scope)this.scopeStack.peek();
    Scope scope = new Scope(type, nextGenId(), enclosingScope);
    this.scopeStack.push(scope);
    this.allScopes.add(scope);
    return scope;
  }
  
  public void popScope()
  {
    this.scopeStack.pop();
  }
  
  public Scope currentScope()
  {
    if (this.scopeStack.size() > 0) {
      return (Scope)this.scopeStack.peek();
    }
    return (Scope)this.allScopes.get(0);
  }
  
  public Scope getScope(int genId)
  {
    for (Scope scope : this.scopeStack) {
      if (scope.genId == genId) {
        return scope;
      }
    }
    return null;
  }
  
  private int nextGenId()
  {
    this.genId += 1;
    return this.genId;
  }
  
  public List<String> checkDuplicate()
  {
    List<String> nameArray = new ArrayList();
    List<String> outDuplicate = new ArrayList();
    Set<String> array = new HashSet();
    for (Scope scope : this.scopeStack.subList(0, this.scopeStack.size())) {
      nameArray.addAll(scope.symbolMap.keySet());
    }
    for (String k : nameArray) {
      if (!array.add(k)) {
        outDuplicate.add(k);
      }
    }
    return outDuplicate;
  }
  
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    for (Scope scope : this.scopeStack.subList(0, this.scopeStack.size())) {
      sb.append("Symbol table " + scope.type + "\n" + scope.toString() + "\n");
    }
    String temp = sb.toString().replace("[", "").replace("]", "");
    String temp2 = temp.replaceAll(", ", "");
    return temp2;
  }
}