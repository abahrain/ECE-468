 import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
 
 public class Liveness
 {
   private ArrayList<String> IntermediateRepresentaton;
   private SymbolTable table;
   protected Map<String, Map<String, NodeStructure>> tableMap = new LinkedHashMap<String, Map<String, NodeStructure>>();
   private Map<String, Integer> labelPosition = new LinkedHashMap<String,Integer>();
   protected Stack<CFGNode> CFG = new Stack<CFGNode>();
   private Map<Integer, ArrayList<Integer>> loop = new LinkedHashMap<Integer, ArrayList<Integer>>();
   private Map<Integer, Set<String>> loopRecord = new LinkedHashMap<Integer, Set<String>>();
   private Set<String> globalSet = new HashSet<String>();
   private String result = "";
   
   public Liveness(ArrayList<String> input, Map<String, Map<String, NodeStructure>> inputMap, SymbolTable table)
   {
     this.IntermediateRepresentaton = input;
     this.tableMap = inputMap;
     this.table = table;
     initialGlobal();
     makeCFG();
     
     makeLiveness();
   }
   
   public String toString()
   {
     for (int i = 0; i < this.CFG.size(); i++) {
       this.result = this.result.concat(i + " " + (String)this.IntermediateRepresentaton.get(i) + " " + ((CFGNode)this.CFG.get(i)).livenessVar + "\n");
     }
     return this.result;
   }
   
   private void makeCFG()
   {
     for (int i = 0; i < this.IntermediateRepresentaton.size(); i++)
     {
       String[] elements = ((String)this.IntermediateRepresentaton.get(i)).split(" ");
       ArrayList<String> use = new ArrayList<String>();
       ArrayList<String> define = new ArrayList<String>();
       if ((elements[0].equalsIgnoreCase("STOREI")) || (elements[0].equalsIgnoreCase("STOREF")))
       {
         if ((findVar(elements[1])) && (findVar(elements[2])))
         {
           use.add(elements[2]);
           define.add(elements[1]);
           CFGNode newNode = new CFGNode(define, use, 1);
           this.CFG.add(newNode);
         }
         else if (findVar(elements[2]))
         {
           use.add(elements[2]);
           CFGNode newNode = new CFGNode(define, use, 1);
           this.CFG.add(newNode);
         }
       }
       else if ((elements[0].equalsIgnoreCase("MULTI")) || (elements[0].equalsIgnoreCase("MULTF")))
       {
         use.add(elements[3]);
         define.add(elements[1]);
         define.add(elements[2]);
         CFGNode newNode = new CFGNode(define, use, 1);
         this.CFG.add(newNode);
       }
       else if ((elements[0].equalsIgnoreCase("ADDI")) || (elements[0].equalsIgnoreCase("ADDF")))
       {
         use.add(elements[3]);
         define.add(elements[1]);
         define.add(elements[2]);
         CFGNode newNode = new CFGNode(define, use, 1);
         this.CFG.add(newNode);
       }
       else if ((elements[0].equalsIgnoreCase("DIVI")) || (elements[0].equalsIgnoreCase("DIVF")))
       {
         use.add(elements[3]);
         define.add(elements[1]);
         define.add(elements[2]);
         CFGNode newNode = new CFGNode(define, use, 1);
         this.CFG.add(newNode);
       }
       else if ((elements[0].equalsIgnoreCase("SUBI")) || (elements[0].equalsIgnoreCase("SUBF")))
       {
         use.add(elements[3]);
         define.add(elements[1]);
         define.add(elements[2]);
         CFGNode newNode = new CFGNode(define, use, 1);
         this.CFG.add(newNode);
       }
       else if ((elements[0].equalsIgnoreCase("WRITEI")) || (elements[0].equalsIgnoreCase("WRITEF")))
       {
         define.add(elements[1]);
         CFGNode newNode = new CFGNode(define, use, 1);
         this.CFG.add(newNode);
       }
       else if (elements[0].equalsIgnoreCase("WRITES"))
       {
         CFGNode newNode = new CFGNode(define, use, 1);
         this.CFG.add(newNode);
       }
       else if ((elements[0].equalsIgnoreCase("READI")) || (elements[0].equalsIgnoreCase("READF")))
       {
         use.add(elements[1]);
         CFGNode newNode = new CFGNode(define, use, 1);
         this.CFG.add(newNode);
       }
       else if (elements[0].equalsIgnoreCase("LABEL"))
       {
         use.add(elements[1]);
         CFGNode newNode = new CFGNode(define, use, 3);
         this.CFG.add(newNode);
       }
       else if (elements[0].equalsIgnoreCase("JUMP"))
       {
         use.add(elements[1]);
         CFGNode newNode = new CFGNode(define, use, 4);
         this.CFG.add(newNode);
       }
       else if ((elements[0].equalsIgnoreCase("LEI")) || (elements[0].equalsIgnoreCase("LEF")))
       {
         define.add(elements[1]);
         define.add(elements[2]);
         use.add(elements[3]);
         CFGNode newNode = new CFGNode(define, use, 2);
         this.CFG.add(newNode);
       }
       else if ((elements[0].equalsIgnoreCase("GEI")) || (elements[0].equalsIgnoreCase("GEF")))
       {
         define.add(elements[1]);
         define.add(elements[2]);
         use.add(elements[3]);
         CFGNode newNode = new CFGNode(define, use, 2);
         this.CFG.add(newNode);
       }
       else if ((elements[0].equalsIgnoreCase("NEI")) || (elements[0].equalsIgnoreCase("NEF")))
       {
         define.add(elements[1]);
         define.add(elements[2]);
         use.add(elements[3]);
         CFGNode newNode = new CFGNode(define, use, 2);
         this.CFG.add(newNode);
       }
       else if ((elements[0].equalsIgnoreCase("EQI")) || (elements[0].equalsIgnoreCase("EQF")))
       {
         define.add(elements[1]);
         define.add(elements[2]);
         use.add(elements[3]);
         CFGNode newNode = new CFGNode(define, use, 2);
         this.CFG.add(newNode);
       }
       else if ((elements[0].equalsIgnoreCase("GTI")) || (elements[0].equalsIgnoreCase("GTF")))
       {
         define.add(elements[1]);
         define.add(elements[2]);
         use.add(elements[3]);
         CFGNode newNode = new CFGNode(define, use, 2);
         this.CFG.add(newNode);
       }
       else if ((elements[0].equalsIgnoreCase("LTI")) || (elements[0].equalsIgnoreCase("LTF")))
       {
         define.add(elements[1]);
         define.add(elements[2]);
         use.add(elements[3]);
         CFGNode newNode = new CFGNode(define, use, 2);
         this.CFG.add(newNode);
       }
       else if (elements[0].equalsIgnoreCase("jsr"))
       {
         use.add(elements[1]);
         CFGNode newNode = new CFGNode(define, use, 5);
         this.CFG.add(newNode);
       }
       else if (elements[0].equalsIgnoreCase("push"))
       {
         if (elements.length == 1)
         {
           CFGNode newNode = new CFGNode(define, use, 1);
           this.CFG.add(newNode);
         }
         else
         {
           define.add(elements[1]);
           CFGNode newNode = new CFGNode(define, use, 1);
           this.CFG.add(newNode);
         }
       }
       else if (elements[0].equalsIgnoreCase("pop"))
       {
         if (elements.length == 1)
         {
           CFGNode newNode = new CFGNode(define, use, 1);
           this.CFG.add(newNode);
         }
         else
         {
           use.add(elements[1]);
           CFGNode newNode = new CFGNode(define, use, 1);
           this.CFG.add(newNode);
         }
       }
       else if (elements[0].equalsIgnoreCase("link"))
       {
         CFGNode newNode = new CFGNode(define, use, 6);
         this.CFG.add(newNode);
       }
       else if (elements[0].equalsIgnoreCase("RET"))
       {
         define.add("$R");
         CFGNode newNode = new CFGNode(define, use, 7);
         this.CFG.add(newNode);
       }
     }
     linkCFG();
   }
   
   private void linkCFG()
   {
     for (int i = this.CFG.size() - 1; i >= 0; i--) {
       if (((CFGNode)this.CFG.elementAt(i)).type == 3) {
         this.labelPosition.put((String)((CFGNode)this.CFG.elementAt(i)).use.get(0), Integer.valueOf(i));
       }
     }
     for (int i = 0; i < this.CFG.size(); i++) {
       if ((((CFGNode)this.CFG.elementAt(i)).type == 2) || (((CFGNode)this.CFG.elementAt(i)).type == 4)) {
         if (i < ((Integer)this.labelPosition.get(((CFGNode)this.CFG.elementAt(i)).use.get(0))).intValue())
         {
           if (this.loop.containsKey(this.labelPosition.get(((CFGNode)this.CFG.elementAt(i)).use.get(0))))
           {
             ((ArrayList<Integer>)this.loop.get(this.labelPosition.get(((CFGNode)this.CFG.elementAt(i)).use.get(0)))).add(Integer.valueOf(i));
           }
           else
           {
             ArrayList<Integer> temp = new ArrayList<Integer>();
             temp.add(Integer.valueOf(i));
             this.loop.put((Integer)this.labelPosition.get(((CFGNode)this.CFG.elementAt(i)).use.get(0)), temp);
           }
         }
         else if (this.loop.containsKey(this.labelPosition.get(((CFGNode)this.CFG.elementAt(i)).use.get(0))))
         {
           ((ArrayList<Integer>)this.loop.get(this.labelPosition.get(((CFGNode)this.CFG.elementAt(i)).use.get(0)))).add(Integer.valueOf(i));
         }
         else
         {
           ArrayList<Integer> temp = new ArrayList<Integer>();
           temp.add(Integer.valueOf(i));
           this.loop.put((Integer)this.labelPosition.get(((CFGNode)this.CFG.elementAt(i)).use.get(0)), temp);
         }
       }
     }
   }
   
   private boolean findVar(String var)
   {
     return (((Map<?, ?>)this.tableMap.get("GLOBAL")).containsKey(var)) || (var.contains("$"));
   }
   
   private void initialGlobal()
   {
     for (Boresight scope : this.table.scopeStack.subList(0, this.table.scopeStack.size())) {
       if (scope.type.equalsIgnoreCase("GLOBAL")) {
         for (String key : scope.symbolMap.keySet()) {
           if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.INT) {
             this.globalSet.add(key);
           } else if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.FLOAT) {
             this.globalSet.add(key);
           } else if (((BuildNode)scope.symbolMap.get(key)).type != VariableType.STRING) {
             System.out.println("error@initialGlobal");
           }
         }
       }
     }
   }
   
   private boolean containsAll(Set<String> in1, Set<String> in2)
   {
     if (in2.isEmpty())
     {
       if (in1.isEmpty()) {
         return true;
       }
       return false;
     }
     return in1.containsAll(in2);
   }
   
   private void makeLiveness()
   {
     int allTheSame = 0;
     for (int i = this.CFG.size() - 1; (i >= 0) && (allTheSame == 0); i--)
     {
       this.loopRecord.put(Integer.valueOf(i), new HashSet<String>(((CFGNode)this.CFG.get(i)).livenessVar));
       if (i == this.CFG.size() - 1)
       {
         ((CFGNode)this.CFG.get(i)).livenessVar.addAll(this.globalSet);
       }
       else if (this.loop.containsKey(Integer.valueOf(i)))
       {
         for (int j = 0; j < ((ArrayList<?>)this.loop.get(Integer.valueOf(i))).size(); j++)
         {
           ((CFGNode)this.CFG.get(i)).livenessVar.addAll(((CFGNode)this.CFG.get(i + 1)).livenessVar);
           ((CFGNode)this.CFG.get(i)).livenessVar.removeAll(((CFGNode)this.CFG.get(i + 1)).use);
           ((CFGNode)this.CFG.get(i)).livenessVar.addAll(((CFGNode)this.CFG.get(i + 1)).define);
           ((CFGNode)this.CFG.get(((Integer)((ArrayList<?>)this.loop.get(Integer.valueOf(i))).get(j)).intValue())).livenessVar.addAll(((CFGNode)this.CFG.get(i)).livenessVar);
           ((CFGNode)this.CFG.get(((Integer)((ArrayList<?>)this.loop.get(Integer.valueOf(i))).get(j)).intValue())).livenessVar.removeAll(((CFGNode)this.CFG.get(i)).use);
           ((CFGNode)this.CFG.get(((Integer)((ArrayList<?>)this.loop.get(Integer.valueOf(i))).get(j)).intValue())).livenessVar.addAll(((CFGNode)this.CFG.get(i)).define);
         }
       }
       else if (((CFGNode)this.CFG.get(i)).type == 6)
       {
         ((CFGNode)this.CFG.get(i)).livenessVar.addAll(this.globalSet);
       }
       else
       {
         ((CFGNode)this.CFG.get(i)).livenessVar.addAll(((CFGNode)this.CFG.get(i + 1)).livenessVar);
         ((CFGNode)this.CFG.get(i)).livenessVar.removeAll(((CFGNode)this.CFG.get(i + 1)).use);
         ((CFGNode)this.CFG.get(i)).livenessVar.addAll(((CFGNode)this.CFG.get(i + 1)).define);
       }
       if (i == 0) {
         for (int j = this.CFG.size() - 1; j >= 0; j--) {
           if (containsAll((Set<String>)this.loopRecord.get(Integer.valueOf(j)), ((CFGNode)this.CFG.get(j)).livenessVar))
           {
             allTheSame = 1;
           }
           else
           {
             allTheSame = 0;
             i = this.CFG.size();
             break;
           }
         }
       }
     }
   }
 }
