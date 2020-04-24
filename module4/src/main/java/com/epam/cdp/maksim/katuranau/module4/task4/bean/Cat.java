package com.epam.cdp.maksim.katuranau.module4.task4.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Cat extends Animal {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void play() {
        LOGGER.info("Cat play");
    }

    @Override
    public void voice() {
        LOGGER.info("Cat voice");
    }
}
