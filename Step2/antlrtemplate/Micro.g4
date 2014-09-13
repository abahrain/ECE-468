grammar Micro;

valid: 'PROGRAM' id 'BEGIN' pgm_body 'END';

id: IDENTIFIER;

pgm_body: ;

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

INTLITERAL
	: [0-9]+
	;

FLOATLITERAL
	: [0-9]*['.'][0-9]*
	;
	
STRINGLITERAL
	: ('"')(~('\n'|'\r'))*('"')
	;

SPACE: (' ' | '\n' | '\t' | '\r' | '\f')+ -> skip;

COMMENT: '--'(~('\n'|'\r'))* -> skip;
