grammar Micro;

program: 'PROGRAM' name 'BEGIN' program_body 'END';

name: IDENTIFIER;
key: KEYWORD;
integer: INTLITERAL;

program_body: (declaration function_declaration)?;

declaration: (string_declaration_list | variable_declaration_list)*;

function_declaration: 'FUNCTION' key name '(' (key name(',')?)* ')' 'BEGIN' function_body 'END';

string_declaration_list: 'STRING' (name(',')?)+ (OPERATOR STRINGLITERAL)?';';

variable_declaration_list: (('INT' (name(',')?)+ (OPERATOR INTLITERAL)?)|('FLOAT' (name(',')?)+ (OPERATOR FLOATLITERAL)?))';';

function_body: (declaration statement ('RETURN' operation?';')?)?;

statement: (if_statement|while_loop|operation)+;

if_statement: 'IF' '(' operation ')' statement else_statement? 'ENDIF';

else_statement: 'ELSE' statement;

while_loop: 'WHILE' '(' operation ')' statement 'ENDWILE';

//for_loop: 'FOR' '(' loop_init loop_test loop_increment ')' ;

operation: name|integer ((OPERATOR name|integer)|(OPERATOR '('name|integer')'))+';'?;


KEYWORD
	: 'STRING'
	| 'INT'
	| 'VOID'
	| 'FLOAT'
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
