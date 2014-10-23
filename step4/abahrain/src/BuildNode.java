public class BuildNode
{
	protected Boresight scope;
	protected Quantifier descriptor;
	protected String name;
	protected VariableType type;

	public BuildNode(Quantifier descriptor, String name, VariableType type)
	{
		this.descriptor = descriptor;
		this.name = name;
		this.type = type;
	}

	public Quantifier getDescriptor()
	{
		return this.descriptor;
	}

	public String getName()
	{
			return this.name;
	}

	public VariableType getType()
	{
		return this.type;
	}

	public void setNode(Boresight scope)
	{
		this.scope = scope;
	}
  
	public Boresight getNode()
	{
		return this.scope;
	}

	public int genId()
	{
		return this.scope.generateName;
	}

	public String toString()
	{
		if (this.type != null)
		{
			if (this.type == VariableType.STRING)
			{
				return "name " + getName() + " type " + this.type + " value " + this.descriptor.content + "\n";
			}
			
		return "name " + getName() + " type " + this.type + "\n";
		
		}
		
		return getName();
		
		}
	}