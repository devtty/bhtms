package com.devtty.antlr;

public class App {
    
    public static void main(String[] args) throws Exception {
        QueryTrain qt = new LineFactory().createLine(App.class.getResourceAsStream("/neb27.line"));
        qt.query();
    }
       
}
