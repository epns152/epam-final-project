package com.pavlenko.payments.model.DB;

import com.pavlenko.payments.model.entity.Account;
import com.pavlenko.payments.model.entity.Payment;
import com.pavlenko.payments.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;

public class AdminDAOImpl implements AdminDAO {

    private static final Logger LOG = LoggerFactory.getLogger(AdminDAOImpl.class);
    private int noOfRecordsAccounts;
    private int noOfRecordsPayments;
    private int noOfRecordsUsers;

    private static final String BLOCK_USER = "update users set user_status = 'blocked' where id = ?";
    private static final String UNBLOCK_USER = "update users set user_status = 'unblocked' where id = ?";
    private static final String BLOCK_ACCOUNT = "update accounts set account_status = 'blocked' where id = ?";
    private static final String UNBLOCK_ACCOUNT = "update accounts set account_status = 'unblocked', unblock_request = 0 where id = ?";
    private static final String GET_ALL_ACCOUNTS_WITH_REQUEST_TO_UNBLOCK =
            "SELECT id, account_name, balance_amount, unblock_request, account_status " +
                    "FROM accounts WHERE unblock_request = 1;";

    @Override
    public ArrayList<User> getAllUsers(int offset, int noOfRecords) {
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT SQL_CALC_FOUND_ROWS id, firstname, lastname, user_status, user_role, registration_date " +
                "FROM users WHERE user_role = 'customer' limit " + offset + ", " + noOfRecords + ";";
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet;
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstname = resultSet.getString(2);
                String lastname = resultSet.getString(3);
                String userStatus = resultSet.getString(4);
                String userRole = resultSet.getString(5);
                Date registrationDate = resultSet.getDate(6);
                users.add(new User(userRole, id, firstname, lastname, userStatus, registrationDate));
            }
            resultSet.close();
            resultSet = preparedStatement.executeQuery("SELECT FOUND_ROWS()");
            if (resultSet.next()) {
                this.noOfRecordsUsers = resultSet.getInt(1);
            }
            LOG.info("got users by admin");
            return users;
        } catch (SQLException e) {
            LOG.error("sql exception", e);
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public ArrayList<Payment> getAllUserPayments(int userId, int offset, int noOfRecords) {
        ArrayList<Payment> payments = new ArrayList<>();
        String query = "SELECT SQL_CALC_FOUND_ROWS id, price, payment_name, creation_date, payment_status, account_id " +
                "FROM payments WHERE users_id = ? limit " + offset + ", " + noOfRecords + ";";
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet;
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                double price = resultSet.getDouble(2);
                String paymentName = resultSet.getString(3);
                Date date = resultSet.getDate(4);
                int paymentStatus = resultSet.getInt(5);
                int accountId = resultSet.getInt(6);
                payments.add(new Payment(id, price, paymentName, date, paymentStatus, accountId));
            }
            resultSet.close();
            resultSet = preparedStatement.executeQuery("SELECT FOUND_ROWS()");
            if (resultSet.next()) {
                this.noOfRecordsPayments = resultSet.getInt(1);
            }
            LOG.info("got user payments by admin");
            return payments;
        } catch (SQLException e) {
            LOG.error("sql exception", e);
            throw new RuntimeException("Sql exc", e);
        }
    }


    @Override
    public ArrayList<Account> getAllUserAccounts(int userId, int offset, int noOfRecords) {
        ArrayList<Account> accounts = new ArrayList<>();
        String query = "SELECT SQL_CALC_FOUND_ROWS id, account_name, balance_amount, unblock_request, account_status " +
                "FROM accounts WHERE Users_id = ? limit " + offset + ", " + noOfRecords + ";";
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet;
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String accountName = resultSet.getString(2);
                double balanceAmount = resultSet.getDouble(3);
                int unblockRequest = resultSet.getInt(4);
                String accountStatus = resultSet.getString(5);
                accounts.add(new Account(id, balanceAmount, accountName, accountStatus, unblockRequest));
            }
            resultSet.close();
            resultSet = preparedStatement.executeQuery("SELECT FOUND_ROWS()");
            if (resultSet.next()) {
                this.noOfRecordsAccounts = resultSet.getInt(1);
            }
            LOG.info("got user accounts by admin");
            return accounts;
        } catch (SQLException e) {
            LOG.error("sql exception", e);
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public boolean blockUser(int userId) {
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(BLOCK_USER);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            LOG.info("user blocked by admin");
            return true;
        } catch (SQLException e) {
            LOG.error("sql exception", e);
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public boolean unblockUser(int userId) {
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(UNBLOCK_USER);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            LOG.info("user unblocked by admin");
            return true;
        } catch (SQLException e) {
            LOG.error("sql exception", e);
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public boolean blockAccount(int accountId) {
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(BLOCK_ACCOUNT);
            preparedStatement.setInt(1, accountId);
            preparedStatement.executeUpdate();
            LOG.info("account blocked by admin");
            return true;
        } catch (SQLException e) {
            LOG.error("sql exception", e);
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public boolean unblockAccount(int accountId) {
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(UNBLOCK_ACCOUNT);
            preparedStatement.setInt(1, accountId);
            preparedStatement.executeUpdate();
            LOG.info("account unblocked by admin");
            return true;
        } catch (SQLException e) {
            LOG.error("sql exception", e);
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public ArrayList<Account> getAllBlockedAccountsWithRequestToUnblock() {
        ArrayList<Account> accounts = new ArrayList<>();
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(GET_ALL_ACCOUNTS_WITH_REQUEST_TO_UNBLOCK);
            ResultSet resultSet;
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String accountName = resultSet.getString(2);
                double balanceAmount = resultSet.getDouble(3);
                int unblockRequest = resultSet.getInt(4);
                String accountStatus = resultSet.getString(5);
                accounts.add(new Account(id, balanceAmount, accountName, accountStatus, unblockRequest));
            }
            LOG.info("get all blocked accounts with request to unblock by admin");
            return accounts;
        } catch (SQLException e) {
            LOG.error("sql exception", e);
            throw new RuntimeException("Sql exc", e);
        }
    }

    public int getNoOfRecordsAccounts() { return noOfRecordsAccounts; }
    public int getNoOfRecordsPayments() { return noOfRecordsPayments; }
    public int getNoOfRecordsUsers() { return noOfRecordsUsers; }
}
