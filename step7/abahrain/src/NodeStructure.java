public class NodeStructure
{
  public String content;
  public int type;
  public String value;
  
  public NodeStructure(String newContent, int newType)
  {
    this.content = newContent;
    this.type = newType;
  }
  
  public String toString()
  {
    return this.content;
  }
  
  public void addValue(String value)
  {
    this.value = value;
  }
}
