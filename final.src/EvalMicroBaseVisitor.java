 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.LinkedHashMap;
 import java.util.Map;
 import java.util.Stack;
 import org.antlr.v4.runtime.misc.NotNull;
 import org.antlr.v4.runtime.tree.TerminalNode;
 
 public class EvalMicroBaseVisitor
   extends MicroBaseVisitor<Node>
 {
   public ArrayList<String> outputList = new ArrayList();
   public ArrayList<String> pushStack = new ArrayList();
   private Stack<String> labelStack = new Stack();
   private Stack<String> continueStack = new Stack();
   private Stack<String> breakStack = new Stack();
   private Stack<String> functionStack = new Stack();
   private Stack<Integer> functionPopNumberStack = new Stack();
   private Stack<ArrayList<Node>> factorStack = new Stack();
   private Stack<ArrayList<Node>> exprStack = new Stack();
   protected Map<String, Map<String, Node>> tableMap = new LinkedHashMap();
   protected Map<String, Integer> functionMap = new LinkedHashMap();
   protected Map<String, ArrayList<String>> tempMap = new LinkedHashMap();
   private int tempIndex = 0;
   public int finalTempIndex = 0;
   private int varIndex = 0;
   private int paramIndex = 0;
   private int labelIndex = 0;
   private int countPUSH = 0;
   private String functionRecord = "GLOBAL";
   
   public EvalMicroBaseVisitor(SymbolTable table, Map<String, Integer> functionMap)
   {
     this.functionMap = functionMap;
     for (Scope scope : table.scopeStack.subList(0, table.scopeStack.size()))
     {
       Map<String, Node> varMap = new LinkedHashMap();
       if (scope.type.equalsIgnoreCase("GLOBAL")) {
         for (String key : scope.symbolMap.keySet()) {
           if (((Symbol)scope.symbolMap.get(key)).type == ValueType.INT) {
             varMap.put(key, new Node(key, 1));
           } else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.FLOAT) {
             varMap.put(key, new Node(key, 2));
           } else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.STRING) {
             varMap.put(key, new Node(key, 5));
           } else {
             System.out.println("error@constructor");
           }
         }
       } else {
         for (String key : scope.symbolMap.keySet()) {
           if (((Symbol)scope.symbolMap.get(key)).descriptor.isParameter)
           {
             if (((Symbol)scope.symbolMap.get(key)).type == ValueType.INT) {
               varMap.put(key, new Node(createVar(true), 1));
             } else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.FLOAT) {
               varMap.put(key, new Node(createVar(true), 2));
             } else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.STRING) {
               varMap.put(key, new Node(key, 5));
             } else {
               System.out.println("error@constructor");
             }
           }
           else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.INT) {
             varMap.put(key, new Node(createVar(false), 1));
           } else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.FLOAT) {
             varMap.put(key, new Node(createVar(false), 2));
           } else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.STRING) {
             varMap.put(key, new Node(key, 5));
           } else {
             System.out.println("error@constructor");
           }
         }
       }
       this.tableMap.put(scope.type, varMap);
       this.varIndex = 0;
       this.paramIndex = 0;
     }
   }
   
   private String createVar(boolean isParameter)
   {
     if (isParameter)
     {
       this.paramIndex += 1;
       return "$P" + Integer.toString(this.paramIndex);
     }
     this.varIndex += 1;
     return "$L" + Integer.toString(this.varIndex);
   }
   
   public Node findIdNode(String id, String scopeName)
   {
     if (((Map)this.tableMap.get(scopeName)).get(id) == null)
     {
       if (((Map)this.tableMap.get("GLOBAL")).get(id) == null)
       {
         System.out.println("can't find assign variable, error@findIdNode");
         return null;
       }
       return (Node)((Map)this.tableMap.get("GLOBAL")).get(id);
     }
     return (Node)((Map)this.tableMap.get(scopeName)).get(id);
   }
   
   public Node visitPrimary(@NotNull MicroParser.PrimaryContext ctx)
   {
     if (ctx.expr() != null) {
       return (Node)visit(ctx.expr());
     }
     if (ctx.id() != null) {
       return findIdNode(ctx.id().getText(), this.functionRecord);
     }
     if (ctx.INTLITERAL() != null)
     {
       Node newNode = new Node(createTemp(), 1);
       this.outputList.add("STOREI " + ctx.INTLITERAL().getText() + " " + newNode.content);
       
       return newNode;
     }
     Node newNode = new Node(createTemp(), 2);
     this.outputList.add("STOREF " + ctx.FLOATLITERAL().getText() + " " + newNode.content);
     
     return newNode;
   }
   
   public Node visitFunc_decl(@NotNull MicroParser.Func_declContext ctx)
   {
     ArrayList<String> newTempList = new ArrayList();
     
     this.outputList.add("LABEL " + ctx.id().getText());
     this.functionRecord = ctx.id().getText();
     this.tempMap.put(this.functionRecord, newTempList);
     this.outputList.add("LINK ");
     visitChildren(ctx);
     this.tempIndex = 0;
     if (ctx.any_type().getText().equals("VOID")) {
       this.outputList.add("RET");
     }
     return null;
   }
   
   public Node visitCall_expr(@NotNull MicroParser.Call_exprContext ctx)
   {
     this.functionPopNumberStack.push(Integer.valueOf(this.countPUSH));
     this.countPUSH = 0;
     if (ctx.expr_list() != null) {
       visit(ctx.expr_list());
     }
     this.outputList.add("PUSH ");
     String[] reverseList = new String[this.countPUSH];
     for (int i = 0; i < this.countPUSH; i++) {
       reverseList[i] = ((String)this.functionStack.pop());
     }
     for (int i = this.countPUSH - 1; i >= 0; i--) {
       this.outputList.add("PUSH " + reverseList[i]);
     }
     this.outputList.add("JSR " + ctx.id().getText());
     for (int i = 0; i < this.countPUSH; i++) {
       this.outputList.add("POP ");
     }
     this.countPUSH = ((Integer)this.functionPopNumberStack.pop()).intValue();
     
     Node newNode = new Node(createTemp(), ((Integer)this.functionMap.get(this.functionRecord)).intValue());
     this.outputList.add("POP " + newNode.content);
     return newNode;
   }
   
   public Node visitExpr_list(@NotNull MicroParser.Expr_listContext ctx)
   {
     Node exprNode = (Node)visit(ctx.expr());
     
 
     this.functionStack.push(exprNode.content);
     this.countPUSH += 1;
     if (!"".equals(ctx.expr_list_tail().getText())) {
       visit(ctx.expr_list_tail());
     }
     return null;
   }
   
   public Node visitExpr_list_tail(@NotNull MicroParser.Expr_list_tailContext ctx)
   {
     Node exprNode = (Node)visit(ctx.expr());
     
     this.functionStack.push(exprNode.content);
     this.countPUSH += 1;
     if (!"".equals(ctx.expr_list_tail().getText())) {
       visit(ctx.expr_list_tail());
     }
     return null;
   }
   
   public Node visitReturn_stmt(@NotNull MicroParser.Return_stmtContext ctx)
   {
     Node exprNode = (Node)visit(ctx.expr());
     Node tempNode = new Node(createTemp(), exprNode.type);
     if (exprNode.type == 1)
     {
       this.outputList.add("STOREI " + exprNode + " " + tempNode);
       this.outputList.add("STOREI " + tempNode + " $R");
     }
     else
     {
       this.outputList.add("STOREF " + exprNode + " " + tempNode);
       this.outputList.add("STOREF " + tempNode + " $R");
     }
     this.outputList.add("RET");
     return null;
   }
   
   public Node visitParam_decl(@NotNull MicroParser.Param_declContext ctx)
   {
     Node newNode;
     Node newNode;
     if (ctx.var_type().getText().equalsIgnoreCase("INT")) {
       newNode = new Node(ctx.id().getText(), 1);
     } else {
       newNode = new Node(ctx.id().getText(), 2);
     }
     return null;
   }
   
   public Node visitExpr(@NotNull MicroParser.ExprContext ctx)
   {
     if (!"".equals(ctx.expr_prefix().getText()))
     {
       ArrayList<Node> exprList = new ArrayList();
       this.exprStack.push(exprList);
       Node exprNode = (Node)visit(ctx.expr_prefix());
       Node factorNode = (Node)visit(ctx.factor());
       ((ArrayList)this.exprStack.peek()).add(factorNode);
       Node resolveNode = resolve((ArrayList)this.exprStack.pop());
       
       return resolveNode;
     }
     Node factorNode = (Node)visit(ctx.factor());
     return factorNode;
   }
   
   public Node visitExpr_prefix(@NotNull MicroParser.Expr_prefixContext ctx)
   {
     if (!"".equals(ctx.expr_prefix().getText())) {
       visit(ctx.expr_prefix());
     }
     Node opNode = new Node(ctx.addop().getText(), 3);
     Node factorNode = (Node)visit(ctx.factor());
     ((ArrayList)this.exprStack.peek()).add(factorNode);
     ((ArrayList)this.exprStack.peek()).add(opNode);
     
     return null;
   }
   
   public Node visitFactor(@NotNull MicroParser.FactorContext ctx)
   {
     if (!"".equals(ctx.factor_prefix().getText()))
     {
       ArrayList<Node> factorList = new ArrayList();
       this.factorStack.push(factorList);
       Node exprNode = (Node)visit(ctx.factor_prefix());
       Node postfixNode = (Node)visit(ctx.postfix_expr());
       ((ArrayList)this.factorStack.peek()).add(postfixNode);
       Node resolveNode = resolve((ArrayList)this.factorStack.pop());
       
       return resolveNode;
     }
     return (Node)visit(ctx.postfix_expr());
   }
   
   public Node visitFactor_prefix(@NotNull MicroParser.Factor_prefixContext ctx)
   {
     if (!"".equals(ctx.factor_prefix().getText())) {
       visit(ctx.factor_prefix());
     }
     Node opNode = new Node(ctx.mulop().getText(), 3);
     Node postfixNode = (Node)visit(ctx.postfix_expr());
     ((ArrayList)this.factorStack.peek()).add(postfixNode);
     ((ArrayList)this.factorStack.peek()).add(opNode);
     
     return null;
   }
   
   public Node visitWrite_stmt(@NotNull MicroParser.Write_stmtContext ctx)
   {
     String[] idArray = ctx.id_list().getText().split(",");
     for (int i = 0; i < idArray.length; i++)
     {
       Node newNode = findIdNode(idArray[i], this.functionRecord);
       if (newNode.type == 1) {
         this.outputList.add("WRITEI " + newNode.content);
       } else if (newNode.type == 5) {
         this.outputList.add("WRITES " + newNode.content);
       } else {
         this.outputList.add("WRITEF " + newNode.content);
       }
     }
     return null;
   }
   
   public Node visitRead_stmt(@NotNull MicroParser.Read_stmtContext ctx)
   {
     String[] idArray = ctx.id_list().getText().split(",");
     for (int i = 0; i < idArray.length; i++)
     {
       Node newNode = findIdNode(idArray[i], this.functionRecord);
       if (newNode.type == 1) {
         this.outputList.add("READI " + newNode.content);
       } else {
         this.outputList.add("READF " + newNode.content);
       }
     }
     return null;
   }
   
   public Node visitAssign_expr(@NotNull MicroParser.Assign_exprContext ctx)
   {
     Node exprNode = (Node)visit(ctx.expr());
     Node newNode = findIdNode(ctx.id().getText(), this.functionRecord);
     if (newNode.type == 1) {
       this.outputList.add("STOREI " + exprNode.content + " " + newNode.content);
     } else {
       this.outputList.add("STOREF " + exprNode.content + " " + newNode.content);
     }
     return null;
   }
   
   public Node visitWhile_stmt(@NotNull MicroParser.While_stmtContext ctx)
   {
     String newLabel = createLabel();
     this.outputList.add("LABEL " + newLabel);
     String newLabel2 = createLabel();
     this.labelStack.add(newLabel2);
     Node comp = (Node)visit(ctx.cond());
     
     this.continueStack.push("JUMP " + newLabel);
     this.breakStack.push("JUMP " + newLabel2);
     this.outputList.add(comp.content + " " + newLabel2);
     visit(ctx.aug_stmt_list());
     this.outputList.add("JUMP " + newLabel);
     this.outputList.add("LABEL " + newLabel2);
     this.breakStack.pop();
     this.continueStack.pop();
     return null;
   }
   
   public Node visitAug_if_stmt(@NotNull MicroParser.Aug_if_stmtContext ctx)
   {
     if (!"".equals(ctx.aug_else_part().getText()))
     {
       Node comp = (Node)visit(ctx.cond());
       if (comp.content.equalsIgnoreCase("TRUE"))
       {
         visit(ctx.aug_stmt_list());
       }
       else if (comp.content.equalsIgnoreCase("FALSE"))
       {
         String newLabel2 = createLabel();
         this.labelStack.push(newLabel2);
         visit(ctx.aug_else_part());
         this.outputList.add("LABEL " + newLabel2);
       }
       else
       {
         String newLabel = createLabel();
         this.outputList.add(comp.content + " " + newLabel);
         visit(ctx.aug_stmt_list());
         String newLabel2 = createLabel();
         this.labelStack.push(newLabel2);
         this.outputList.add("JUMP " + newLabel2);
         this.outputList.add("LABEL " + newLabel);
         visit(ctx.aug_else_part());
         this.outputList.add("LABEL " + newLabel2);
       }
     }
     else
     {
       Node comp = (Node)visit(ctx.cond());
       if (comp.content.equalsIgnoreCase("TRUE"))
       {
         visit(ctx.aug_stmt_list());
       }
       else if (!comp.content.equalsIgnoreCase("FALSE"))
       {
         String newLabel2 = createLabel();
         this.outputList.add(comp.content + " " + newLabel2);
         visit(ctx.aug_stmt_list());
         this.outputList.add("LABEL " + newLabel2);
       }
     }
     return null;
   }
   
   public Node visitAug_else_part(@NotNull MicroParser.Aug_else_partContext ctx)
   {
     visit(ctx.aug_stmt_list());
     return null;
   }
   
   public Node visitIf_stmt(@NotNull MicroParser.If_stmtContext ctx)
   {
     if (!"".equals(ctx.else_part().getText()))
     {
       Node comp = (Node)visit(ctx.cond());
       if (comp.content.equalsIgnoreCase("TRUE"))
       {
         visit(ctx.stmt_list());
       }
       else if (comp.content.equalsIgnoreCase("FALSE"))
       {
         String newLabel2 = createLabel();
         this.labelStack.push(newLabel2);
         visit(ctx.else_part());
         this.outputList.add("LABEL " + newLabel2);
       }
       else
       {
         String newLabel = createLabel();
         this.outputList.add(comp.content + " " + newLabel);
         visit(ctx.stmt_list());
         String newLabel2 = createLabel();
         this.labelStack.push(newLabel2);
         this.outputList.add("JUMP " + newLabel2);
         this.outputList.add("LABEL " + newLabel);
         visit(ctx.else_part());
         this.outputList.add("LABEL " + newLabel2);
       }
     }
     else
     {
       Node comp = (Node)visit(ctx.cond());
       if (comp.content.equalsIgnoreCase("TRUE"))
       {
         visit(ctx.stmt_list());
       }
       else if (!comp.content.equalsIgnoreCase("FALSE"))
       {
         String newLabel2 = createLabel();
         this.outputList.add(comp.content + " " + newLabel2);
         visit(ctx.stmt_list());
         this.outputList.add("LABEL " + newLabel2);
       }
     }
     return null;
   }
   
   public Node visitElse_part(@NotNull MicroParser.Else_partContext ctx)
   {
     visit(ctx.stmt_list());
     return null;
   }
   
   public Node visitAug_break(@NotNull MicroParser.Aug_breakContext ctx)
   {
     this.outputList.add((String)this.breakStack.peek());
     return null;
   }
   
   public Node visitAug_continue(@NotNull MicroParser.Aug_continueContext ctx)
   {
     this.outputList.add((String)this.continueStack.peek());
     this.outputList.add((String)this.breakStack.peek());
     return null;
   }
   
   public Node visitCond(@NotNull MicroParser.CondContext ctx)
   {
     Node op1 = (Node)visit(ctx.cond_expr());
     
     visit(ctx.compop());
     Node op2 = (Node)visit(ctx.cond_expr1());
     return resolveComp(op1, op2, ctx.compop().getText());
   }
   
   public String resolveDoComp(String input)
   {
     if (input.contains("GEI")) {
       return input.replace("GEI", "LTI");
     }
     if (input.contains("LEI")) {
       return input.replace("LEI", "GTI");
     }
     if (input.contains("NEI")) {
       return input.replace("NEI", "EQI");
     }
     if (input.contains("EQI")) {
       return input.replace("EQI", "NEI");
     }
     if (input.contains("GTI")) {
       return input.replace("GTI", "LEI");
     }
     if (input.contains("LTI")) {
       return input.replace("LTI", "GEI");
     }
     if (input.contains("GEF")) {
       return input.replace("GEF", "LTF");
     }
     if (input.contains("LEF")) {
       return input.replace("LEF", "GTF");
     }
     if (input.contains("NEF")) {
       return input.replace("NEF", "EQF");
     }
     if (input.contains("EQF")) {
       return input.replace("EQF", "NEF");
     }
     if (input.contains("GTF")) {
       return input.replace("GTF", "LEF");
     }
     if (input.contains("LTF")) {
       return input.replace("LTF", "GEF");
     }
     System.out.println("ERROR @ resolveDoComp");
     return null;
   }
   
   public Node resolveComp(Node op1, Node op2, String op)
   {
     if ((op1.type == 1) && (op2.type == 1))
     {
       if (op.equalsIgnoreCase("<")) {
         return new Node("GEI " + op1.content + " " + op2.content, 4);
       }
       if (op.equalsIgnoreCase(">")) {
         return new Node("LEI " + op1.content + " " + op2.content, 4);
       }
       if (op.equalsIgnoreCase("=")) {
         return new Node("NEI " + op1.content + " " + op2.content, 4);
       }
       if (op.equalsIgnoreCase("!=")) {
         return new Node("EQI " + op1.content + " " + op2.content, 4);
       }
       if (op.equalsIgnoreCase("<=")) {
         return new Node("GTI " + op1.content + " " + op2.content, 4);
       }
       if (op.equalsIgnoreCase(">=")) {
         return new Node("LTI " + op1.content + " " + op2.content, 4);
       }
       System.out.println("ERROR @ resolveComp");
       return null;
     }
     if (op.equalsIgnoreCase("<")) {
       return new Node("GEF " + op1.content + " " + op2.content, 4);
     }
     if (op.equalsIgnoreCase(">")) {
       return new Node("LEF " + op1.content + " " + op2.content, 4);
     }
     if (op.equalsIgnoreCase("=")) {
       return new Node("NEF " + op1.content + " " + op2.content, 4);
     }
     if (op.equalsIgnoreCase("!=")) {
       return new Node("EQF " + op1.content + " " + op2.content, 4);
     }
     if (op.equalsIgnoreCase("<=")) {
       return new Node("GTF " + op1.content + " " + op2.content, 4);
     }
     if (op.equalsIgnoreCase(">=")) {
       return new Node("LTF " + op1.content + " " + op2.content, 4);
     }
     System.out.println("ERROR @ resolveComp");
     return null;
   }
   
   public String createLabel()
   {
     this.labelIndex += 1;
     return "label" + Integer.toString(this.labelIndex);
   }
   
   public String createTemp()
   {
     this.tempIndex += 1;
     if (this.tempIndex > this.finalTempIndex) {
       this.finalTempIndex = this.tempIndex;
     }
     ((ArrayList)this.tempMap.get(this.functionRecord)).add("$T" + Integer.toString(this.tempIndex));
     return "$T" + Integer.toString(this.tempIndex);
   }
   
   public Node resolve(ArrayList<Node> input)
   {
     while (input.size() >= 3)
     {
       Node op1 = (Node)input.get(0);
       Node op = (Node)input.get(1);
       Node op2 = (Node)input.get(2);
       
       Node newNode = new Node(createTemp(), op1.type);
       if (op.content.equalsIgnoreCase("+"))
       {
         if (op1.type == 1)
         {
           String output = "ADDI " + op1.content + " " + op2.content + " " + newNode.content;
           this.outputList.add(output);
         }
         else
         {
           String output = "ADDF " + op1.content + " " + op2.content + " " + newNode.content;
           this.outputList.add(output);
         }
       }
       else if (op.content.equalsIgnoreCase("-"))
       {
         if (op1.type == 1)
         {
           String output = "SUBI " + op1.content + " " + op2.content + " " + newNode.content;
           this.outputList.add(output);
         }
         else
         {
           String output = "SUBF " + op1.content + " " + op2.content + " " + newNode.content;
           this.outputList.add(output);
         }
       }
       else if (op.content.equalsIgnoreCase("*"))
       {
         if (op1.type == 1)
         {
           String output = "MULTI " + op1.content + " " + op2.content + " " + newNode.content;
           this.outputList.add(output);
         }
         else
         {
           String output = "MULTF " + op1.content + " " + op2.content + " " + newNode.content;
           this.outputList.add(output);
         }
       }
       else if (op1.type == 1)
       {
         String output = "DIVI " + op1.content + " " + op2.content + " " + newNode.content;
         this.outputList.add(output);
       }
       else
       {
         String output = "DIVF " + op1.content + " " + op2.content + " " + newNode.content;
         this.outputList.add(output);
       }
       input.remove(0);
       input.remove(0);
       input.remove(0);
       
       input.add(0, newNode);
     }
     Node returnValue = (Node)input.get(0);
     input.removeAll(input);
     return returnValue;
   }
   
   public String printOutput()
   {
     String result = "";
     for (int i = 0; i < this.outputList.size(); i++)
     {
       result = result + (String)this.outputList.get(i);
       result = result + "\n";
     }
     return result;
   }
 }