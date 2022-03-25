grammar Expr;

// The start rule
prog: stat+;

stat: expr NEWLINE    # printExpr
    | expr EOF        # eofExpr
    | NEWLINE         # blank
    ;

expr: expr op=(MUL | DIV) expr    # MulDiv
    | expr op=(ADD | SUB | MOD) expr    # AddSub
    | INT                         # int
    | '(' expr ')'                # parens
    ;

MUL: '*';
MOD: '%';
DIV: '/';
ADD: '+';
SUB: '-';

INT: [0-9]+;
NEWLINE: '\r' ? '\n';
WS: [ \t]+ -> skip;  // throw out whitespace
