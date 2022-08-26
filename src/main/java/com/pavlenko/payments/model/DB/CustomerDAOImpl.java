package com.pavlenko.payments.model.DB;

import com.pavlenko.payments.model.entity.*;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public User getAllInfo(User user) {
        String query = "SELECT firstname, lastname, registration_date, user_status FROM users WHERE id = ? and user_role = ?;";
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getRole());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String firstname = resultSet.getString(1);
                String lastname = resultSet.getString(2);
                Date regDate = resultSet.getDate(3);
                String userStatus = resultSet.getString(4);
                return new User(user.getRole(), user.getId(), firstname, lastname, userStatus, regDate);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public boolean accountExist(String login, String pass) {
        String query = "SELECT COUNT(*) FROM users WHERE login = ? and password = ?;";
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, pass);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public User login(String login, String pass) {
        String query = "SELECT id, user_role FROM users WHERE login = ? and password = ?;";
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, pass);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String role = resultSet.getString(2);
                return new User(role, id);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public boolean register(String login, String pass, String firstName, String lastName) {
        String query = "INSERT users(login, password, firstname, lastname) VALUES (?, ?, ?, ?);";
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, pass);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, lastName);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public boolean addAccount(int userId, String name, double balance) {
        String query = "INSERT accounts(account_name, balance_amount, Users_id) VALUES (?, ?, ?);";
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, balance);
            preparedStatement.setInt(3, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public boolean blockAccount(int userId, int accountId) {
        String query = "UPDATE accounts set account_status = ? where Users_id = ? and id = ?;";
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, "blocked");
            preparedStatement.setInt(3, accountId);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public boolean requestToUnblockAccount(int userId, int accountId) {
        String query = "UPDATE accounts set unblock_request = ? where Users_id = ? and id = ?;";
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(3, accountId);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public boolean addPayment(int userId, String name, double price) {
        String query = "INSERT payments(price, payment_name, users_id) VALUES (?, ?, ?);";
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setDouble(1, price);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public ArrayList<Account> getAccounts(User user, String sortBy) {
        ArrayList<Account> accounts = new ArrayList<>();
        String query = "SELECT id, account_name, balance_amount, unblock_request, account_status" +
                " FROM accounts WHERE Users_id = ? ORDER BY " + sortBy + ";";
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, user.getId());
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
    public ArrayList<Payment> getPayments(User user, String sortBy) {
        ArrayList<Payment> payments = new ArrayList<>();
        String query = "SELECT id, price, payment_name, creation_date, payment_status, account_id" +
                " FROM payments WHERE users_id = ? ORDER BY " + sortBy + ";";
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, user.getId());
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
    public boolean makePayment(int accountId, int paymentId) {
        String query = "update accounts set balance_amount = balance_amount - " +
                "(select price from payments where id = ? limit 1)  where id = ?;"
//                "update payments set payment_status = 0 where id = 1;"
                ;
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, paymentId);
            preparedStatement.setInt(2, accountId);
            preparedStatement.executeUpdate();
            preparedStatement = con.prepareStatement("update payments set payment_status = 0 where id = ?;");
            preparedStatement.setInt(1, paymentId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Sql exc", e);
        }
    }

}
