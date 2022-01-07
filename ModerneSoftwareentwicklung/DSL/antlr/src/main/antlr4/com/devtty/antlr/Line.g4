grammar Line;

line:
  name=Str NL
  (fahrt NL)+
  EOF;
  
fahrt: uhrzeit WS 'Uhr' WS 'von' WS startbahnhof=Str WS 'nach' WS zielbahnhof=Str;
uhrzeit: stunde=Int ':' minute=Int;

Str: '"' ('A'..'Z' | 'a'..'z' | ' ')+ '"' ;
Int: ('0'..'9')+;

WS: (' ' | '\t')+;
NL: '\r'? '\n';
