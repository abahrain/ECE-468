import java.util.*;

public class MakeSymbolTable
{

    class SymbolBlock
	{
        String topName;
        SymbolBlock top;
        ArrayList<SymbolBlock> bottom;
        LinkedHashMap<String, String> stack;

        public SymbolBlock (String topName)
		{
            this.topName = topName;
            top = null;
            bottom = new ArrayList<SymbolBlock>();
            stack = new LinkedHashMap<String, String>();
        }

        public void printSection()
		{
            System.out.println("Symbol table " + topName);
            Set<String> keys = stack.keySet();
            for (String key : keys)
			{
                System.out.println(stack.get(key));
            }
        }
    }

    SymbolBlock global = null;
    SymbolBlock current = null;
    int number_blocks = 1;

    public MakeSymbolTable ()
	{
        global = new SymbolBlock("GLOBAL");
        current = global;
    }

    public void openScope()
	{
        openScope("BLOCK " + number_blocks);
        number_blocks += 1;
    }

    public void openScope(String topName)
	{
        SymbolBlock state = new SymbolBlock(topName);
        state.top = current;
        current = state;
        state.top.bottom.add(state);
    }

    public void closeScope()
	{
        current = current.top;
    }

    public void addListOfVariables(ArrayList<String> names, String type)
	{
        for (String name : names)
		{
            addSingleVariable(name, type);
        }
    }

    public void addSingleVariable(String name, String type)
	{

        if (current.stack.containsKey(name))
		{
            System.out.println("DECLARATION ERROR " + name);
            System.exit(0);
        }

        current.stack.put(name, new String("name " + name + " type " + type));
		//System.out.println(current.stack.get(name));
    }
	
    public void insertString(String name, String value)
	{
        current.stack.put(name, new String("name " + name + " type STRING value " + value));
		//System.out.println(current.table.get(name));
    }


    public void printTree()
	{
        printTreeHelper(global);
    }

    private void printTreeHelper(SymbolBlock state)
	{
        state.printSection();
        System.out.println();
        for (SymbolBlock next : state.bottom)
		{
            printTreeHelper(next);
        }
    }
}