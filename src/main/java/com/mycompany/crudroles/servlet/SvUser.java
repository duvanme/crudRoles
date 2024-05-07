/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.crudroles.servlet;

import com.mycompany.crudroles.dao.UserDAO;
import com.mycompany.crudroles.model.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duvan
 */
@WebServlet("/")
public class SvUser extends HttpServlet {

    UserDAO userDAO;

    public void init() {
        String jdbcURL = "jdbc:mysql://localhost:3306/dbusers";
        String jdbcUser = "root";
        String jdbcPassword = "1234";

        userDAO = new UserDAO(jdbcURL, jdbcUser, jdbcPassword);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/newUserForm":
                    showNewForm(request, response);
                    break;
                case "/showEditForm":
                    showEditForm(request, response);
                    break;
                case "/newUser":
                    InsertUser(request, response);
                    break;
                case "/updateUser":
                    updateUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/list":
                    ListUsers(request, response);
                    break;
                case "/logOut":
                    logOut(request, response);
                    break;
                case "/changePass":
                    changePassword(request, response);
                    break;
                default:
                    validatUserLogin(request, response);
            }
        } catch (SQLException e) {

            System.err.println("Error validating user login: " + e.getMessage());

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SvUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

    protected void validatUserLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NoSuchAlgorithmException {
        //Varaibles
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User loggedUser = new User(username, password);

        try {
            if (userDAO.validateLogin(loggedUser)) {

                User newUser = userDAO.getUserLogin(loggedUser);
                RequestDispatcher dispatcher = request.getRequestDispatcher("mainView.jsp");
                HttpSession session = request.getSession();
                session.setAttribute("loggedUser", newUser);

                request.setAttribute("loggedUser", newUser);
                ListUsers(request, response);
                dispatcher.forward(request, response);

            } else {

                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {

            System.err.println("Error validating user login: " + e.getMessage());

        }

    }

    protected void ListUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String Searched = request.getParameter("searchedUser");
        if (Searched == null) {
            List<User> listUser = userDAO.listUsers();
            RequestDispatcher dispatcher = request.getRequestDispatcher("mainView.jsp");
            request.setAttribute("listUser", listUser);
            dispatcher.forward(request, response);
        } else {
            
            List<User> listUser = userDAO.listUsers(Searched);
            RequestDispatcher dispatcher = request.getRequestDispatcher("mainView.jsp");
            request.setAttribute("listUser", listUser);
            dispatcher.forward(request, response);
            
        
        }

    }

    protected void InsertUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NoSuchAlgorithmException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("ROLE: " + request.getParameter("role"));
        int id_role = Integer.parseInt(request.getParameter("role"));
        User user = new User(username, email, password, id_role);
        userDAO.insertUser(user);
        response.sendRedirect("list");

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        User editUser = userDAO.getUser(id);

        RequestDispatcher dispatcher = request.getRequestDispatcher("form.jsp");
        request.setAttribute("user", editUser);
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        int id_role = Integer.parseInt(request.getParameter("role"));
        
        User user = new User(id, username, email, id_role);
        userDAO.updateUser(user);
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = new User(id);
        userDAO.deleteUser(user);
        response.sendRedirect("list");
    }

    private void logOut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            request.removeAttribute("loggedUser");
            response.sendRedirect("index.jsp");
        } else {

            response.sendRedirect("index.jsp");
        }
    }
    
    
     private void changePassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NoSuchAlgorithmException {
         
        int id = Integer.parseInt(request.getParameter("id"));
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        
        boolean change = userDAO.changePassword(id, newPassword, confirmPassword);
        
         if (change == true) {
             response.sendRedirect("list");
         } else {
             
             
             System.out.println("change: "+ change);
             
             RequestDispatcher dispatcher = request.getRequestDispatcher("changePass.jsp");
             request.setAttribute("change", change);
             dispatcher.forward(request, response);
             
         }
        
        
    }
    
}

   