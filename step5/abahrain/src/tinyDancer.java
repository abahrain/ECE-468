import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class tinyDancer
{
  ArrayList<String> Intermediate_Representation;
  SymbolTable information_Table;
  public int temporaryIndex = -1;
  Map<String, String> Tony_Danza = new LinkedHashMap<>();
  
  public tinyDancer(ArrayList<String> input, SymbolTable information_Table)
  {
    this.Intermediate_Representation = input;
    this.information_Table = information_Table;
  }
  
  public String toString()
  {
    System.out.println(";IR code");
    for (int i = 0; i < this.Intermediate_Representation.size(); i++)
    {
      System.out.println(";" + this.Intermediate_Representation.get(i).toString());
    }
    System.out.println(";tiny code");
    

    String[] tinyParts = this.information_Table.toString().split(" ");
    for (int i = 0; i < tinyParts.length; i++)
    {
      if (tinyParts[i].contains("name"))
      {
        if (tinyParts[(i + 3)].contains("STRING"))
        {
          System.out.println("str " + tinyParts[(i + 1)] + " " + tinyParts[(i + 5)].substring(tinyParts[(i + 5)].indexOf("\""), tinyParts[(i + 5)].lastIndexOf("\"") + 1));
        } else
        {
          System.out.println("var " + tinyParts[(i + 1)]);
        }
      }
    }
    for (int i = 0; i < this.Intermediate_Representation.size(); i++)
    {
      String[] string_chunks = ((String)this.Intermediate_Representation.get(i)).split(" ");
      switch(string_chunks[0])
      {
    	  case("MULTI"):
    	  {
    		  if ((string_chunks[1].contains("$T")) && (string_chunks[2].contains("$T")))
    		  {
    			  System.out.println("move " + Temporary_String(string_chunks[1]) + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("muli " + Temporary_String(string_chunks[2]) + " " + Temporary_String(string_chunks[3]));
    		  }
    		  else if (string_chunks[1].contains("$T"))
    		  {
    			  System.out.println("move " + Temporary_String(string_chunks[1]) + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("muli " + string_chunks[2] + " " + Temporary_String(string_chunks[3]));
    		  }
    		  else if (string_chunks[2].contains("$T"))
    		  {
    			  System.out.println("move " + string_chunks[1] + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("muli " + Temporary_String(string_chunks[2]) + " " + Temporary_String(string_chunks[3]));
    		  }
    		  else
    		  {
    			  System.out.println("move " + string_chunks[1] + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("muli " + string_chunks[2] + " " + Temporary_String(string_chunks[3]));
    		  }
    		  break;
    	  }
    	  case("STOREI"):
    	  {
    		  if (string_chunks[1].contains("$T"))
    		  {
    			  System.out.println("move " + Temporary_String(string_chunks[1]) + " " + string_chunks[2]);
    		  }
    		  else
    		  {
    			  System.out.println("move " + string_chunks[1] + " " + Temporary_String(string_chunks[2]));
    		  }
    		  break;
    	  }
    	  case("ADDI"):
    	  {
    		  if ((string_chunks[1].contains("$T")) && (string_chunks[2].contains("$T")))
    		  {
    			  System.out.println("move " + Temporary_String(string_chunks[1]) + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("addi " + Temporary_String(string_chunks[2]) + " " + Temporary_String(string_chunks[3]));
    		  }
    		  else if (string_chunks[1].contains("$T"))
    		  {
    			  System.out.println("move " + Temporary_String(string_chunks[1]) + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("addi " + string_chunks[2] + " " + Temporary_String(string_chunks[3]));
    		  }
    		  else if (string_chunks[2].contains("$T"))
    		  {
    			  System.out.println("move " + string_chunks[1] + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("addi " + Temporary_String(string_chunks[2]) + " " + Temporary_String(string_chunks[3]));
    		  }
    		  else
    		  {
    			  System.out.println("move " + string_chunks[1] + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("addi " + string_chunks[2] + " " + Temporary_String(string_chunks[3]));
    		  }
    		  break;
    	  }
    	  case("DIVI"):
    	  {
    		  if ((string_chunks[1].contains("$T")) && (string_chunks[2].contains("$T")))
    		  {
    			  System.out.println("move " + Temporary_String(string_chunks[1]) + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("divi " + Temporary_String(string_chunks[2]) + " " + Temporary_String(string_chunks[3]));
    		  }
    		  else if (string_chunks[1].contains("$T"))
    		  {
    			  System.out.println("move " + Temporary_String(string_chunks[1]) + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("divi " + string_chunks[2] + " " + Temporary_String(string_chunks[3]));
    		  }
    		  else if (string_chunks[2].contains("$T"))
    		  {
    			  System.out.println("move " + string_chunks[1] + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("divi " + Temporary_String(string_chunks[2]) + " " + Temporary_String(string_chunks[3]));
    		  }
    		  else
    		  {
    			  System.out.println("move " + string_chunks[1] + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("divi " + string_chunks[2] + " " + Temporary_String(string_chunks[3]));
    		  }
    		  break;
    	  }
    	  case("SUBI"):
    	  {
    		  if ((string_chunks[1].contains("$T")) && (string_chunks[2].contains("$T")))
    		  {
    			  System.out.println("move " + Temporary_String(string_chunks[1]) + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("subi " + Temporary_String(string_chunks[2]) + " " + Temporary_String(string_chunks[3]));
    		  }
    		  else if (string_chunks[1].contains("$T"))
    		  {
    			  System.out.println("move " + Temporary_String(string_chunks[1]) + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("subi " + string_chunks[2] + " " + Temporary_String(string_chunks[3]));
    		  }
    		  else if (string_chunks[2].contains("$T"))
    		  {
    			  System.out.println("move " + string_chunks[1] + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("subi " + Temporary_String(string_chunks[2]) + " " + Temporary_String(string_chunks[3]));
    		  }
    		  else
    		  {
    			  System.out.println("move " + string_chunks[1] + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("subi " + string_chunks[2] + " " + Temporary_String(string_chunks[3]));
    		  }
    		  break;
    	  }
    	  case("WRITEI"):
    	  {
    		  if (string_chunks[1].contains("$")) 
    		  {
    			  System.out.println("sys writei " + Temporary_String(string_chunks[1]));
    		  }
    		  else
    		  {
    			  System.out.println("sys writei " + string_chunks[1]);
    		  }
    		  break;
    	  }
    	  case("WRITES"):
    	  {
    		  System.out.println("sys writes " + string_chunks[1]);
    		  break;
    	  }
    	  case("READI"):
    	  {
    		  System.out.println("sys readi " + string_chunks[1] + " ");
    		  break;
    	  }
    	  case("STOREF"):
    	  {
    		  if (string_chunks[1].contains("$T"))
    		  {
    			  System.out.println("move " + Temporary_String(string_chunks[1]) + " " + string_chunks[2]);
    		  }
    		  else
    		  {
    			  System.out.println("move " + string_chunks[1] + " " + Temporary_String(string_chunks[2]));
    		  }
    		  break;
    	  }
    	  case("MULTF"):
    	  {
    		  if ((string_chunks[1].contains("$T")) && (string_chunks[2].contains("$T")))
    		  {
    			  System.out.println("move " + Temporary_String(string_chunks[1]) + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("mulr " + Temporary_String(string_chunks[2]) + " " + Temporary_String(string_chunks[3]));
    		  }
    		  else if (string_chunks[1].contains("$T"))
    		  {
    			  System.out.println("move " + Temporary_String(string_chunks[1]) + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("mulr " + string_chunks[2] + " " + Temporary_String(string_chunks[3]));
    		  }
    		  else if (string_chunks[2].contains("$T"))
    		  {
    			  System.out.println("move " + string_chunks[1] + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("mulr " + Temporary_String(string_chunks[2]) + " " + Temporary_String(string_chunks[3]));
    		  }
    		  else
    		  {
    			  System.out.println("move " + string_chunks[1] + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("mulr " + string_chunks[2] + " " + Temporary_String(string_chunks[3]));
    		  }
    		  break;
    	  }
    	  case("ADDF"):
    	  {
    		  if ((string_chunks[1].contains("$T")) && (string_chunks[2].contains("$T")))
    		  {
    			  System.out.println("move " + Temporary_String(string_chunks[1]) + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("addr " + Temporary_String(string_chunks[2]) + " " + Temporary_String(string_chunks[3]));
    		  }
    		  else if (string_chunks[1].contains("$T"))
    		  {
    			  System.out.println("move " + Temporary_String(string_chunks[1]) + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("addr " + string_chunks[2] + " " + Temporary_String(string_chunks[3]));
    		  }
    		  else if (string_chunks[2].contains("$T"))
    		  {
    			  System.out.println("move " + string_chunks[1] + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("addr " + Temporary_String(string_chunks[2]) + " " + Temporary_String(string_chunks[3]));
    		  }
    		  else
    		  {
    			  System.out.println("move " + string_chunks[1] + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("addr " + string_chunks[2] + " " + Temporary_String(string_chunks[3]));
    		  }
    		  break;
    	  }
    	  case("DIVF"):
    	  {
    		  if ((string_chunks[1].contains("$T")) && (string_chunks[2].contains("$T")))
    		  {
    			  System.out.println("move " + Temporary_String(string_chunks[1]) + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("divr " + Temporary_String(string_chunks[2]) + " " + Temporary_String(string_chunks[3]));
    		  }
    		  else if (string_chunks[1].contains("$T"))
    		  {
    			  System.out.println("move " + Temporary_String(string_chunks[1]) + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("divr " + string_chunks[2] + " " + Temporary_String(string_chunks[3]));
    		  }
    		  else if (string_chunks[2].contains("$T"))
    		  {
    			  System.out.println("move " + string_chunks[1] + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("divr " + Temporary_String(string_chunks[2]) + " " + Temporary_String(string_chunks[3]));
    		  }
    		  else
    		  {
    			  System.out.println("move " + string_chunks[1] + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("divr " + string_chunks[2] + " " + Temporary_String(string_chunks[3]));
    		  }
    		  break;
    	  }
    	  case("SUBF"):
    	  {
    		  if ((string_chunks[1].contains("$T")) && (string_chunks[2].contains("$T")))
    		  {
    			  System.out.println("move " + Temporary_String(string_chunks[1]) + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("subr " + Temporary_String(string_chunks[2]) + " " + Temporary_String(string_chunks[3]));
    		  }
    		  else if (string_chunks[1].contains("$T"))
    		  {
    			  System.out.println("move " + Temporary_String(string_chunks[1]) + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("subr " + string_chunks[2] + " " + Temporary_String(string_chunks[3]));
    		  }
    		  else if (string_chunks[2].contains("$T"))
    		  {
    			  System.out.println("move " + string_chunks[1] + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("subr " + Temporary_String(string_chunks[2]) + " " + Temporary_String(string_chunks[3]));
    		  }
    		  else
    		  {
    			  System.out.println("move " + string_chunks[1] + " " + Temporary_String(string_chunks[3]));
    			  System.out.println("subr " + string_chunks[2] + " " + Temporary_String(string_chunks[3]));
    		  }
    		  break;
    	  }
    	  case("WRITEF"):
    	  {
    		  System.out.println("sys writer " + string_chunks[1] + " ");
    		  break;
    	  }
    	  case("READF"):
    	  {
    		  System.out.println("sys readr " + string_chunks[1] + " ");
    		  break;
    	  }
    	  default:
    		  break;
      }
    }
    System.out.println("sys halt");
    return "";
  }
  
  public String Temporary_String(String temp)
  {
    if (this.Tony_Danza.get(temp) != null)
    {
      return (String)this.Tony_Danza.get(temp);
    }
    this.temporaryIndex += 1;
    String register = "r" + Integer.toString(this.temporaryIndex);
    this.Tony_Danza.put(temp, register);
    return register;
  }
}