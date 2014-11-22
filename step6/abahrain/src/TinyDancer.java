import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class TinyDancer
{
  private ArrayList<String> Intermediate_Representation;
  private String result = "";
  private int registerNumber;
  private SymbolTable information_table;
  private int paramIndex = this.registerNumber + 1;
  private int labelIndex = 0;
  private String labelIndicator = null;
  private int count = 0;
  private int IndexPosition = 0;
  protected Map<String, Map<String, String>> TonyDanza = new LinkedHashMap<>();
  protected Map<String, Map<String, NodeStructure>> tableMap = new LinkedHashMap<>();
  protected Map<String, Integer> Number_of_Links = new LinkedHashMap<>();
  protected Map<String, ArrayList<String>> temporary_Map = new LinkedHashMap<>();
  
  public TinyDancer(ArrayList<String> input, SymbolTable information_table, Map<String, Map<String, NodeStructure>> inputMap, Map<String, ArrayList<String>> temporary_Map, int registerNumber)
  {
    this.Intermediate_Representation = input;
    this.information_table = information_table;
    this.registerNumber = registerNumber;
    this.paramIndex = (registerNumber + 1);
    this.IndexPosition = this.paramIndex;
    this.tableMap = inputMap;
    this.temporary_Map = temporary_Map;
    for (String key : this.tableMap.keySet())
    {
      Map<String, String> newTR = new LinkedHashMap<>();
      for (int i = 1; i <= registerNumber; i++)
      {
        newTR.put("$T" + Integer.toString(i), "r" + Integer.toString(i - 1));
      }
      for (NodeStructure each : (this.tableMap.get(key)).values()) //removed mapped cast
      {
        if ((each.content.contains("$P")) && (!this.TonyDanza.containsKey(each.content)))
        {
          newTR.put(each.content, "$" + Integer.toString(Integer.parseInt(each.content.substring(2)) + this.paramIndex));
          
          this.IndexPosition = (Integer.parseInt(each.content.substring(2)) + this.paramIndex + 1);
        }
        else if (each.content.contains("$L"))
        {
          newTR.put(each.content, "$" + Integer.toString(-Integer.parseInt(each.content.substring(2)) - this.labelIndex));
          this.count += 1;
        }
        newTR.put("$R", "$" + Integer.toString(this.IndexPosition));
        this.TonyDanza.put(key, newTR);
      }
      this.Number_of_Links.put(key, Integer.valueOf(this.count));
      this.count = 0;
      this.IndexPosition = this.paramIndex;
    }
  }
  
  public String toString()
  {
    this.result = this.result.concat(";IR code\n");
    for (int i = 0; i < this.Intermediate_Representation.size(); i++) 
    {
      this.result = this.result.concat(";" + (String)this.Intermediate_Representation.get(i) + "\n");
    }
    this.result = this.result.concat(";tiny code\n");
    initialGlobal();
    initailMain();
    for (int i = 0; i < this.Intermediate_Representation.size(); i++)
    {
      String[] string_chunk = ((String)this.Intermediate_Representation.get(i)).split(" ");
      if (string_chunk[0].equalsIgnoreCase("STOREI"))
      {
        if ((string_chunk[1].contains("$")) && (string_chunk[2].contains("$")))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
        }
        else if (string_chunk[1].contains("$"))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + string_chunk[2] + "\n");
        }
        else if (string_chunk[2].contains("$"))
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
        if ((string_chunk[1].contains("$")) && (string_chunk[2].contains("$")))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("muli " + createTemp(string_chunk[2]) + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[1].contains("$"))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("muli " + string_chunk[2] + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[2].contains("$"))
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
        if ((string_chunk[1].contains("$")) && (string_chunk[2].contains("$")))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("addi " + createTemp(string_chunk[2]) + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[1].contains("$"))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("addi " + string_chunk[2] + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[2].contains("$"))
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
        if ((string_chunk[1].contains("$")) && (string_chunk[2].contains("$")))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("divi " + createTemp(string_chunk[2]) + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[1].contains("$"))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("divi " + string_chunk[2] + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[2].contains("$"))
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
        if ((string_chunk[1].contains("$")) && (string_chunk[2].contains("$")))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("subi " + createTemp(string_chunk[2]) + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[1].contains("$"))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("subi " + string_chunk[2] + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[2].contains("$"))
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
      else if (string_chunk[0].equalsIgnoreCase("WRITEI"))
      {
        if (string_chunk[1].contains("$")) 
        {
          this.result = this.result.concat("sys writei " + createTemp(string_chunk[1]) + "\n");
        }
        else 
        {
          this.result = this.result.concat("sys writei " + string_chunk[1] + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("WRITES"))
      {
        this.result = this.result.concat("sys writes " + string_chunk[1] + "\n");
      }
      else if (string_chunk[0].equalsIgnoreCase("READI"))
      {
        if (string_chunk[1].contains("$")) 
        {
          this.result = this.result.concat("sys readi " + createTemp(string_chunk[1]) + "\n");
        } 
        else
        {
          this.result = this.result.concat("sys readi " + string_chunk[1] + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("STOREF"))
      {
        if ((string_chunk[1].contains("$")) && (string_chunk[2].contains("$")))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
        }
        else if (string_chunk[1].contains("$"))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + string_chunk[2] + "\n");
        }
        else if (string_chunk[2].contains("$"))
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
        if ((string_chunk[1].contains("$")) && (string_chunk[2].contains("$")))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("mulr " + createTemp(string_chunk[2]) + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[1].contains("$"))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("mulr " + string_chunk[2] + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[2].contains("$"))
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
        if ((string_chunk[1].contains("$")) && (string_chunk[2].contains("$")))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("addr " + createTemp(string_chunk[2]) + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[1].contains("$"))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("addr " + string_chunk[2] + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[2].contains("$"))
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
        if ((string_chunk[1].contains("$")) && (string_chunk[2].contains("$")))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("divr " + createTemp(string_chunk[2]) + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[1].contains("$"))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("divr " + string_chunk[2] + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[2].contains("$"))
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
        if ((string_chunk[1].contains("$")) && (string_chunk[2].contains("$")))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("subr " + createTemp(string_chunk[2]) + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[1].contains("$"))
        {
          this.result = this.result.concat("move " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[3]) + "\n");
          this.result = this.result.concat("subr " + string_chunk[2] + " " + createTemp(string_chunk[3]) + "\n");
        }
        else if (string_chunk[2].contains("$"))
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
      else if (string_chunk[0].equalsIgnoreCase("WRITEF"))
      {
        if (string_chunk[1].contains("$")) 
        {
          this.result = this.result.concat("sys writer " + createTemp(string_chunk[1]) + " " + "\n");
        } 
        else 
        {
          this.result = this.result.concat("sys writer " + string_chunk[1] + " " + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("READF"))
      {
        if (string_chunk[1].contains("$")) 
        {
          this.result = this.result.concat("sys readr " + createTemp(string_chunk[1]) + " " + "\n");
        } 
        else
        {
          this.result = this.result.concat("sys readr " + string_chunk[1] + " " + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("LABEL"))
      {
        this.result = this.result.concat("label " + string_chunk[1] + " " + "\n");
        if (!string_chunk[1].contains("label")) 
        {
          this.labelIndicator = string_chunk[1];
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("JUMP"))
      {
        this.result = this.result.concat("jmp " + string_chunk[1] + " " + "\n");
      }
      else if (string_chunk[0].equalsIgnoreCase("LEI"))
      {
        if ((string_chunk[1].contains("$")) && (string_chunk[2].contains("$")))
        {
          if ((string_chunk[1].contains("$P")) || (string_chunk[1].contains("$L")) || (string_chunk[2].contains("$P")) || (string_chunk[2].contains("$L")))
          {
            this.result = this.result.concat("move " + createTemp(string_chunk[2]) + " " + createTemp(shiftToUnusedR((String)((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).get(((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).size() - 1))) + "\n");
            this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(shiftToUnusedR((String)((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).get(((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).size() - 1))) + "\n");
          }
          else
          {
            this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          }
          this.result = this.result.concat("jle " + string_chunk[3] + "\n");
        }
        else if (string_chunk[1].contains("$"))
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jle " + string_chunk[3] + "\n");
        }
        else if (string_chunk[2].contains("$"))
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
        if ((string_chunk[1].contains("$")) && (string_chunk[2].contains("$")))
        {
          if ((string_chunk[1].contains("$P")) || (string_chunk[1].contains("$L")) || (string_chunk[2].contains("$P")) || (string_chunk[2].contains("$L")))
          {
            this.result = this.result.concat("move " + createTemp(string_chunk[2]) + " " + createTemp(shiftToUnusedR((String)((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).get(((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).size() - 1))) + "\n");
            this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(shiftToUnusedR((String)((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).get(((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).size() - 1))) + "\n");
          }
          else
          {
            this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          }
          this.result = this.result.concat("jge " + string_chunk[3] + "\n");
        }
        else if (string_chunk[1].contains("$"))
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jge " + string_chunk[3] + "\n");
        }
        else if (string_chunk[2].contains("$"))
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
        if ((string_chunk[1].contains("$")) && (string_chunk[2].contains("$")))
        {
          if ((string_chunk[1].contains("$P")) || (string_chunk[1].contains("$L")) || (string_chunk[2].contains("$P")) || (string_chunk[2].contains("$L")))
          {
            this.result = this.result.concat("move " + createTemp(string_chunk[2]) + " " + createTemp(shiftToUnusedR((String)((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).get(((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).size() - 1))) + "\n");
            this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(shiftToUnusedR((String)((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).get(((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).size() - 1))) + "\n");
          }
          else
          {
            this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          }
          this.result = this.result.concat("jne " + string_chunk[3] + "\n");
        }
        else if (string_chunk[1].contains("$"))
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jne " + string_chunk[3] + "\n");
        }
        else if (string_chunk[2].contains("$"))
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
        if ((string_chunk[1].contains("$")) && (string_chunk[2].contains("$")))
        {
          if ((string_chunk[1].contains("$P")) || (string_chunk[1].contains("$L")) || (string_chunk[2].contains("$P")) || (string_chunk[2].contains("$L")))
          {
            this.result = this.result.concat("move " + createTemp(string_chunk[2]) + " " + createTemp(shiftToUnusedR((String)((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).get(((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).size() - 1))) + "\n");
            this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(shiftToUnusedR((String)((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).get(((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).size() - 1))) + "\n");
          }
          else
          {
            this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          }
          this.result = this.result.concat("jeq " + string_chunk[3] + "\n");
        }
        else if (string_chunk[1].contains("$"))
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jeq " + string_chunk[3] + "\n");
        }
        else if (string_chunk[2].contains("$"))
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
        if ((string_chunk[1].contains("$")) && (string_chunk[2].contains("$")))
        {
          if ((string_chunk[1].contains("$P")) || (string_chunk[1].contains("$L")) || (string_chunk[2].contains("$P")) || (string_chunk[2].contains("$L")))
          {
            this.result = this.result.concat("move " + createTemp(string_chunk[2]) + " " + createTemp(shiftToUnusedR((String)((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).get(((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).size() - 1))) + "\n");
            this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(shiftToUnusedR((String)((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).get(((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).size() - 1))) + "\n");
          }
          else
          {
            this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          }
          this.result = this.result.concat("jgt " + string_chunk[3] + "\n");
        }
        else if (string_chunk[1].contains("$"))
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jgt " + string_chunk[3] + "\n");
        }
        else if (string_chunk[2].contains("$"))
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
        if ((string_chunk[1].contains("$")) && (string_chunk[2].contains("$")))
        {
          if ((string_chunk[1].contains("$P")) || (string_chunk[1].contains("$L")) || (string_chunk[2].contains("$P")) || (string_chunk[2].contains("$L")))
          {
            this.result = this.result.concat("move " + createTemp(string_chunk[2]) + " " + createTemp(shiftToUnusedR((String)((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).get(((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).size() - 1))) + "\n");
            this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(shiftToUnusedR((String)((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).get(((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).size() - 1))) + "\n");
          }
          else
          {
            this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          }
          this.result = this.result.concat("jlt " + string_chunk[3] + "\n");
        }
        else if (string_chunk[1].contains("$"))
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpi " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jlt " + string_chunk[3] + "\n");
        }
        else if (string_chunk[2].contains("$"))
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
        if ((string_chunk[1].contains("$")) && (string_chunk[2].contains("$")))
        {
          if ((string_chunk[1].contains("$P")) || (string_chunk[1].contains("$L")) || (string_chunk[2].contains("$P")) || (string_chunk[2].contains("$L")))
          {
            this.result = this.result.concat("move " + createTemp(string_chunk[2]) + " " + createTemp(shiftToUnusedR((String)((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).get(((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).size() - 1))) + "\n");
            this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(shiftToUnusedR((String)((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).get(((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).size() - 1))) + "\n");
          }
          else
          {
            this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          }
          this.result = this.result.concat("jle " + string_chunk[3] + "\n");
        }
        else if (string_chunk[1].contains("$"))
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jle " + string_chunk[3] + "\n");
        }
        else if (string_chunk[2].contains("$"))
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
        if ((string_chunk[1].contains("$")) && (string_chunk[2].contains("$")))
        {
          if ((string_chunk[1].contains("$P")) || (string_chunk[1].contains("$L")) || (string_chunk[2].contains("$P")) || (string_chunk[2].contains("$L")))
          {
            this.result = this.result.concat("move " + createTemp(string_chunk[2]) + " " + createTemp(shiftToUnusedR((String)((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).get(((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).size() - 1))) + "\n");
            this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(shiftToUnusedR((String)((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).get(((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).size() - 1))) + "\n");
          }
          else
          {
            this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          }
          this.result = this.result.concat("jge " + string_chunk[3] + "\n");
        }
        else if (string_chunk[1].contains("$"))
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jge " + string_chunk[3] + "\n");
        }
        else if (string_chunk[2].contains("$"))
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
        if ((string_chunk[1].contains("$")) && (string_chunk[2].contains("$")))
        {
          if ((string_chunk[1].contains("$P")) || (string_chunk[1].contains("$L")) || (string_chunk[2].contains("$P")) || (string_chunk[2].contains("$L")))
          {
            this.result = this.result.concat("move " + createTemp(string_chunk[2]) + " " + createTemp(shiftToUnusedR((String)((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).get(((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).size() - 1))) + "\n");
            this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(shiftToUnusedR((String)((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).get(((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).size() - 1))) + "\n");
          }
          else
          {
            this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          }
          this.result = this.result.concat("jne " + string_chunk[3] + "\n");
        }
        else if (string_chunk[1].contains("$"))
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jne " + string_chunk[3] + "\n");
        }
        else if (string_chunk[2].contains("$"))
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
        if ((string_chunk[1].contains("$")) && (string_chunk[2].contains("$")))
        {
          if ((string_chunk[1].contains("$P")) || (string_chunk[1].contains("$L")) || (string_chunk[2].contains("$P")) || (string_chunk[2].contains("$L")))
          {
            this.result = this.result.concat("move " + createTemp(string_chunk[2]) + " " + createTemp(shiftToUnusedR((String)((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).get(((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).size() - 1))) + "\n");
            this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(shiftToUnusedR((String)((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).get(((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).size() - 1))) + "\n");
          }
          else
          {
            this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          }
          this.result = this.result.concat("jeq " + string_chunk[3] + "\n");
        }
        else if (string_chunk[1].contains("$"))
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jeq " + string_chunk[3] + "\n");
        }
        else if (string_chunk[2].contains("$"))
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
        if ((string_chunk[1].contains("$")) && (string_chunk[2].contains("$")))
        {
          if ((string_chunk[1].contains("$P")) || (string_chunk[1].contains("$L")) || (string_chunk[2].contains("$P")) || (string_chunk[2].contains("$L")))
          {
            this.result = this.result.concat("move " + createTemp(string_chunk[2]) + " " + createTemp(shiftToUnusedR((String)((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).get(((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).size() - 1))) + "\n");
            this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(shiftToUnusedR((String)((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).get(((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).size() - 1))) + "\n");
          }
          else
          {
            this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          }
          this.result = this.result.concat("jgt " + string_chunk[3] + "\n");
        }
        else if (string_chunk[1].contains("$"))
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jgt " + string_chunk[3] + "\n");
        }
        else if (string_chunk[2].contains("$"))
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
      else if (string_chunk[0].equalsIgnoreCase("LTF"))
      {
        if ((string_chunk[1].contains("$")) && (string_chunk[2].contains("$")))
        {
          if ((string_chunk[1].contains("$P")) || (string_chunk[1].contains("$L")) || (string_chunk[2].contains("$P")) || (string_chunk[2].contains("$L")))
          {
            this.result = this.result.concat("move " + createTemp(string_chunk[2]) + " " + createTemp(shiftToUnusedR((String)((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).get(((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).size() - 1))) + "\n");
            this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(shiftToUnusedR((String)((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).get(((ArrayList<String>)this.temporary_Map.get(this.labelIndicator)).size() - 1))) + "\n");
          }
          else
          {
            this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          }
          this.result = this.result.concat("jlt " + string_chunk[3] + "\n");
        }
        else if (string_chunk[1].contains("$"))
        {
          this.result = this.result.concat("move " + string_chunk[2] + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("cmpr " + createTemp(string_chunk[1]) + " " + createTemp(string_chunk[2]) + "\n");
          this.result = this.result.concat("jlt " + string_chunk[3] + "\n");
        }
        else if (string_chunk[2].contains("$"))
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
      else if (string_chunk[0].equalsIgnoreCase("jsr"))
      {
        for (int k = 0; k < this.registerNumber; k++)
        {
          this.result = this.result.concat("push r" + Integer.toString(k) + "\n");
        }
        this.result = this.result.concat("jsr " + string_chunk[1] + "\n");
        for (int k = this.registerNumber - 1; k >= 0; k--)
        {
          this.result = this.result.concat("pop r" + Integer.toString(k) + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("push"))
      {
        if (string_chunk.length == 1) 
        {
          this.result = this.result.concat("push\n");
        }
        else if (string_chunk[1].contains("$")) 
        {
          this.result = this.result.concat("push " + createTemp(string_chunk[1]) + "\n");
        }
        else 
        {
          this.result = this.result.concat("push " + string_chunk[1] + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("pop"))
      {
        if (string_chunk.length == 1) 
        {
          this.result = this.result.concat("pop\n");
        } 
        else if (string_chunk[1].contains("$")) 
        {
          this.result = this.result.concat("pop " + createTemp(string_chunk[1]) + "\n");
        } 
        else 
        {
          this.result = this.result.concat("pop " + string_chunk[1] + "\n");
        }
      }
      else if (string_chunk[0].equalsIgnoreCase("link"))
      {
        this.result = this.result.concat("link " + this.Number_of_Links.get(this.labelIndicator) + "\n");
      }
      else if (string_chunk[0].equalsIgnoreCase("RET"))
      {
        this.result = this.result.concat("unlnk\n");
        this.result = this.result.concat("ret\n");
      }
    }
    this.result = this.result.concat("end");
    return this.result;
  }
  
  private String shiftToUnusedR(String input)
  {
    return "$T" + Integer.toString(Integer.parseInt(input.substring(2)) + 1);
  }
  
  public void initailMain()
  {
    this.result = this.result.concat("push\n");
    for (int k = 0; k < this.registerNumber; k++) 
    {
      this.result = this.result.concat("push r" + Integer.toString(k) + "\n");
    }
    this.result = this.result.concat("jsr main\n");
    this.result = this.result.concat("sys halt\n");
  }
  
  public void initialGlobal()
  {
    for (Boresight scope : this.information_table.scopeStack.subList(0, this.information_table.scopeStack.size())) 
    {
      if (scope.type.equalsIgnoreCase("GLOBAL")) 
      {
        for (String key : scope.symbolMap.keySet()) 
        {
          if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.INT) 
          {
            this.result = this.result.concat("var " + key + "\n");
          } 
          else if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.FLOAT) 
          {
            this.result = this.result.concat("var " + key + "\n");
          } 
          else if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.STRING) 
          {
            this.result = this.result.concat("str " + key + " " + ((BuildNode)scope.symbolMap.get(key)).descriptor.content + "\n");
          }
        }
      }
    }
  }
  
  public String createTemp(String temp)
  {
    if (((Map<?,?>)this.TonyDanza.get(this.labelIndicator)).containsKey(temp)) {
      return (String)((Map<?,?>)this.TonyDanza.get(this.labelIndicator)).get(temp);
    }
    return null;
  }
}