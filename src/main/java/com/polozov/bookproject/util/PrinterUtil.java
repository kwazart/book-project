package com.polozov.bookproject.util;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrinterUtil {
    public void printObject(Object obj) {
        System.out.println(obj);
    }

    public void printObjectList(List<?> objects) {
        for (Object o : objects) {
            printObject(o);
        }
    }
}
