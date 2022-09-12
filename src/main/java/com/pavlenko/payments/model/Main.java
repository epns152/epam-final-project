package com.pavlenko.payments.model;

import com.pavlenko.payments.controller.Register;
import com.pavlenko.payments.model.DB.CustomerDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.SQLException;
import java.util.regex.Pattern;

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
        System.out.println(validate("login", "firstname", "lastname"));
    }

    private static boolean validate(String login, String firstname, String lastname) {
        Pattern loginPattern = Pattern.compile("(?=.{3,20}$)(?![_.])(?!.*[_.]{2})\\w+(?<![_.])");
        Pattern namePattern = Pattern.compile("(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[A-ZА-ЯІЄЇ][a-zа-яіїє]+(?<![_.])");
        if (login == null || firstname == null || lastname == null) {
            return false;
        }
        System.out.println(loginPattern.matcher(login).matches());
        System.out.println(namePattern.matcher(firstname).matches());
        System.out.println(namePattern.matcher(lastname).matches());
        return loginPattern.matcher(login).matches() && namePattern.matcher(firstname).matches() && namePattern.matcher(lastname).matches();
    }
}
