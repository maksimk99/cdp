package com.epam.cdp.maksim.katuranau.module6.task2.spel;


import org.springframework.expression.Expression;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.math.BigDecimal;

public class RandomNumberFromOneValueSpelExpressionParser extends SpelExpressionParser {

    @Override
    public Expression parseExpression(String expressionString) throws ParseException {
        BigDecimal dollarCourse = this.createRandomDollarCourse(expressionString);
        return super.parseExpression(String.valueOf(dollarCourse));
    }

    private BigDecimal createRandomDollarCourse(String expressionString) {
        return BigDecimal.valueOf(Double.parseDouble(expressionString) + Math.random());
    }
}

