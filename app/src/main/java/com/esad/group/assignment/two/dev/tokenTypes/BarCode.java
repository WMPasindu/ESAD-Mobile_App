package com.esad.group.assignment.two.dev.tokenTypes;

import com.esad.group.assignment.two.dev.interfaces.TokenType;

public class BarCode implements TokenType {
    @Override
    public String selectedTokenType() {
        return "Bar_Code";
    }
}
