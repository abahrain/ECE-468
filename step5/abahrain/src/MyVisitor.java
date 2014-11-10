import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
 
 public class MyVisitor extends MicroBaseVisitor<NodeStructure>
 {
   public ArrayList<String> outputList = new ArrayList<String>();
   public ArrayList<String> pushStack = new ArrayList<String>();
   private Stack<String> labelStack = new Stack<String>();
   private Stack<String> continueStack = new Stack<String>();
   private Stack<String> breakStack = new Stack<String>();
   private Stack<String> functionStack = new Stack<String>();
   private Stack<Integer> functionPopNumberStack = new Stack<Integer>();
   private Stack<ArrayList<NodeStructure>> factorStack = new Stack<ArrayList<NodeStructure>>();
   private Stack<ArrayList<NodeStructure>> exprStack = new Stack<ArrayList<NodeStructure>>();
   protected Map<String, Map<String, NodeStructure>> tableMap = new LinkedHashMap<String, Map<String, NodeStructure>>();
   protected Map<String, Integer> functionMap = new LinkedHashMap<String, Integer>();
   protected Map<String, ArrayList<String>> tempMap = new LinkedHashMap<String, ArrayList<String>>();
   private int tempIndex = 0;
   public int finalTempIndex = 0;
   private int varIndex = 0;
   private int paramIndex = 0;
   private int labelIndex = 0;
   private int countPUSH = 0;
   private String functionRecord = "GLOBAL";
   
   public MyVisitor(SymbolTable table, Map<String, Integer> functionMap)
   {
     this.functionMap = functionMap;
     for (Boresight scope : table.scopeStack.subList(0, table.scopeStack.size()))
     {
       Map<String, NodeStructure> varMap = new LinkedHashMap<String, NodeStructure>();
       if (scope.type.equalsIgnoreCase("GLOBAL")) {
         for (String key : scope.symbolMap.keySet()) {
           if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.INT) {
             varMap.put(key, new NodeStructure(key, 1));
           } else if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.FLOAT) {
             varMap.put(key, new NodeStructure(key, 2));
           } else if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.STRING) {
             varMap.put(key, new NodeStructure(key, 5));
           } else {
             System.out.println("error@constructor");
           }
         }
       } else {
         for (String key : scope.symbolMap.keySet()) {
           if (((BuildNode)scope.symbolMap.get(key)).descriptor.isParameter)
           {
             if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.INT) {
               varMap.put(key, new NodeStructure(createVar(true), 1));
             } else if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.FLOAT) {
               varMap.put(key, new NodeStructure(createVar(true), 2));
             } else if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.STRING) {
               varMap.put(key, new NodeStructure(key, 5));
             } else {
               System.out.println("error@constructor");
             }
           }
           else if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.INT) {
             varMap.put(key, new NodeStructure(createVar(false), 1));
           } else if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.FLOAT) {
             varMap.put(key, new NodeStructure(createVar(false), 2));
           } else if (((BuildNode)scope.symbolMap.get(key)).type == VariableType.STRING) {
             varMap.put(key, new NodeStructure(key, 5));
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
   
   public NodeStructure findIdNode(String name, String scopeName)
   {
     if (((Map<?, ?>)this.tableMap.get(scopeName)).get(name) == null)
     {
       if (((Map<?, ?>)this.tableMap.get("GLOBAL")).get(name) == null)
       {
         System.out.println("can't find assign variable, error@findIdNode");
         return null;
       }
       return (NodeStructure)((Map<?, ?>)this.tableMap.get("GLOBAL")).get(name);
     }
     return (NodeStructure)((Map<?, ?>)this.tableMap.get(scopeName)).get(name);
   }
   
   public NodeStructure visitPrimary(@NotNull MicroParser.PrimaryContext ctx)
   {
     if (ctx.expression() != null) {
       return (NodeStructure)visit(ctx.expression());
     }
     if (ctx.name() != null) {
       return findIdNode(ctx.name().getText(), this.functionRecord);
     }
     if (ctx.INTLITERAL() != null)
     {
       NodeStructure newNode = new NodeStructure(createTemp(), 1);
       this.outputList.add("STOREI " + ctx.INTLITERAL().getText() + " " + newNode.content);
       
       return newNode;
     }
     NodeStructure newNode = new NodeStructure(createTemp(), 2);
     this.outputList.add("STOREF " + ctx.FLOATLITERAL().getText() + " " + newNode.content);
     
     return newNode;
   }
   
   public NodeStructure visitFunction_declaration(@NotNull MicroParser.Function_declarationContext ctx)
   {
     ArrayList<String> newTempList = new ArrayList<String>();
     
     this.outputList.add("LABEL " + ctx.name().getText());
     this.functionRecord = ctx.name().getText();
     this.tempMap.put(this.functionRecord, newTempList);
     this.outputList.add("LINK ");
     visitChildren(ctx);
     this.tempIndex = 0;
     if (ctx.any_type().getText().equals("VOID")) {
       this.outputList.add("RET");
     }
     return null;
   }
   
   public NodeStructure visitExpression_call(@NotNull MicroParser.Expression_callContext ctx)
   {
     this.functionPopNumberStack.push(Integer.valueOf(this.countPUSH));
     this.countPUSH = 0;
     if (ctx.expression_list() != null) {
       visit(ctx.expression_list());
     }
     this.outputList.add("PUSH ");
     String[] reverseList = new String[this.countPUSH];
     for (int i = 0; i < this.countPUSH; i++) {
       reverseList[i] = ((String)this.functionStack.pop());
     }
     for (int i = this.countPUSH - 1; i >= 0; i--) {
       this.outputList.add("PUSH " + reverseList[i]);
     }
     this.outputList.add("JSR " + ctx.name().getText());
     for (int i = 0; i < this.countPUSH; i++) {
       this.outputList.add("POP ");
     }
     this.countPUSH = ((Integer)this.functionPopNumberStack.pop()).intValue();
     
     NodeStructure newNode = new NodeStructure(createTemp(), ((Integer)this.functionMap.get(this.functionRecord)).intValue());
     this.outputList.add("POP " + newNode.content);
     return newNode;
   }
   
   public NodeStructure visitExpression_list(@NotNull MicroParser.Expression_listContext ctx)
   {
	 ParseTree temp = (ParseTree)ctx.expression(); 
     NodeStructure exprNode = (NodeStructure)visit(temp);
     
 
     this.functionStack.push(exprNode.content);
     this.countPUSH += 1;
     temp = (ParseTree)ctx.expression_list_repeat();
     if (!"".equals(temp.getText())) {
       visit(temp);
     }
     return null;
   }
   
   public NodeStructure visitExpression_list_repeat(@NotNull MicroParser.Expression_list_repeatContext ctx)
   {
	 ParseTree temp = (ParseTree)ctx.expression();
     NodeStructure exprNode = (NodeStructure)visit(temp);
     
     this.functionStack.push(exprNode.content);
     this.countPUSH += 1;
     temp = (ParseTree)ctx.expression_list_repeat();
     if (!"".equals(temp.getText())) {
       visit(temp);
     }
     return null;
   }
   
   public NodeStructure visitRe_turn(@NotNull MicroParser.Re_turnContext ctx)
   {
     NodeStructure exprNode = (NodeStructure)visit(ctx.expression());
     NodeStructure tempNode = new NodeStructure(createTemp(), exprNode.type);
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
   
   public NodeStructure visitParameter_declaration(@NotNull MicroParser.Parameter_declarationContext ctx)
   {
     if (ctx.variable_type().getText().equalsIgnoreCase("INT")) {
       new NodeStructure(ctx.name().getText(), 1);
     } else {
       new NodeStructure(ctx.name().getText(), 2);
     }
     return null;
   }
   
   public NodeStructure visitExpression(@NotNull MicroParser.ExpressionContext ctx)
   {
     if (!"".equals(ctx.pre_expression().getText()))
     {
       ArrayList<NodeStructure> exprList = new ArrayList<NodeStructure>();
       this.exprStack.push(exprList);
       visit(ctx.pre_expression());
       NodeStructure factorNode = (NodeStructure)visit(ctx.factor());
       ((ArrayList<NodeStructure>)this.exprStack.peek()).add(factorNode);
       NodeStructure resolveNode = resolve((ArrayList<NodeStructure>)this.exprStack.pop());
       
       return resolveNode;
     }
     NodeStructure factorNode = (NodeStructure)visit(ctx.factor());
     return factorNode;
   }
   
   public NodeStructure visitPre_expression(@NotNull MicroParser.Pre_expressionContext ctx)
   {
     if (!"".equals(ctx.pre_expression().getText())) {
       visit(ctx.pre_expression());
     }
     NodeStructure opNode = new NodeStructure(ctx.addition_operation().getText(), 3);
     NodeStructure factorNode = (NodeStructure)visit(ctx.factor());
     ((ArrayList<NodeStructure>)this.exprStack.peek()).add(factorNode);
     ((ArrayList<NodeStructure>)this.exprStack.peek()).add(opNode);
     
     return null;
   }
   
   public NodeStructure visitFactor(@NotNull MicroParser.FactorContext ctx)
   {
     if (!"".equals(ctx.pre_factor().getText()))
     {
       ArrayList<NodeStructure> factorList = new ArrayList<NodeStructure>();
       this.factorStack.push(factorList);
       visit(ctx.pre_factor());
       NodeStructure postfixNode = (NodeStructure)visit(ctx.post_expression());
       ((ArrayList<NodeStructure>)this.factorStack.peek()).add(postfixNode);
       NodeStructure resolveNode = resolve((ArrayList<NodeStructure>)this.factorStack.pop());
       
       return resolveNode;
     }
     return (NodeStructure)visit(ctx.post_expression());
   }
   
   public NodeStructure visitPre_factor(@NotNull MicroParser.Pre_factorContext ctx)
   {
     if (!"".equals(ctx.pre_factor().getText())) {
       visit(ctx.pre_factor());
     }
     NodeStructure opNode = new NodeStructure(ctx.multiplication_operation().getText(), 3);
     NodeStructure postfixNode = (NodeStructure)visit(ctx.post_expression());
     ((ArrayList<NodeStructure>)this.factorStack.peek()).add(postfixNode);
     ((ArrayList<NodeStructure>)this.factorStack.peek()).add(opNode);
     
     return null;
   }
   
   public NodeStructure visitWrite(@NotNull MicroParser.WriteContext ctx)
   {
     String[] idArray = ctx.name_list().getText().split(",");
     for (int i = 0; i < idArray.length; i++)
     {
       NodeStructure newNode = findIdNode(idArray[i], this.functionRecord);
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
   
   public NodeStructure visitRead(@NotNull MicroParser.ReadContext ctx)
   {
     String[] idArray = ctx.name_list().getText().split(",");
     for (int i = 0; i < idArray.length; i++)
     {
       NodeStructure newNode = findIdNode(idArray[i], this.functionRecord);
       if (newNode.type == 1) {
         this.outputList.add("READI " + newNode.content);
       } else {
         this.outputList.add("READF " + newNode.content);
       }
     }
     return null;
   }
   
   public NodeStructure visitAssignment_frame(@NotNull MicroParser.Assignment_frameContext ctx)
   {
     NodeStructure exprNode = (NodeStructure)visit(ctx.expression());
     NodeStructure newNode = findIdNode(ctx.name().getText(), this.functionRecord);
     if (newNode.type == 1) {
       this.outputList.add("STOREI " + exprNode.content + " " + newNode.content);
     } else {
       this.outputList.add("STOREF " + exprNode.content + " " + newNode.content);
     }
     return null;
   }
   
   public NodeStructure visitWhile_statement(@NotNull MicroParser.While_statementContext ctx)
   {
     String newLabel = createLabel();
     this.outputList.add("LABEL " + newLabel);
     String newLabel2 = createLabel();
     this.labelStack.add(newLabel2);
     NodeStructure comp = (NodeStructure)visit(ctx.condition());
     
     this.continueStack.push("JUMP " + newLabel);
     this.breakStack.push("JUMP " + newLabel2);
     this.outputList.add(comp.content + " " + newLabel2);
     this.outputList.add("JUMP " + newLabel);
     this.outputList.add("LABEL " + newLabel2);
     this.breakStack.pop();
     this.continueStack.pop();
     return null;
   }
   
   public NodeStructure visitIf_statement(@NotNull MicroParser.If_statementContext ctx)
   {
     if (!"".equals(ctx.else_portion().getText()))
     {
       NodeStructure comp = (NodeStructure)visit(ctx.condition());
       if (comp.content.equalsIgnoreCase("TRUE"))
       {
         visit(ctx.statement_list());
       }
       else if (comp.content.equalsIgnoreCase("FALSE"))
       {
         String newLabel2 = createLabel();
         this.labelStack.push(newLabel2);
         visit(ctx.else_portion());
         this.outputList.add("LABEL " + newLabel2);
       }
       else
       {
         String newLabel = createLabel();
         this.outputList.add(comp.content + " " + newLabel);
         visit(ctx.statement_list());
         String newLabel2 = createLabel();
         this.labelStack.push(newLabel2);
         this.outputList.add("JUMP " + newLabel2);
         this.outputList.add("LABEL " + newLabel);
         visit(ctx.else_portion());
         this.outputList.add("LABEL " + newLabel2);
       }
     }
     else
     {
       NodeStructure comp = (NodeStructure)visit(ctx.condition());
       if (comp.content.equalsIgnoreCase("TRUE"))
       {
         visit(ctx.statement_list());
       }
       else if (!comp.content.equalsIgnoreCase("FALSE"))
       {
         String newLabel2 = createLabel();
         this.outputList.add(comp.content + " " + newLabel2);
         visit(ctx.statement_list());
         this.outputList.add("LABEL " + newLabel2);
       }
     }
     return null;
   }
   
   public NodeStructure visitElse_portion(@NotNull MicroParser.Else_portionContext ctx)
   {
     visit(ctx.statement_list());
     return null;
   }
   
   public NodeStructure visitCondition(@NotNull MicroParser.ConditionContext ctx)
   {
     NodeStructure op1 = (NodeStructure)visit(ctx.expression(0));
     
     visit(ctx.comparison_operator());
     NodeStructure op2 = (NodeStructure)visit(ctx.expression(1));
     return resolveComp(op1, op2, ctx.comparison_operator().getText());
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
   
   public NodeStructure resolveComp(NodeStructure op1, NodeStructure op2, String op)
   {
     if ((op1.type == 1) && (op2.type == 1))
     {
       if (op.equalsIgnoreCase("<")) {
         return new NodeStructure("GEI " + op1.content + " " + op2.content, 4);
       }
       if (op.equalsIgnoreCase(">")) {
         return new NodeStructure("LEI " + op1.content + " " + op2.content, 4);
       }
       if (op.equalsIgnoreCase("=")) {
         return new NodeStructure("NEI " + op1.content + " " + op2.content, 4);
       }
       if (op.equalsIgnoreCase("!=")) {
         return new NodeStructure("EQI " + op1.content + " " + op2.content, 4);
       }
       if (op.equalsIgnoreCase("<=")) {
         return new NodeStructure("GTI " + op1.content + " " + op2.content, 4);
       }
       if (op.equalsIgnoreCase(">=")) {
         return new NodeStructure("LTI " + op1.content + " " + op2.content, 4);
       }
       System.out.println("ERROR @ resolveComp");
       return null;
     }
     if (op.equalsIgnoreCase("<")) {
       return new NodeStructure("GEF " + op1.content + " " + op2.content, 4);
     }
     if (op.equalsIgnoreCase(">")) {
       return new NodeStructure("LEF " + op1.content + " " + op2.content, 4);
     }
     if (op.equalsIgnoreCase("=")) {
       return new NodeStructure("NEF " + op1.content + " " + op2.content, 4);
     }
     if (op.equalsIgnoreCase("!=")) {
       return new NodeStructure("EQF " + op1.content + " " + op2.content, 4);
     }
     if (op.equalsIgnoreCase("<=")) {
       return new NodeStructure("GTF " + op1.content + " " + op2.content, 4);
     }
     if (op.equalsIgnoreCase(">=")) {
       return new NodeStructure("LTF " + op1.content + " " + op2.content, 4);
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
   
   public NodeStructure resolve(ArrayList<NodeStructure> input)
   {
     while (input.size() >= 3)
     {
       NodeStructure op1 = (NodeStructure)input.get(0);
       NodeStructure op = (NodeStructure)input.get(1);
       NodeStructure op2 = (NodeStructure)input.get(2);
       
       NodeStructure newNode = new NodeStructure(createTemp(), op1.type);
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
     NodeStructure returnValue = (NodeStructure)input.get(0);
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
