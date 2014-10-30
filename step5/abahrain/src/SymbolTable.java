import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class SymbolTable
{
  protected Stack<Boresight> scopeBlocks;
  protected ArrayList<Boresight> scopesTower;
  protected int generateName;
  
  public SymbolTable()
  {
    init();
  }
  
  private void init()
  {
    this.scopeBlocks = new Stack<>();
    this.scopesTower = new ArrayList<>();
    this.generateName = 0;
    
    Boresight globals = new Boresight("GLOBAL", nextGenId(), null);
    this.scopeBlocks.push(globals);
    this.scopesTower.add(globals);
  }
  
  public Boresight pushScope(String type)
  {
    Boresight enclosingScope = (Boresight)this.scopeBlocks.peek();
    Boresight scope = new Boresight(type, nextGenId(), enclosingScope);
    this.scopeBlocks.push(scope);
    this.scopesTower.add(scope);
    return scope;
  }
  
  public void popScope()
  {
    this.scopeBlocks.pop();
  }
  
  public Boresight workingScope()
  {
    if (this.scopeBlocks.size() > 0) {
      return (Boresight)this.scopeBlocks.peek();
    }
    return (Boresight)this.scopesTower.get(0);
  }
  
  public Boresight getScope(int generateName)
  {
    for (Boresight scope : this.scopeBlocks) {
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
    List<String> nameArray = new ArrayList<>();
    List<String> outDuplicate = new ArrayList<>();
    Set<String> array = new HashSet<>();
    for (Boresight scope : this.scopeBlocks.subList(0, this.scopeBlocks.size())) 
    {
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
    for (Boresight scope : this.scopeBlocks.subList(0, this.scopeBlocks.size())) {
      sb.append("Symbol table " + scope.type + "\n" + scope.toString() + "\n");
    }
    String temp = sb.toString().replace("[", "").replace("]", "");
    String temp2 = temp.replaceAll(", ", "");
    return temp2;
  }
}