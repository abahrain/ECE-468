import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class TinyDancer
{
  ArrayList<String> IR;
  String result = "";
  SymbolTable table;
  public int tempIndex = -1;
  Map<String, String> TR = new LinkedHashMap<String, String>();
  
  public TinyDancer(ArrayList<String> input, SymbolTable table)
  {
    this.IR = input;
    this.table = table;
  }
  
  public String toString()
  {
    this.result = this.result.concat(";IR code\n");
    for (int i = 0; i < this.IR.size(); i++) {
      this.result = this.result.concat(";" + (String)this.IR.get(i) + "\n");
    }
    this.result = this.result.concat(";tiny code\n");
    

    String[] varTable = this.table.toString().split(" ");
    for (int i = 0; i < varTable.length; i++) {
      if (varTable[i].contains("name")) {
        if (varTable[(i + 3)].contains("STRING")) {
          this.result = this.result.concat("str " + varTable[(i + 1)] + " " + varTable[(i + 5)].substring(varTable[(i + 5)].indexOf("\""), varTable[(i + 5)].lastIndexOf("\"") + 1) + "\n");
        } else {
          this.result = this.result.concat("var " + varTable[(i + 1)] + "\n");
        }
      }
    }
    for (int i = 0; i < this.IR.size(); i++)
    {
      String[] elements = ((String)this.IR.get(i)).split(" ");
      if (elements[0].equalsIgnoreCase("STOREI"))
      {
        if (elements[1].contains("$T"))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + elements[2] + "\n");
        }
        else if (elements[2].contains("$T"))
        {
          this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[2]) + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("move " + createTemp(elements[2]) + " " + elements[2] + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("MULTI"))
      {
        if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("muli " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[1].contains("$T"))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("muli " + elements[2] + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[2].contains("$T"))
        {
          this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("muli " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("muli " + elements[2] + " " + createTemp(elements[3]) + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("ADDI"))
      {
        if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("addi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[1].contains("$T"))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("addi " + elements[2] + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[2].contains("$T"))
        {
          this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("addi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("addi " + elements[2] + " " + createTemp(elements[3]) + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("DIVI"))
      {
        if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("divi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[1].contains("$T"))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("divi " + elements[2] + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[2].contains("$T"))
        {
          this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("divi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("divi " + elements[2] + " " + createTemp(elements[3]) + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("SUBI"))
      {
        if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("subi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[1].contains("$T"))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("subi " + elements[2] + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[2].contains("$T"))
        {
          this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("subi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("subi " + elements[2] + " " + createTemp(elements[3]) + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("WRITEI")) {
        this.result = this.result.concat("sys writei " + elements[1] + " " + "\n");
      } else if (elements[0].equalsIgnoreCase("WRITES")) {
        this.result = this.result.concat("sys writes " + elements[1] + "\n");
      } else if (elements[0].equalsIgnoreCase("READI")) {
        this.result = this.result.concat("sys readi " + elements[1] + " " + "\n");
      } else if (elements[0].equalsIgnoreCase("STOREF"))
      {
        if (elements[1].contains("$T"))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + elements[2] + "\n");
        }
        else if (elements[2].contains("$T"))
        {
          this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[2]) + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("move " + createTemp(elements[2]) + " " + elements[2] + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("MULTF"))
      {
        if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("mulr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[1].contains("$T"))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("mulr " + elements[2] + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[2].contains("$T"))
        {
          this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("mulr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("mulr " + elements[2] + " " + createTemp(elements[3]) + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("ADDF"))
      {
        if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("addr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[1].contains("$T"))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("addr " + elements[2] + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[2].contains("$T"))
        {
          this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("addr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("addr " + elements[2] + " " + createTemp(elements[3]) + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("DIVF"))
      {
        if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("divr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[1].contains("$T"))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("divr " + elements[2] + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[2].contains("$T"))
        {
          this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("divr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("divr " + elements[2] + " " + createTemp(elements[3]) + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("SUBF"))
      {
        if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("subr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[1].contains("$T"))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("subr " + elements[2] + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[2].contains("$T"))
        {
          this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("subr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("subr " + elements[2] + " " + createTemp(elements[3]) + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("WRITEF")) {
        this.result = this.result.concat("sys writer " + elements[1] + " " + "\n");
      } else if (elements[0].equalsIgnoreCase("READF")) {
        this.result = this.result.concat("sys readr " + elements[1] + " " + "\n");
      } else if (elements[0].equalsIgnoreCase("LABEL")) {
        this.result = this.result.concat("label " + elements[1] + " " + "\n");
      } else if (elements[0].equalsIgnoreCase("JUMP")) {
        this.result = this.result.concat("jmp " + elements[1] + " " + "\n");
      } else if (elements[0].equalsIgnoreCase("LEI"))
      {
        if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
        {
          this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jle " + elements[3] + "\n");
        }
        else if (elements[1].contains("$T"))
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jle " + elements[3] + "\n");
        }
        else if (elements[2].contains("$T"))
        {
          this.result = this.result.concat("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jle " + elements[3] + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jle " + elements[3] + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("GEI"))
      {
        if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
        {
          this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jge " + elements[3] + "\n");
        }
        else if (elements[1].contains("$T"))
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jge " + elements[3] + "\n");
        }
        else if (elements[2].contains("$T"))
        {
          this.result = this.result.concat("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jge " + elements[3] + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jge " + elements[3] + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("NEI"))
      {
        if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
        {
          this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jne " + elements[3] + "\n");
        }
        else if (elements[1].contains("$T"))
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jne " + elements[3] + "\n");
        }
        else if (elements[2].contains("$T"))
        {
          this.result = this.result.concat("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jne " + elements[3] + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jne " + elements[3] + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("EQI"))
      {
        if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
        {
          this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jeq " + elements[3] + "\n");
        }
        else if (elements[1].contains("$T"))
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jeq " + elements[3] + "\n");
        }
        else if (elements[2].contains("$T"))
        {
          this.result = this.result.concat("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jeq " + elements[3] + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jeq " + elements[3] + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("GTI"))
      {
        if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
        {
          this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jgt " + elements[3] + "\n");
        }
        else if (elements[1].contains("$T"))
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jgt " + elements[3] + "\n");
        }
        else if (elements[2].contains("$T"))
        {
          this.result = this.result.concat("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jgt " + elements[3] + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jgt " + elements[3] + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("LTI"))
      {
        if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
        {
          this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jlt " + elements[3] + "\n");
        }
        else if (elements[1].contains("$T"))
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jlt " + elements[3] + "\n");
        }
        else if (elements[2].contains("$T"))
        {
          this.result = this.result.concat("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jlt " + elements[3] + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jlt " + elements[3] + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("LEF"))
      {
        if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
        {
          this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jle " + elements[3] + "\n");
        }
        else if (elements[1].contains("$T"))
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jle " + elements[3] + "\n");
        }
        else if (elements[2].contains("$T"))
        {
          this.result = this.result.concat("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jle " + elements[3] + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jle " + elements[3] + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("GEF"))
      {
        if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
        {
          this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jge " + elements[3] + "\n");
        }
        else if (elements[1].contains("$T"))
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jge " + elements[3] + "\n");
        }
        else if (elements[2].contains("$T"))
        {
          this.result = this.result.concat("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jge " + elements[3] + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jge " + elements[3] + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("NEF"))
      {
        if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
        {
          this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jne " + elements[3] + "\n");
        }
        else if (elements[1].contains("$T"))
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jne " + elements[3] + "\n");
        }
        else if (elements[2].contains("$T"))
        {
          this.result = this.result.concat("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jne " + elements[3] + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jne " + elements[3] + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("EQF"))
      {
        if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
        {
          this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jeq " + elements[3] + "\n");
        }
        else if (elements[1].contains("$T"))
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jeq " + elements[3] + "\n");
        }
        else if (elements[2].contains("$T"))
        {
          this.result = this.result.concat("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jeq " + elements[3] + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jeq " + elements[3] + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("GTF"))
      {
        if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
        {
          this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jgt " + elements[3] + "\n");
        }
        else if (elements[1].contains("$T"))
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jgt " + elements[3] + "\n");
        }
        else if (elements[2].contains("$T"))
        {
          this.result = this.result.concat("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jgt " + elements[3] + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jgt " + elements[3] + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("LTF")) {
        if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
        {
          this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jlt " + elements[3] + "\n");
        }
        else if (elements[1].contains("$T"))
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jlt " + elements[3] + "\n");
        }
        else if (elements[2].contains("$T"))
        {
          this.result = this.result.concat("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jlt " + elements[3] + "\n");
        }
        else
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jlt " + elements[3] + "\n");
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
