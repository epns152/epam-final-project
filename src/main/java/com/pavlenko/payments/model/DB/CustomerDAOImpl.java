package com.pavlenko.payments.model.DB;

import com.pavlenko.payments.model.entity.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerDAOImpl.class);
    private int noOfRecordsAccounts;
    private int noOfRecordsPayments;
    private static final String GET_ALL_INFO = "SELECT firstname, lastname, registration_date, user_status FROM users WHERE id = ? and user_role = ?;";
    private static final String ACCOUNT_EXIST = "SELECT COUNT(*) FROM users WHERE login = ? and password = ?;";
    private static final String LOGIN = "SELECT id, user_role, user_status FROM users WHERE login = ? and password = ?;";
    private static final String REGISTER = "INSERT users(login, password, firstname, lastname) VALUES (?, ?, ?, ?);";
    private static final String ADD_ACCOUNT = "INSERT accounts(account_name, balance_amount, Users_id) VALUES (?, ?, ?);";
    private static final String BLOCK_ACCOUNT = "UPDATE accounts set account_status = ? where Users_id = ? and id = ?;";
    private static final String REQUEST_TO_UNBLOCK_ACCOUNT = "UPDATE accounts set unblock_request = ? where Users_id = ? and id = ?;";
    private static final String ADD_PAYMENT = "insert payments(price, payment_name, users_id, account_number_received) VALUES (?, ?, ?, (SELECT card_id from accounts where card_id = ? limit 1));";
    private static final String MAKE_PAYMENT_WITHDRAWAL = "update accounts set balance_amount = balance_amount - (select price from payments where id = ? limit 1)  where id = ?;";
    private static final String MAKE_PAYMENT_REPLENISHMENT = "update accounts set balance_amount = balance_amount + (select price from payments where id = ? limit 1)  where id = ?;";
    private static final String SELECT_RECEIVED_ACCOUNT_ID = "select id from accounts where card_id = (select account_number_received from payments where id = ? limit 1);";
    private static final String UPDATE_PAYMENT_STATUS = "update payments set account_number_sent = (select card_id from accounts where id = ? limit 1), payment_status = 0 where id = ?";
    private static final String REPLENISH_CARD = "update accounts set balance_amount = balance_amount + ? where id = ? and users_id = ?;";

    @Override
    public User getAllInfo(User user) {
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(GET_ALL_INFO);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getRole());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String firstname = resultSet.getString(1);
                String lastname = resultSet.getString(2);
                Date regDate = resultSet.getDate(3);
                String userStatus = resultSet.getString(4);
                LOG.info("user info got");
                return new User(user.getRole(), user.getId(), firstname, lastname, userStatus, regDate);
            }
            return null;
        } catch (SQLException e) {
            LOG.error("sql exception", e);
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public boolean accountExist(String login, String pass) {
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(ACCOUNT_EXIST);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, md5(pass));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            LOG.info("user exist");
            return resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            LOG.error("sql exception", e);
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public User login(String login, String pass) {
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(LOGIN);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, md5(pass));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String role = resultSet.getString(2);
                String status = resultSet.getString(3);
                if (status.equals("blocked")) {
                    LOG.warn("user is blocked");
                    return new User("none", -1);
                }
                LOG.info("user logged");
                return new User(role, id);
            }
            LOG.warn("user not found");
            return null;
        } catch (SQLException e) {
            LOG.error("sql exception", e);
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public boolean register(String login, String pass, String firstName, String lastName) {
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(REGISTER);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, md5(pass));
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, lastName);
            preparedStatement.executeUpdate();
            LOG.info("user registered");
            return true;
        } catch (SQLException e) {
            LOG.error("sql exception", e);
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public boolean addAccount(int userId, String name, double balance) {
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(ADD_ACCOUNT);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, balance);
            preparedStatement.setInt(3, userId);
            preparedStatement.executeUpdate();
            LOG.info("account added");
            return true;
        } catch (SQLException e) {
            LOG.error("sql exception", e);
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public boolean blockAccount(int userId, int accountId) {
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(BLOCK_ACCOUNT);
            preparedStatement.setString(1, "blocked");
            preparedStatement.setInt(3, accountId);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            LOG.info("account blocked by user");
            return true;
        } catch (SQLException e) {
            LOG.error("sql exception", e);
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public boolean requestToUnblockAccount(int userId, int accountId) {
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(REQUEST_TO_UNBLOCK_ACCOUNT);
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(3, accountId);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            LOG.info("request to unblock account by user");
            return true;
        } catch (SQLException e) {
            LOG.error("sql exception", e);
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public boolean addPayment(int userId, String name, double price, long receiveCard) {
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(ADD_PAYMENT);
            preparedStatement.setDouble(1, price);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, userId);
            preparedStatement.setLong(4, receiveCard);
            preparedStatement.executeUpdate();
            LOG.info("payment added");
            return true;
        } catch (SQLException e) {
            LOG.error("sql exception", e);
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public ArrayList<Account> getAccounts(User user, String sortBy, int offset, int noOfRecords) {
        ArrayList<Account> accounts = new ArrayList<>();
        String query = "SELECT SQL_CALC_FOUND_ROWS id, account_name, balance_amount, unblock_request, account_status, card_id" +
                " FROM accounts WHERE Users_id = ? ORDER BY " + sortBy + " limit " + offset + ", " + noOfRecords + ";";
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
                long cardNum = resultSet.getLong(6);
                accounts.add(new Account(id, balanceAmount, accountName, accountStatus, unblockRequest, cardNum));
            }
            resultSet.close();
            resultSet = preparedStatement.executeQuery("SELECT FOUND_ROWS()");
            if (resultSet.next()) {
                this.noOfRecordsAccounts = resultSet.getInt(1);
            }
            LOG.info("got user accounts");
            return accounts;
        } catch (SQLException e) {
            LOG.error("sql exception", e);
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public ArrayList<Payment> getPayments(User user, String sortBy, int offset, int noOfRecords) {
        ArrayList<Payment> payments = new ArrayList<>();
        String query = "SELECT SQL_CALC_FOUND_ROWS id, price, payment_name, creation_date, payment_status, " +
                "account_number_received, account_number_sent FROM payments WHERE users_id = ? ORDER BY " + sortBy + " limit " + offset + ", " + noOfRecords + ";";
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
                long cardNumReceived = resultSet.getLong(6);
                long cardNumSent = resultSet.getLong(7);
                payments.add(new Payment(id, price, paymentName, date, paymentStatus, cardNumReceived, cardNumSent));
            }
            resultSet.close();
            resultSet = preparedStatement.executeQuery("SELECT FOUND_ROWS()");
            if (resultSet.next()) {
                this.noOfRecordsPayments = resultSet.getInt(1);
            }
            LOG.info("got user payments");
            return payments;
        } catch (SQLException e) {
            LOG.error("sql exception", e);
            throw new RuntimeException("Sql exc", e);
        }
    }

    @Override
    public boolean makePayment(int accountId, int paymentId) {
//        private static final String ADD_PAYMENT = "insert payments(price, payment_name, users_id, account_number_received) VALUES (?, ?, ?, (SELECT card_id from accounts where card_id = ? limit 1));";
//        private static final String MAKE_PAYMENT_WITHDRAWAL = "update accounts set balance_amount = balance_amount - (select price from payments where id = ? limit 1)  where id = ?;";
//        private static final String MAKE_PAYMENT_REPLENISHMENT = "update accounts set balance_amount = balance_amount + (select price from payments where id = ? limit 1)  where id = ?;";
//        private static final String SELECT_RECEIVED_ACCOUNT_ID = "select id from accounts where card_id = (select account_number_received from payments where id = ? limit 1);";
//        private static final String UPDATE_PAYMENT_STATUS = "update payments set account_number_sent = (select card_id from accounts where id = ? limit 1), payment_status = 0 where id = ?";

        Connection con = null;
        try {
            int receivedId = 0;
            con = ConnectionPool.getConnection();
            con.setAutoCommit(false);
            PreparedStatement preparedStatement = con.prepareStatement(MAKE_PAYMENT_WITHDRAWAL);
            preparedStatement.setInt(1, paymentId);
            preparedStatement.setInt(2, accountId);
            preparedStatement.executeUpdate();
            preparedStatement = con.prepareStatement(SELECT_RECEIVED_ACCOUNT_ID);
            preparedStatement.setInt(1, paymentId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                receivedId = rs.getInt(1);
            }
            preparedStatement = con.prepareStatement(MAKE_PAYMENT_REPLENISHMENT);
            preparedStatement.setInt(1, paymentId);
            preparedStatement.setInt(2, receivedId);
            preparedStatement.executeUpdate();
            preparedStatement = con.prepareStatement(UPDATE_PAYMENT_STATUS);
            preparedStatement.setInt(1, accountId);
            preparedStatement.setInt(2, paymentId);
            preparedStatement.executeUpdate();
            con.commit();
            LOG.info("payment made");
            return true;
        } catch (SQLException e) {
            LOG.error("sql exception", e);
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("Sql exc", e);
        } finally {
            try {
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException e) {
                LOG.error("sql exception", e);
            }
        }
    }

    @Override
    public boolean replenishAccount(int accountId, int userId, double topUpAmount) {
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(REPLENISH_CARD);
            preparedStatement.setDouble(1, topUpAmount);
            preparedStatement.setInt(2, accountId);
            preparedStatement.setInt(3, userId);
            preparedStatement.executeUpdate();
            LOG.info("account replenished");
            return true;
        } catch (SQLException e) {
            LOG.error("sql exception", e);
            throw new RuntimeException("Sql exc", e);
        }
    }

    public static String md5(String pass) {
        return DigestUtils.md5Hex(pass).toLowerCase();
    }
    public int getNoOfRecordsAccounts() { return noOfRecordsAccounts; }
    public int getNoOfRecordsPayments() { return noOfRecordsPayments; }
}
