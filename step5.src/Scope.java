/*  1:   */ import java.util.ArrayList;
/*  2:   */ import java.util.LinkedHashMap;
/*  3:   */ import java.util.Map;
/*  4:   */ 
/*  5:   */ public class Scope
/*  6:   */ {
/*  7:   */   public final int genId;
/*  8:   */   public String type;
/*  9:   */   public Scope enclosingScope;
/* 10:26 */   protected Map<String, Symbol> symbolMap = new LinkedHashMap();
/* 11:   */   
/* 12:   */   public Scope(String type, int genId, Scope enclosingScope)
/* 13:   */   {
/* 14:29 */     this.type = type;
/* 15:30 */     this.genId = genId;
/* 16:31 */     this.enclosingScope = enclosingScope;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void define(String name, ArrayList<String> parameters)
/* 20:   */   {
/* 21:39 */     String params = Strings.asString(parameters, true, ".");
/* 22:40 */     Symbol symbol = new Symbol(null, name + params, null);
/* 23:41 */     define(symbol);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void define(BaseDescriptor descriptor, String name, ValueType type)
/* 27:   */   {
/* 28:46 */     Symbol symbol = new Symbol(descriptor, name, type);
/* 29:47 */     define(symbol);
/* 30:   */   }
/* 31:   */   
/* 32:   */   private void define(Symbol symbol)
/* 33:   */   {
/* 34:52 */     symbol.setScope(this);
/* 35:53 */     if (this.symbolMap.containsKey(symbol.name)) {
/* 36:54 */       throw new IllegalArgumentException("DECLARATION ERROR " + symbol.name);
/* 37:   */     }
/* 38:56 */     this.symbolMap.put(symbol.name, symbol);
/* 39:   */   }
/* 40:   */   
/* 41:   */   private Symbol resolve(String name)
/* 42:   */   {
/* 43:67 */     Symbol symbol = (Symbol)this.symbolMap.get(name);
/* 44:68 */     if (symbol != null) {
/* 45:68 */       return symbol;
/* 46:   */     }
/* 47:69 */     if (this.enclosingScope != null) {
/* 48:69 */       return this.enclosingScope.resolve(name);
/* 49:   */     }
/* 50:70 */     return null;
/* 51:   */   }
/* 52:   */   
/* 53:   */   public Symbol resolve(String name, ArrayList<String> parameters)
/* 54:   */   {
/* 55:77 */     String params = Strings.asString(parameters, true, ".");
/* 56:78 */     return resolve(name + params);
/* 57:   */   }
/* 58:   */   
/* 59:   */   public Scope enclosingScope()
/* 60:   */   {
/* 61:83 */     return this.enclosingScope;
/* 62:   */   }
/* 63:   */   
/* 64:   */   public String toString()
/* 65:   */   {
/* 66:87 */     return this.symbolMap.values().toString();
/* 67:   */   }
/* 68:   */ }


/* Location:           C:\Users\Adam\Downloads\step5.jar
 * Qualified Name:     Scope
 * JD-Core Version:    0.7.0.1
 */