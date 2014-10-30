class Quantifier
{
  String content = "default";
  boolean query = false;
  
  public Quantifier(String input)
  {
    this.content = input;
  }
  
  public Quantifier(boolean premise)
  {
    this.query = premise;
  }
  
  public Quantifier() {}
}