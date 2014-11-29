 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.LinkedHashMap;
 import java.util.Map;
 import java.util.Set;
 import java.util.Stack;
 
 public class TinyDancer
 {
   private ArrayList<String> Intermediate_Representation;
   private String output = "";
   private Stack<String> result = new Stack();
   private int registerNumber;
   private int temporary_Number;
   private SymbolTable information_table;
   private int paramIndex = this.registerNumber + 1;
   private int labelIndex = 0;
   private String labelIndicator = null;
   private int count = 0;
   private int RightPosition = 0;
   private int IndexPosition = 0;
   private ArrayList<String> holdingCells = new ArrayList();
   protected Map<String, Map<String, String>> TonyDanza = new LinkedHashMap();
   protected Map<String, Map<String, Node>> tableMap = new LinkedHashMap();
   protected Map<String, Integer> linkCount = new LinkedHashMap();
   protected Map<String, ArrayList<String>> tempMap = new LinkedHashMap();
   protected Map<String, String> registerAllocate = new LinkedHashMap();
   private Stack<CFGNode> CFG = new Stack();
   private ArrayList<String> usedTemp = new ArrayList();
   private int loopPosition = 0;
   
   public TinyDancer(ArrayList<String> input, SymbolTable information_table, Map<String, Map<String, Node>> inputMap, Map<String, ArrayList<String>> tempMap, Stack<CFGNode> CFG, int registerNumber, int temporary_Number)
   {
     this.Intermediate_Representation = input;
     this.information_table = information_table;
     this.registerNumber = registerNumber;
     this.temporary_Number = temporary_Number;
     this.paramIndex = (registerNumber + 1);
     this.RightPosition = this.paramIndex;
     this.tableMap = inputMap;
     this.tempMap = tempMap;
     this.CFG = CFG;
     for (int i = 0; i < registerNumber; i++) {
       this.registerAllocate.put("r" + Integer.toString(i), null);
     }
     for (String key : this.tableMap.keySet())
     {
       Map<String, String> newTR = new LinkedHashMap();
       for (Node each : ((Map)this.tableMap.get(key)).values()) {
         if ((each.content.contains("$P")) && (!this.TonyDanza.containsKey(each.content)))
         {
           newTR.put(each.content, "$" + Integer.toString(Integer.parseInt(each.content.substring(2)) + this.paramIndex));
           
           this.RightPosition = (Integer.parseInt(each.content.substring(2)) + this.paramIndex);
         }
         else if (each.content.contains("$L"))
         {
           newTR.put(each.content, "$" + Integer.toString(-Integer.parseInt(each.content.substring(2)) - this.labelIndex));
           this.IndexPosition = (-Integer.parseInt(each.content.substring(2)) - this.labelIndex);
           this.count += 1;
         }
         else
         {
           newTR.put(each.content, each.content);
         }
       }
       newTR.put("$R", "$" + Integer.toString(this.RightPosition + 1));
       for (int i = 1; i <= this.temporary_Number; i++)
       {
         newTR.put("$T" + Integer.toString(i), "$" + Integer.toString(this.IndexPosition - i));
         this.count += 1;
       }
       for (String p : newTR.keySet()) {
         if (p.contains("$P")) {
           newTR.put(p, "$" + (this.RightPosition - Integer.parseInt(p.substring(2)) + 1));
         }
       }
       this.TonyDanza.put(key, newTR);
       this.linkCount.put(key, Integer.valueOf(this.count));
       this.count = 0;
       this.IndexPosition = 0;
       this.RightPosition = this.paramIndex;
     }
   }
   
   public String toString()
   {
     makeIR();
     for (int i = 0; i < this.result.size(); i++) {
       this.output = this.output.concat((String)this.result.get(i));
     }
     return this.output;
   }
   
   public String makeIR()
   {
     this.result.add(";Intermediate_Representation code\n");
     IRcodeComment();
     this.result.add(";tiny code\n");
     initialGlobal();
     initailMain();
     for (this.loopPosition = 0; this.loopPosition < this.Intermediate_Representation.size(); this.loopPosition += 1)
     {
       String[] elements = ((String)this.Intermediate_Representation.get(this.loopPosition)).split(" ");
       if (elements[0].equalsIgnoreCase("STOREI"))
       {
         if ((elements[1].contains("$")) && (elements[2].contains("$")))
         {
           if ((elements[2].contains("$R")) || (elements[2].contains("$P"))) {
             this.result.add("move " + createTemp(elements[1]) + " " + (String)((Map)this.TonyDanza.get(this.labelIndicator)).get(elements[2]) + "\n");
           } else {
             this.result.add("move " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           }
         }
         else if (elements[1].contains("$"))
         {
           this.result.add("move " + createTemp(elements[1]) + " " + elements[2] + "\n");
         }
         else if (elements[2].contains("$"))
         {
           this.result.add("move " + elements[1] + " " + createTemp(elements[2]) + "\n");
         }
         else
         {
           this.result.add("move " + elements[1] + " " + findRegister(elements[2]) + "\n");
           this.result.add("move " + findRegister(elements[2]) + " " + elements[2] + "\n");
         }
       }
       else if (elements[0].equalsIgnoreCase("MULTI"))
       {
         this.holdingCells.add(elements[3]);
         this.holdingCells.add(elements[2]);this.holdingCells.add(elements[1]);
         if ((elements[1].contains("$")) && (elements[2].contains("$")))
         {
           this.result.add("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
           this.result.add("muli " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
         }
         else if (elements[1].contains("$"))
         {
           this.result.add("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
           this.result.add("muli " + elements[2] + " " + createTemp(elements[3]) + "\n");
         }
         else if (elements[2].contains("$"))
         {
           this.result.add("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
           this.result.add("muli " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
         }
         else
         {
           this.result.add("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
           this.result.add("muli " + elements[2] + " " + createTemp(elements[3]) + "\n");
         }
         this.holdingCells.clear();
       }
       else if (elements[0].equalsIgnoreCase("ADDI"))
       {
         this.holdingCells.add(elements[3]);
         this.holdingCells.add(elements[2]);this.holdingCells.add(elements[1]);
         if ((elements[1].contains("$")) && (elements[2].contains("$")))
         {
           this.result.add("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
           this.result.add("addi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
         }
         else if (elements[1].contains("$"))
         {
           this.result.add("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
           this.result.add("addi " + elements[2] + " " + createTemp(elements[3]) + "\n");
         }
         else if (elements[2].contains("$"))
         {
           this.result.add("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
           this.result.add("addi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
         }
         else
         {
           this.result.add("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
           this.result.add("addi " + elements[2] + " " + createTemp(elements[3]) + "\n");
         }
         this.holdingCells.clear();
       }
       else if (elements[0].equalsIgnoreCase("DIVI"))
       {
         this.holdingCells.add(elements[3]);
         this.holdingCells.add(elements[2]);this.holdingCells.add(elements[1]);
         if ((elements[1].contains("$")) && (elements[2].contains("$")))
         {
           this.result.add("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
           this.result.add("divi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
         }
         else if (elements[1].contains("$"))
         {
           this.result.add("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
           this.result.add("divi " + elements[2] + " " + createTemp(elements[3]) + "\n");
         }
         else if (elements[2].contains("$"))
         {
           this.result.add("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
           this.result.add("divi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
         }
         else
         {
           this.result.add("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
           this.result.add("divi " + elements[2] + " " + createTemp(elements[3]) + "\n");
         }
         this.holdingCells.clear();
       }
       else if (elements[0].equalsIgnoreCase("SUBI"))
       {
         this.holdingCells.add(elements[3]);
         this.holdingCells.add(elements[2]);this.holdingCells.add(elements[1]);
         if ((elements[1].contains("$")) && (elements[2].contains("$")))
         {
           this.result.add("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
           this.result.add("subi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
         }
         else if (elements[1].contains("$"))
         {
           this.result.add("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
           this.result.add("subi " + elements[2] + " " + createTemp(elements[3]) + "\n");
         }
         else if (elements[2].contains("$"))
         {
           this.result.add("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
           this.result.add("subi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
         }
         else
         {
           this.result.add("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
           this.result.add("subi " + elements[2] + " " + createTemp(elements[3]) + "\n");
         }
         this.holdingCells.clear();
       }
       else if (elements[0].equalsIgnoreCase("WRITEI"))
       {
         if (elements[1].contains("$")) {
           this.result.add("sys writei " + createTemp(elements[1]) + "\n");
         } else {
           this.result.add("sys writei " + elements[1] + "\n");
         }
       }
       else if (elements[0].equalsIgnoreCase("WRITES"))
       {
         this.result.add("sys writes " + elements[1] + "\n");
       }
       else if (elements[0].equalsIgnoreCase("READI"))
       {
         if (elements[1].contains("$")) {
           this.result.add("sys readi " + createTemp(elements[1]) + "\n");
         } else {
           this.result.add("sys readi " + elements[1] + "\n");
         }
       }
       else if (elements[0].equalsIgnoreCase("STOREF"))
       {
         if ((elements[1].contains("$")) && (elements[2].contains("$")))
         {
           if ((elements[2].contains("$R")) || (elements[2].contains("$P"))) {
             this.result.add("move " + createTemp(elements[1]) + " " + (String)((Map)this.TonyDanza.get(this.labelIndicator)).get(elements[2]) + "\n");
           } else {
             this.result.add("move " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           }
         }
         else if (elements[1].contains("$"))
         {
           this.result.add("move " + createTemp(elements[1]) + " " + elements[2] + "\n");
         }
         else if (elements[2].contains("$"))
         {
           this.result.add("move " + elements[1] + " " + createTemp(elements[2]) + "\n");
         }
         else
         {
           this.result.add("move " + elements[1] + " " + findRegister(elements[2]) + "\n");
           this.result.add("move " + findRegister(elements[2]) + " " + elements[2] + "\n");
         }
       }
       else if (elements[0].equalsIgnoreCase("MULTF"))
       {
         this.holdingCells.add(elements[3]);
         this.holdingCells.add(elements[2]);this.holdingCells.add(elements[1]);
         if ((elements[1].contains("$")) && (elements[2].contains("$")))
         {
           this.result.add("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
           this.result.add("mulr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
         }
         else if (elements[1].contains("$"))
         {
           this.result.add("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
           this.result.add("mulr " + elements[2] + " " + createTemp(elements[3]) + "\n");
         }
         else if (elements[2].contains("$"))
         {
           this.result.add("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
           this.result.add("mulr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
         }
         else
         {
           this.result.add("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
           this.result.add("mulr " + elements[2] + " " + createTemp(elements[3]) + "\n");
         }
         this.holdingCells.clear();
       }
       else if (elements[0].equalsIgnoreCase("ADDF"))
       {
         this.holdingCells.add(elements[3]);
         this.holdingCells.add(elements[2]);this.holdingCells.add(elements[1]);
         if ((elements[1].contains("$")) && (elements[2].contains("$")))
         {
           this.result.add("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
           this.result.add("addr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
         }
         else if (elements[1].contains("$"))
         {
           this.result.add("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
           this.result.add("addr " + elements[2] + " " + createTemp(elements[3]) + "\n");
         }
         else if (elements[2].contains("$"))
         {
           this.result.add("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
           this.result.add("addr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
         }
         else
         {
           this.result.add("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
           this.result.add("addr " + elements[2] + " " + createTemp(elements[3]) + "\n");
         }
         this.holdingCells.clear();
       }
       else if (elements[0].equalsIgnoreCase("DIVF"))
       {
         this.holdingCells.add(elements[3]);
         this.holdingCells.add(elements[2]);this.holdingCells.add(elements[1]);
         if ((elements[1].contains("$")) && (elements[2].contains("$")))
         {
           this.result.add("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
           this.result.add("divr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
         }
         else if (elements[1].contains("$"))
         {
           this.result.add("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
           this.result.add("divr " + elements[2] + " " + createTemp(elements[3]) + "\n");
         }
         else if (elements[2].contains("$"))
         {
           this.result.add("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
           this.result.add("divr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
         }
         else
         {
           this.result.add("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
           this.result.add("divr " + elements[2] + " " + createTemp(elements[3]) + "\n");
         }
         this.holdingCells.clear();
       }
       else if (elements[0].equalsIgnoreCase("SUBF"))
       {
         this.holdingCells.add(elements[3]);
         this.holdingCells.add(elements[2]);this.holdingCells.add(elements[1]);
         if ((elements[1].contains("$")) && (elements[2].contains("$")))
         {
           this.result.add("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
           this.result.add("subr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
         }
         else if (elements[1].contains("$"))
         {
           this.result.add("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
           this.result.add("subr " + elements[2] + " " + createTemp(elements[3]) + "\n");
         }
         else if (elements[2].contains("$"))
         {
           this.result.add("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
           this.result.add("subr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
         }
         else
         {
           this.result.add("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
           this.result.add("subr " + elements[2] + " " + createTemp(elements[3]) + "\n");
         }
         this.holdingCells.clear();
       }
       else if (elements[0].equalsIgnoreCase("WRITEF"))
       {
         if (elements[1].contains("$")) {
           this.result.add("sys writer " + createTemp(elements[1]) + " " + "\n");
         } else {
           this.result.add("sys writer " + elements[1] + " " + "\n");
         }
       }
       else if (elements[0].equalsIgnoreCase("READF"))
       {
         if (elements[1].contains("$")) {
           this.result.add("sys readr " + createTemp(elements[1]) + " " + "\n");
         } else {
           this.result.add("sys readr " + elements[1] + " " + "\n");
         }
       }
       else if (elements[0].equalsIgnoreCase("LABEL"))
       {
         spillRegister();
         this.result.add("label " + elements[1] + " " + "\n");
         if (!elements[1].contains("label")) {
           this.labelIndicator = elements[1];
         }
       }
       else if (elements[0].equalsIgnoreCase("JUMP"))
       {
         spillRegister();
         this.result.add("jmp " + elements[1] + " " + "\n");
       }
       else if (elements[0].equalsIgnoreCase("LEI"))
       {
         if ((elements[1].contains("$")) && (elements[2].contains("$")))
         {
           this.result.add("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jle " + elements[3] + "\n");
         }
         else if (elements[1].contains("$"))
         {
           this.result.add("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
           this.result.add("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jle " + elements[3] + "\n");
         }
         else if (elements[2].contains("$"))
         {
           this.result.add("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jle " + elements[3] + "\n");
         }
         else
         {
           this.result.add("move " + elements[2] + " " + findRegister(elements[2]) + "\n");
           this.result.add("cmpi " + elements[1] + " " + findRegister(elements[2]) + "\n");
           spillRegister();
           this.result.add("jle " + elements[3] + "\n");
         }
       }
       else if (elements[0].equalsIgnoreCase("GEI"))
       {
         if ((elements[1].contains("$")) && (elements[2].contains("$")))
         {
           this.result.add("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jge " + elements[3] + "\n");
         }
         else if (elements[1].contains("$"))
         {
           this.result.add("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
           this.result.add("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jge " + elements[3] + "\n");
         }
         else if (elements[2].contains("$"))
         {
           this.result.add("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jge " + elements[3] + "\n");
         }
         else
         {
           this.result.add("move " + elements[2] + " " + findRegister(elements[2]) + "\n");
           this.result.add("cmpi " + elements[1] + " " + findRegister(elements[2]) + "\n");
           spillRegister();
           this.result.add("jge " + elements[3] + "\n");
         }
       }
       else if (elements[0].equalsIgnoreCase("NEI"))
       {
         if ((elements[1].contains("$")) && (elements[2].contains("$")))
         {
           this.result.add("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jne " + elements[3] + "\n");
         }
         else if (elements[1].contains("$"))
         {
           this.result.add("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
           this.result.add("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jne " + elements[3] + "\n");
         }
         else if (elements[2].contains("$"))
         {
           this.result.add("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jne " + elements[3] + "\n");
         }
         else
         {
           this.result.add("move " + elements[2] + " " + findRegister(elements[2]) + "\n");
           this.result.add("cmpi " + elements[1] + " " + findRegister(elements[2]) + "\n");
           spillRegister();
           this.result.add("jne " + elements[3] + "\n");
         }
       }
       else if (elements[0].equalsIgnoreCase("EQI"))
       {
         if ((elements[1].contains("$")) && (elements[2].contains("$")))
         {
           this.result.add("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jeq " + elements[3] + "\n");
         }
         else if (elements[1].contains("$"))
         {
           this.result.add("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
           this.result.add("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jeq " + elements[3] + "\n");
         }
         else if (elements[2].contains("$"))
         {
           this.result.add("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jeq " + elements[3] + "\n");
         }
         else
         {
           this.result.add("move " + elements[2] + " " + findRegister(elements[2]) + "\n");
           this.result.add("cmpi " + elements[1] + " " + findRegister(elements[2]) + "\n");
           spillRegister();
           this.result.add("jeq " + elements[3] + "\n");
         }
       }
       else if (elements[0].equalsIgnoreCase("GTI"))
       {
         if ((elements[1].contains("$")) && (elements[2].contains("$")))
         {
           this.result.add("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jgt " + elements[3] + "\n");
         }
         else if (elements[1].contains("$"))
         {
           this.result.add("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
           this.result.add("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jgt " + elements[3] + "\n");
         }
         else if (elements[2].contains("$"))
         {
           this.result.add("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jgt " + elements[3] + "\n");
         }
         else
         {
           this.result.add("move " + elements[2] + " " + findRegister(elements[2]) + "\n");
           this.result.add("cmpi " + elements[1] + " " + findRegister(elements[2]) + "\n");
           spillRegister();
           this.result.add("jgt " + elements[3] + "\n");
         }
       }
       else if (elements[0].equalsIgnoreCase("LTI"))
       {
         if ((elements[1].contains("$")) && (elements[2].contains("$")))
         {
           this.result.add("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jlt " + elements[3] + "\n");
         }
         else if (elements[1].contains("$"))
         {
           this.result.add("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
           this.result.add("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jlt " + elements[3] + "\n");
         }
         else if (elements[2].contains("$"))
         {
           this.result.add("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jlt " + elements[3] + "\n");
         }
         else
         {
           this.result.add("move " + elements[2] + " " + findRegister(elements[2]) + "\n");
           this.result.add("cmpi " + elements[1] + " " + findRegister(elements[2]) + "\n");
           spillRegister();
           this.result.add("jlt " + elements[3] + "\n");
         }
       }
       else if (elements[0].equalsIgnoreCase("LEF"))
       {
         if ((elements[1].contains("$")) && (elements[2].contains("$")))
         {
           this.result.add("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jle " + elements[3] + "\n");
         }
         else if (elements[1].contains("$"))
         {
           this.result.add("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
           this.result.add("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jle " + elements[3] + "\n");
         }
         else if (elements[2].contains("$"))
         {
           this.result.add("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jle " + elements[3] + "\n");
         }
         else
         {
           this.result.add("move " + elements[2] + " " + findRegister(elements[2]) + "\n");
           this.result.add("cmpr " + elements[1] + " " + findRegister(elements[2]) + "\n");
           spillRegister();
           this.result.add("jle " + elements[3] + "\n");
         }
       }
       else if (elements[0].equalsIgnoreCase("GEF"))
       {
         if ((elements[1].contains("$")) && (elements[2].contains("$")))
         {
           this.result.add("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jge " + elements[3] + "\n");
         }
         else if (elements[1].contains("$"))
         {
           this.result.add("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
           this.result.add("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jge " + elements[3] + "\n");
         }
         else if (elements[2].contains("$"))
         {
           this.result.add("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jge " + elements[3] + "\n");
         }
         else
         {
           this.result.add("move " + elements[2] + " " + findRegister(elements[2]) + "\n");
           this.result.add("cmpr " + elements[1] + " " + findRegister(elements[2]) + "\n");
           spillRegister();
           this.result.add("jge " + elements[3] + "\n");
         }
       }
       else if (elements[0].equalsIgnoreCase("NEF"))
       {
         if ((elements[1].contains("$")) && (elements[2].contains("$")))
         {
           this.result.add("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jne " + elements[3] + "\n");
         }
         else if (elements[1].contains("$"))
         {
           this.result.add("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
           this.result.add("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jne " + elements[3] + "\n");
         }
         else if (elements[2].contains("$"))
         {
           this.result.add("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jne " + elements[3] + "\n");
         }
         else
         {
           this.result.add("move " + elements[2] + " " + findRegister(elements[2]) + "\n");
           this.result.add("cmpr " + elements[1] + " " + findRegister(elements[2]) + "\n");
           spillRegister();
           this.result.add("jne " + elements[3] + "\n");
         }
       }
       else if (elements[0].equalsIgnoreCase("EQF"))
       {
         if ((elements[1].contains("$")) && (elements[2].contains("$")))
         {
           this.result.add("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jeq " + elements[3] + "\n");
         }
         else if (elements[1].contains("$"))
         {
           this.result.add("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
           this.result.add("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jeq " + elements[3] + "\n");
         }
         else if (elements[2].contains("$"))
         {
           this.result.add("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jeq " + elements[3] + "\n");
         }
         else
         {
           this.result.add("move " + elements[2] + " " + findRegister(elements[2]) + "\n");
           this.result.add("cmpr " + elements[1] + " " + findRegister(elements[2]) + "\n");
           spillRegister();
           this.result.add("jeq " + elements[3] + "\n");
         }
       }
       else if (elements[0].equalsIgnoreCase("GTF"))
       {
         if ((elements[1].contains("$")) && (elements[2].contains("$")))
         {
           this.result.add("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jgt " + elements[3] + "\n");
         }
         else if (elements[1].contains("$"))
         {
           this.result.add("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
           this.result.add("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jgt " + elements[3] + "\n");
         }
         else if (elements[2].contains("$"))
         {
           this.result.add("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jgt " + elements[3] + "\n");
         }
         else
         {
           this.result.add("move " + elements[2] + " " + findRegister(elements[2]) + "\n");
           this.result.add("cmpr " + elements[1] + " " + findRegister(elements[2]) + "\n");
           spillRegister();
           this.result.add("jgt " + elements[3] + "\n");
         }
       }
       else if (elements[0].equalsIgnoreCase("LTF"))
       {
         if ((elements[1].contains("$")) && (elements[2].contains("$")))
         {
           this.result.add("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jlt " + elements[3] + "\n");
         }
         else if (elements[1].contains("$"))
         {
           this.result.add("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
           this.result.add("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jlt " + elements[3] + "\n");
         }
         else if (elements[2].contains("$"))
         {
           this.result.add("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
           spillRegister();
           this.result.add("jlt " + elements[3] + "\n");
         }
         else
         {
           this.result.add("move " + elements[2] + " " + findRegister(elements[2]) + "\n");
           this.result.add("cmpr " + elements[1] + " " + findRegister(elements[2]) + "\n");
           spillRegister();
           this.result.add("jlt " + elements[3] + "\n");
         }
       }
       else if (elements[0].equalsIgnoreCase("jsr"))
       {
         for (int k = 0; k < this.registerNumber; k++) {
           this.result.add("push r" + Integer.toString(k) + "\n");
         }
         this.result.add("jsr " + elements[1] + "\n");
         for (int k = this.registerNumber - 1; k >= 0; k--) {
           this.result.add("pop r" + Integer.toString(k) + "\n");
         }
       }
       else if (elements[0].equalsIgnoreCase("push"))
       {
         if (elements.length == 1) {
           this.result.add("push\n");
         } else if (elements[1].contains("$")) {
           this.result.add("push " + createTemp(elements[1]) + "\n");
         } else {
           this.result.add("push " + elements[1] + "\n");
         }
       }
       else if (elements[0].equalsIgnoreCase("pop"))
       {
         if (elements.length == 1) {
           this.result.add("pop\n");
         } else if (elements[1].contains("$")) {
           this.result.add("pop " + createTemp(elements[1]) + "\n");
         } else {
           this.result.add("pop " + elements[1] + "\n");
         }
       }
       else if (elements[0].equalsIgnoreCase("link"))
       {
         this.result.add("link " + this.linkCount.get(this.labelIndicator) + "\n");
       }
       else if (elements[0].equalsIgnoreCase("RET"))
       {
         spillRegister();
         this.result.add("unlnk\n");
         this.result.add("ret\n");
       }
       freeRegister();
     }
     this.result.add("end");
     return this.result.toString();
   }
   
   private void spillRegister()
   {
     for (int k = 0; k < this.registerAllocate.size(); k++) {
       if (this.registerAllocate.get("r" + Integer.toString(k)) != null)
       {
         this.result.add("move r" + Integer.toString(k) + " " + (String)((Map)this.TonyDanza.get(this.labelIndicator)).get(this.registerAllocate.get(new StringBuilder("r").append(Integer.toString(k)).toString())) + "\n");
         this.registerAllocate.put("r" + Integer.toString(k), null);
       }
     }
   }
   
   public void initailMain()
   {
     this.result.add("push\n");
     for (int k = 0; k < this.registerNumber; k++) {
       this.result.add("push r" + Integer.toString(k) + "\n");
     }
     this.result.add("jsr main\n");
     this.result.add("sys halt\n");
   }
   
   public void IRcodeComment()
   {
     for (int i = 0; i < this.Intermediate_Representation.size(); i++) {
       this.result.add(";" + (String)this.Intermediate_Representation.get(i) + "\n");
     }
   }
   
   public void initialGlobal()
   {
     for (Boresight scope : this.information_table.scopeStack.subList(0, this.information_table.scopeStack.size())) {
       if (scope.type.equalsIgnoreCase("GLOBAL")) {
         for (String key : scope.symbolMap.keySet()) {
           if (((Symbol)scope.symbolMap.get(key)).type == ValueType.INT) {
             this.result.add("var " + key + "\n");
           } else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.FLOAT) {
             this.result.add("var " + key + "\n");
           } else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.STRING) {
             this.result.add("str " + key + " " + ((Symbol)scope.symbolMap.get(key)).descriptor.content + "\n");
           } else {
             System.out.println("error@initialGlobal");
           }
         }
       }
     }
   }
   
   private boolean registerFull()
   {
     for (int i = 0; i < this.registerAllocate.size(); i++) {
       if (this.registerAllocate.get("r" + Integer.toString(i)) == null) {
         return false;
       }
     }
     return true;
   }
   
   private void freeRegister()
   {
     for (int i = 0; i < this.registerAllocate.size(); i++) {
       if ((this.registerAllocate.get("r" + Integer.toString(i)) != null) && 
         (!((CFGNode)this.CFG.get(this.loopPosition)).livenessVar.contains(this.registerAllocate.get("r" + Integer.toString(i))))) {
         this.registerAllocate.put("r" + Integer.toString(i), null);
       }
     }
   }
   
   private String findRegister(String in)
   {
     for (int i = 0; i < this.registerAllocate.size(); i++) {
       if (this.registerAllocate.get("r" + Integer.toString(i)) == null) {
         return "r" + Integer.toString(i);
       }
     }
     return null;
   }
   
   public String createTemp(String temp)
   {
     if (((Map)this.TonyDanza.get(this.labelIndicator)).containsKey(temp))
     {
       if (this.registerAllocate.containsValue(temp))
       {
         for (int i = 0; i < this.registerAllocate.size(); i++) {
           if ((this.registerAllocate.get("r" + Integer.toString(i)) != null) && 
             (((String)this.registerAllocate.get("r" + Integer.toString(i))).equals(temp)))
           {
             if (this.usedTemp.contains(temp))
             {
               this.result.add("move " + (String)((Map)this.TonyDanza.get(this.labelIndicator)).get(temp) + " " + "r" + Integer.toString(i) + "\n");
               this.usedTemp.remove(temp);
             }
             return "r" + Integer.toString(i);
           }
         }
       }
       else
       {
         if (registerFull())
         {
           String tempR = null;
           if (this.holdingCells != null) {
             for (int i = 0; i < this.registerNumber; i++)
             {
               tempR = "r" + Integer.toString(i);
               if (!this.holdingCells.contains(this.registerAllocate.get(tempR))) {
                 break;
               }
             }
           } else {
             tempR = "r1";
           }
           if (((CFGNode)this.CFG.get(this.loopPosition)).livenessVar.contains(this.registerAllocate.get(tempR)))
           {
             this.result.add("move " + tempR + " " + (String)((Map)this.TonyDanza.get(this.labelIndicator)).get(this.registerAllocate.get(tempR)) + "\n");
             this.usedTemp.add((String)this.registerAllocate.get(tempR));
             this.registerAllocate.put(tempR, temp);
             if (this.usedTemp.contains(temp))
             {
               this.result.add("move " + (String)((Map)this.TonyDanza.get(this.labelIndicator)).get(temp) + " " + tempR + "\n");
               this.usedTemp.remove(temp);
             }
             else
             {
               this.result.add("move " + (String)((Map)this.TonyDanza.get(this.labelIndicator)).get(temp) + " " + tempR + "\n");
             }
             return tempR;
           }
           this.registerAllocate.put(tempR, temp);
           if (this.usedTemp.contains(temp))
           {
             this.result.add("move " + (String)((Map)this.TonyDanza.get(this.labelIndicator)).get(temp) + " " + tempR + "\n");
             this.usedTemp.remove(temp);
           }
           return tempR;
         }
         for (int i = 0; i < this.registerAllocate.size(); i++) {
           if (this.registerAllocate.get("r" + Integer.toString(i)) == null)
           {
             this.registerAllocate.put("r" + Integer.toString(i), temp);
             if (temp.contains("P")) {
               this.result.add("move " + (String)((Map)this.TonyDanza.get(this.labelIndicator)).get(temp) + " " + "r" + Integer.toString(i) + "\n");
             }
             if (this.usedTemp.contains(temp))
             {
               this.result.add("move " + (String)((Map)this.TonyDanza.get(this.labelIndicator)).get(temp) + " " + "r" + Integer.toString(i) + "\n");
               this.usedTemp.remove(temp);
               return "r" + Integer.toString(i);
             }
             if (temp.contains("L")) {
               this.result.add("move " + (String)((Map)this.TonyDanza.get(this.labelIndicator)).get(temp) + " " + "r" + Integer.toString(i) + "\n");
             }
             if (temp.contains("T")) {
               this.result.add("move " + (String)((Map)this.TonyDanza.get(this.labelIndicator)).get(temp) + " " + "r" + Integer.toString(i) + "\n");
             }
             return "r" + Integer.toString(i);
           }
         }
       }
     }
     else
     {
       if (((Map)this.TonyDanza.get("GLOBAL")).containsKey(temp)) {
         return (String)((Map)this.TonyDanza.get("GLOBAL")).get(temp);
       }
       System.out.println("register not enough!! " + temp);
       return null;
     }
     System.out.println("register not enough!! " + temp);
     return null;
   }
 }