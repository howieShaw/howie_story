package com.lianshang.generator.meta;

import com.lianshang.generator.exception.ServiceException;

/**
 * Created by walker on 16/2/4.
 */
public class ColumnTypeUtil {

    public static ColumnType get(String type, int dataSize) throws ServiceException{

        if ("BIT".equalsIgnoreCase(type)) {
            return ColumnType.BOOl;
        }
        else if ("TINYINT UNSIGNED".equalsIgnoreCase(type) && dataSize == 1) {
            return ColumnType.BOOl;
        }
        else if ("TINYINT".equalsIgnoreCase(type)
                || "TINYINT UNSIGNED".equalsIgnoreCase(type)) {
            return ColumnType.BYTE;
        }
        else if ("INT".equalsIgnoreCase(type)
                || "INT UNSIGNED".equalsIgnoreCase(type)) {
            return ColumnType.INT;
        }
        else if ("BIGINT".equalsIgnoreCase(type)
                || "BIGINT UNSIGNED".equalsIgnoreCase(type)) {
            return ColumnType.BIGINT;
        }
        else if ("VARCHAR".equalsIgnoreCase(type)
                || "CHAR".equalsIgnoreCase(type)
                || "TEXT".equalsIgnoreCase(type)
                || "MEDIUMTEXT".equalsIgnoreCase(type)) {
            return ColumnType.STRING;
        }
        else if ("FLOAT".equalsIgnoreCase(type)
                || "FLOAT UNSIGNED".equalsIgnoreCase(type)
                || "DOUBLE".equalsIgnoreCase(type)
                || "DOUBLE UNSIGNED".equalsIgnoreCase(type)) {
            return ColumnType.DOUBLE;
        }
        else if ("DECIMAL".equalsIgnoreCase(type)
                || "DECIMAL UNSIGNED".equalsIgnoreCase(type)) {
            return ColumnType.DECIMAL;
        }
        else if ("TIMESTAMP".equalsIgnoreCase(type)
                || "DATE".equalsIgnoreCase(type)
                || "DATETIME".equalsIgnoreCase(type)) {
            return ColumnType.DATE;
        }

        throw new ServiceException(-1, type + "not supported filed type");
    }
}
