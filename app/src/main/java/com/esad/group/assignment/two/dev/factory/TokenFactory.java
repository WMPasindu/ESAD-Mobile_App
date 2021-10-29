package com.esad.group.assignment.two.dev.factory;

import com.esad.group.assignment.two.dev.interfaces.TokenType;
import com.esad.group.assignment.two.dev.tokenTypes.BarCode;
import com.esad.group.assignment.two.dev.tokenTypes.QRCode;
import com.esad.group.assignment.two.dev.tokenTypes.SmartCard;

public class TokenFactory {

    public TokenType getTokenType(String selectedTokenType) {

        if (selectedTokenType == null) {
            return null;
        }
        if (selectedTokenType.equalsIgnoreCase("Bar Code")) {
            return new BarCode();
        } else if (selectedTokenType.equalsIgnoreCase("Smart Card")) {
            return new SmartCard();
        } else if (selectedTokenType.equalsIgnoreCase("QR Code")) {
            return new QRCode();
        }
        else if (selectedTokenType.equalsIgnoreCase("Select Type")) {
            return null;
        }

        return null;
    }
}
