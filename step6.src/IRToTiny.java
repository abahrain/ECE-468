import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

public class IRToTiny
{
  private ArrayList<String> IR;
  private String result = "";
  private int registerNumber;
  private SymbolTable table;
  private int paramIndex = this.registerNumber + 1;
  private int labelIndex = 0;
  private String labelIndicator = null;
  private int count = 0;
  private int RPosition = 0;
  protected Map<String, Map<String, String>> TR = new LinkedHashMap();
  protected Map<String, Map<String, Node>> tableMap = new LinkedHashMap();
  protected Map<String, Integer> linkCount = new LinkedHashMap();
  protected Map<String, ArrayList<String>> tempMap = new LinkedHashMap();
  
  public IRToTiny(ArrayList<String> input, SymbolTable table, Map<String, Map<String, Node>> inputMap, Map<String, ArrayList<String>> tempMap, int registerNumber)
  {
    this.IR = input;
    this.table = table;
    this.registerNumber = registerNumber;
    this.paramIndex = (registerNumber + 1);
    this.RPosition = this.paramIndex;
    this.tableMap = inputMap;
    this.tempMap = tempMap;
    for (String key : this.tableMap.keySet())
    {
      Map<String, String> newTR = new LinkedHashMap();
      for (int i = 1; i <= registerNumber; i++) {
        newTR.put("$T" + Integer.toString(i), "r" + Integer.toString(i - 1));
      }
      for (Node each : ((Map)this.tableMap.get(key)).values())
      {
        if ((each.content.contains("$P")) && (!this.TR.containsKey(each.content)))
        {
          newTR.put(each.content, "$" + Integer.toString(Integer.parseInt(each.content.substring(2)) + this.paramIndex));
          
          this.RPosition = (Integer.parseInt(each.content.substring(2)) + this.paramIndex + 1);
        }
        else if (each.content.contains("$L"))
        {
          newTR.put(each.content, "$" + Integer.toString(-Integer.parseInt(each.content.substring(2)) - this.labelIndex));
          this.count += 1;
        }
        newTR.put("$R", "$" + Integer.toString(this.RPosition));
        this.TR.put(key, newTR);
      }
      this.linkCount.put(key, Integer.valueOf(this.count));
      this.count = 0;
      this.RPosition = this.paramIndex;
    }
  }
  
  public String toString()
  {
    this.result = this.result.concat(";IR code\n");
    IRcodeComment();
    this.result = this.result.concat(";tiny code\n");
    initialGlobal();
    initailMain();
    for (int i = 0; i < this.IR.size(); i++)
    {
      String[] elements = ((String)this.IR.get(i)).split(" ");
      if (elements[0].equalsIgnoreCase("STOREI"))
      {
        if ((elements[1].contains("$")) && (elements[2].contains("$")))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
        }
        else if (elements[1].contains("$"))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + elements[2] + "\n");
        }
        else if (elements[2].contains("$"))
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
        if ((elements[1].contains("$")) && (elements[2].contains("$")))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("muli " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[1].contains("$"))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("muli " + elements[2] + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[2].contains("$"))
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
        if ((elements[1].contains("$")) && (elements[2].contains("$")))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("addi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[1].contains("$"))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("addi " + elements[2] + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[2].contains("$"))
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
        if ((elements[1].contains("$")) && (elements[2].contains("$")))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("divi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[1].contains("$"))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("divi " + elements[2] + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[2].contains("$"))
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
        if ((elements[1].contains("$")) && (elements[2].contains("$")))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("subi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[1].contains("$"))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("subi " + elements[2] + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[2].contains("$"))
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
      else if (elements[0].equalsIgnoreCase("WRITEI"))
      {
        if (elements[1].contains("$")) {
          this.result = this.result.concat("sys writei " + createTemp(elements[1]) + "\n");
        } else {
          this.result = this.result.concat("sys writei " + elements[1] + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("WRITES"))
      {
        this.result = this.result.concat("sys writes " + elements[1] + "\n");
      }
      else if (elements[0].equalsIgnoreCase("READI"))
      {
        if (elements[1].contains("$")) {
          this.result = this.result.concat("sys readi " + createTemp(elements[1]) + "\n");
        } else {
          this.result = this.result.concat("sys readi " + elements[1] + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("STOREF"))
      {
        if ((elements[1].contains("$")) && (elements[2].contains("$")))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
        }
        else if (elements[1].contains("$"))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + elements[2] + "\n");
        }
        else if (elements[2].contains("$"))
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
        if ((elements[1].contains("$")) && (elements[2].contains("$")))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("mulr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[1].contains("$"))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("mulr " + elements[2] + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[2].contains("$"))
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
        if ((elements[1].contains("$")) && (elements[2].contains("$")))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("addr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[1].contains("$"))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("addr " + elements[2] + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[2].contains("$"))
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
        if ((elements[1].contains("$")) && (elements[2].contains("$")))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("divr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[1].contains("$"))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("divr " + elements[2] + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[2].contains("$"))
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
        if ((elements[1].contains("$")) && (elements[2].contains("$")))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("subr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[1].contains("$"))
        {
          this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
          this.result = this.result.concat("subr " + elements[2] + " " + createTemp(elements[3]) + "\n");
        }
        else if (elements[2].contains("$"))
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
      else if (elements[0].equalsIgnoreCase("WRITEF"))
      {
        if (elements[1].contains("$")) {
          this.result = this.result.concat("sys writer " + createTemp(elements[1]) + " " + "\n");
        } else {
          this.result = this.result.concat("sys writer " + elements[1] + " " + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("READF"))
      {
        if (elements[1].contains("$")) {
          this.result = this.result.concat("sys readr " + createTemp(elements[1]) + " " + "\n");
        } else {
          this.result = this.result.concat("sys readr " + elements[1] + " " + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("LABEL"))
      {
        this.result = this.result.concat("label " + elements[1] + " " + "\n");
        if (!elements[1].contains("label")) {
          this.labelIndicator = elements[1];
        }
      }
      else if (elements[0].equalsIgnoreCase("JUMP"))
      {
        this.result = this.result.concat("jmp " + elements[1] + " " + "\n");
      }
      else if (elements[0].equalsIgnoreCase("LEI"))
      {
        if ((elements[1].contains("$")) && (elements[2].contains("$")))
        {
          if ((elements[1].contains("$P")) || (elements[1].contains("$L")) || (elements[2].contains("$P")) || (elements[2].contains("$L")))
          {
            this.result = this.result.concat("move " + createTemp(elements[2]) + " " + createTemp(shiftToUnusedR((String)((ArrayList)this.tempMap.get(this.labelIndicator)).get(((ArrayList)this.tempMap.get(this.labelIndicator)).size() - 1))) + "\n");
            this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(shiftToUnusedR((String)((ArrayList)this.tempMap.get(this.labelIndicator)).get(((ArrayList)this.tempMap.get(this.labelIndicator)).size() - 1))) + "\n");
          }
          else
          {
            this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          }
          this.result = this.result.concat("jle " + elements[3] + "\n");
        }
        else if (elements[1].contains("$"))
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jle " + elements[3] + "\n");
        }
        else if (elements[2].contains("$"))
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
        if ((elements[1].contains("$")) && (elements[2].contains("$")))
        {
          if ((elements[1].contains("$P")) || (elements[1].contains("$L")) || (elements[2].contains("$P")) || (elements[2].contains("$L")))
          {
            this.result = this.result.concat("move " + createTemp(elements[2]) + " " + createTemp(shiftToUnusedR((String)((ArrayList)this.tempMap.get(this.labelIndicator)).get(((ArrayList)this.tempMap.get(this.labelIndicator)).size() - 1))) + "\n");
            this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(shiftToUnusedR((String)((ArrayList)this.tempMap.get(this.labelIndicator)).get(((ArrayList)this.tempMap.get(this.labelIndicator)).size() - 1))) + "\n");
          }
          else
          {
            this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          }
          this.result = this.result.concat("jge " + elements[3] + "\n");
        }
        else if (elements[1].contains("$"))
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jge " + elements[3] + "\n");
        }
        else if (elements[2].contains("$"))
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
        if ((elements[1].contains("$")) && (elements[2].contains("$")))
        {
          if ((elements[1].contains("$P")) || (elements[1].contains("$L")) || (elements[2].contains("$P")) || (elements[2].contains("$L")))
          {
            this.result = this.result.concat("move " + createTemp(elements[2]) + " " + createTemp(shiftToUnusedR((String)((ArrayList)this.tempMap.get(this.labelIndicator)).get(((ArrayList)this.tempMap.get(this.labelIndicator)).size() - 1))) + "\n");
            this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(shiftToUnusedR((String)((ArrayList)this.tempMap.get(this.labelIndicator)).get(((ArrayList)this.tempMap.get(this.labelIndicator)).size() - 1))) + "\n");
          }
          else
          {
            this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          }
          this.result = this.result.concat("jne " + elements[3] + "\n");
        }
        else if (elements[1].contains("$"))
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jne " + elements[3] + "\n");
        }
        else if (elements[2].contains("$"))
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
        if ((elements[1].contains("$")) && (elements[2].contains("$")))
        {
          if ((elements[1].contains("$P")) || (elements[1].contains("$L")) || (elements[2].contains("$P")) || (elements[2].contains("$L")))
          {
            this.result = this.result.concat("move " + createTemp(elements[2]) + " " + createTemp(shiftToUnusedR((String)((ArrayList)this.tempMap.get(this.labelIndicator)).get(((ArrayList)this.tempMap.get(this.labelIndicator)).size() - 1))) + "\n");
            this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(shiftToUnusedR((String)((ArrayList)this.tempMap.get(this.labelIndicator)).get(((ArrayList)this.tempMap.get(this.labelIndicator)).size() - 1))) + "\n");
          }
          else
          {
            this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          }
          this.result = this.result.concat("jeq " + elements[3] + "\n");
        }
        else if (elements[1].contains("$"))
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jeq " + elements[3] + "\n");
        }
        else if (elements[2].contains("$"))
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
        if ((elements[1].contains("$")) && (elements[2].contains("$")))
        {
          if ((elements[1].contains("$P")) || (elements[1].contains("$L")) || (elements[2].contains("$P")) || (elements[2].contains("$L")))
          {
            this.result = this.result.concat("move " + createTemp(elements[2]) + " " + createTemp(shiftToUnusedR((String)((ArrayList)this.tempMap.get(this.labelIndicator)).get(((ArrayList)this.tempMap.get(this.labelIndicator)).size() - 1))) + "\n");
            this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(shiftToUnusedR((String)((ArrayList)this.tempMap.get(this.labelIndicator)).get(((ArrayList)this.tempMap.get(this.labelIndicator)).size() - 1))) + "\n");
          }
          else
          {
            this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          }
          this.result = this.result.concat("jgt " + elements[3] + "\n");
        }
        else if (elements[1].contains("$"))
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jgt " + elements[3] + "\n");
        }
        else if (elements[2].contains("$"))
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
        if ((elements[1].contains("$")) && (elements[2].contains("$")))
        {
          if ((elements[1].contains("$P")) || (elements[1].contains("$L")) || (elements[2].contains("$P")) || (elements[2].contains("$L")))
          {
            this.result = this.result.concat("move " + createTemp(elements[2]) + " " + createTemp(shiftToUnusedR((String)((ArrayList)this.tempMap.get(this.labelIndicator)).get(((ArrayList)this.tempMap.get(this.labelIndicator)).size() - 1))) + "\n");
            this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(shiftToUnusedR((String)((ArrayList)this.tempMap.get(this.labelIndicator)).get(((ArrayList)this.tempMap.get(this.labelIndicator)).size() - 1))) + "\n");
          }
          else
          {
            this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          }
          this.result = this.result.concat("jlt " + elements[3] + "\n");
        }
        else if (elements[1].contains("$"))
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jlt " + elements[3] + "\n");
        }
        else if (elements[2].contains("$"))
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
        if ((elements[1].contains("$")) && (elements[2].contains("$")))
        {
          if ((elements[1].contains("$P")) || (elements[1].contains("$L")) || (elements[2].contains("$P")) || (elements[2].contains("$L")))
          {
            this.result = this.result.concat("move " + createTemp(elements[2]) + " " + createTemp(shiftToUnusedR((String)((ArrayList)this.tempMap.get(this.labelIndicator)).get(((ArrayList)this.tempMap.get(this.labelIndicator)).size() - 1))) + "\n");
            this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(shiftToUnusedR((String)((ArrayList)this.tempMap.get(this.labelIndicator)).get(((ArrayList)this.tempMap.get(this.labelIndicator)).size() - 1))) + "\n");
          }
          else
          {
            this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          }
          this.result = this.result.concat("jle " + elements[3] + "\n");
        }
        else if (elements[1].contains("$"))
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jle " + elements[3] + "\n");
        }
        else if (elements[2].contains("$"))
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
        if ((elements[1].contains("$")) && (elements[2].contains("$")))
        {
          if ((elements[1].contains("$P")) || (elements[1].contains("$L")) || (elements[2].contains("$P")) || (elements[2].contains("$L")))
          {
            this.result = this.result.concat("move " + createTemp(elements[2]) + " " + createTemp(shiftToUnusedR((String)((ArrayList)this.tempMap.get(this.labelIndicator)).get(((ArrayList)this.tempMap.get(this.labelIndicator)).size() - 1))) + "\n");
            this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(shiftToUnusedR((String)((ArrayList)this.tempMap.get(this.labelIndicator)).get(((ArrayList)this.tempMap.get(this.labelIndicator)).size() - 1))) + "\n");
          }
          else
          {
            this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          }
          this.result = this.result.concat("jge " + elements[3] + "\n");
        }
        else if (elements[1].contains("$"))
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jge " + elements[3] + "\n");
        }
        else if (elements[2].contains("$"))
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
        if ((elements[1].contains("$")) && (elements[2].contains("$")))
        {
          if ((elements[1].contains("$P")) || (elements[1].contains("$L")) || (elements[2].contains("$P")) || (elements[2].contains("$L")))
          {
            this.result = this.result.concat("move " + createTemp(elements[2]) + " " + createTemp(shiftToUnusedR((String)((ArrayList)this.tempMap.get(this.labelIndicator)).get(((ArrayList)this.tempMap.get(this.labelIndicator)).size() - 1))) + "\n");
            this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(shiftToUnusedR((String)((ArrayList)this.tempMap.get(this.labelIndicator)).get(((ArrayList)this.tempMap.get(this.labelIndicator)).size() - 1))) + "\n");
          }
          else
          {
            this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          }
          this.result = this.result.concat("jne " + elements[3] + "\n");
        }
        else if (elements[1].contains("$"))
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jne " + elements[3] + "\n");
        }
        else if (elements[2].contains("$"))
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
        if ((elements[1].contains("$")) && (elements[2].contains("$")))
        {
          if ((elements[1].contains("$P")) || (elements[1].contains("$L")) || (elements[2].contains("$P")) || (elements[2].contains("$L")))
          {
            this.result = this.result.concat("move " + createTemp(elements[2]) + " " + createTemp(shiftToUnusedR((String)((ArrayList)this.tempMap.get(this.labelIndicator)).get(((ArrayList)this.tempMap.get(this.labelIndicator)).size() - 1))) + "\n");
            this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(shiftToUnusedR((String)((ArrayList)this.tempMap.get(this.labelIndicator)).get(((ArrayList)this.tempMap.get(this.labelIndicator)).size() - 1))) + "\n");
          }
          else
          {
            this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          }
          this.result = this.result.concat("jeq " + elements[3] + "\n");
        }
        else if (elements[1].contains("$"))
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jeq " + elements[3] + "\n");
        }
        else if (elements[2].contains("$"))
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
        if ((elements[1].contains("$")) && (elements[2].contains("$")))
        {
          if ((elements[1].contains("$P")) || (elements[1].contains("$L")) || (elements[2].contains("$P")) || (elements[2].contains("$L")))
          {
            this.result = this.result.concat("move " + createTemp(elements[2]) + " " + createTemp(shiftToUnusedR((String)((ArrayList)this.tempMap.get(this.labelIndicator)).get(((ArrayList)this.tempMap.get(this.labelIndicator)).size() - 1))) + "\n");
            this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(shiftToUnusedR((String)((ArrayList)this.tempMap.get(this.labelIndicator)).get(((ArrayList)this.tempMap.get(this.labelIndicator)).size() - 1))) + "\n");
          }
          else
          {
            this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          }
          this.result = this.result.concat("jgt " + elements[3] + "\n");
        }
        else if (elements[1].contains("$"))
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jgt " + elements[3] + "\n");
        }
        else if (elements[2].contains("$"))
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
      else if (elements[0].equalsIgnoreCase("LTF"))
      {
        if ((elements[1].contains("$")) && (elements[2].contains("$")))
        {
          if ((elements[1].contains("$P")) || (elements[1].contains("$L")) || (elements[2].contains("$P")) || (elements[2].contains("$L")))
          {
            this.result = this.result.concat("move " + createTemp(elements[2]) + " " + createTemp(shiftToUnusedR((String)((ArrayList)this.tempMap.get(this.labelIndicator)).get(((ArrayList)this.tempMap.get(this.labelIndicator)).size() - 1))) + "\n");
            this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(shiftToUnusedR((String)((ArrayList)this.tempMap.get(this.labelIndicator)).get(((ArrayList)this.tempMap.get(this.labelIndicator)).size() - 1))) + "\n");
          }
          else
          {
            this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          }
          this.result = this.result.concat("jlt " + elements[3] + "\n");
        }
        else if (elements[1].contains("$"))
        {
          this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
          this.result = this.result.concat("jlt " + elements[3] + "\n");
        }
        else if (elements[2].contains("$"))
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
      else if (elements[0].equalsIgnoreCase("jsr"))
      {
        for (int k = 0; k < this.registerNumber; k++) {
          this.result = this.result.concat("push r" + Integer.toString(k) + "\n");
        }
        this.result = this.result.concat("jsr " + elements[1] + "\n");
        for (int k = this.registerNumber - 1; k >= 0; k--) {
          this.result = this.result.concat("pop r" + Integer.toString(k) + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("push"))
      {
        if (elements.length == 1) {
          this.result = this.result.concat("push\n");
        } else if (elements[1].contains("$")) {
          this.result = this.result.concat("push " + createTemp(elements[1]) + "\n");
        } else {
          this.result = this.result.concat("push " + elements[1] + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("pop"))
      {
        if (elements.length == 1) {
          this.result = this.result.concat("pop\n");
        } else if (elements[1].contains("$")) {
          this.result = this.result.concat("pop " + createTemp(elements[1]) + "\n");
        } else {
          this.result = this.result.concat("pop " + elements[1] + "\n");
        }
      }
      else if (elements[0].equalsIgnoreCase("link"))
      {
        this.result = this.result.concat("link " + this.linkCount.get(this.labelIndicator) + "\n");
      }
      else if (elements[0].equalsIgnoreCase("RET"))
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
    for (int k = 0; k < this.registerNumber; k++) {
      this.result = this.result.concat("push r" + Integer.toString(k) + "\n");
    }
    this.result = this.result.concat("jsr main\n");
    this.result = this.result.concat("sys halt\n");
  }
  
  public void IRcodeComment()
  {
    for (int i = 0; i < this.IR.size(); i++) {
      this.result = this.result.concat(";" + (String)this.IR.get(i) + "\n");
    }
  }
  
  public void initialGlobal()
  {
    for (Scope scope : this.table.scopeStack.subList(0, this.table.scopeStack.size())) {
      if (scope.type.equalsIgnoreCase("GLOBAL")) {
        for (String key : scope.symbolMap.keySet()) {
          if (((Symbol)scope.symbolMap.get(key)).type == ValueType.INT) {
            this.result = this.result.concat("var " + key + "\n");
          } else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.FLOAT) {
            this.result = this.result.concat("var " + key + "\n");
          } else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.STRING) {
            this.result = this.result.concat("str " + key + " " + ((Symbol)scope.symbolMap.get(key)).descriptor.content + "\n");
          }
        }
      }
    }
  }
  
  public String createTemp(String temp)
  {
    if (((Map)this.TR.get(this.labelIndicator)).containsKey(temp)) {
      return (String)((Map)this.TR.get(this.labelIndicator)).get(temp);
    }
    return null;
  }
}