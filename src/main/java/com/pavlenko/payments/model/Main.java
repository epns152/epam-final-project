package com.pavlenko.payments.model;

import com.pavlenko.payments.model.DB.CustomerDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.SQLException;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws SQLException {
//        try (DBConnection dbConnection = new DBConnection()) {
//            Connection con = dbConnection.getConnection();
//            System.out.println("Connection successful!");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        LOGGER.trace("asd");
        LOGGER.debug("asd");
        LOGGER.info("asd");
        LOGGER.warn("asd");
        LOGGER.error("asd");
        System.out.println(CustomerDAOImpl.md5("321"));
        System.out.println(CustomerDAOImpl.md5("root"));
        System.out.println(CustomerDAOImpl.md5("admin"));
    }
}
