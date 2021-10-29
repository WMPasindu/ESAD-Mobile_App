package com.esad.group.assignment.two.dev.tokenTypes;

import com.esad.group.assignment.two.dev.interfaces.TokenType;

public class QRCode implements TokenType {
    @Override
    public String selectedTokenType() {
        return "QR_Code";
    }
}
