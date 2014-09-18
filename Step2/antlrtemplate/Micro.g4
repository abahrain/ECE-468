grammar Micro;

program: 'PROGRAM' name 'BEGIN' program_body 'END';

name: IDENTIFIER;
program_body: declaration function;
declaration: (string_declaration_list | variable_declaration_list)*;

string_declaration_list: 'STRING' name ':=' STRINGLITERAL';';

variable_declaration_list: variable_type name_list';';
variable_type: 'FLOAT'|'INT';
any_type: variable_type | 'VOID';
name_list: name name_repeat;
name_repeat: (',' name name_repeat)*;

parameter_declaration_list: (parameter_declaration parameter_declaration_repeat)?;
parameter_declaration: variable_type name;
parameter_declaration_repeat: (',' parameter_declaration parameter_declaration_repeat)*;

function: (function_declaration function)?;
function_declaration: 'FUNCTION' any_type name '('parameter_declaration_list')' 'BEGIN' function_body 'END';
function_body: declaration statement_list;

statement_list: (statement statement_list)?;
statement: basic_statement | if_statement | while_statement;
basic_statement: assignment | read | write | re_turn;

assignment: assignment_frame';';
assignment_frame: name ':=' expression;
read: 'READ' '('name_list')'';';
write: 'WRITE' '('name_list')'';';
re_turn: 'RETURN' expression';';

expression: pre_expression factor;
pre_expression: pre_expression factor addition_operation | ;
factor: pre_factor post_expression;
pre_factor: pre_factor post_expression multiplication_operation | ;
post_expression: primary | expression_call;
expression_call: name '('expression_list')';
expression_list: (expression expression_list_repeat)*;
expression_list_repeat: (',' expression expression_list_repeat)*;
primary: '('expression')' | name | INTLITERAL | FLOATLITERAL;
addition_operation: '+'|'-';
multiplication_operation: '*'|'/';

if_statement: 'IF' '('condition')' declaration statement_list else_portion 'ENDIF';
else_portion: ('ELSE' declaration statement_list)?;
while_statement: 'WHILE' '('condition')' declaration statement_list 'ENDWHILE';
condition: expression comparison_operator expression;
comparison_operator: '<'|'>'|'='|'!='|'<='|'>=';

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
	: ('"')(~('\n'|'\r'|'"'))*('"')
	;

SPACE: (' ' | '\n' | '\t' | '\r' | '\f')+ -> skip;

COMMENT: '--'(~('\n'|'\r'))* -> skip;
