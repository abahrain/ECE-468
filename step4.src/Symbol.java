/*  1:   */ public class Symbol
/*  2:   */ {
/*  3:   */   protected Scope scope;
/*  4:   */   protected BaseDescriptor descriptor;
/*  5:   */   protected String name;
/*  6:   */   protected ValueType type;
/*  7:   */   
/*  8:   */   public Symbol(BaseDescriptor descriptor, String name, ValueType type)
/*  9:   */   {
/* 10:17 */     this.descriptor = descriptor;
/* 11:18 */     this.name = name;
/* 12:19 */     this.type = type;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public BaseDescriptor getDescriptor()
/* 16:   */   {
/* 17:23 */     return this.descriptor;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public String getName()
/* 21:   */   {
/* 22:27 */     return this.name;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public ValueType getType()
/* 26:   */   {
/* 27:31 */     return this.type;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void setScope(Scope scope)
/* 31:   */   {
/* 32:35 */     this.scope = scope;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public Scope getScope()
/* 36:   */   {
/* 37:39 */     return this.scope;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public int genId()
/* 41:   */   {
/* 42:43 */     return this.scope.genId;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public String toString()
/* 46:   */   {
/* 47:48 */     if (this.type != null)
/* 48:   */     {
/* 49:49 */       if (this.type == ValueType.STRING) {
/* 50:50 */         return "name " + getName() + " type " + this.type + " value " + this.descriptor.content + "\n";
/* 51:   */       }
/* 52:52 */       return "name " + getName() + " type " + this.type + "\n";
/* 53:   */     }
/* 54:54 */     return getName();
/* 55:   */   }
/* 56:   */ }


/* Location:           C:\Users\Adam\Downloads\step4.jar
 * Qualified Name:     Symbol
 * JD-Core Version:    0.7.0.1
 */