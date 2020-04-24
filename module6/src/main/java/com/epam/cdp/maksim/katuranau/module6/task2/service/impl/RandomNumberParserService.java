package com.epam.cdp.maksim.katuranau.module6.task2.service.impl;

import com.epam.cdp.maksim.katuranau.module6.task2.service.RandomNumberParserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Service;

@Service
public class RandomNumberParserService {

    private final RandomNumberParserFactory parserFactory;

    @Autowired
    public RandomNumberParserService(RandomNumberParserFactory parserFactory) {
        this.parserFactory = parserFactory;
    }

    public Expression doParse(String expression, String parserType) {
        SpelExpressionParser parser = parserFactory.getParser(parserType);
        return parser.parseExpression(expression);
    }
}
