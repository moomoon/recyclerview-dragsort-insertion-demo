package org.dxm.recyclerviewinsertion;

import java.util.Random;

/**
 * Created by ants on 9/13/16.
 */

public class Utils {
    private static final Random colorRandom = new Random();
    public static int randomColor() {
        return colorRandom.nextInt() | 0xFF000000;
    }
}
