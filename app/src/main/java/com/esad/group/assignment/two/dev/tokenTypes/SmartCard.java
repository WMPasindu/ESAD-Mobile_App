package com.esad.group.assignment.two.dev.tokenTypes;

import com.esad.group.assignment.two.dev.interfaces.TokenType;

public class SmartCard implements TokenType {
    @Override
    public String selectedTokenType() {
        return "Smart_Card";
    }
}
