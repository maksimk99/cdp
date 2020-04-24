package com.epam.cdp.maksim.katuranau.module6.task2.service;

import org.springframework.expression.spel.standard.SpelExpressionParser;

public interface RandomNumberParserFactory {

    public SpelExpressionParser getParser(String parserType);
}
