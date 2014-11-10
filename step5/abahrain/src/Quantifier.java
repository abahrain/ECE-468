class Quantifier
{
  String content = "default";
  boolean isParameter = false;
  
  public Quantifier(String input)
  {
    this.content = input;
  }
  
  public Quantifier(boolean justify)
  {
    this.isParameter = justify;
  }
  
  public Quantifier() {}
}
