package com.devtty.antlr;

import org.antlr.v4.runtime.*;
import org.junit.jupiter.api.Test;

public class AntlrTest {

    /*    public AntlrTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
     */
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testExampleField() throws Exception {
        LineLexer l = new LineLexer(new ANTLRInputStream(getClass().getResourceAsStream("/neb27.line")));
        LineParser p = new LineParser(new CommonTokenStream(l));
        p.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });
        p.line();
    }

}
