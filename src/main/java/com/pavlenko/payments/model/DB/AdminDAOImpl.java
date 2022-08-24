package com.pavlenko.payments.model.DB;

import com.pavlenko.payments.model.entity.Account;
import com.pavlenko.payments.model.entity.Payment;
import com.pavlenko.payments.model.entity.User;

import java.sql.*;
import java.util.ArrayList;

public class AdminDAOImpl implements AdminDAO {
    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT id, firstname, lastname, user_status, user_role, registration_date " +
                "FROM users WHERE user_role = 'customer';";
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
            return users;
        } catch (SQLException e) {
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public ArrayList<Payment> getAllUserPayments(int userId) {
        ArrayList<Payment> payments = new ArrayList<>();
        String query = "SELECT id, price, payment_name, creation_date, payment_status, account_id " +
                "FROM payments WHERE users_id = ?;";
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
            return payments;
        } catch (SQLException e) {
            throw new RuntimeException("Sql exc", e);
        }
    }


    @Override
    public ArrayList<Account> getAllUserAccounts(int userId) {
        ArrayList<Account> accounts = new ArrayList<>();
        String query = "SELECT id, account_name, balance_amount, unblock_request, account_status " +
                "FROM accounts WHERE Users_id = ?;";
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
            return accounts;
        } catch (SQLException e) {
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public boolean blockUser(int userId) {
        String query = "update users set user_status = 'blocked' where id = ?";
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public boolean unblockUser(int userId) {
        String query = "update users set user_status = 'unblocked' where id = ?";
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public boolean blockAccount(int accountId) {
        String query = "update accounts set account_status = 'blocked' where id = ?";
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, accountId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public boolean unblockAccount(int accountId) {
        String query = "update accounts set account_status = 'unblocked', unblock_request = 0 where id = ?";
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, accountId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public ArrayList<Account> getAllBlockedAccountsWithRequestToUnblock() {
        ArrayList<Account> accounts = new ArrayList<>();
        String query = "SELECT id, account_name, balance_amount, unblock_request, account_status " +
                "FROM accounts WHERE unblock_request = 1;";
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(query);
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
            return accounts;
        } catch (SQLException e) {
            throw new RuntimeException("Sql exc", e);
        }
    }
}
