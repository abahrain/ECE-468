grammar Micro;

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
	
/*STRINGLITERAL
	: ('"')(~('\n'|'\r'))*('"')
	//: ('"')(~('\n'|'\r'))*?('"')
	;
*/

INTLITERAL
	: [0-9]+
	;

FLOATLITERAL
	: [0-9]*['.'][0-9]*
	// : [0-9]*?['.'][0-9]*  What is the difference between lazy and greedy?
	;
	
SPACE: (' ' | '\n' | '\t' | '\r' | '\f')+ -> skip;
//SPACE: (' '|'\\'.)+ -> skip;

COMMENT: '--'(~('\n'|'\r'))* -> skip;
