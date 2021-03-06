/**
* Define a grammar called Grammar
*/
grammar Grammar;
/*
* Parser Rules
*/

expression :
LPAREN expression RPAREN #ParenthesizedExpr
| expression EXPONENT expression #ExponentialExpr
| expression operatorToken=(MULTIPLY | DIVIDE) expression #MultiplicativeExpr
| expression operatorToken=(ADD | SUBTRACT) expression #AdditiveExpr
| NUMBER #NumberExpr
| SQRT LPAREN expression RPAREN #SquareRootExpression
;
/*
* Lexer Rules
*/
NUMBER : INT ('.' INT)?;
INT : ('0'..'9')+;
EXPONENT : '^';
MULTIPLY : '*';
DIVIDE : '/';
SUBTRACT : '-';
ADD : '+';
LPAREN : '(';
RPAREN : ')';
SQRT: 'sqrt';
WS : [ \t\r\n] -> channel(HIDDEN);