grammar Micro;
	
@members
{
	public MakeSymbolTable tree = new MakeSymbolTable();
}

program: 'PROGRAM' name 'BEGIN' program_body 'END';

name returns [String identifier]: IDENTIFIER {$identifier = (String)$IDENTIFIER.text;};
program_body: declaration function;
declaration: (string_declaration_list | variable_declaration_list)*;

string_declaration_list: 'STRING' name ':=' string';'{tree.insertString($name.text,$string.text);};
string: STRINGLITERAL;
variable_declaration_list: variable_type name_list';'{tree.addListOfVariables($name_list.names,$variable_type.text);};
variable_type: 'FLOAT'|'INT';
any_type: variable_type | 'VOID';
name_list returns [ArrayList<String> names]: name name_repeat {$names = $name_repeat.names_list; $names.add(0,$name.text);};
name_repeat returns [ArrayList<String> names_list]: ',' name named_list = name_repeat{$names_list = $named_list.names_list; $names_list.add(0,$name.text);}|{$names_list = new ArrayList<String>();};

parameter_declaration_list: (parameter_declaration parameter_declaration_repeat)?;
parameter_declaration: variable_type name {tree.addSingleVariable($name.text,$variable_type.text);};
parameter_declaration_repeat: (',' parameter_declaration parameter_declaration_repeat)*;

function: (function_declaration function)?;
function_declaration: 'FUNCTION' any_type name {tree.openScope($name.text);} '('parameter_declaration_list')' 'BEGIN' function_body 'END'{tree.closeScope();};
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
expression_list: (expression expression_list_repeat)|;
expression_list_repeat: (',' expression expression_list_repeat)|;
primary: '('expression')' | name | INTLITERAL | FLOATLITERAL;
addition_operation: '+'|'-';
multiplication_operation: '*'|'/';

if_statement: 'IF' {tree.openScope();} '('condition')' declaration statement_list {tree.closeScope();} else_portion 'ENDIF';
else_portion: ( 'ELSE' {tree.openScope();} declaration statement_list {tree.closeScope();})?;
while_statement: 'WHILE' {tree.openScope();} '('condition')' declaration statement_list 'ENDWHILE' {tree.closeScope();};
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
