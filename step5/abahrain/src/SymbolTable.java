import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class SymbolTable
{
  protected Stack<Boresight> scopeStack;
  protected ArrayList<Boresight> allScopes;
  protected int generateName;
  
  public SymbolTable()
  {
    init();
  }
  
  private void init()
  {
    this.scopeStack = new Stack<>();
    this.allScopes = new ArrayList<>();
    this.generateName = 0;
    
    Boresight globals = new Boresight("GLOBAL", nextGenId(), null);
    this.scopeStack.push(globals);
    this.allScopes.add(globals);
  }
  
  public Boresight pushScope(String type)
  {
    Boresight enclosingScope = (Boresight)this.scopeStack.peek();
    Boresight scope = new Boresight(type, nextGenId(), enclosingScope);
    this.scopeStack.push(scope);
    this.allScopes.add(scope);
    return scope;
  }
  
  public void popScope()
  {
    this.scopeStack.pop();
  }
  
  public Boresight currentScope()
  {
    if (this.scopeStack.size() > 0) {
      return (Boresight)this.scopeStack.peek();
    }
    return (Boresight)this.allScopes.get(0);
  }
  
  public Boresight getScope(int generateName)
  {
    for (Boresight scope : this.scopeStack) {
      if (scope.generateName == generateName) {
        return scope;
      }
    }
    return null;
  }
  
  private int nextGenId()
  {
    this.generateName += 1;
    return this.generateName;
  }
  
  public List<String> checkDuplicate()
  {
    List<String> nameArray = new ArrayList<String>();
    List<String> outDuplicate = new ArrayList<String>();
    Set<String> array = new HashSet<String>();
    for (Boresight scope : this.scopeStack.subList(0, this.scopeStack.size())) {
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
    for (Boresight scope : this.scopeStack.subList(0, this.scopeStack.size())) {
      sb.append("Symbol table " + scope.type + "\n" + scope.toString() + "\n");
    }
    String temp = sb.toString().replace("[", "").replace("]", "");
    String temp2 = temp.replaceAll(", ", "");
    return temp2;
  }
}
