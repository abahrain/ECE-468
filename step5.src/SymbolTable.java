/*  1:   */ import java.util.ArrayList;
/*  2:   */ import java.util.HashSet;
/*  3:   */ import java.util.List;
/*  4:   */ import java.util.Map;
/*  5:   */ import java.util.Set;
/*  6:   */ import java.util.Stack;
/*  7:   */ 
/*  8:   */ public class SymbolTable
/*  9:   */ {
/* 10:   */   protected Stack<Scope> scopeStack;
/* 11:   */   protected ArrayList<Scope> allScopes;
/* 12:   */   protected int genId;
/* 13:   */   
/* 14:   */   public SymbolTable()
/* 15:   */   {
/* 16:24 */     init();
/* 17:   */   }
/* 18:   */   
/* 19:   */   private void init()
/* 20:   */   {
/* 21:28 */     this.scopeStack = new Stack();
/* 22:29 */     this.allScopes = new ArrayList();
/* 23:30 */     this.genId = 0;
/* 24:   */     
/* 25:32 */     Scope globals = new Scope("GLOBAL", nextGenId(), null);
/* 26:33 */     this.scopeStack.push(globals);
/* 27:34 */     this.allScopes.add(globals);
/* 28:   */   }
/* 29:   */   
/* 30:   */   public Scope pushScope(String type)
/* 31:   */   {
/* 32:38 */     Scope enclosingScope = (Scope)this.scopeStack.peek();
/* 33:39 */     Scope scope = new Scope(type, nextGenId(), enclosingScope);
/* 34:40 */     this.scopeStack.push(scope);
/* 35:41 */     this.allScopes.add(scope);
/* 36:42 */     return scope;
/* 37:   */   }
/* 38:   */   
/* 39:   */   public void popScope()
/* 40:   */   {
/* 41:46 */     this.scopeStack.pop();
/* 42:   */   }
/* 43:   */   
/* 44:   */   public Scope currentScope()
/* 45:   */   {
/* 46:50 */     if (this.scopeStack.size() > 0) {
/* 47:51 */       return (Scope)this.scopeStack.peek();
/* 48:   */     }
/* 49:54 */     return (Scope)this.allScopes.get(0);
/* 50:   */   }
/* 51:   */   
/* 52:   */   public Scope getScope(int genId)
/* 53:   */   {
/* 54:58 */     for (Scope scope : this.scopeStack) {
/* 55:59 */       if (scope.genId == genId) {
/* 56:59 */         return scope;
/* 57:   */       }
/* 58:   */     }
/* 59:61 */     return null;
/* 60:   */   }
/* 61:   */   
/* 62:   */   private int nextGenId()
/* 63:   */   {
/* 64:65 */     this.genId += 1;
/* 65:66 */     return this.genId;
/* 66:   */   }
/* 67:   */   
/* 68:   */   public List<String> checkDuplicate()
/* 69:   */   {
/* 70:70 */     List<String> nameArray = new ArrayList();
/* 71:71 */     List<String> outDuplicate = new ArrayList();
/* 72:72 */     Set<String> array = new HashSet();
/* 73:73 */     for (Scope scope : this.scopeStack.subList(0, this.scopeStack.size())) {
/* 74:75 */       nameArray.addAll(scope.symbolMap.keySet());
/* 75:   */     }
/* 76:78 */     for (String k : nameArray) {
/* 77:79 */       if (!array.add(k)) {
/* 78:80 */         outDuplicate.add(k);
/* 79:   */       }
/* 80:   */     }
/* 81:84 */     return outDuplicate;
/* 82:   */   }
/* 83:   */   
/* 84:   */   public String toString()
/* 85:   */   {
/* 86:89 */     StringBuilder sb = new StringBuilder();
/* 87:90 */     for (Scope scope : this.scopeStack.subList(0, this.scopeStack.size())) {
/* 88:91 */       sb.append("Symbol table " + scope.type + "\n" + scope.toString() + "\n");
/* 89:   */     }
/* 90:94 */     String temp = sb.toString().replace("[", "").replace("]", "");
/* 91:95 */     String temp2 = temp.replaceAll(", ", "");
/* 92:96 */     return temp2;
/* 93:   */   }
/* 94:   */ }


/* Location:           C:\Users\Adam\Downloads\step5.jar
 * Qualified Name:     SymbolTable
 * JD-Core Version:    0.7.0.1
 */