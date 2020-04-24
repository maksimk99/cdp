package com.epam.cdp.maksim.katuranau.module5.spel;


import org.springframework.expression.Expression;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CustomSpelExpressionParser extends SpelExpressionParser {

    @Override
    public Expression parseExpression(String expressionString) throws ParseException {
        BigDecimal dollarCourse = this.createRandomDollarCourse(expressionString);
        return super.parseExpression(String.valueOf(dollarCourse));
    }

    private BigDecimal createRandomDollarCourse(String expressionString) {
        String[] fromToString = expressionString.split(":");
        Double[] fromToDouble = new Double[2];
        fromToDouble[0] = Double.valueOf(fromToString[0]);
        fromToDouble[1] = Double.valueOf(fromToString[1]);
        return BigDecimal.valueOf(fromToDouble[0] + (fromToDouble[1] - fromToDouble[0]) * Math.random());
    }

}

