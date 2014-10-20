/*  1:   */ class BaseDescriptor
/*  2:   */ {
/*  3:11 */   String content = "default";
/*  4:12 */   boolean isParameter = false;
/*  5:   */   
/*  6:   */   public BaseDescriptor(String input)
/*  7:   */   {
/*  8:14 */     this.content = input;
/*  9:   */   }
/* 10:   */   
/* 11:   */   public BaseDescriptor(boolean justify)
/* 12:   */   {
/* 13:17 */     this.isParameter = justify;
/* 14:   */   }
/* 15:   */   
/* 16:   */   public BaseDescriptor() {}
/* 17:   */ }


/* Location:           C:\Users\Adam\Downloads\step4.jar
 * Qualified Name:     BaseDescriptor
 * JD-Core Version:    0.7.0.1
 */