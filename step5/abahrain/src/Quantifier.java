class Quantifier
{
  String content = "default";
  boolean query = false;
  
  public Quantifier(String input)
  {
    this.content = input;
  }
  
  public Quantifier(boolean justify)
  {
    this.query = justify;
  }
  
  public Quantifier() {}
}
