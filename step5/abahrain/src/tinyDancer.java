import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class TinyDancer
{
  ArrayList<String> Intermediate_Representation;
  String result = "";
  SymbolTable information_table;
  public int tempIndex = -1;
  Map<String, String> TR = new LinkedHashMap<String, String>();
  
  public TinyDancer(ArrayList<String> input, SymbolTable information_table)
  {
    this.Intermediate_Representation = input;
    this.information_table = information_table;
  }
  
  public String toString()
  {
    this.result = this.result.concat(";IR code\n");
    for (int i = 0; i < this.Intermediate_Representation.size(); i++) {
      this.result = this.result.concat(";" + (String)this.Intermediate_Representation.get(i) + "\n");
    }
    this.result = this.result.concat(";tiny code\n");
    

    String[] varTable = this.information_table.toString().split(" ");
    for (int i = 0; i < varTable.length; i++) {
      if (varTable[i].contains("name")) {
        if (varTable[(i + 3)].contains("STRING")) {
          this.result = this.result.concat("str " + varTable[(i + 1)] + " " + varTable[(i + 5)].substring(varTable[(i + 5)].indexOf("\""), varTable[(i + 5)].lastIndexOf("\"") + 1) + "\n");
        } else {
          this.result = this.result.concat("var " + varTable[(i + 1)] + "\n");
        }
      }
    }
    for (int i = 0; i < this.Intermediate_Representation.size(); i++)
    {
      String[] string_chunk = ((String)this.Intermediate_Representation.get(i)).split(" ");
      if (string_chunk[0].equalsIgnoreCase("STOREI"))
      {
        if (string_chunk[1].contains("$T"))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + string_chunk[2] + "\n");
        }
        else if (string_chunk[2].contains("$T"))
        {
          this.result = this.result.concat("move " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("move " + createTemp(string_chunk[2]) + " " + string_chunk[2] + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("MULTI"))
      {
        if ((string_chunk[1].contains("$T")) && (string_chunk[2].contains("$T")))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("muli " + createTemp(string_chunk[2]) + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[1].contains("$T"))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("muli " + string_chunk[2] + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[2].contains("$T"))
        {
          this.result = this.result.concat("move " + string_chunk[1] + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("muli " + createTemp(string_chunk[2]) + " " + createTemp(string_chunk[3]) + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + string_chunk[1] + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("muli " + string_chunk[2] + " " + createTemp(string_chunk[3]) + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("ADDI"))
      {
        if ((string_chunk[1].contains("$T")) && (string_chunk[2].contains("$T")))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("addi " + createTemp(string_chunk[2]) + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[1].contains("$T"))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("addi " + string_chunk[2] + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[2].contains("$T"))
        {
          this.result = this.result.concat("move " + string_chunk[1] + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("addi " + createTemp(string_chunk[2]) + " " + createTemp(string_chunk[3]) + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + string_chunk[1] + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("addi " + string_chunk[2] + " " + createTemp(string_chunk[3]) + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("DIVI"))
      {
        if ((string_chunk[1].contains("$T")) && (string_chunk[2].contains("$T")))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("divi " + createTemp(string_chunk[2]) + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[1].contains("$T"))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("divi " + string_chunk[2] + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[2].contains("$T"))
        {
          this.result = this.result.concat("move " + string_chunk[1] + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("divi " + createTemp(string_chunk[2]) + " " + createTemp(string_chunk[3]) + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + string_chunk[1] + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("divi " + string_chunk[2] + " " + createTemp(string_chunk[3]) + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("SUBI"))
      {
        if ((string_chunk[1].contains("$T")) && (string_chunk[2].contains("$T")))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("subi " + createTemp(string_chunk[2]) + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[1].contains("$T"))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("subi " + string_chunk[2] + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[2].contains("$T"))
        {
          this.result = this.result.concat("move " + string_chunk[1] + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("subi " + createTemp(string_chunk[2]) + " " + createTemp(string_chunk[3]) + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + string_chunk[1] + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("subi " + string_chunk[2] + " " + createTemp(string_chunk[3]) + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("WRITEI")) {
        this.result = this.result.concat("sys writei " + string_chunk[1] + " " + "\n");
      } else if (string_chunk[0].equalsIgnoreCase("WRITES")) {
        this.result = this.result.concat("sys writes " + string_chunk[1] + "\n");
      } else if (string_chunk[0].equalsIgnoreCase("READI")) {
        this.result = this.result.concat("sys readi " + string_chunk[1] + " " + "\n");
      } else if (string_chunk[0].equalsIgnoreCase("STOREF"))
      {
        if (string_chunk[1].contains("$T"))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + string_chunk[2] + "\n");
        }
        else if (string_chunk[2].contains("$T"))
        {
          this.result = this.result.concat("move " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("move " + createTemp(string_chunk[2]) + " " + string_chunk[2] + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("MULTF"))
      {
        if ((string_chunk[1].contains("$T")) && (string_chunk[2].contains("$T")))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("mulr " + createTemp(string_chunk[2]) + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[1].contains("$T"))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("mulr " + string_chunk[2] + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[2].contains("$T"))
        {
          this.result = this.result.concat("move " + string_chunk[1] + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("mulr " + createTemp(string_chunk[2]) + " " + createTemp(string_chunk[3]) + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + string_chunk[1] + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("mulr " + string_chunk[2] + " " + createTemp(string_chunk[3]) + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("ADDF"))
      {
        if ((string_chunk[1].contains("$T")) && (string_chunk[2].contains("$T")))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("addr " + createTemp(string_chunk[2]) + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[1].contains("$T"))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("addr " + string_chunk[2] + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[2].contains("$T"))
        {
          this.result = this.result.concat("move " + string_chunk[1] + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("addr " + createTemp(string_chunk[2]) + " " + createTemp(string_chunk[3]) + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + string_chunk[1] + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("addr " + string_chunk[2] + " " + createTemp(string_chunk[3]) + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("DIVF"))
      {
        if ((string_chunk[1].contains("$T")) && (string_chunk[2].contains("$T")))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("divr " + createTemp(string_chunk[2]) + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[1].contains("$T"))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("divr " + string_chunk[2] + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[2].contains("$T"))
        {
          this.result = this.result.concat("move " + string_chunk[1] + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("divr " + createTemp(string_chunk[2]) + " " + createTemp(string_chunk[3]) + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + string_chunk[1] + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("divr " + string_chunk[2] + " " + createTemp(string_chunk[3]) + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("SUBF"))
      {
        if ((string_chunk[1].contains("$T")) && (string_chunk[2].contains("$T")))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("subr " + createTemp(string_chunk[2]) + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[1].contains("$T"))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("subr " + string_chunk[2] + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[2].contains("$T"))
        {
          this.result = this.result.concat("move " + string_chunk[1] + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("subr " + createTemp(string_chunk[2]) + " " + createTemp(string_chunk[3]) + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + string_chunk[1] + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("subr " + string_chunk[2] + " " + createTemp(string_chunk[3]) + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("WRITEF")) {
        this.result = this.result.concat("sys writer " + string_chunk[1] + " " + "\n");
      } else if (string_chunk[0].equalsIgnoreCase("READF")) {
        this.result = this.result.concat("sys readr " + string_chunk[1] + " " + "\n");
      } else if (string_chunk[0].equalsIgnoreCase("LABEL")) {
        this.result = this.result.concat("label " + string_chunk[1] + " " + "\n");
      } else if (string_chunk[0].equalsIgnoreCase("JUMP")) {
        this.result = this.result.concat("jmp " + string_chunk[1] + " " + "\n");
      } else if (string_chunk[0].equalsIgnoreCase("LEI"))
      {
        if ((string_chunk[1].contains("$T")) && (string_chunk[2].contains("$T")))
        {
          this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jle " + string_chunk[3] + "\n");
        }
        else if (string_chunk[1].contains("$T"))
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jle " + string_chunk[3] + "\n");
        }
        else if (string_chunk[2].contains("$T"))
        {
          this.result = this.result.concat("cmpi " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jle " + string_chunk[3] + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpi " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jle " + string_chunk[3] + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("GEI"))
      {
        if ((string_chunk[1].contains("$T")) && (string_chunk[2].contains("$T")))
        {
          this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jge " + string_chunk[3] + "\n");
        }
        else if (string_chunk[1].contains("$T"))
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jge " + string_chunk[3] + "\n");
        }
        else if (string_chunk[2].contains("$T"))
        {
          this.result = this.result.concat("cmpi " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jge " + string_chunk[3] + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpi " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jge " + string_chunk[3] + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("NEI"))
      {
        if ((string_chunk[1].contains("$T")) && (string_chunk[2].contains("$T")))
        {
          this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jne " + string_chunk[3] + "\n");
        }
        else if (string_chunk[1].contains("$T"))
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jne " + string_chunk[3] + "\n");
        }
        else if (string_chunk[2].contains("$T"))
        {
          this.result = this.result.concat("cmpi " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jne " + string_chunk[3] + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpi " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jne " + string_chunk[3] + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("EQI"))
      {
        if ((string_chunk[1].contains("$T")) && (string_chunk[2].contains("$T")))
        {
          this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jeq " + string_chunk[3] + "\n");
        }
        else if (string_chunk[1].contains("$T"))
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jeq " + string_chunk[3] + "\n");
        }
        else if (string_chunk[2].contains("$T"))
        {
          this.result = this.result.concat("cmpi " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jeq " + string_chunk[3] + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpi " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jeq " + string_chunk[3] + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("GTI"))
      {
        if ((string_chunk[1].contains("$T")) && (string_chunk[2].contains("$T")))
        {
          this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jgt " + string_chunk[3] + "\n");
        }
        else if (string_chunk[1].contains("$T"))
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jgt " + string_chunk[3] + "\n");
        }
        else if (string_chunk[2].contains("$T"))
        {
          this.result = this.result.concat("cmpi " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jgt " + string_chunk[3] + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpi " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jgt " + string_chunk[3] + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("LTI"))
      {
        if ((string_chunk[1].contains("$T")) && (string_chunk[2].contains("$T")))
        {
          this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jlt " + string_chunk[3] + "\n");
        }
        else if (string_chunk[1].contains("$T"))
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jlt " + string_chunk[3] + "\n");
        }
        else if (string_chunk[2].contains("$T"))
        {
          this.result = this.result.concat("cmpi " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jlt " + string_chunk[3] + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpi " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jlt " + string_chunk[3] + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("LEF"))
      {
        if ((string_chunk[1].contains("$T")) && (string_chunk[2].contains("$T")))
        {
          this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jle " + string_chunk[3] + "\n");
        }
        else if (string_chunk[1].contains("$T"))
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jle " + string_chunk[3] + "\n");
        }
        else if (string_chunk[2].contains("$T"))
        {
          this.result = this.result.concat("cmpr " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jle " + string_chunk[3] + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpr " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jle " + string_chunk[3] + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("GEF"))
      {
        if ((string_chunk[1].contains("$T")) && (string_chunk[2].contains("$T")))
        {
          this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jge " + string_chunk[3] + "\n");
        }
        else if (string_chunk[1].contains("$T"))
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jge " + string_chunk[3] + "\n");
        }
        else if (string_chunk[2].contains("$T"))
        {
          this.result = this.result.concat("cmpr " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jge " + string_chunk[3] + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpr " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jge " + string_chunk[3] + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("NEF"))
      {
        if ((string_chunk[1].contains("$T")) && (string_chunk[2].contains("$T")))
        {
          this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jne " + string_chunk[3] + "\n");
        }
        else if (string_chunk[1].contains("$T"))
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jne " + string_chunk[3] + "\n");
        }
        else if (string_chunk[2].contains("$T"))
        {
          this.result = this.result.concat("cmpr " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jne " + string_chunk[3] + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpr " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jne " + string_chunk[3] + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("EQF"))
      {
        if ((string_chunk[1].contains("$T")) && (string_chunk[2].contains("$T")))
        {
          this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jeq " + string_chunk[3] + "\n");
        }
        else if (string_chunk[1].contains("$T"))
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jeq " + string_chunk[3] + "\n");
        }
        else if (string_chunk[2].contains("$T"))
        {
          this.result = this.result.concat("cmpr " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jeq " + string_chunk[3] + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpr " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jeq " + string_chunk[3] + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("GTF"))
      {
        if ((string_chunk[1].contains("$T")) && (string_chunk[2].contains("$T")))
        {
          this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jgt " + string_chunk[3] + "\n");
        }
        else if (string_chunk[1].contains("$T"))
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jgt " + string_chunk[3] + "\n");
        }
        else if (string_chunk[2].contains("$T"))
        {
          this.result = this.result.concat("cmpr " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jgt " + string_chunk[3] + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpr " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jgt " + string_chunk[3] + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("LTF")) {
        if ((string_chunk[1].contains("$T")) && (string_chunk[2].contains("$T")))
        {
          this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jlt " + string_chunk[3] + "\n");
        }
        else if (string_chunk[1].contains("$T"))
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jlt " + string_chunk[3] + "\n");
        }
        else if (string_chunk[2].contains("$T"))
        {
          this.result = this.result.concat("cmpr " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jlt " + string_chunk[3] + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpr " + string_chunk[1] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jlt " + string_chunk[3] + "\n");
        }
      }
    }
    this.result = this.result.concat("sys halt");
    return this.result;
  }
  
  public String createTemp(String temp)
  {
    if (this.TR.get(temp) != null) {
      return (String)this.TR.get(temp);
    }
    this.tempIndex += 1;
    String R = "r" + Integer.toString(this.tempIndex);
    this.TR.put(temp, R);
    return R;
  }
}
