/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudroles.dao;

import com.mycompany.crudroles.model.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duvan
 */
public class UserDAO {

    private String jdbcURL;
    private String jdbcUser;
    private String jdbcPassword;
    Connection jdbcConnection;

    public UserDAO(String jdbcURL, String jdbcUser, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUser = jdbcUser;
        this.jdbcPassword = jdbcPassword;
    }

    private void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
        }

        jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPassword);
    }

    private void disconnect() throws SQLException {
        if (jdbcConnection != null || !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }

    }

    public boolean validateLogin(User user) throws SQLException, NoSuchAlgorithmException {
        //user
        boolean validUser = false;

        //SQL SENTENCE
        String sql = "SELECT id_role FROM tbusers WHERE username = ? AND password = ?";
        //connect to database
        connect();
        //statement

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getUsername());
        statement.setString(2, EncPassword(user.getPassword()));

        // Resultset
        ResultSet resultset = statement.executeQuery();
        //attributes

        validUser = resultset.next();

        statement.close();
        resultset.close();
        disconnect();

        return validUser;
    }

    public User getUserLogin(User user) throws SQLException, NoSuchAlgorithmException {
        //user
        User loginUser = null;

        //SQL SENTENCE
        String sql = "SELECT * FROM tbusers WHERE username = ? AND password = ?";
        //connect to database
        connect();
        //statement

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getUsername());
        statement.setString(2, EncPassword(user.getPassword()));

        // Resultset
        ResultSet resultset = statement.executeQuery();

        //attributes
        if (resultset.next()) {
            int id = resultset.getInt("id");
            String username = user.getUsername();
            String email = resultset.getString("email");
            int id_role = resultset.getInt("id_role");
            String role;

            if (id_role == 1) {
                role = "Admin";
            } else {
                role = "General";
            }
            loginUser = new User(id, username, email, id_role, role);
        }
        statement.close();
        resultset.close();
        disconnect();

        return loginUser;
    }

    public List<User> listUsers() throws SQLException {
        String role = "";
        List<User> listUser = new ArrayList();
        String SQL = "SELECT * FROM tbusers";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultset = statement.executeQuery(SQL);

        while (resultset.next()) {
            int id = resultset.getInt("id");
            String username = resultset.getString("username");
            String email = resultset.getString("email");
            int id_role = resultset.getInt("id_role");

            if (id_role == 1) {
                role = "Admin";
            } else {
                role = "General";
            }

            User user = new User(id, username, email, id_role, role);
            listUser.add(user);
        }

        return listUser;
    }

    public List<User> listUsers(String Searched) throws SQLException {
        String role = "";
        List<User> listUser = new ArrayList();
        String SQL = "SELECT * FROM tbusers WHERE username LIKE '%" + Searched + "%'";
        connect();

        Statement statement = jdbcConnection.createStatement();

        ResultSet resultset = statement.executeQuery(SQL);

        while (resultset.next()) {
            int id = resultset.getInt("id");
            String username = resultset.getString("username");
            String email = resultset.getString("email");
            int id_role = resultset.getInt("id_role");

            if (id_role == 1) {
                role = "Admin";
            } else {
                role = "General";
            }

            User user = new User(id, username, email, id_role, role);
            listUser.add(user);
        }

        return listUser;
    }

    public boolean insertUser(User user) throws SQLException, NoSuchAlgorithmException {
        String sql = "INSERT INTO tbusers(username,email,password,id_role) VALUES(?,?,?,?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getEmail());
        statement.setString(3, EncPassword(user.getPassword()));
        statement.setInt(4, user.getId_role());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();

        return rowInserted;
    }

    public User getUser(int id) throws SQLException {

        User user = null;
        String sql = "SELECT username, email, id_role FROM tbusers WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultset = statement.executeQuery();

        if (resultset.next()) {

            String username = resultset.getString("username");
            String email = resultset.getString("email");
            int id_role = resultset.getInt("id_role");

            user = new User(id, username, email, id_role);
        }

        resultset.close();
        statement.close();
        disconnect();

        return user;
    }

    public boolean updateUser(User user) throws SQLException {

        String sql = "UPDATE tbusers SET username = ?, email = ?, id_role = ? WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getEmail());
        statement.setInt(3, user.getId_role());
        statement.setInt(4, user.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();

        return rowUpdated;

    }

    public boolean deleteUser(User user) throws SQLException {

        String sql = "DELETE FROM tbusers WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, user.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();

        return rowDeleted;

    }

    public boolean changePassword(int id, String newPassword, String confirmPassword) throws SQLException, NoSuchAlgorithmException {

        if (!newPassword.equals(confirmPassword)) {

            return false;

        } else {

            String sql = "UPDATE tbusers SET password = ? WHERE id = ?";
            connect();

            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, EncPassword(newPassword));
            statement.setInt(2, id);

            boolean rowUpdated = statement.executeUpdate() > 0;
            statement.close();
            disconnect();

            return rowUpdated;

        }

    }

    public String EncPassword(String Password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Password.getBytes());
        byte[] bytes = md.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        String hashedPassword = sb.toString();
        return hashedPassword;
    }

}
