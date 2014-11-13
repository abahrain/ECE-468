/*   1:    */ import java.util.ArrayList;
/*   2:    */ import java.util.LinkedHashMap;
/*   3:    */ import java.util.Map;
/*   4:    */ 
/*   5:    */ public class IRToTiny
/*   6:    */ {
/*   7:    */   ArrayList<String> IR;
/*   8: 17 */   String result = "";
/*   9:    */   SymbolTable table;
/*  10: 19 */   public int tempIndex = -1;
/*  11: 20 */   Map<String, String> TR = new LinkedHashMap();
/*  12:    */   
/*  13:    */   public IRToTiny(ArrayList<String> input, SymbolTable table)
/*  14:    */   {
/*  15: 22 */     this.IR = input;
/*  16: 23 */     this.table = table;
/*  17:    */   }
/*  18:    */   
/*  19:    */   public String toString()
/*  20:    */   {
/*  21: 28 */     this.result = this.result.concat(";IR code\n");
/*  22: 29 */     for (int i = 0; i < this.IR.size(); i++) {
/*  23: 30 */       this.result = this.result.concat(";" + (String)this.IR.get(i) + "\n");
/*  24:    */     }
/*  25: 32 */     this.result = this.result.concat(";tiny code\n");
/*  26:    */     
/*  27:    */ 
/*  28: 35 */     String[] varTable = this.table.toString().split(" ");
/*  29: 36 */     for (int i = 0; i < varTable.length; i++) {
/*  30: 38 */       if (varTable[i].contains("name")) {
/*  31: 39 */         if (varTable[(i + 3)].contains("STRING")) {
/*  32: 40 */           this.result = this.result.concat("str " + varTable[(i + 1)] + " " + varTable[(i + 5)].substring(varTable[(i + 5)].indexOf("\""), varTable[(i + 5)].lastIndexOf("\"") + 1) + "\n");
/*  33:    */         } else {
/*  34: 43 */           this.result = this.result.concat("var " + varTable[(i + 1)] + "\n");
/*  35:    */         }
/*  36:    */       }
/*  37:    */     }
/*  38: 47 */     for (int i = 0; i < this.IR.size(); i++)
/*  39:    */     {
/*  40: 48 */       String[] elements = ((String)this.IR.get(i)).split(" ");
/*  41: 49 */       if (elements[0].equalsIgnoreCase("STOREI"))
/*  42:    */       {
/*  43: 50 */         if (elements[1].contains("$T"))
/*  44:    */         {
/*  45: 51 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + elements[2] + "\n");
/*  46:    */         }
/*  47: 53 */         else if (elements[2].contains("$T"))
/*  48:    */         {
/*  49: 54 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[2]) + "\n");
/*  50:    */         }
/*  51:    */         else
/*  52:    */         {
/*  53: 57 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[2]) + "\n");
/*  54: 58 */           this.result = this.result.concat("move " + createTemp(elements[2]) + " " + elements[2] + "\n");
/*  55:    */         }
/*  56:    */       }
/*  57: 61 */       else if (elements[0].equalsIgnoreCase("MULTI"))
/*  58:    */       {
/*  59: 62 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/*  60:    */         {
/*  61: 63 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/*  62: 64 */           this.result = this.result.concat("muli " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/*  63:    */         }
/*  64: 66 */         else if (elements[1].contains("$T"))
/*  65:    */         {
/*  66: 67 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/*  67: 68 */           this.result = this.result.concat("muli " + elements[2] + " " + createTemp(elements[3]) + "\n");
/*  68:    */         }
/*  69: 70 */         else if (elements[2].contains("$T"))
/*  70:    */         {
/*  71: 71 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/*  72: 72 */           this.result = this.result.concat("muli " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/*  73:    */         }
/*  74:    */         else
/*  75:    */         {
/*  76: 75 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/*  77: 76 */           this.result = this.result.concat("muli " + elements[2] + " " + createTemp(elements[3]) + "\n");
/*  78:    */         }
/*  79:    */       }
/*  80: 79 */       else if (elements[0].equalsIgnoreCase("ADDI"))
/*  81:    */       {
/*  82: 80 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/*  83:    */         {
/*  84: 81 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/*  85: 82 */           this.result = this.result.concat("addi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/*  86:    */         }
/*  87: 84 */         else if (elements[1].contains("$T"))
/*  88:    */         {
/*  89: 85 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/*  90: 86 */           this.result = this.result.concat("addi " + elements[2] + " " + createTemp(elements[3]) + "\n");
/*  91:    */         }
/*  92: 88 */         else if (elements[2].contains("$T"))
/*  93:    */         {
/*  94: 89 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/*  95: 90 */           this.result = this.result.concat("addi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/*  96:    */         }
/*  97:    */         else
/*  98:    */         {
/*  99: 93 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 100: 94 */           this.result = this.result.concat("addi " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 101:    */         }
/* 102:    */       }
/* 103: 97 */       else if (elements[0].equalsIgnoreCase("DIVI"))
/* 104:    */       {
/* 105: 98 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/* 106:    */         {
/* 107: 99 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/* 108:100 */           this.result = this.result.concat("divi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/* 109:    */         }
/* 110:102 */         else if (elements[1].contains("$T"))
/* 111:    */         {
/* 112:103 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/* 113:104 */           this.result = this.result.concat("divi " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 114:    */         }
/* 115:106 */         else if (elements[2].contains("$T"))
/* 116:    */         {
/* 117:107 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 118:108 */           this.result = this.result.concat("divi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/* 119:    */         }
/* 120:    */         else
/* 121:    */         {
/* 122:111 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 123:112 */           this.result = this.result.concat("divi " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 124:    */         }
/* 125:    */       }
/* 126:115 */       else if (elements[0].equalsIgnoreCase("SUBI"))
/* 127:    */       {
/* 128:116 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/* 129:    */         {
/* 130:117 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/* 131:118 */           this.result = this.result.concat("subi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/* 132:    */         }
/* 133:120 */         else if (elements[1].contains("$T"))
/* 134:    */         {
/* 135:121 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/* 136:122 */           this.result = this.result.concat("subi " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 137:    */         }
/* 138:124 */         else if (elements[2].contains("$T"))
/* 139:    */         {
/* 140:125 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 141:126 */           this.result = this.result.concat("subi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/* 142:    */         }
/* 143:    */         else
/* 144:    */         {
/* 145:129 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 146:130 */           this.result = this.result.concat("subi " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 147:    */         }
/* 148:    */       }
/* 149:133 */       else if (elements[0].equalsIgnoreCase("WRITEI")) {
/* 150:134 */         this.result = this.result.concat("sys writei " + elements[1] + " " + "\n");
/* 151:136 */       } else if (elements[0].equalsIgnoreCase("WRITES")) {
/* 152:137 */         this.result = this.result.concat("sys writes " + elements[1] + "\n");
/* 153:139 */       } else if (elements[0].equalsIgnoreCase("READI")) {
/* 154:140 */         this.result = this.result.concat("sys readi " + elements[1] + " " + "\n");
/* 155:142 */       } else if (elements[0].equalsIgnoreCase("STOREF"))
/* 156:    */       {
/* 157:143 */         if (elements[1].contains("$T"))
/* 158:    */         {
/* 159:144 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + elements[2] + "\n");
/* 160:    */         }
/* 161:146 */         else if (elements[2].contains("$T"))
/* 162:    */         {
/* 163:147 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 164:    */         }
/* 165:    */         else
/* 166:    */         {
/* 167:150 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 168:151 */           this.result = this.result.concat("move " + createTemp(elements[2]) + " " + elements[2] + "\n");
/* 169:    */         }
/* 170:    */       }
/* 171:154 */       else if (elements[0].equalsIgnoreCase("MULTF"))
/* 172:    */       {
/* 173:155 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/* 174:    */         {
/* 175:156 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/* 176:157 */           this.result = this.result.concat("mulr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/* 177:    */         }
/* 178:159 */         else if (elements[1].contains("$T"))
/* 179:    */         {
/* 180:160 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/* 181:161 */           this.result = this.result.concat("mulr " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 182:    */         }
/* 183:163 */         else if (elements[2].contains("$T"))
/* 184:    */         {
/* 185:164 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 186:165 */           this.result = this.result.concat("mulr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/* 187:    */         }
/* 188:    */         else
/* 189:    */         {
/* 190:168 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 191:169 */           this.result = this.result.concat("mulr " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 192:    */         }
/* 193:    */       }
/* 194:172 */       else if (elements[0].equalsIgnoreCase("ADDF"))
/* 195:    */       {
/* 196:173 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/* 197:    */         {
/* 198:174 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/* 199:175 */           this.result = this.result.concat("addr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/* 200:    */         }
/* 201:177 */         else if (elements[1].contains("$T"))
/* 202:    */         {
/* 203:178 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/* 204:179 */           this.result = this.result.concat("addr " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 205:    */         }
/* 206:181 */         else if (elements[2].contains("$T"))
/* 207:    */         {
/* 208:182 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 209:183 */           this.result = this.result.concat("addr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/* 210:    */         }
/* 211:    */         else
/* 212:    */         {
/* 213:186 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 214:187 */           this.result = this.result.concat("addr " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 215:    */         }
/* 216:    */       }
/* 217:190 */       else if (elements[0].equalsIgnoreCase("DIVF"))
/* 218:    */       {
/* 219:191 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/* 220:    */         {
/* 221:192 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/* 222:193 */           this.result = this.result.concat("divr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/* 223:    */         }
/* 224:195 */         else if (elements[1].contains("$T"))
/* 225:    */         {
/* 226:196 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/* 227:197 */           this.result = this.result.concat("divr " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 228:    */         }
/* 229:199 */         else if (elements[2].contains("$T"))
/* 230:    */         {
/* 231:200 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 232:201 */           this.result = this.result.concat("divr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/* 233:    */         }
/* 234:    */         else
/* 235:    */         {
/* 236:204 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 237:205 */           this.result = this.result.concat("divr " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 238:    */         }
/* 239:    */       }
/* 240:208 */       else if (elements[0].equalsIgnoreCase("SUBF"))
/* 241:    */       {
/* 242:209 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/* 243:    */         {
/* 244:210 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/* 245:211 */           this.result = this.result.concat("subr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/* 246:    */         }
/* 247:213 */         else if (elements[1].contains("$T"))
/* 248:    */         {
/* 249:214 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/* 250:215 */           this.result = this.result.concat("subr " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 251:    */         }
/* 252:217 */         else if (elements[2].contains("$T"))
/* 253:    */         {
/* 254:218 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 255:219 */           this.result = this.result.concat("subr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/* 256:    */         }
/* 257:    */         else
/* 258:    */         {
/* 259:222 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 260:223 */           this.result = this.result.concat("subr " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 261:    */         }
/* 262:    */       }
/* 263:226 */       else if (elements[0].equalsIgnoreCase("WRITEF")) {
/* 264:227 */         this.result = this.result.concat("sys writer " + elements[1] + " " + "\n");
/* 265:229 */       } else if (elements[0].equalsIgnoreCase("READF")) {
/* 266:230 */         this.result = this.result.concat("sys readr " + elements[1] + " " + "\n");
/* 267:232 */       } else if (elements[0].equalsIgnoreCase("LABEL")) {
/* 268:233 */         this.result = this.result.concat("label " + elements[1] + " " + "\n");
/* 269:235 */       } else if (elements[0].equalsIgnoreCase("JUMP")) {
/* 270:236 */         this.result = this.result.concat("jmp " + elements[1] + " " + "\n");
/* 271:239 */       } else if (elements[0].equalsIgnoreCase("LEI"))
/* 272:    */       {
/* 273:240 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/* 274:    */         {
/* 275:241 */           this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
/* 276:242 */           this.result = this.result.concat("jle " + elements[3] + "\n");
/* 277:    */         }
/* 278:244 */         else if (elements[1].contains("$T"))
/* 279:    */         {
/* 280:245 */           this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
/* 281:246 */           this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
/* 282:247 */           this.result = this.result.concat("jle " + elements[3] + "\n");
/* 283:    */         }
/* 284:249 */         else if (elements[2].contains("$T"))
/* 285:    */         {
/* 286:250 */           this.result = this.result.concat("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 287:251 */           this.result = this.result.concat("jle " + elements[3] + "\n");
/* 288:    */         }
/* 289:    */         else
/* 290:    */         {
/* 291:254 */           this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
/* 292:255 */           this.result = this.result.concat("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 293:256 */           this.result = this.result.concat("jle " + elements[3] + "\n");
/* 294:    */         }
/* 295:    */       }
/* 296:259 */       else if (elements[0].equalsIgnoreCase("GEI"))
/* 297:    */       {
/* 298:260 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/* 299:    */         {
/* 300:261 */           this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
/* 301:262 */           this.result = this.result.concat("jge " + elements[3] + "\n");
/* 302:    */         }
/* 303:264 */         else if (elements[1].contains("$T"))
/* 304:    */         {
/* 305:265 */           this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
/* 306:266 */           this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
/* 307:267 */           this.result = this.result.concat("jge " + elements[3] + "\n");
/* 308:    */         }
/* 309:269 */         else if (elements[2].contains("$T"))
/* 310:    */         {
/* 311:270 */           this.result = this.result.concat("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 312:271 */           this.result = this.result.concat("jge " + elements[3] + "\n");
/* 313:    */         }
/* 314:    */         else
/* 315:    */         {
/* 316:274 */           this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
/* 317:275 */           this.result = this.result.concat("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 318:276 */           this.result = this.result.concat("jge " + elements[3] + "\n");
/* 319:    */         }
/* 320:    */       }
/* 321:279 */       else if (elements[0].equalsIgnoreCase("NEI"))
/* 322:    */       {
/* 323:280 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/* 324:    */         {
/* 325:281 */           this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
/* 326:282 */           this.result = this.result.concat("jne " + elements[3] + "\n");
/* 327:    */         }
/* 328:284 */         else if (elements[1].contains("$T"))
/* 329:    */         {
/* 330:285 */           this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
/* 331:286 */           this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
/* 332:287 */           this.result = this.result.concat("jne " + elements[3] + "\n");
/* 333:    */         }
/* 334:289 */         else if (elements[2].contains("$T"))
/* 335:    */         {
/* 336:290 */           this.result = this.result.concat("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 337:291 */           this.result = this.result.concat("jne " + elements[3] + "\n");
/* 338:    */         }
/* 339:    */         else
/* 340:    */         {
/* 341:294 */           this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
/* 342:295 */           this.result = this.result.concat("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 343:296 */           this.result = this.result.concat("jne " + elements[3] + "\n");
/* 344:    */         }
/* 345:    */       }
/* 346:299 */       else if (elements[0].equalsIgnoreCase("EQI"))
/* 347:    */       {
/* 348:300 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/* 349:    */         {
/* 350:301 */           this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
/* 351:302 */           this.result = this.result.concat("jeq " + elements[3] + "\n");
/* 352:    */         }
/* 353:304 */         else if (elements[1].contains("$T"))
/* 354:    */         {
/* 355:305 */           this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
/* 356:306 */           this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
/* 357:307 */           this.result = this.result.concat("jeq " + elements[3] + "\n");
/* 358:    */         }
/* 359:309 */         else if (elements[2].contains("$T"))
/* 360:    */         {
/* 361:310 */           this.result = this.result.concat("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 362:311 */           this.result = this.result.concat("jeq " + elements[3] + "\n");
/* 363:    */         }
/* 364:    */         else
/* 365:    */         {
/* 366:314 */           this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
/* 367:315 */           this.result = this.result.concat("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 368:316 */           this.result = this.result.concat("jeq " + elements[3] + "\n");
/* 369:    */         }
/* 370:    */       }
/* 371:319 */       else if (elements[0].equalsIgnoreCase("GTI"))
/* 372:    */       {
/* 373:320 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/* 374:    */         {
/* 375:321 */           this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
/* 376:322 */           this.result = this.result.concat("jgt " + elements[3] + "\n");
/* 377:    */         }
/* 378:324 */         else if (elements[1].contains("$T"))
/* 379:    */         {
/* 380:325 */           this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
/* 381:326 */           this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
/* 382:327 */           this.result = this.result.concat("jgt " + elements[3] + "\n");
/* 383:    */         }
/* 384:329 */         else if (elements[2].contains("$T"))
/* 385:    */         {
/* 386:330 */           this.result = this.result.concat("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 387:331 */           this.result = this.result.concat("jgt " + elements[3] + "\n");
/* 388:    */         }
/* 389:    */         else
/* 390:    */         {
/* 391:334 */           this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
/* 392:335 */           this.result = this.result.concat("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 393:336 */           this.result = this.result.concat("jgt " + elements[3] + "\n");
/* 394:    */         }
/* 395:    */       }
/* 396:339 */       else if (elements[0].equalsIgnoreCase("LTI"))
/* 397:    */       {
/* 398:340 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/* 399:    */         {
/* 400:341 */           this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
/* 401:342 */           this.result = this.result.concat("jlt " + elements[3] + "\n");
/* 402:    */         }
/* 403:344 */         else if (elements[1].contains("$T"))
/* 404:    */         {
/* 405:345 */           this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
/* 406:346 */           this.result = this.result.concat("cmpi " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
/* 407:347 */           this.result = this.result.concat("jlt " + elements[3] + "\n");
/* 408:    */         }
/* 409:349 */         else if (elements[2].contains("$T"))
/* 410:    */         {
/* 411:350 */           this.result = this.result.concat("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 412:351 */           this.result = this.result.concat("jlt " + elements[3] + "\n");
/* 413:    */         }
/* 414:    */         else
/* 415:    */         {
/* 416:354 */           this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
/* 417:355 */           this.result = this.result.concat("cmpi " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 418:356 */           this.result = this.result.concat("jlt " + elements[3] + "\n");
/* 419:    */         }
/* 420:    */       }
/* 421:360 */       else if (elements[0].equalsIgnoreCase("LEF"))
/* 422:    */       {
/* 423:361 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/* 424:    */         {
/* 425:362 */           this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
/* 426:363 */           this.result = this.result.concat("jle " + elements[3] + "\n");
/* 427:    */         }
/* 428:365 */         else if (elements[1].contains("$T"))
/* 429:    */         {
/* 430:366 */           this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
/* 431:367 */           this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
/* 432:368 */           this.result = this.result.concat("jle " + elements[3] + "\n");
/* 433:    */         }
/* 434:370 */         else if (elements[2].contains("$T"))
/* 435:    */         {
/* 436:371 */           this.result = this.result.concat("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 437:372 */           this.result = this.result.concat("jle " + elements[3] + "\n");
/* 438:    */         }
/* 439:    */         else
/* 440:    */         {
/* 441:375 */           this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
/* 442:376 */           this.result = this.result.concat("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 443:377 */           this.result = this.result.concat("jle " + elements[3] + "\n");
/* 444:    */         }
/* 445:    */       }
/* 446:380 */       else if (elements[0].equalsIgnoreCase("GEF"))
/* 447:    */       {
/* 448:381 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/* 449:    */         {
/* 450:382 */           this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
/* 451:383 */           this.result = this.result.concat("jge " + elements[3] + "\n");
/* 452:    */         }
/* 453:385 */         else if (elements[1].contains("$T"))
/* 454:    */         {
/* 455:386 */           this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
/* 456:387 */           this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
/* 457:388 */           this.result = this.result.concat("jge " + elements[3] + "\n");
/* 458:    */         }
/* 459:390 */         else if (elements[2].contains("$T"))
/* 460:    */         {
/* 461:391 */           this.result = this.result.concat("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 462:392 */           this.result = this.result.concat("jge " + elements[3] + "\n");
/* 463:    */         }
/* 464:    */         else
/* 465:    */         {
/* 466:395 */           this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
/* 467:396 */           this.result = this.result.concat("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 468:397 */           this.result = this.result.concat("jge " + elements[3] + "\n");
/* 469:    */         }
/* 470:    */       }
/* 471:400 */       else if (elements[0].equalsIgnoreCase("NEF"))
/* 472:    */       {
/* 473:401 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/* 474:    */         {
/* 475:402 */           this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
/* 476:403 */           this.result = this.result.concat("jne " + elements[3] + "\n");
/* 477:    */         }
/* 478:405 */         else if (elements[1].contains("$T"))
/* 479:    */         {
/* 480:406 */           this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
/* 481:407 */           this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
/* 482:408 */           this.result = this.result.concat("jne " + elements[3] + "\n");
/* 483:    */         }
/* 484:410 */         else if (elements[2].contains("$T"))
/* 485:    */         {
/* 486:411 */           this.result = this.result.concat("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 487:412 */           this.result = this.result.concat("jne " + elements[3] + "\n");
/* 488:    */         }
/* 489:    */         else
/* 490:    */         {
/* 491:415 */           this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
/* 492:416 */           this.result = this.result.concat("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 493:417 */           this.result = this.result.concat("jne " + elements[3] + "\n");
/* 494:    */         }
/* 495:    */       }
/* 496:420 */       else if (elements[0].equalsIgnoreCase("EQF"))
/* 497:    */       {
/* 498:421 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/* 499:    */         {
/* 500:422 */           this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
/* 501:423 */           this.result = this.result.concat("jeq " + elements[3] + "\n");
/* 502:    */         }
/* 503:425 */         else if (elements[1].contains("$T"))
/* 504:    */         {
/* 505:426 */           this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
/* 506:427 */           this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
/* 507:428 */           this.result = this.result.concat("jeq " + elements[3] + "\n");
/* 508:    */         }
/* 509:430 */         else if (elements[2].contains("$T"))
/* 510:    */         {
/* 511:431 */           this.result = this.result.concat("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 512:432 */           this.result = this.result.concat("jeq " + elements[3] + "\n");
/* 513:    */         }
/* 514:    */         else
/* 515:    */         {
/* 516:435 */           this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
/* 517:436 */           this.result = this.result.concat("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 518:437 */           this.result = this.result.concat("jeq " + elements[3] + "\n");
/* 519:    */         }
/* 520:    */       }
/* 521:440 */       else if (elements[0].equalsIgnoreCase("GTF"))
/* 522:    */       {
/* 523:441 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/* 524:    */         {
/* 525:442 */           this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
/* 526:443 */           this.result = this.result.concat("jgt " + elements[3] + "\n");
/* 527:    */         }
/* 528:445 */         else if (elements[1].contains("$T"))
/* 529:    */         {
/* 530:446 */           this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
/* 531:447 */           this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
/* 532:448 */           this.result = this.result.concat("jgt " + elements[3] + "\n");
/* 533:    */         }
/* 534:450 */         else if (elements[2].contains("$T"))
/* 535:    */         {
/* 536:451 */           this.result = this.result.concat("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 537:452 */           this.result = this.result.concat("jgt " + elements[3] + "\n");
/* 538:    */         }
/* 539:    */         else
/* 540:    */         {
/* 541:455 */           this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
/* 542:456 */           this.result = this.result.concat("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 543:457 */           this.result = this.result.concat("jgt " + elements[3] + "\n");
/* 544:    */         }
/* 545:    */       }
/* 546:460 */       else if (elements[0].equalsIgnoreCase("LTF")) {
/* 547:461 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/* 548:    */         {
/* 549:462 */           this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
/* 550:463 */           this.result = this.result.concat("jlt " + elements[3] + "\n");
/* 551:    */         }
/* 552:465 */         else if (elements[1].contains("$T"))
/* 553:    */         {
/* 554:466 */           this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
/* 555:467 */           this.result = this.result.concat("cmpr " + createTemp(elements[1]) + " " + createTemp(elements[2]) + "\n");
/* 556:468 */           this.result = this.result.concat("jlt " + elements[3] + "\n");
/* 557:    */         }
/* 558:470 */         else if (elements[2].contains("$T"))
/* 559:    */         {
/* 560:471 */           this.result = this.result.concat("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 561:472 */           this.result = this.result.concat("jlt " + elements[3] + "\n");
/* 562:    */         }
/* 563:    */         else
/* 564:    */         {
/* 565:475 */           this.result = this.result.concat("move " + elements[2] + " " + createTemp(elements[2]) + "\n");
/* 566:476 */           this.result = this.result.concat("cmpr " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 567:477 */           this.result = this.result.concat("jlt " + elements[3] + "\n");
/* 568:    */         }
/* 569:    */       }
/* 570:    */     }
/* 571:482 */     this.result = this.result.concat("sys halt");
/* 572:483 */     return this.result;
/* 573:    */   }
/* 574:    */   
/* 575:    */   public String createTemp(String temp)
/* 576:    */   {
/* 577:487 */     if (this.TR.get(temp) != null) {
/* 578:488 */       return (String)this.TR.get(temp);
/* 579:    */     }
/* 580:491 */     this.tempIndex += 1;
/* 581:492 */     String R = "r" + Integer.toString(this.tempIndex);
/* 582:493 */     this.TR.put(temp, R);
/* 583:494 */     return R;
/* 584:    */   }
/* 585:    */ }


/* Location:           C:\Users\Adam\Downloads\step5.jar
 * Qualified Name:     IRToTiny
 * JD-Core Version:    0.7.0.1
 */