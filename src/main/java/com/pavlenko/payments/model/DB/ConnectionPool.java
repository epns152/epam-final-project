package com.pavlenko.payments.model.DB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class  ConnectionPool {

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionPool.class);

    private ConnectionPool() {
        //private constructor
    }

    private static DataSource dataSource;

    static {
        try {
            Context ctx;
            ctx = new InitialContext();
            dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/mypool");
        } catch (NamingException e) {
            e.printStackTrace();
            LOG.error("Connection pool error", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
