import java.util.*;

public class SymbolTableClass
{
  class SymbolTableClass
  {
    String topName;
    SymbolTableClass top;
    ArrayList<SymbolTableClass> bottom;
    LinkedHashMap<String,Name> table;
    
    public SymbolTableClass(String topName)
    {
      this.topName = topName;
      top = null;
      bottom = new ArrayList<SymbolTableClass>();
      table = new LinkedHashMap<String, Name>();
    }
    
    public void showTable()
    {
      System.out.println("Symbol table " + topName);
      Set<String> keys = table.keySet();
      for(String key : keys)
      {
	System.out.println(table.get(key));
      }
    }
  }
  
  SymbolTableClass global;
  SymbolTableClass current;
  int counter = 1;
  
  public SymbolTableClass()
  {
    global = new SymbolTableClass("GLOBAL");
    current = global;
  }
  
  public void startScope(String topName)
  {
	SymbolTableClass start = new SymbolTableClass;
	start.top = current;
	start.top.bottom.add(start);
  }
  
  public void endScope()
  {
	current = current.top;
  }
  
  public void addallVar(ArrayList<String> names, String type)
  {
	for(String name : names)
	{
		addallVar(name, type);
	}
  }
  
  public void addsomeVar(String name, String type)
  {
	checkForShadowInParents(current.top, name);
	
	if(current.table.containsKey(name))
	{
		System.out.println("DECLARATION ERROR "+name);
		System.exit(0);
	}
	
	current.table.put(name, new Name(name,type));
  }
  
  private void checkForShadowInParents9SymbolTableClass node, String variable)
  {
	if(node == null)
	{
		return;
	}
	if(node.table.containsKey(variable))
	{
		System.out.println("SHADOW WARNING "+variable);
	}
	checkForShadowsInParents(node.top, var);
  }
  
  public void insertString(String name, String value)
  {
	current.table.put(name, new Name(name,"STRING",value);
  }
  
  public void printTree()
  {
	printTreeHelper(global);
  }
  
  private void printTreeHelper(SymbolTableClass state)
  {
	state.printTable();
	System.out.println();
	for(SymbolTableClass layer : state.bottom)
	{
		printTreeHelper(layer);
	}
  }