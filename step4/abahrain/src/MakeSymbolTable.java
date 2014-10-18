import java.util.*;

public class MakeSymbolTable
{

    class SymbolBlock
	{
        String topName;
        String scopeType;
        SymbolBlock top;
        ArrayList<SymbolBlock> bottom;
        LinkedHashMap<String, Name> stack;
		int node_index = 0;

        public SymbolBlock (String topName)
		{
            this.topName = topName;
            top = null;
            bottom = new ArrayList<SymbolBlock>();
            stack = new LinkedHashMap<String, Name>();
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
	
	class Name
	{
		String name;
		String type;
		String value;
		int size;
		int offset;

		public Name (String name, String type) 
		{
			this.name = name;
			this.type = type;
			this.value = null;
		}

		public Name (String name, String type, String value) 
		{
			this.name = name;
			this.type = type;
			this.value = value;
		}

		private int sizeof(String type)
		{
			if (type.equals("INT")) 
			{
				return 8;
			}
			else if (type.equals("FLOAT")) 
			{
				return 32;
			}
			else if (type.equals("STRING"))
			{
				return 8;
			}
			else 
			{
				return -1;
			}
		}
		
		public boolean isString() 
		{
			return type.equals("STRING");
		}
		
		@Override
		public String toString() 
		{
			if (isString()) 
			{
				return new String("name " + name + " type " + type + " value " + value); 
			} 
			else 
			{
				return new String("name " + name + " type " + type); 
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
	
	public Name lookup(String variable)
	{
		return lacky(current,variable);
	}
	
	public Name lacky(SymbolBlock block, String variable)
	{
		if(block.stack.containsKey(variable))
		{
			return block.stack.get(variable);
		}
		else
		{
			return lacky(block.top, variable);
		}
	}
    public void openScope()
	{
        openScope("BLOCK " + number_blocks);
        current.scopeType = "block";
        number_blocks += 1;
    }

    public void openScope(String topName)
	{
        SymbolBlock state = new SymbolBlock(topName);
        state.scopeType = "function";
        state.top = current;
        current = state;
        state.top.bottom.add(state);
    }

    public void closeScope()
	{
        current = current.top;
    }

    public void enterScope()
    {
     current = current.bottom.get(current.node_index);
     current.node_index++;
    }
    
    public void exitScope()
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

        current.stack.put(name, new Name(name,type));
		//System.out.println(current.stack.get(name));
    }
	
    public void insertString(String name, String value)
	{
        current.stack.put(name, new Name(name, "STRING", value));
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