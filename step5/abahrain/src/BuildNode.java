public class BuildNode
{
	protected Boresight scope;
	protected Quantifier quantifying;
	protected String name;
	protected VariableType type;

	public BuildNode(Quantifier quantifying, String name, VariableType type)
	{
		this.quantifying = quantifying;
		this.name = name;
		this.type = type;
	}

	public Quantifier getQuantified()
	{
		return this.quantifying;
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

	public int generateName()
	{
		return this.scope.generateName;
	}
}