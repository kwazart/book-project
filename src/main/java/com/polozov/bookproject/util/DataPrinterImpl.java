package com.polozov.bookproject.util;

import org.springframework.stereotype.Component;

@Component
public class DataPrinterImpl implements DataPrinter {

    @Override
    public void printLine(String line) {
        System.out.println(line);
    }
}
