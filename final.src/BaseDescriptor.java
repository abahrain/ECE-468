class BaseDescriptor
{
  String content = "default";
  boolean isParameter = false;
  
  public BaseDescriptor(String input)
  {
    this.content = input;
  }
  
  public BaseDescriptor(boolean justify)
  {
    this.isParameter = justify;
  }
  
  public BaseDescriptor() {}
}
