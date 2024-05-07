<%-- 
    Document   : form
    Created on : Mar 4, 2024, 8:59:30 PM
    Author     : duvan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <title>User form</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg bg-primary">

            <div class="container-fluid">
                <a class="navbar-brand" href="#">Navbar</a>
                <!-- SIGN OUT BUTTON -->
                <a href="logOut">
                    <button type="button" class="btn btn-danger">Sign out</button>
                </a>
            </div>
        </nav>

        <c:if test="${user == null}">
        <center><h1>New User</h1></center>
        </c:if>
        <c:if test="${user != null}">
        <center><h1>Edit User</h1></center>
        </c:if>


    <div class="container">
        <c:if test="${user == null}">
        <form action="newUser" method="POST">
        </c:if>
           
        <c:if test="${user != null}">
        <form action="updateUser" method="POST">
        </c:if>
            
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" name="username" id="username" placeholder="Enter Username" value="${user.username}">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" placeholder="Email" name="email" value="${user.email}">
            </div>
            <c:if test="${user == null}">
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" placeholder="Password" name="password">
            </div>
            </c:if>
            <br>
            <select class="form-select" aria-label="Default select example" name="role" value="${user.id_role}">
                <option selected>Select Role</option>
                <option value="1">Admin</option>
                <option value="2">General</option>
            </select>
            <br>
            <c:if test="${user != null}">
            <input type="hidden" name="id" value="${user.id}">
            </c:if>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>        
</body>
</html>
