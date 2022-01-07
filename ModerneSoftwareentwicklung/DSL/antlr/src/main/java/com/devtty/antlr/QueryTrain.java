package com.devtty.antlr;

import java.util.Map;
import java.util.Scanner;

public class QueryTrain {
    
    private String line;
    private Map<String, Fahrt> fahrts;
    
    public QueryTrain(String line, Map<String, Fahrt> fahrts){
        this.line = line;
        this.fahrts = fahrts;
    }
    
    public void query(){
        Scanner in = new Scanner(System.in);
        
        System.out.println("Start: " + line);
        
        while (true) {
            System.out.println("Abfahrtsort: ");
            String inStr = in.nextLine();
            if (!inStr.isEmpty()) {
                if (inStr.equals("exit")) 
                    return;
                
                Fahrt fahrt = fahrts.get(inStr);
                if (fahrt != null) 
                    System.out.printf("um %s nach %s%n", fahrt.getUhrzeit(), fahrt.getZiel());
                else 
                    System.out.println("keine Fahrt");
            }
        }
    }
    
}
