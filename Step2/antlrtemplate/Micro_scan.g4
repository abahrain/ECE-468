lexer grammar Micro_scan;

KEYWORD
	: 'PROGRAM'
	| 'BEGIN'
	| 'STRING'
	| 'FUNCTION'
	| 'INT'
	| 'IF'
	| 'RETURN'
	| 'ELSE'
	| 'ENDIF'
	| 'END'
	| 'VOID'
	| 'WRITE'
	| 'READ'
	| 'WHILE'
	| 'ENDWHILE'
	| 'FLOAT'
	| 'CONTINUE'
	| 'BREAK'
	;

IDENTIFIER
	:[A-z_][A-z0-9_]*
	;

OPERATOR
	: ','
	| ';'
	| '('
	| ')'
	| '>'
	| '<'
	| '-'
	| '+'
	| '*'
	| '/'
	| '='
	| ':='
	| '!='
	| '<='
	| '>='
	;
	
STRINGLITERAL
	: ('"')(~('\n'|'\r'))*('"')
	;

INTLITERAL
	: [0-9]+
	;

FLOATLITERAL
	: [0-9]*['.'][0-9]*
	;
	
SPACE: (' ' | '\n' | '\t' | '\r' | '\f')+ -> skip;

COMMENT: '--'(~('\n'|'\r'))* -> skip;
