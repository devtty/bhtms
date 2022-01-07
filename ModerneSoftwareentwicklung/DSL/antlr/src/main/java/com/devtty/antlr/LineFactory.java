package com.devtty.antlr;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import org.antlr.v4.runtime.*;

public class LineFactory {
  
    public QueryTrain createLine(InputStream in) throws IOException {
        LineLexer l = new LineLexer(new ANTLRInputStream(in));
        LineParser p = new LineParser(new CommonTokenStream(l));
        
        p.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });
        
        final AtomicReference<String> line = new AtomicReference<>();
        final Map<String, Fahrt> fahrts = new HashMap<>();
        
        p.addParseListener(new LineBaseListener() {
            @Override
            public void exitFahrt(LineParser.FahrtContext ctx) {
                Fahrt m = Fahrt.builder()
                        .uhrzeit(ctx.uhrzeit().getText())
                        .start(ctx.startbahnhof.getText())
                        .ziel(ctx.zielbahnhof.getText())
                        .build();
                fahrts.put(ctx.startbahnhof.getText(), m);
            }

            @Override
            public void exitLine(LineParser.LineContext ctx) {
                line.set(ctx.name.getText());
            }
            
        });
        
        p.line();
        
        return new QueryTrain(line.get(), fahrts);
        
    }
    
}
