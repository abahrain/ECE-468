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
/*  43: 50 */         if (elements[1].contains("$T")) {
/*  44: 51 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + elements[2] + "\n");
/*  45:    */         } else {
/*  46: 54 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[2]) + "\n");
/*  47:    */         }
/*  48:    */       }
/*  49: 57 */       else if (elements[0].equalsIgnoreCase("MULTI"))
/*  50:    */       {
/*  51: 58 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/*  52:    */         {
/*  53: 59 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/*  54: 60 */           this.result = this.result.concat("muli " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/*  55:    */         }
/*  56: 62 */         else if (elements[1].contains("$T"))
/*  57:    */         {
/*  58: 63 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/*  59: 64 */           this.result = this.result.concat("muli " + elements[2] + " " + createTemp(elements[3]) + "\n");
/*  60:    */         }
/*  61: 66 */         else if (elements[2].contains("$T"))
/*  62:    */         {
/*  63: 67 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/*  64: 68 */           this.result = this.result.concat("muli " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/*  65:    */         }
/*  66:    */         else
/*  67:    */         {
/*  68: 71 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/*  69: 72 */           this.result = this.result.concat("muli " + elements[2] + " " + createTemp(elements[3]) + "\n");
/*  70:    */         }
/*  71:    */       }
/*  72: 75 */       else if (elements[0].equalsIgnoreCase("ADDI"))
/*  73:    */       {
/*  74: 76 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/*  75:    */         {
/*  76: 77 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/*  77: 78 */           this.result = this.result.concat("addi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/*  78:    */         }
/*  79: 80 */         else if (elements[1].contains("$T"))
/*  80:    */         {
/*  81: 81 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/*  82: 82 */           this.result = this.result.concat("addi " + elements[2] + " " + createTemp(elements[3]) + "\n");
/*  83:    */         }
/*  84: 84 */         else if (elements[2].contains("$T"))
/*  85:    */         {
/*  86: 85 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/*  87: 86 */           this.result = this.result.concat("addi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/*  88:    */         }
/*  89:    */         else
/*  90:    */         {
/*  91: 89 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/*  92: 90 */           this.result = this.result.concat("addi " + elements[2] + " " + createTemp(elements[3]) + "\n");
/*  93:    */         }
/*  94:    */       }
/*  95: 93 */       else if (elements[0].equalsIgnoreCase("DIVI"))
/*  96:    */       {
/*  97: 94 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/*  98:    */         {
/*  99: 95 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/* 100: 96 */           this.result = this.result.concat("divi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/* 101:    */         }
/* 102: 98 */         else if (elements[1].contains("$T"))
/* 103:    */         {
/* 104: 99 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/* 105:100 */           this.result = this.result.concat("divi " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 106:    */         }
/* 107:102 */         else if (elements[2].contains("$T"))
/* 108:    */         {
/* 109:103 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 110:104 */           this.result = this.result.concat("divi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/* 111:    */         }
/* 112:    */         else
/* 113:    */         {
/* 114:107 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 115:108 */           this.result = this.result.concat("divi " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 116:    */         }
/* 117:    */       }
/* 118:111 */       else if (elements[0].equalsIgnoreCase("SUBI"))
/* 119:    */       {
/* 120:112 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/* 121:    */         {
/* 122:113 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/* 123:114 */           this.result = this.result.concat("subi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/* 124:    */         }
/* 125:116 */         else if (elements[1].contains("$T"))
/* 126:    */         {
/* 127:117 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/* 128:118 */           this.result = this.result.concat("subi " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 129:    */         }
/* 130:120 */         else if (elements[2].contains("$T"))
/* 131:    */         {
/* 132:121 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 133:122 */           this.result = this.result.concat("subi " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/* 134:    */         }
/* 135:    */         else
/* 136:    */         {
/* 137:125 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 138:126 */           this.result = this.result.concat("subi " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 139:    */         }
/* 140:    */       }
/* 141:129 */       else if (elements[0].equalsIgnoreCase("WRITEI"))
/* 142:    */       {
/* 143:130 */         if (elements[1].contains("$")) {
/* 144:131 */           this.result = this.result.concat("sys writei " + createTemp(elements[1]) + "\n");
/* 145:    */         } else {
/* 146:134 */           this.result = this.result.concat("sys writei " + elements[1] + "\n");
/* 147:    */         }
/* 148:    */       }
/* 149:137 */       else if (elements[0].equalsIgnoreCase("WRITES")) {
/* 150:138 */         this.result = this.result.concat("sys writes " + elements[1] + "\n");
/* 151:140 */       } else if (elements[0].equalsIgnoreCase("READI")) {
/* 152:141 */         this.result = this.result.concat("sys readi " + elements[1] + " " + "\n");
/* 153:143 */       } else if (elements[0].equalsIgnoreCase("STOREF"))
/* 154:    */       {
/* 155:144 */         if (elements[1].contains("$T")) {
/* 156:145 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + elements[2] + "\n");
/* 157:    */         } else {
/* 158:148 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[2]) + "\n");
/* 159:    */         }
/* 160:    */       }
/* 161:151 */       else if (elements[0].equalsIgnoreCase("MULTF"))
/* 162:    */       {
/* 163:152 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/* 164:    */         {
/* 165:153 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/* 166:154 */           this.result = this.result.concat("mulr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/* 167:    */         }
/* 168:156 */         else if (elements[1].contains("$T"))
/* 169:    */         {
/* 170:157 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/* 171:158 */           this.result = this.result.concat("mulr " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 172:    */         }
/* 173:160 */         else if (elements[2].contains("$T"))
/* 174:    */         {
/* 175:161 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 176:162 */           this.result = this.result.concat("mulr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/* 177:    */         }
/* 178:    */         else
/* 179:    */         {
/* 180:165 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 181:166 */           this.result = this.result.concat("mulr " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 182:    */         }
/* 183:    */       }
/* 184:169 */       else if (elements[0].equalsIgnoreCase("ADDF"))
/* 185:    */       {
/* 186:170 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/* 187:    */         {
/* 188:171 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/* 189:172 */           this.result = this.result.concat("addr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/* 190:    */         }
/* 191:174 */         else if (elements[1].contains("$T"))
/* 192:    */         {
/* 193:175 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/* 194:176 */           this.result = this.result.concat("addr " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 195:    */         }
/* 196:178 */         else if (elements[2].contains("$T"))
/* 197:    */         {
/* 198:179 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 199:180 */           this.result = this.result.concat("addr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/* 200:    */         }
/* 201:    */         else
/* 202:    */         {
/* 203:183 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 204:184 */           this.result = this.result.concat("addr " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 205:    */         }
/* 206:    */       }
/* 207:187 */       else if (elements[0].equalsIgnoreCase("DIVF"))
/* 208:    */       {
/* 209:188 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/* 210:    */         {
/* 211:189 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/* 212:190 */           this.result = this.result.concat("divr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/* 213:    */         }
/* 214:192 */         else if (elements[1].contains("$T"))
/* 215:    */         {
/* 216:193 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/* 217:194 */           this.result = this.result.concat("divr " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 218:    */         }
/* 219:196 */         else if (elements[2].contains("$T"))
/* 220:    */         {
/* 221:197 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 222:198 */           this.result = this.result.concat("divr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/* 223:    */         }
/* 224:    */         else
/* 225:    */         {
/* 226:201 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 227:202 */           this.result = this.result.concat("divr " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 228:    */         }
/* 229:    */       }
/* 230:205 */       else if (elements[0].equalsIgnoreCase("SUBF"))
/* 231:    */       {
/* 232:206 */         if ((elements[1].contains("$T")) && (elements[2].contains("$T")))
/* 233:    */         {
/* 234:207 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/* 235:208 */           this.result = this.result.concat("subr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/* 236:    */         }
/* 237:210 */         else if (elements[1].contains("$T"))
/* 238:    */         {
/* 239:211 */           this.result = this.result.concat("move " + createTemp(elements[1]) + " " + createTemp(elements[3]) + "\n");
/* 240:212 */           this.result = this.result.concat("subr " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 241:    */         }
/* 242:214 */         else if (elements[2].contains("$T"))
/* 243:    */         {
/* 244:215 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 245:216 */           this.result = this.result.concat("subr " + createTemp(elements[2]) + " " + createTemp(elements[3]) + "\n");
/* 246:    */         }
/* 247:    */         else
/* 248:    */         {
/* 249:219 */           this.result = this.result.concat("move " + elements[1] + " " + createTemp(elements[3]) + "\n");
/* 250:220 */           this.result = this.result.concat("subr " + elements[2] + " " + createTemp(elements[3]) + "\n");
/* 251:    */         }
/* 252:    */       }
/* 253:223 */       else if (elements[0].equalsIgnoreCase("WRITEF")) {
/* 254:224 */         this.result = this.result.concat("sys writer " + elements[1] + " " + "\n");
/* 255:226 */       } else if (elements[0].equalsIgnoreCase("READF")) {
/* 256:227 */         this.result = this.result.concat("sys readr " + elements[1] + " " + "\n");
/* 257:    */       }
/* 258:    */     }
/* 259:230 */     this.result = this.result.concat("sys halt");
/* 260:231 */     return this.result;
/* 261:    */   }
/* 262:    */   
/* 263:    */   public String createTemp(String temp)
/* 264:    */   {
/* 265:235 */     if (this.TR.get(temp) != null) {
/* 266:236 */       return (String)this.TR.get(temp);
/* 267:    */     }
/* 268:239 */     this.tempIndex += 1;
/* 269:240 */     String R = "r" + Integer.toString(this.tempIndex);
/* 270:241 */     this.TR.put(temp, R);
/* 271:242 */     return R;
/* 272:    */   }
/* 273:    */ }


/* Location:           C:\Users\Adam\Downloads\step4.jar
 * Qualified Name:     IRToTiny
 * JD-Core Version:    0.7.0.1
 */