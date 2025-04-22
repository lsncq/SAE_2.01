package org.example.demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Element {
    Herbe , Marguerite , Cactus , Roche;

    private static final List<Element> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Element randomElement()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}
