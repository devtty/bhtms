package com.devtty.antlr;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Fahrt {
    
    private String start;
    private String ziel;
    private String uhrzeit;
}
