package org.dxm.recyclerviewinsertion;

import android.graphics.PointF;
import android.view.View;

import java.util.Random;

/**
 * Created by ants on 9/13/16.
 */

public class Utils {
    private static final Random colorRandom = new Random();
    public static int randomColor() {
        return colorRandom.nextInt() | 0xFF000000;
    }

    public static PointF translate(PointF point, View from, View to) {
        PointF translated = new PointF(point.x, point.y);
        int[] loc = new int[2];
        from.getLocationOnScreen(loc);
        translated.offset(loc[0], loc[1]);
        to.getLocationOnScreen(loc);
        translated.offset(-loc[0], -loc[1]);
        return translated;
    }
}
