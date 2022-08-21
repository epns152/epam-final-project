package com.pavlenko.payments.model.DB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private ConnectionPool() {
        //private constructor
    }

    private static ConnectionPool instance = null;

    public synchronized static ConnectionPool getInstance() {
        if (instance==null)
            instance = new ConnectionPool();
        return instance;
    }

    public Connection getConnection() {
        Context ctx;
        Connection c;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mypool");
            c = ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException("Naming exc", e);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("SQL exc", e);
        }
        return c;
    }
}
