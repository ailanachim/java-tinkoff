package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class Task0 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task0() {
    }

    public static void greet() {
        LOGGER.info("Привет, мир!");
    }
}
