package com.lianshang.generator.util;

import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.meta.ColumnType;

import java.util.Random;

/**
 * Created by walker on 16/2/18.
 */
public class ValueGenerateUtil {

    public static String general(ColumnType type, int dataSize) throws ServiceException{

        switch (type) {
            case BOOl:
                return generalBool();
            case BYTE:
                return generalByte(dataSize);
            case INT:
                return generalInt(dataSize);
            case BIGINT:
                return generalLong(dataSize);
            case STRING:
                return generalString(dataSize);
            case DOUBLE:
                return generalDouble(dataSize);
            case DECIMAL:
                return generalDecimal(dataSize);
            case DATE:
                return generalDate();
            default:
                throw new ServiceException(-1, "invalid type " + type);
        }
    }

    private static String generalBool() {

        return "" + new Random().nextBoolean();
    }

    private static String generalByte(int dataSize) {

        int maxValue = getMaxByteValue(dataSize);
        int value = new Random().nextInt(maxValue);

        return "(byte)" + value;
    }

    private static String generalInt(int dataSize) {

        int maxValue = getMaxIntValue(dataSize);
        int value = new Random().nextInt(maxValue);

        return "" + value;
    }

    private static String generalLong(int dataSize) {

        long maxValue = getMaxLongValue(dataSize);

        long value = new Random().nextLong();
        if (value < 0) {
            value = -value;
        }
        while (value > maxValue) {
            value /= 2;
        }

        return "" + value + "L";
    }

    private static String generalDouble(int dataSize) {

        double maxValue = getMaxDoubleValue(dataSize);

        double value = new Random().nextDouble();
        if (value < 0) {
            value = -value;
        }
        while (value > maxValue) {
            value /= 2;
        }

        return "" + value;
    }

    private static String generalDate() {

        int diff = new Random().nextInt(200000) - 100000;

        return "new Date(" + (System.currentTimeMillis() + diff)+ "L)";
    }

    private static String generalDecimal(int dataSize) {

        return "new BigDecimal(" + generalDouble(dataSize) + ")";
    }

    private static String generalString(int dataSize) {

        int len = new Random().nextInt(dataSize) + 1;
        String array = "abcdefghijklmnopqrstuvwxyz";

        String str = "";
        for (int i = 0; i < len; ++i) {

            int id = new Random().nextInt(26);
            str += array.charAt(id);
        }

        return "\"" + str + "\"";
    }

    private static int getMaxByteValue(int dataSize) {

        int value = 1;
        for (int i = 0; i < dataSize; ++i) {
            value *= 2;
        }

        return value - 1;
    }

    private static int getMaxIntValue(int dataSize) {

        int value = 1;
        for (int i = 0; i < dataSize; ++i) {
            value *= 10;
        }

        if (value <= 0) {
            value = Integer.MAX_VALUE;
        }

        return value - 1;
    }

    private static long getMaxLongValue(int dataSize) {

        long value = 1;
        for (int i = 0; i < dataSize; ++i) {
            value *= 10;
        }

        if (value <= 0) {
            value = Long.MAX_VALUE;
        }

        return value - 1;
    }

    private static double getMaxDoubleValue(int dataSize) {

        double value = 1;
        for (int i = 0; i < dataSize; ++i) {
            value *= 10;
        }

        return value - 1;
    }
}
