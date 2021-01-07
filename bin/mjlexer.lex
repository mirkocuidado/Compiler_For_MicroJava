
package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

/********** WHITE SPACES **********/
" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

/********** KEY WORDS **********/
"program"   { return new_symbol(sym.PROG, yytext());}
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"return" 	{ return new_symbol(sym.RETURN, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }
"break" 	{return new_symbol(sym.BREAK, yytext()); }
"class"     {return new_symbol(sym.CLASS, yytext()); }
"enum"		{return new_symbol(sym.ENUM, yytext()); }
"else"      {return new_symbol(sym.ELSE, yytext());}
"const"		{return new_symbol(sym.CONST, yytext());}
"if"		{return new_symbol(sym.IF, yytext()); }
"switch"	{return new_symbol(sym.SWITCH, yytext()); }
"do" 		{return new_symbol(sym.DO, yytext()); }
"while"		{return new_symbol(sym.WHILE, yytext()); }
"new"		{return new_symbol(sym.NEW, yytext()); }
"read" 		{return new_symbol(sym.READ, yytext()); }
"extends" 	{return new_symbol(sym.EXTENDS, yytext()); }
"continue"	{return new_symbol(sym.CONTINUE, yytext()); }
"case"		{return new_symbol(sym.CASE, yytext()); }

/********** OPERATORS **********/
"+" 		{ return new_symbol(sym.PLUS, yytext()); }
"=" 		{ return new_symbol(sym.EQUAL, yytext()); }
";" 		{ return new_symbol(sym.SEMI, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
"(" 		{ return new_symbol(sym.LPAREN, yytext()); }
")" 		{ return new_symbol(sym.RPAREN, yytext()); }
"{" 		{ return new_symbol(sym.LBRACE, yytext()); }
"}"			{ return new_symbol(sym.RBRACE, yytext()); }
"-" 		{ return new_symbol(sym.MINUS, yytext()); }
"*" 		{ return new_symbol(sym.MUL, yytext()); }
"/" 		{ return new_symbol(sym.DIV, yytext()); }
"%" 		{ return new_symbol(sym.MOD, yytext()); }
"==" 		{ return new_symbol(sym.EQUAL_COMPARATION, yytext()); }
"!=" 		{ return new_symbol(sym.NOT_EQUAL_COMPARATION, yytext()); }
">" 		{ return new_symbol(sym.GREATER, yytext()); }
">="		{ return new_symbol(sym.GREATER_OR_EQUAL, yytext()); }
"<" 		{ return new_symbol(sym.LESS, yytext()); }
"<=" 		{ return new_symbol(sym.LESS_OR_EQUAL, yytext()); }
"&&" 		{ return new_symbol(sym.AND, yytext()); }
"||" 		{ return new_symbol(sym.OR, yytext()); }
"++" 		{ return new_symbol(sym.PLUS_PLUS, yytext()); }
"--" 		{ return new_symbol(sym.MINUS_MINUS, yytext()); }
"." 		{ return new_symbol(sym.DOT, yytext()); }
"["			{ return new_symbol(sym.LSQUARE_BRACKET, yytext()); }
"]"			{ return new_symbol(sym.RSQUARE_BRACKET, yytext()); }
"?"			{ return new_symbol(sym.TERNARY, yytext()); }			
":" 		{ return new_symbol(sym.COLON, yytext()); }

/********** COMMENTS **********/
"//" {yybegin(COMMENT);}
<COMMENT> . {yybegin(COMMENT);}
<COMMENT> "\r\n" { yybegin(YYINITIAL); }


/********** NUMBER **********/
[0-9]+  { return new_symbol(sym.NUMBER, new Integer (yytext())); }

/********** CHAR **********/
'[\x20-\x7E]' {return new_symbol(sym.CHAR, new Character(yytext().charAt(1)));}

/********** BOOLEAN **********/
("true"|"false") {return new_symbol(sym.BOOLEAN, yytext());}

/********** IDENTIFICATORS **********/
([a-z]|[A-Z])[a-z|A-Z|0-9|_]* 	{return new_symbol (sym.IDENT, yytext()); }

/********** ERROR **********/
. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)); }

