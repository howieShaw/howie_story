package com.lianshang.generator.util;

import com.lianshang.generator.config.DBConfig;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by walker on 16/2/3.
 */
public class DBUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBUtil.class);

    private static final int MAX_ACTIVE_CONNECTION = 1;

    private static final int MAX_IDLE_CONNECTION = 1;

    static {
        try {
            Class.forName(getDriverName());
        }
        catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static BasicDataSource getDataSource(DBConfig dbConfig) {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(getDriverName());
        dataSource.setUrl(getConnectionUrl(dbConfig));
        dataSource.setUsername(dbConfig.getUsername());
        dataSource.setPassword(dbConfig.getPassword());
        dataSource.setMaxIdle(MAX_IDLE_CONNECTION);
        dataSource.setMaxActive(MAX_ACTIVE_CONNECTION);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnReturn(true);
        dataSource.setValidationQuery("select 1");
        dataSource.setTimeBetweenEvictionRunsMillis(600000);
        dataSource.setNumTestsPerEvictionRun(MAX_ACTIVE_CONNECTION);
        dataSource.setMinEvictableIdleTimeMillis(3600000);

        return dataSource;
    }

    public static void close(AutoCloseable autoCloseable) {

        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            }
            catch (Exception e) {
            }
        }
    }

    private static String getDriverName(){

        return "com.mysql.jdbc.Driver";
    }

    public static String getConnectionUrl(DBConfig dbConfig){

        return "jdbc:mysql://" + dbConfig.getIp() + ":" + dbConfig.getPort() + "/" + dbConfig.getDatabase() + "?characterEncoding=utf8";
    }
}
