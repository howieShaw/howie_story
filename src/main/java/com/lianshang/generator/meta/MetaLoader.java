package com.lianshang.generator.meta;

import com.lianshang.generator.config.ModuleConfig;
import com.lianshang.generator.exception.ServiceException;
import com.lianshang.generator.util.DBUtil;
import com.lianshang.generator.util.StringUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by walker on 16/2/4.
 */
public class MetaLoader {

    public static List<TableMeta> load(ModuleConfig config) throws ServiceException{

        Connection connection = null;

        List<TableMeta> tableMetas = null;

        try {

            DataSource dataSource = DBUtil.getDataSource(config.getDbConfig());

            connection = dataSource.getConnection();

            List<String> tables = loadTables(config, connection);

            tableMetas = new ArrayList<>();
            for (String table : tables) {

                TableMeta tableMeta = new TableMeta();
                tableMeta.setTableName(table);
                tableMeta.setColumnMetas(loadColumns(table, connection));
                tableMeta.setPrimaryKeys(loadPrimaryKeys(table, connection));
                tableMeta.setPrefixName(getClassPrefixName(config, table));

                tableMetas.add(tableMeta);
            }
        }
        catch (Exception e) {
            DBUtil.close(connection);
            throw new ServiceException(-1, e.getMessage());
        }

        DBUtil.close(connection);

        return tableMetas;
    }

    private static List<String> loadTables(ModuleConfig config, Connection connection) throws ServiceException{

        ResultSet resultSet = null;

        List<String> tables = null;

        try {
            DatabaseMetaData dbMetaData = connection.getMetaData();

           tables = new ArrayList<>();

            resultSet = dbMetaData.getTables(null, "%", "", new String[]{"TABLE"});
            while (resultSet.next()) {
                String table = resultSet.getString("TABLE_NAME");
                if (isValidTable(config, table)) {
                    tables.add(table);
                }
            }
        }
        catch (Exception e) {
            DBUtil.close(resultSet);

            throw new ServiceException(-1, e.getMessage());
        }

        DBUtil.close(resultSet);

        return tables;
    }

    private static List<ColumnMeta> loadColumns(String table, Connection connection) throws ServiceException{

        ResultSet resultSet = null;

        List<ColumnMeta> columnMetas = null;

        try {

            DatabaseMetaData dbMetaData = connection.getMetaData();

            columnMetas = new ArrayList<>();

            resultSet = dbMetaData.getColumns(null, "%", table, "%");
            while (resultSet.next()) {

                String columnName = resultSet.getString("COLUMN_NAME");
                String columnType = resultSet.getString("TYPE_NAME");
                int dataSize = resultSet.getInt("COLUMN_SIZE");
                int nullable = resultSet.getInt("NULLABLE");
                boolean isAutoIncrement = resultSet.getBoolean("IS_AUTOINCREMENT");
                String comment = resultSet.getString("REMARKS");

                ColumnMeta columnMeta = new ColumnMeta();
                columnMeta.setColumnName(columnName);
                columnMeta.setColumnType(ColumnTypeUtil.get(columnType, dataSize));
                columnMeta.setIsAutoIncrement(isAutoIncrement);
                columnMeta.setDataSize(dataSize);
                columnMeta.setNullable(nullable > 0);
                columnMeta.setComment(comment);

                columnMetas.add(columnMeta);
            }
        }
        catch (Exception e) {
            DBUtil.close(resultSet);

            throw new ServiceException(-1, e.getMessage());
        }

        DBUtil.close(resultSet);

        return columnMetas;
    }

    private static List<String> loadPrimaryKeys(String table, Connection connection) throws ServiceException{

        ResultSet resultSet = null;

        List<String> primaryKeys = null;

        try {

            DatabaseMetaData dbMetaData = connection.getMetaData();

            primaryKeys = new ArrayList<>();

            resultSet = dbMetaData.getPrimaryKeys(null, "%", table);
            while (resultSet.next()) {

                primaryKeys.add(resultSet.getString(4));
            }
        }
        catch (Exception e) {
            DBUtil.close(resultSet);

            throw new ServiceException(-1, e.getMessage());
        }

        DBUtil.close(resultSet);

        return primaryKeys;
    }

    private static boolean isValidTable(ModuleConfig config, String table) {

        if (config.getExclusionTables() != null) {
            for (String exclusionTable : config.getExclusionTables()) {
                if (table.equalsIgnoreCase(exclusionTable)) {
                    return false;
                }
            }
        }

        if (config.getTablePrefix() != null && !table.startsWith(config.getTablePrefix())) {
            return false;
        }

        if (config.getAllowedTables() != null) {

            for (String allowedTable : config.getAllowedTables()) {
                if ("*".equalsIgnoreCase(allowedTable) || table.equalsIgnoreCase(allowedTable)) {
                    return true;
                }
            }

            return false;
        }
        else {

            return true;
        }
    }

    private static String getClassPrefixName(ModuleConfig config, String tableName) {

        String name;

        if (config.getTablePrefix() != null && tableName.startsWith(config.getTablePrefix())) {

            name = tableName.substring(config.getTablePrefix().length());
        }
        else {
            name = tableName;
        }

        return getDefineName(name);
    }

    public static String getDefineName(String name) {

        StringBuilder builder = new StringBuilder();

        boolean nextNeedSuperCase = false;
        for (int i = 0; i < name.length(); ++i) {

            char ch = name.charAt(i);
            if (i == 0) {
                builder.append(StringUtil.getUpperChar(ch));
            }
            else {
                if ('_' == name.charAt(i)) {
                    nextNeedSuperCase = true;
                }
                else {
                    if (nextNeedSuperCase || StringUtil.isUpperChar(ch)) {
                        builder.append(StringUtil.getUpperChar(ch));
                    }
                    else {
                        builder.append(StringUtil.getLowerChar(ch));
                    }

                    nextNeedSuperCase = false;
                }
            }
        }

        return builder.toString();
    }
}
