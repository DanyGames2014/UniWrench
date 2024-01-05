package net.danygames2014.uniwrench.util;

public class MathUtil {
    public static int clamp(int value, int min, int max) {
        if (value > max) {
            return max;
        }

        if (value < min) {
            return min;
        }

        return value;
    }
}
