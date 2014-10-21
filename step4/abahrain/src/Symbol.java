public class Symbol
{
	protected Scope scope;
	protected BaseDescriptor descriptor;
	protected String name;
	protected ValueType type;

	public Symbol(BaseDescriptor descriptor, String name, ValueType type)
	{
		this.descriptor = descriptor;
		this.name = name;
		this.type = type;
	}

	public BaseDescriptor getDescriptor()
	{
		return this.descriptor;
	}

	public String getName()
	{
			return this.name;
	}

	public ValueType getType()
	{
		return this.type;
	}

	public void setScope(Scope scope)
	{
		this.scope = scope;
	}
  
	public Scope getScope()
	{
		return this.scope;
	}

	public int genId()
	{
		return this.scope.genId;
	}

	public String toString()
	{
		if (this.type != null)
		{
			if (this.type == ValueType.STRING)
			{
				return "name " + getName() + " type " + this.type + " value " + this.descriptor.content + "\n";
			}
			
		return "name " + getName() + " type " + this.type + "\n";
		
		}
		
		return getName();
		
		}
	}