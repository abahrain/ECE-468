/*   1:    */ import java.io.PrintStream;
/*   2:    */ import java.util.ArrayList;
/*   3:    */ import java.util.LinkedHashMap;
/*   4:    */ import java.util.Map;
/*   5:    */ import java.util.Stack;
/*   6:    */ import org.antlr.v4.runtime.misc.NotNull;
/*   7:    */ import org.antlr.v4.runtime.tree.TerminalNode;
/*   8:    */ 
/*   9:    */ public class EvalMicroBaseVisitor
/*  10:    */   extends MicroBaseVisitor<Node>
/*  11:    */ {
/*  12: 22 */   public ArrayList<String> outputList = new ArrayList();
/*  13: 23 */   public ArrayList<String> pushStack = new ArrayList();
/*  14: 24 */   private Stack<String> labelStack = new Stack();
/*  15: 25 */   private Stack<String> continueStack = new Stack();
/*  16: 26 */   private Stack<String> breakStack = new Stack();
/*  17: 27 */   private Stack<String> functionStack = new Stack();
/*  18: 28 */   private Stack<Integer> functionPopNumberStack = new Stack();
/*  19: 29 */   private Stack<ArrayList<Node>> factorStack = new Stack();
/*  20: 30 */   private Stack<ArrayList<Node>> exprStack = new Stack();
/*  21: 32 */   protected Map<String, Map<String, Node>> tableMap = new LinkedHashMap();
/*  22: 33 */   protected Map<String, Integer> functionMap = new LinkedHashMap();
/*  23: 34 */   protected Map<String, ArrayList<String>> tempMap = new LinkedHashMap();
/*  24: 36 */   private int tempIndex = 0;
/*  25: 37 */   public int finalTempIndex = 0;
/*  26: 38 */   private int varIndex = 0;
/*  27: 39 */   private int paramIndex = 0;
/*  28: 40 */   private int labelIndex = 0;
/*  29: 41 */   private int countPUSH = 0;
/*  30: 42 */   private String functionRecord = "GLOBAL";
/*  31:    */   
/*  32:    */   public EvalMicroBaseVisitor(SymbolTable table, Map<String, Integer> functionMap)
/*  33:    */   {
/*  34: 46 */     this.functionMap = functionMap;
/*  35: 48 */     for (Scope scope : table.scopeStack.subList(0, table.scopeStack.size()))
/*  36:    */     {
/*  37: 50 */       Map<String, Node> varMap = new LinkedHashMap();
/*  38: 51 */       if (scope.type.equalsIgnoreCase("GLOBAL")) {
/*  39: 52 */         for (String key : scope.symbolMap.keySet()) {
/*  40: 53 */           if (((Symbol)scope.symbolMap.get(key)).type == ValueType.INT) {
/*  41: 54 */             varMap.put(key, new Node(key, 1));
/*  42: 56 */           } else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.FLOAT) {
/*  43: 57 */             varMap.put(key, new Node(key, 2));
/*  44: 59 */           } else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.STRING) {
/*  45: 60 */             varMap.put(key, new Node(key, 5));
/*  46:    */           } else {
/*  47: 64 */             System.out.println("error@constructor");
/*  48:    */           }
/*  49:    */         }
/*  50:    */       } else {
/*  51: 69 */         for (String key : scope.symbolMap.keySet()) {
/*  52: 70 */           if (((Symbol)scope.symbolMap.get(key)).descriptor.isParameter)
/*  53:    */           {
/*  54: 71 */             if (((Symbol)scope.symbolMap.get(key)).type == ValueType.INT) {
/*  55: 72 */               varMap.put(key, new Node(createVar(true), 1));
/*  56: 74 */             } else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.FLOAT) {
/*  57: 75 */               varMap.put(key, new Node(createVar(true), 2));
/*  58: 77 */             } else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.STRING) {
/*  59: 78 */               varMap.put(key, new Node(key, 5));
/*  60:    */             } else {
/*  61: 82 */               System.out.println("error@constructor");
/*  62:    */             }
/*  63:    */           }
/*  64: 86 */           else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.INT) {
/*  65: 87 */             varMap.put(key, new Node(createVar(false), 1));
/*  66: 89 */           } else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.FLOAT) {
/*  67: 90 */             varMap.put(key, new Node(createVar(false), 2));
/*  68: 92 */           } else if (((Symbol)scope.symbolMap.get(key)).type == ValueType.STRING) {
/*  69: 93 */             varMap.put(key, new Node(key, 5));
/*  70:    */           } else {
/*  71: 97 */             System.out.println("error@constructor");
/*  72:    */           }
/*  73:    */         }
/*  74:    */       }
/*  75:102 */       this.tableMap.put(scope.type, varMap);
/*  76:103 */       this.varIndex = 0;
/*  77:104 */       this.paramIndex = 0;
/*  78:    */     }
/*  79:    */   }
/*  80:    */   
/*  81:    */   private String createVar(boolean isParameter)
/*  82:    */   {
/*  83:110 */     if (isParameter)
/*  84:    */     {
/*  85:111 */       this.paramIndex += 1;
/*  86:112 */       return "$P" + Integer.toString(this.paramIndex);
/*  87:    */     }
/*  88:115 */     this.varIndex += 1;
/*  89:116 */     return "$L" + Integer.toString(this.varIndex);
/*  90:    */   }
/*  91:    */   
/*  92:    */   public Node findIdNode(String id, String scopeName)
/*  93:    */   {
/*  94:121 */     if (((Map)this.tableMap.get(scopeName)).get(id) == null)
/*  95:    */     {
/*  96:122 */       if (((Map)this.tableMap.get("GLOBAL")).get(id) == null)
/*  97:    */       {
/*  98:123 */         System.out.println("can't find assign variable, error@findIdNode");
/*  99:124 */         return null;
/* 100:    */       }
/* 101:127 */       return (Node)((Map)this.tableMap.get("GLOBAL")).get(id);
/* 102:    */     }
/* 103:132 */     return (Node)((Map)this.tableMap.get(scopeName)).get(id);
/* 104:    */   }
/* 105:    */   
/* 106:    */   public Node visitPrimary(@NotNull MicroParser.PrimaryContext ctx)
/* 107:    */   {
/* 108:150 */     if (ctx.expr() != null) {
/* 109:151 */       return (Node)visit(ctx.expr());
/* 110:    */     }
/* 111:152 */     if (ctx.id() != null) {
/* 112:153 */       return findIdNode(ctx.id().getText(), this.functionRecord);
/* 113:    */     }
/* 114:154 */     if (ctx.INTLITERAL() != null)
/* 115:    */     {
/* 116:155 */       Node newNode = new Node(createTemp(), 1);
/* 117:156 */       this.outputList.add("STOREI " + ctx.INTLITERAL().getText() + " " + newNode.content);
/* 118:    */       
/* 119:158 */       return newNode;
/* 120:    */     }
/* 121:161 */     Node newNode = new Node(createTemp(), 2);
/* 122:162 */     this.outputList.add("STOREF " + ctx.FLOATLITERAL().getText() + " " + newNode.content);
/* 123:    */     
/* 124:164 */     return newNode;
/* 125:    */   }
/* 126:    */   
/* 127:    */   public Node visitFunc_decl(@NotNull MicroParser.Func_declContext ctx)
/* 128:    */   {
/* 129:170 */     ArrayList<String> newTempList = new ArrayList();
/* 130:    */     
/* 131:172 */     this.outputList.add("LABEL " + ctx.id().getText());
/* 132:173 */     this.functionRecord = ctx.id().getText();
/* 133:174 */     this.tempMap.put(this.functionRecord, newTempList);
/* 134:175 */     this.outputList.add("LINK ");
/* 135:176 */     visitChildren(ctx);
/* 136:177 */     this.tempIndex = 0;
/* 137:178 */     if (ctx.any_type().getText().equals("VOID")) {
/* 138:179 */       this.outputList.add("RET");
/* 139:    */     }
/* 140:181 */     return null;
/* 141:    */   }
/* 142:    */   
/* 143:    */   public Node visitCall_expr(@NotNull MicroParser.Call_exprContext ctx)
/* 144:    */   {
/* 145:187 */     this.functionPopNumberStack.push(Integer.valueOf(this.countPUSH));
/* 146:188 */     this.countPUSH = 0;
/* 147:189 */     if (ctx.expr_list() != null) {
/* 148:190 */       visit(ctx.expr_list());
/* 149:    */     }
/* 150:193 */     this.outputList.add("PUSH ");
/* 151:194 */     String[] reverseList = new String[this.countPUSH];
/* 152:195 */     for (int i = 0; i < this.countPUSH; i++) {
/* 153:196 */       reverseList[i] = ((String)this.functionStack.pop());
/* 154:    */     }
/* 155:198 */     for (int i = this.countPUSH - 1; i >= 0; i--) {
/* 156:199 */       this.outputList.add("PUSH " + reverseList[i]);
/* 157:    */     }
/* 158:201 */     this.outputList.add("JSR " + ctx.id().getText());
/* 159:202 */     for (int i = 0; i < this.countPUSH; i++) {
/* 160:203 */       this.outputList.add("POP ");
/* 161:    */     }
/* 162:205 */     this.countPUSH = ((Integer)this.functionPopNumberStack.pop()).intValue();
/* 163:    */     
/* 164:207 */     Node newNode = new Node(createTemp(), ((Integer)this.functionMap.get(this.functionRecord)).intValue());
/* 165:208 */     this.outputList.add("POP " + newNode.content);
/* 166:209 */     return newNode;
/* 167:    */   }
/* 168:    */   
/* 169:    */   public Node visitExpr_list(@NotNull MicroParser.Expr_listContext ctx)
/* 170:    */   {
/* 171:214 */     Node exprNode = (Node)visit(ctx.expr());
/* 172:    */     
/* 173:    */ 
/* 174:217 */     this.functionStack.push(exprNode.content);
/* 175:218 */     this.countPUSH += 1;
/* 176:219 */     if (!"".equals(ctx.expr_list_tail().getText())) {
/* 177:220 */       visit(ctx.expr_list_tail());
/* 178:    */     }
/* 179:222 */     return null;
/* 180:    */   }
/* 181:    */   
/* 182:    */   public Node visitExpr_list_tail(@NotNull MicroParser.Expr_list_tailContext ctx)
/* 183:    */   {
/* 184:227 */     Node exprNode = (Node)visit(ctx.expr());
/* 185:    */     
/* 186:229 */     this.functionStack.push(exprNode.content);
/* 187:230 */     this.countPUSH += 1;
/* 188:231 */     if (!"".equals(ctx.expr_list_tail().getText())) {
/* 189:232 */       visit(ctx.expr_list_tail());
/* 190:    */     }
/* 191:235 */     return null;
/* 192:    */   }
/* 193:    */   
/* 194:    */   public Node visitReturn_stmt(@NotNull MicroParser.Return_stmtContext ctx)
/* 195:    */   {
/* 196:240 */     Node exprNode = (Node)visit(ctx.expr());
/* 197:241 */     Node tempNode = new Node(createTemp(), exprNode.type);
/* 198:242 */     if (exprNode.type == 1)
/* 199:    */     {
/* 200:243 */       this.outputList.add("STOREI " + exprNode + " " + tempNode);
/* 201:244 */       this.outputList.add("STOREI " + tempNode + " $R");
/* 202:    */     }
/* 203:    */     else
/* 204:    */     {
/* 205:247 */       this.outputList.add("STOREF " + exprNode + " " + tempNode);
/* 206:248 */       this.outputList.add("STOREF " + tempNode + " $R");
/* 207:    */     }
/* 208:250 */     this.outputList.add("RET");
/* 209:251 */     return null;
/* 210:    */   }
/* 211:    */   
/* 212:    */   public Node visitParam_decl(@NotNull MicroParser.Param_declContext ctx)
/* 213:    */   {
/* 214:    */     Node newNode;
/* 215:    */     Node newNode;
/* 216:257 */     if (ctx.var_type().getText().equalsIgnoreCase("INT")) {
/* 217:258 */       newNode = new Node(ctx.id().getText(), 1);
/* 218:    */     } else {
/* 219:261 */       newNode = new Node(ctx.id().getText(), 2);
/* 220:    */     }
/* 221:264 */     return null;
/* 222:    */   }
/* 223:    */   
/* 224:    */   public Node visitExpr(@NotNull MicroParser.ExprContext ctx)
/* 225:    */   {
/* 226:269 */     if (!"".equals(ctx.expr_prefix().getText()))
/* 227:    */     {
/* 228:270 */       ArrayList<Node> exprList = new ArrayList();
/* 229:271 */       this.exprStack.push(exprList);
/* 230:272 */       Node exprNode = (Node)visit(ctx.expr_prefix());
/* 231:273 */       Node factorNode = (Node)visit(ctx.factor());
/* 232:274 */       ((ArrayList)this.exprStack.peek()).add(factorNode);
/* 233:275 */       Node resolveNode = resolve((ArrayList)this.exprStack.pop());
/* 234:    */       
/* 235:277 */       return resolveNode;
/* 236:    */     }
/* 237:282 */     Node factorNode = (Node)visit(ctx.factor());
/* 238:283 */     return factorNode;
/* 239:    */   }
/* 240:    */   
/* 241:    */   public Node visitExpr_prefix(@NotNull MicroParser.Expr_prefixContext ctx)
/* 242:    */   {
/* 243:290 */     if (!"".equals(ctx.expr_prefix().getText())) {
/* 244:291 */       visit(ctx.expr_prefix());
/* 245:    */     }
/* 246:293 */     Node opNode = new Node(ctx.addop().getText(), 3);
/* 247:294 */     Node factorNode = (Node)visit(ctx.factor());
/* 248:295 */     ((ArrayList)this.exprStack.peek()).add(factorNode);
/* 249:296 */     ((ArrayList)this.exprStack.peek()).add(opNode);
/* 250:    */     
/* 251:298 */     return null;
/* 252:    */   }
/* 253:    */   
/* 254:    */   public Node visitFactor(@NotNull MicroParser.FactorContext ctx)
/* 255:    */   {
/* 256:304 */     if (!"".equals(ctx.factor_prefix().getText()))
/* 257:    */     {
/* 258:305 */       ArrayList<Node> factorList = new ArrayList();
/* 259:306 */       this.factorStack.push(factorList);
/* 260:307 */       Node exprNode = (Node)visit(ctx.factor_prefix());
/* 261:308 */       Node postfixNode = (Node)visit(ctx.postfix_expr());
/* 262:309 */       ((ArrayList)this.factorStack.peek()).add(postfixNode);
/* 263:310 */       Node resolveNode = resolve((ArrayList)this.factorStack.pop());
/* 264:    */       
/* 265:312 */       return resolveNode;
/* 266:    */     }
/* 267:315 */     return (Node)visit(ctx.postfix_expr());
/* 268:    */   }
/* 269:    */   
/* 270:    */   public Node visitFactor_prefix(@NotNull MicroParser.Factor_prefixContext ctx)
/* 271:    */   {
/* 272:321 */     if (!"".equals(ctx.factor_prefix().getText())) {
/* 273:322 */       visit(ctx.factor_prefix());
/* 274:    */     }
/* 275:324 */     Node opNode = new Node(ctx.mulop().getText(), 3);
/* 276:325 */     Node postfixNode = (Node)visit(ctx.postfix_expr());
/* 277:326 */     ((ArrayList)this.factorStack.peek()).add(postfixNode);
/* 278:327 */     ((ArrayList)this.factorStack.peek()).add(opNode);
/* 279:    */     
/* 280:329 */     return null;
/* 281:    */   }
/* 282:    */   
/* 283:    */   public Node visitWrite_stmt(@NotNull MicroParser.Write_stmtContext ctx)
/* 284:    */   {
/* 285:334 */     String[] idArray = ctx.id_list().getText().split(",");
/* 286:335 */     for (int i = 0; i < idArray.length; i++)
/* 287:    */     {
/* 288:336 */       Node newNode = findIdNode(idArray[i], this.functionRecord);
/* 289:337 */       if (newNode.type == 1) {
/* 290:338 */         this.outputList.add("WRITEI " + newNode.content);
/* 291:341 */       } else if (newNode.type == 5) {
/* 292:342 */         this.outputList.add("WRITES " + newNode.content);
/* 293:    */       } else {
/* 294:346 */         this.outputList.add("WRITEF " + newNode.content);
/* 295:    */       }
/* 296:    */     }
/* 297:350 */     return null;
/* 298:    */   }
/* 299:    */   
/* 300:    */   public Node visitRead_stmt(@NotNull MicroParser.Read_stmtContext ctx)
/* 301:    */   {
/* 302:355 */     String[] idArray = ctx.id_list().getText().split(",");
/* 303:356 */     for (int i = 0; i < idArray.length; i++)
/* 304:    */     {
/* 305:357 */       Node newNode = findIdNode(idArray[i], this.functionRecord);
/* 306:358 */       if (newNode.type == 1) {
/* 307:359 */         this.outputList.add("READI " + newNode.content);
/* 308:    */       } else {
/* 309:363 */         this.outputList.add("READF " + newNode.content);
/* 310:    */       }
/* 311:    */     }
/* 312:367 */     return null;
/* 313:    */   }
/* 314:    */   
/* 315:    */   public Node visitAssign_expr(@NotNull MicroParser.Assign_exprContext ctx)
/* 316:    */   {
/* 317:372 */     Node exprNode = (Node)visit(ctx.expr());
/* 318:373 */     Node newNode = findIdNode(ctx.id().getText(), this.functionRecord);
/* 319:374 */     if (newNode.type == 1) {
/* 320:375 */       this.outputList.add("STOREI " + exprNode.content + " " + newNode.content);
/* 321:    */     } else {
/* 322:379 */       this.outputList.add("STOREF " + exprNode.content + " " + newNode.content);
/* 323:    */     }
/* 324:382 */     return null;
/* 325:    */   }
/* 326:    */   
/* 327:    */   public Node visitWhile_stmt(@NotNull MicroParser.While_stmtContext ctx)
/* 328:    */   {
/* 329:387 */     String newLabel = createLabel();
/* 330:388 */     this.outputList.add("LABEL " + newLabel);
/* 331:389 */     String newLabel2 = createLabel();
/* 332:390 */     this.labelStack.add(newLabel2);
/* 333:391 */     Node comp = (Node)visit(ctx.cond());
/* 334:    */     
/* 335:393 */     this.continueStack.push("JUMP " + newLabel);
/* 336:394 */     this.breakStack.push("JUMP " + newLabel2);
/* 337:395 */     this.outputList.add(comp.content + " " + newLabel2);
/* 338:396 */     visit(ctx.aug_stmt_list());
/* 339:397 */     this.outputList.add("JUMP " + newLabel);
/* 340:398 */     this.outputList.add("LABEL " + newLabel2);
/* 341:399 */     this.breakStack.pop();
/* 342:400 */     this.continueStack.pop();
/* 343:401 */     return null;
/* 344:    */   }
/* 345:    */   
/* 346:    */   public Node visitAug_if_stmt(@NotNull MicroParser.Aug_if_stmtContext ctx)
/* 347:    */   {
/* 348:407 */     if (!"".equals(ctx.aug_else_part().getText()))
/* 349:    */     {
/* 350:408 */       Node comp = (Node)visit(ctx.cond());
/* 351:409 */       if (comp.content.equalsIgnoreCase("TRUE"))
/* 352:    */       {
/* 353:410 */         visit(ctx.aug_stmt_list());
/* 354:    */       }
/* 355:412 */       else if (comp.content.equalsIgnoreCase("FALSE"))
/* 356:    */       {
/* 357:413 */         String newLabel2 = createLabel();
/* 358:414 */         this.labelStack.push(newLabel2);
/* 359:415 */         visit(ctx.aug_else_part());
/* 360:416 */         this.outputList.add("LABEL " + newLabel2);
/* 361:    */       }
/* 362:    */       else
/* 363:    */       {
/* 364:419 */         String newLabel = createLabel();
/* 365:420 */         this.outputList.add(comp.content + " " + newLabel);
/* 366:421 */         visit(ctx.aug_stmt_list());
/* 367:422 */         String newLabel2 = createLabel();
/* 368:423 */         this.labelStack.push(newLabel2);
/* 369:424 */         this.outputList.add("JUMP " + newLabel2);
/* 370:425 */         this.outputList.add("LABEL " + newLabel);
/* 371:426 */         visit(ctx.aug_else_part());
/* 372:427 */         this.outputList.add("LABEL " + newLabel2);
/* 373:    */       }
/* 374:    */     }
/* 375:    */     else
/* 376:    */     {
/* 377:431 */       Node comp = (Node)visit(ctx.cond());
/* 378:432 */       if (comp.content.equalsIgnoreCase("TRUE"))
/* 379:    */       {
/* 380:433 */         visit(ctx.aug_stmt_list());
/* 381:    */       }
/* 382:435 */       else if (!comp.content.equalsIgnoreCase("FALSE"))
/* 383:    */       {
/* 384:439 */         String newLabel2 = createLabel();
/* 385:440 */         this.outputList.add(comp.content + " " + newLabel2);
/* 386:441 */         visit(ctx.aug_stmt_list());
/* 387:442 */         this.outputList.add("LABEL " + newLabel2);
/* 388:    */       }
/* 389:    */     }
/* 390:445 */     return null;
/* 391:    */   }
/* 392:    */   
/* 393:    */   public Node visitAug_else_part(@NotNull MicroParser.Aug_else_partContext ctx)
/* 394:    */   {
/* 395:450 */     visit(ctx.aug_stmt_list());
/* 396:451 */     return null;
/* 397:    */   }
/* 398:    */   
/* 399:    */   public Node visitIf_stmt(@NotNull MicroParser.If_stmtContext ctx)
/* 400:    */   {
/* 401:456 */     if (!"".equals(ctx.else_part().getText()))
/* 402:    */     {
/* 403:457 */       Node comp = (Node)visit(ctx.cond());
/* 404:458 */       if (comp.content.equalsIgnoreCase("TRUE"))
/* 405:    */       {
/* 406:459 */         visit(ctx.stmt_list());
/* 407:    */       }
/* 408:461 */       else if (comp.content.equalsIgnoreCase("FALSE"))
/* 409:    */       {
/* 410:462 */         String newLabel2 = createLabel();
/* 411:463 */         this.labelStack.push(newLabel2);
/* 412:464 */         visit(ctx.else_part());
/* 413:465 */         this.outputList.add("LABEL " + newLabel2);
/* 414:    */       }
/* 415:    */       else
/* 416:    */       {
/* 417:468 */         String newLabel = createLabel();
/* 418:469 */         this.outputList.add(comp.content + " " + newLabel);
/* 419:470 */         visit(ctx.stmt_list());
/* 420:471 */         String newLabel2 = createLabel();
/* 421:472 */         this.labelStack.push(newLabel2);
/* 422:473 */         this.outputList.add("JUMP " + newLabel2);
/* 423:474 */         this.outputList.add("LABEL " + newLabel);
/* 424:475 */         visit(ctx.else_part());
/* 425:476 */         this.outputList.add("LABEL " + newLabel2);
/* 426:    */       }
/* 427:    */     }
/* 428:    */     else
/* 429:    */     {
/* 430:480 */       Node comp = (Node)visit(ctx.cond());
/* 431:481 */       if (comp.content.equalsIgnoreCase("TRUE"))
/* 432:    */       {
/* 433:482 */         visit(ctx.stmt_list());
/* 434:    */       }
/* 435:484 */       else if (!comp.content.equalsIgnoreCase("FALSE"))
/* 436:    */       {
/* 437:488 */         String newLabel2 = createLabel();
/* 438:489 */         this.outputList.add(comp.content + " " + newLabel2);
/* 439:490 */         visit(ctx.stmt_list());
/* 440:491 */         this.outputList.add("LABEL " + newLabel2);
/* 441:    */       }
/* 442:    */     }
/* 443:494 */     return null;
/* 444:    */   }
/* 445:    */   
/* 446:    */   public Node visitElse_part(@NotNull MicroParser.Else_partContext ctx)
/* 447:    */   {
/* 448:499 */     visit(ctx.stmt_list());
/* 449:500 */     return null;
/* 450:    */   }
/* 451:    */   
/* 452:    */   public Node visitAug_break(@NotNull MicroParser.Aug_breakContext ctx)
/* 453:    */   {
/* 454:505 */     this.outputList.add((String)this.breakStack.peek());
/* 455:506 */     return null;
/* 456:    */   }
/* 457:    */   
/* 458:    */   public Node visitAug_continue(@NotNull MicroParser.Aug_continueContext ctx)
/* 459:    */   {
/* 460:511 */     this.outputList.add((String)this.continueStack.peek());
/* 461:512 */     this.outputList.add((String)this.breakStack.peek());
/* 462:513 */     return null;
/* 463:    */   }
/* 464:    */   
/* 465:    */   public Node visitCond(@NotNull MicroParser.CondContext ctx)
/* 466:    */   {
/* 467:520 */     Node op1 = (Node)visit(ctx.cond_expr());
/* 468:    */     
/* 469:522 */     visit(ctx.compop());
/* 470:523 */     Node op2 = (Node)visit(ctx.cond_expr1());
/* 471:524 */     return resolveComp(op1, op2, ctx.compop().getText());
/* 472:    */   }
/* 473:    */   
/* 474:    */   public String resolveDoComp(String input)
/* 475:    */   {
/* 476:529 */     if (input.contains("GEI")) {
/* 477:530 */       return input.replace("GEI", "LTI");
/* 478:    */     }
/* 479:532 */     if (input.contains("LEI")) {
/* 480:533 */       return input.replace("LEI", "GTI");
/* 481:    */     }
/* 482:535 */     if (input.contains("NEI")) {
/* 483:536 */       return input.replace("NEI", "EQI");
/* 484:    */     }
/* 485:538 */     if (input.contains("EQI")) {
/* 486:539 */       return input.replace("EQI", "NEI");
/* 487:    */     }
/* 488:541 */     if (input.contains("GTI")) {
/* 489:542 */       return input.replace("GTI", "LEI");
/* 490:    */     }
/* 491:544 */     if (input.contains("LTI")) {
/* 492:545 */       return input.replace("LTI", "GEI");
/* 493:    */     }
/* 494:547 */     if (input.contains("GEF")) {
/* 495:548 */       return input.replace("GEF", "LTF");
/* 496:    */     }
/* 497:550 */     if (input.contains("LEF")) {
/* 498:551 */       return input.replace("LEF", "GTF");
/* 499:    */     }
/* 500:553 */     if (input.contains("NEF")) {
/* 501:554 */       return input.replace("NEF", "EQF");
/* 502:    */     }
/* 503:556 */     if (input.contains("EQF")) {
/* 504:557 */       return input.replace("EQF", "NEF");
/* 505:    */     }
/* 506:559 */     if (input.contains("GTF")) {
/* 507:560 */       return input.replace("GTF", "LEF");
/* 508:    */     }
/* 509:562 */     if (input.contains("LTF")) {
/* 510:563 */       return input.replace("LTF", "GEF");
/* 511:    */     }
/* 512:566 */     System.out.println("ERROR @ resolveDoComp");
/* 513:567 */     return null;
/* 514:    */   }
/* 515:    */   
/* 516:    */   public Node resolveComp(Node op1, Node op2, String op)
/* 517:    */   {
/* 518:572 */     if ((op1.type == 1) && (op2.type == 1))
/* 519:    */     {
/* 520:573 */       if (op.equalsIgnoreCase("<")) {
/* 521:574 */         return new Node("GEI " + op1.content + " " + op2.content, 4);
/* 522:    */       }
/* 523:576 */       if (op.equalsIgnoreCase(">")) {
/* 524:577 */         return new Node("LEI " + op1.content + " " + op2.content, 4);
/* 525:    */       }
/* 526:579 */       if (op.equalsIgnoreCase("=")) {
/* 527:580 */         return new Node("NEI " + op1.content + " " + op2.content, 4);
/* 528:    */       }
/* 529:582 */       if (op.equalsIgnoreCase("!=")) {
/* 530:583 */         return new Node("EQI " + op1.content + " " + op2.content, 4);
/* 531:    */       }
/* 532:585 */       if (op.equalsIgnoreCase("<=")) {
/* 533:586 */         return new Node("GTI " + op1.content + " " + op2.content, 4);
/* 534:    */       }
/* 535:588 */       if (op.equalsIgnoreCase(">=")) {
/* 536:589 */         return new Node("LTI " + op1.content + " " + op2.content, 4);
/* 537:    */       }
/* 538:592 */       System.out.println("ERROR @ resolveComp");
/* 539:593 */       return null;
/* 540:    */     }
/* 541:597 */     if (op.equalsIgnoreCase("<")) {
/* 542:598 */       return new Node("GEF " + op1.content + " " + op2.content, 4);
/* 543:    */     }
/* 544:600 */     if (op.equalsIgnoreCase(">")) {
/* 545:601 */       return new Node("LEF " + op1.content + " " + op2.content, 4);
/* 546:    */     }
/* 547:603 */     if (op.equalsIgnoreCase("=")) {
/* 548:604 */       return new Node("NEF " + op1.content + " " + op2.content, 4);
/* 549:    */     }
/* 550:606 */     if (op.equalsIgnoreCase("!=")) {
/* 551:607 */       return new Node("EQF " + op1.content + " " + op2.content, 4);
/* 552:    */     }
/* 553:609 */     if (op.equalsIgnoreCase("<=")) {
/* 554:610 */       return new Node("GTF " + op1.content + " " + op2.content, 4);
/* 555:    */     }
/* 556:612 */     if (op.equalsIgnoreCase(">=")) {
/* 557:613 */       return new Node("LTF " + op1.content + " " + op2.content, 4);
/* 558:    */     }
/* 559:616 */     System.out.println("ERROR @ resolveComp");
/* 560:617 */     return null;
/* 561:    */   }
/* 562:    */   
/* 563:    */   public String createLabel()
/* 564:    */   {
/* 565:625 */     this.labelIndex += 1;
/* 566:626 */     return "label" + Integer.toString(this.labelIndex);
/* 567:    */   }
/* 568:    */   
/* 569:    */   public String createTemp()
/* 570:    */   {
/* 571:630 */     this.tempIndex += 1;
/* 572:631 */     if (this.tempIndex > this.finalTempIndex) {
/* 573:632 */       this.finalTempIndex = this.tempIndex;
/* 574:    */     }
/* 575:635 */     ((ArrayList)this.tempMap.get(this.functionRecord)).add("$T" + Integer.toString(this.tempIndex));
/* 576:636 */     return "$T" + Integer.toString(this.tempIndex);
/* 577:    */   }
/* 578:    */   
/* 579:    */   public Node resolve(ArrayList<Node> input)
/* 580:    */   {
/* 581:641 */     while (input.size() >= 3)
/* 582:    */     {
/* 583:643 */       Node op1 = (Node)input.get(0);
/* 584:644 */       Node op = (Node)input.get(1);
/* 585:645 */       Node op2 = (Node)input.get(2);
/* 586:    */       
/* 587:647 */       Node newNode = new Node(createTemp(), op1.type);
/* 588:648 */       if (op.content.equalsIgnoreCase("+"))
/* 589:    */       {
/* 590:649 */         if (op1.type == 1)
/* 591:    */         {
/* 592:650 */           String output = "ADDI " + op1.content + " " + op2.content + " " + newNode.content;
/* 593:651 */           this.outputList.add(output);
/* 594:    */         }
/* 595:    */         else
/* 596:    */         {
/* 597:655 */           String output = "ADDF " + op1.content + " " + op2.content + " " + newNode.content;
/* 598:656 */           this.outputList.add(output);
/* 599:    */         }
/* 600:    */       }
/* 601:660 */       else if (op.content.equalsIgnoreCase("-"))
/* 602:    */       {
/* 603:661 */         if (op1.type == 1)
/* 604:    */         {
/* 605:662 */           String output = "SUBI " + op1.content + " " + op2.content + " " + newNode.content;
/* 606:663 */           this.outputList.add(output);
/* 607:    */         }
/* 608:    */         else
/* 609:    */         {
/* 610:667 */           String output = "SUBF " + op1.content + " " + op2.content + " " + newNode.content;
/* 611:668 */           this.outputList.add(output);
/* 612:    */         }
/* 613:    */       }
/* 614:672 */       else if (op.content.equalsIgnoreCase("*"))
/* 615:    */       {
/* 616:673 */         if (op1.type == 1)
/* 617:    */         {
/* 618:674 */           String output = "MULTI " + op1.content + " " + op2.content + " " + newNode.content;
/* 619:675 */           this.outputList.add(output);
/* 620:    */         }
/* 621:    */         else
/* 622:    */         {
/* 623:679 */           String output = "MULTF " + op1.content + " " + op2.content + " " + newNode.content;
/* 624:680 */           this.outputList.add(output);
/* 625:    */         }
/* 626:    */       }
/* 627:685 */       else if (op1.type == 1)
/* 628:    */       {
/* 629:686 */         String output = "DIVI " + op1.content + " " + op2.content + " " + newNode.content;
/* 630:687 */         this.outputList.add(output);
/* 631:    */       }
/* 632:    */       else
/* 633:    */       {
/* 634:691 */         String output = "DIVF " + op1.content + " " + op2.content + " " + newNode.content;
/* 635:692 */         this.outputList.add(output);
/* 636:    */       }
/* 637:696 */       input.remove(0);
/* 638:697 */       input.remove(0);
/* 639:698 */       input.remove(0);
/* 640:    */       
/* 641:700 */       input.add(0, newNode);
/* 642:    */     }
/* 643:704 */     Node returnValue = (Node)input.get(0);
/* 644:705 */     input.removeAll(input);
/* 645:706 */     return returnValue;
/* 646:    */   }
/* 647:    */   
/* 648:    */   public String printOutput()
/* 649:    */   {
/* 650:710 */     String result = "";
/* 651:711 */     for (int i = 0; i < this.outputList.size(); i++)
/* 652:    */     {
/* 653:712 */       result = result + (String)this.outputList.get(i);
/* 654:713 */       result = result + "\n";
/* 655:    */     }
/* 656:715 */     return result;
/* 657:    */   }
/* 658:    */ }


/* Location:           C:\Users\Adam\Downloads\step4.jar
 * Qualified Name:     EvalMicroBaseVisitor
 * JD-Core Version:    0.7.0.1
 */